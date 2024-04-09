/*
 * @ (#) StateThreadDemo.java       02/01/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package iuh.fit;

/*
 * @description
 * @author: Khanh Nguyen
 * @date:   02/01/2024
 */
public class StateThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {

            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " Hello");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        };

        Thread thread = new Thread(task, "Thread 1");

        System.out.println("Before start: " + thread.getState());

        thread.run();

        System.out.println("After start: " + thread.getState());

        thread.join();

        System.out.println("After join: " + thread.getState());

    }
}
