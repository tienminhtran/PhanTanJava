/*
 * @ (#) ThreadDemo1.java       02/01/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

/*
 * @description
 * @author: Khanh Nguyen
 * @date:   02/01/2024
 */
public class ThreadDemo1 {

    public static void main(String[] args) {
        Runnable task1 = () -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
        };

        Thread thread1 = new Thread(task1, "Thread 1");
        Thread thread2 = new Thread(task1, "Thread 2");

        thread1.start();
        thread2.start();
    }
}
