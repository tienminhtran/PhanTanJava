/*
 * @ (#) CounterDemo.java       09/01/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package iuh.fit;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;

/*
 * @description
 * @author: Khanh Nguyen Thi Hoang
 * @date:   09/01/2024
 */
public class CounterDemo2 {

    private static final int NUM_OF_THREADS = 10;
    private static Counter2 counter = new Counter2(); // Sharing object, count = 0

    public static void main(String[] args) {
        Runnable task = () -> {
            counter.increment();
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

class Counter2 {
    private int count = 0;
    private final Lock lock = new ReentrantLock(true); // Fair lock (true)
//    private final Lock lock = new ReentrantLock();

    public void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public void decrement() {
        lock.lock();
        try {
            count--;
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        return count;
    }
}
