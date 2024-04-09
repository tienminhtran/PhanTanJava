/*
 * @(#)TaskRun.java 1.0  8/1/2024
 * Copy right (c) 2024 IUH. All rights reserved
 */

/*
 *@description:
 *@Athour:   TranMinhTien
 *@MSSV:     21010611
 *@DATE:     8/1/2024
 *@Version:  1.0
 */


package session01.mtheard.ex02;

public class TaskRun {
    public static void main(String[] args) {
        AnotherTask task1 = new AnotherTask("Task 1", 5);
        AnotherTask task2 = new AnotherTask("Task 2", 10);
        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        task1.start();
        task2.start();
    }
}
