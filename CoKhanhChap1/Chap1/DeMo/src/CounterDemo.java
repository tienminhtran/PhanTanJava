/*
 * @ (#) CounterDemo.java       09/01/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package iuh.fit;

import java.util.concurrent.*;

/*
 * @description
 * @author: Khanh Nguyen Thi Hoang
 * @date:   09/01/2024
 */
public class CounterDemo {

    private static final int NUM_OF_THREADS = 10;
    private static Counter counter = new Counter(); // Sharing object, count = 0

    public static void main(String[] args) {
        Runnable task = () -> {
            synchronized (counter) { // Synchronized block
                counter.increment();
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(NUM_OF_THREADS);

        for (int i = 0; i < 1000; i++) {
            executorService.execute(task);
        }

        for (int i = 0; i < 1000; i++) {
            executorService.execute(task);
        }

        executorService.shutdown();

        while (!executorService.isTerminated()) {
            // wait until all tasks are finished
        }

        System.out.println("Count = " + counter.getCount()); // Expected Count = 2000
    }

}

class Counter {
    private int count = 0;

//    public synchronized void increment() { // Synchronized method
    public void increment() {
        count++;
    }

    public synchronized void decrement() {
        count--;
    }

    public int getCount() {
        return count;
    }
}
