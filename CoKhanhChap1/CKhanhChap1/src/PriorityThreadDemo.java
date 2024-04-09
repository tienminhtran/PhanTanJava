/*
 * @ (#) PriorityThreadDemo.java       02/01/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

/*
 * @description
 * @author: Khanh Nguyen
 * @date:   02/01/2024
 */
public class PriorityThreadDemo {

    public static void main(String[] args) {

        System.out.println("Main thread priority : " + Thread.currentThread().getPriority());

        Runnable task = () -> {
            System.out.println("Hello from thread 1");
        };

        Thread.currentThread().setPriority(10); // Main thread priority : 7
        System.out.println("Main thread priority : " + Thread.currentThread().getPriority());

        Thread thread1 = new Thread(task, "Thread 1");

        System.out.println("Thread 1 priority : " + thread1.getPriority()); // Thread 1 priority : 7

        thread1.start();
    }

}
