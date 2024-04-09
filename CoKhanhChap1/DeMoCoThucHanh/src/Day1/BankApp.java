/*
 * @ (#) BankName.java       02/01/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package Day1;

import java.util.concurrent.*;

/*
 * @description
 * @author: Khanh Nguyen
 * @date:   02/01/2024
 */
public class BankApp {

    private static BankAccount account = new BankAccount("Khanh"); // 0

    public static void main(String[] args) {

        Runnable task = () -> {
            account.deposit(100);


        };


        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executor.submit(task);

        }
        executor.shutdown();
        while(!executor.isTerminated()) {
//            System.out.println("Waiting for all threads to finish...");
        }

        System.out.println("Balance: " + account.getBalance()); // 10_000



    }
}
