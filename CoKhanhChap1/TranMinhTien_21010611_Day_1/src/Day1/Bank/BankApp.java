/*
 * @(#)BankApp.java 1.0  9/1/2024
 * Copy right (c) 2024 IUH. All rights reserved
 */

/*
 *@description:
 *@Athour:   TranMinhTien
 *@MSSV:     21010611
 *@DATE:     9/1/2024
 *@Version:  1.0
 */


package Day1.Bank;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BankApp {
    private static BankAccount account = new BankAccount(0); // 0

    public static void main(String[] args) throws InterruptedException {

        Runnable task = () -> {
            while (true) {
                account.deposit(1);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable task2 = () -> {
            while (true) {
                account.withdraw(1);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        ExecutorService executor = Executors.newFixedThreadPool(10);
        executor.submit(task);
        executor.submit(task2);
        executor.shutdown();


        System.out.println("So Tien trong tai khoan: " + account.getBankAccount()); // 1000
    }

}
