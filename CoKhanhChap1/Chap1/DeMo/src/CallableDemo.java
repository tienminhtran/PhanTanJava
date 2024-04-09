/*
 * @ (#) CallableDemo.java       02/01/2024
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
public class CallableDemo {

    public static void main(String[] args) throws InterruptedException {

        Callable<Integer> task = () -> {
            Thread.sleep(5000);
            return IntStream.rangeClosed(1, 1_000).sum();
        };

        FutureTask<Integer> futureTask = new FutureTask<>(task);

        Thread thread = new Thread(futureTask, "Thread 1");

        System.out.println("Before start thread: " + thread.isAlive());

        thread.start();

        System.out.println("After start thread: " + thread.isAlive());

//        while (thread.isAlive()) {
//        }

        thread.join();

        System.out.println(Thread.currentThread().getName() + " finished! " + thread.isAlive());


        //        try{
//            int result = futureTask.get(1000, TimeUnit.MILLISECONDS);
//            System.out.println("Result = " + result);
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(Thread.currentThread().getName() + " finished");
    }

}
