/*
 * @(#)new_Task.java 1.0  31/12/2023
 * Copy right (c) 2023 IUH. All rights reserved
 */

/*
 *@description:
 *@Athour:   TranMinhTien
 *@MSSV:     21010611
 *@DATE:     31/12/2023
 *@Version:  1.0
 */


public class New_Task implements Runnable {
    //tao task
    private char chuoi;
    private int time;

    public New_Task(char chuoi, int time) {
        this.chuoi = chuoi;
        this.time = time;
    }

    @Override
    public void run() {
        for (int i = 0; i < time; i++) {
            System.out.print(chuoi+"          ");
        }
    }
}
