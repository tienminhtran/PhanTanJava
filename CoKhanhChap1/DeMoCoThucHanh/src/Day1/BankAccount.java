/*
 * @ (#) BankAccount.java       02/01/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package Day1;

/*
 * @description
 * @author: Khanh Nguyen
 * @date:   02/01/2024
 */
public class BankAccount {
    private String name;
    private int balance = 0;

    public BankAccount(String name) {
        this.name = name;
    }

    public BankAccount(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    /**
     * Deposit money to account
     *
     * @param amount
     */
    public synchronized void deposit(int amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Amount must be positive");
        else {
            balance += amount;
            notifyAll(); // notify all threads waiting for deposit
        }
    }

    public synchronized int withdraw(int amount) throws InterruptedException {
        if (amount <= 0)
            throw new IllegalArgumentException("Amount must be positive");
        else
            while (amount > balance) {
                System.out.println("Not enough money, waiting for deposit...");
//                wait(); // wait for deposit
                wait(2000); // wait for deposit
            }

        balance -= amount;
        return amount;
    }

    public int getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return String.format("Account[%s, %d]", name, balance);
    }
}
