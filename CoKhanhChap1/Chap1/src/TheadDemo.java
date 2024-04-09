/*
 * @(#)TheadDemo.java 1.0  31/12/2023
 * Copy right (c) 2023 IUH. All rights reserved
 */

/*
 *@description:
 *@Athour:   TranMinhTien
 *@MSSV:     21010611
 *@DATE:     31/12/2023
 *@Version:  1.0
 */


public class TheadDemo {
    public static void main(String[] args) {

        long startTime = System.nanoTime();

        Runnable task1 = new CharPrinnter('A',20);
        Runnable task2 = new CharPrinnter('B',20);

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        //Thread thread2 = new Thread(task2, "Ten 2);


        thread1.setName("thread 1");
        //thread2.setName("thread 2");
        // uu tien thread 1
        thread1.setPriority(10);

        thread1.start();
        //thread2.run();
        //thread2.start();

        while(thread1.isAlive()) {

        }


        // tinh time luc dau - luc  sau

        long endTime = System.nanoTime();
        long tong = endTime - startTime;
        System.out.println("tong"+tong);
    }
}
