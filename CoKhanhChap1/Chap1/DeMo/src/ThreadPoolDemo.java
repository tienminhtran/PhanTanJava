/*
 * @ (#) ThreadPoolDemo.java       02/01/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package iuh.fit;

import java.util.concurrent.*;
import java.util.stream.*;

/*
 * @description
 * @author: Khanh Nguyen
 * @date:   02/01/2024
 */
public class ThreadPoolDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Callable<Long> task1 = new SumRangeTask(0, 1000);
        Callable<Long> task2 = new SumRangeTask(1000, 2000);
        Callable<Long> task3 = new SumRangeTask(2000, 3000);
        Callable<Long> task4 = new SumRangeTask(3000, 4000);


        ExecutorService executor = Executors.newFixedThreadPool(2);

        Future<Long> fu1 = executor.submit(task1);
        Future<Long> fu2 = executor.submit(task2);
        Future<Long> fu3 = executor.submit(task3);
        Future<Long> fu4 = executor.submit(task4);

        executor.shutdown();

//        while(!executor.isTerminated()) {
//        }

        System.out.println("Total sum = " + (fu1.get() + fu2.get() + fu3.get() + fu4.get()));



    }
}

class SumRangeTask implements Callable<Long> {
    private int from;
    private int to;

    public SumRangeTask(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public Long call() throws Exception {
        return IntStream.range(from, to).mapToLong(i -> i).sum();
    }
}