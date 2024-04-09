/*
 * @(#)NumberTask.java 1.0  9/1/2024
 * Copy right (c) 2024 IUH. All rights reserved
 */

/*
 *@description:
 *@Athour:   TranMinhTien
 *@MSSV:     21010611
 *@DATE:     9/1/2024
 *@Version:  1.0
 */



public class BankAccount {
    private int sodu = 0;

    public BankAccount(int sodu){
        this.sodu = sodu;
    }
    public int getBankAccount(){
        return sodu;
    }
    public synchronized void deposit(int amount) {
        if(amount <= 0)
            throw new IllegalArgumentException("Tiền lớn hơn 0");
        sodu =  sodu + amount;
        System.out.println("Đã gởi $" + amount + ". Trong tài khoản còn: $" + sodu);
    }
    public synchronized void withdraw(int amount) {
        while (sodu < amount) {
            try {
                System.out.println("\n" + "Không đủ tiền");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        sodu =  sodu - amount;
        System.out.println("Đã rút $" + amount + ". Trong tài khoản còn: $" + sodu);
    }

    @Override
    public String toString() {
        return toString().formatted("Số dư: %d", sodu);
    }
}
