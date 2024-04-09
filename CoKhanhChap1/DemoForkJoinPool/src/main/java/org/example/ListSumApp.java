/*
 * @(#)ListSumApp.java 1.0  18/2/2024
 * Copy right (c) 2024 IUH. All rights reserved
 */

/*
 *@description:
 *@Athour:   TranMinhTien
 *@MSSV:     21010611
 *@DATE:     18/2/2024
 *@Version:  1.0
 */


package org.example;

import jdk.jfr.Threshold;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class ListSumApp {
    private static int sumParallel(List<Integer> numbers) {
        return numbers.parallelStream().mapToInt(Integer::intValue).sum();
    }
    public static void main(String[] args) {

        List<Integer> numbers = IntStream
                .range(0,100)
                .boxed()
                .collect(Collectors.toList());
        ListSumTask task = new ListSumTask(numbers);
        ForkJoinPool pool = ForkJoinPool.commonPool();
        pool.invoke(task);
        pool.shutdown();
//        int sum = sumParallel(numbers);
//        System.out.println("Result: " + sum);
        System.out.println("Result: " + task.getResult());
//        List<Integer> numbers = IntStream.range(0, 10000000).boxed().collect(Collectors.toList());
//        ListSumTask task = new ListSumTask(numbers);
//        ForkJoinPool pool = ForkJoinPool.commonPool();
//        pool.invoke(task);
//        pool.shutdown();
//        System.out.println("Result: " + task.getResult().get());
    }

    private static class ListSumTask extends RecursiveAction {
        private List<Integer> mumbers;
        private AtomicInteger result = new AtomicInteger(0);
        static final int THRESHOLD = 10;
        public ListSumTask(List<Integer> mumbers) {
            this.mumbers = mumbers;
        }

        public AtomicInteger getResult() {
            return result;
        }

        public void setResult(AtomicInteger result) {
            this.result = result;
        }

        public List<Integer> getMumbers() {
            return mumbers;
        }

        @Override
        protected void compute() {
            int len = mumbers.size();
            if (len <= THRESHOLD) {
                // ban đầu là Integer sau đó chuyê về int để tính toán
                // Autoboxing và unboxing
                // Integer -> int là unboxing
                // int -> Integer là Autoboxing
                int temp = mumbers.stream()
                        .mapToInt(x -> x)
                        .sum();
                    result.set(temp);
            }else {
                int mid = len / 2;
//                dividedTasks.add(new ParallelSum(start, (start + end) / 2));
//                dividedTasks.add(new ParallelSum((start + end) / 2 + 1, end));
                ListSumTask left = new ListSumTask(mumbers.subList(0, mid));
                //System.out.println(left.getMumbers());

                ListSumTask right = new ListSumTask(mumbers.subList(mid, len));
               // System.out.println(right.getMumbers());

                left.fork();
                right.fork();
                left.join();
                right.join();

                AtomicInteger rs1 = left.getResult();
                AtomicInteger rs2 = right.getResult();
                result.set(rs1.get() + rs2.get());

            }
        }
    }
}
