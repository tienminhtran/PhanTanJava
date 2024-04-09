/*
 * @(#)Main_Run_Task.java 1.0  31/12/2023
 * Copy right (c) 2023 IUH. All rights reserved
 */

/*
 *@description:
 *@Athour:   TranMinhTien
 *@MSSV:     21010611
 *@DATE:     31/12/2023
 *@Version:  1.0
 */


import java.util.stream.IntStream;

public class Main_Run_Task {
    public static void main(String[] args) {
        Runnable task1 = new New_Task('A', 10);
        Runnable task2 = new New_Task('B', 5);
        Runnable task3 = new New_Task_Sum();

        Runnable task4 = new Runnable() {
            @Override
            public void run() {
                int sum = 0;
                for (int i = 0; i < 100; i++) {
                    sum = sum - i;

                }
                System.out.println("\nClass Main run task Bieu thuc lamdam:  " + sum);
            }
        };
// dung bieu thuc lamda

        Runnable task5 = () -> {
            int tong = IntStream.rangeClosed(1, 99).sum();
            System.out.println("\n Dung stream:  " + tong);
        };

        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);
        Thread t3 = new Thread(task3);
        Thread t4 = new Thread(task4);
        Thread t5 = new Thread(task5);


        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

    }
}
