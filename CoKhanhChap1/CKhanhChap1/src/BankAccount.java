/*
 * @ (#) BankAccount.java       02/01/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

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

    public synchronized void deposit(int amount) {
        if(amount <= 0)
            throw new IllegalArgumentException("Amount must be positive");
        balance += amount;
    }

    public int getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return String.format("Account[%s, %d]", name, balance);
    }


}
