/*
 * @(#)Demo.java 1.0  2/1/2024
 * Copy right (c) 2024 IUH. All rights reserved
 */

/*
 *@description:
 *@Athour:   TranMinhTien
 *@MSSV:     21010611
 *@DATE:     2/1/2024
 *@Version:  1.0
 */


public class Demo {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(5_000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Hello from t1");
        });

        t1.start(); // Bắt đầu luồng t1
        System.out.println("Hello from main");

        t1.join(); // Chờ cho luồng t1 hoàn thành trước khi tiếp tục
        System.out.println("State of t1: " + t1.getState()); // In ra trạng thái của luồng t1
        System.out.println("Bye from main");
    }
}
