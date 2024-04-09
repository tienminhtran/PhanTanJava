/*
 * @ (#) SharedObjectDemo.java       02/01/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

import java.util.*;

/*
 * @description
 * @author: Khanh Nguyen
 * @date:   02/01/2024
 */
public class SharedObjectDemo {

//    private static List<Integer> list = new ArrayList<>();
    private static List<Integer> list = new Vector<>();

    public static void main(String[] args) throws InterruptedException {
        Random rd = new Random();
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
//                synchronized (list) {
                    list.add(rd.nextInt());
//                }
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(Thread.currentThread().getName() + " " + list.size()); // 2000
    }

}
