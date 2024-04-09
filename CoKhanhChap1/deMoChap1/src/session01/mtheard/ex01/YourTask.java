/*
 * @(#)YourTask.java 1.0  8/1/2024
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

public class YourTask implements Runnable{
    private String taskName;
    private int counter;

    public YourTask(String taskName, int counter) {
        this.taskName = taskName;
        this.counter = counter;
    }
    public void run(){
        for (int i = 0; i < counter; i++) {
            System.out.println(taskName + "#" + i);
        }
    }
}
