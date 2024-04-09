/*
 * @(#)TaskExecute.java 1.0  8/1/2024
 * Copy right (c) 2024 IUH. All rights reserved
 */

/*
 *@description:
 *@Athour:   TranMinhTien
 *@MSSV:     21010611
 *@DATE:     8/1/2024
 *@Version:  1.0
 */


package session01.mtheard.ex01;

public class TaskExecute {
    public static void main(String[] args) {
        YourTask task1 = new YourTask("Task 1", 5);
        YourTask task2 = new YourTask("Task 2", 10);
        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        thread1.start();
        thread2.start();
    }
}
