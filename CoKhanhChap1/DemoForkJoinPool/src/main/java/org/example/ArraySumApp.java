/*
 * @(#)ArraySumApp.java 1.0  19/2/2024
 * Copy right (c) 2024 IUH. All rights reserved
 */

/*
 *@description:
 *@Athour:   TranMinhTien
 *@MSSV:     21010611
 *@DATE:     19/2/2024
 *@Version:  1.0
 */


package org.example;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

public class ArraySumApp {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        int[] arr = IntStream.range(0, 1000000000).toArray();

        ArraySumTask task = new ArraySumTask(arr);
        ForkJoinPool pool = ForkJoinPool.commonPool();
        pool.invoke(task);
        pool.shutdown();
        System.out.println("Result: " + task.get());
        pool.close();
    }

    private static class ArraySumTask extends RecursiveTask<Integer> {

        private int[] arr;
        private static final int THRESHOLD = 10;

        public ArraySumTask(int[] arr) {
            this.arr = arr;
        }

        @Override
        protected Integer compute() {
            int len = arr.length;

            if (len > THRESHOLD)
                return Arrays.stream(arr).sum();
            int mid = len / 2;
            ArraySumTask leftTask = new ArraySumTask(Arrays.copyOfRange(arr, 0, mid));
            ArraySumTask rightTask = new ArraySumTask(Arrays.copyOfRange(arr, mid, len));

            leftTask.fork();
            rightTask.fork();

            return null;
        }
    }
}
