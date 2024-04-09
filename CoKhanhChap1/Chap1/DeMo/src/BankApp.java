/*
 * @ (#) BankName.java       02/01/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package iuh.fit;

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

            account.deposit(1);
        };

        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            executor.submit(task);
        }

        executor.shutdown();

        while (!executor.isTerminated()) {
        }

        System.out.println("Balance: " + account.getBalance()); // 1000

    }
}
