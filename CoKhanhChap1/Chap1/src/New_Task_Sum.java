/*
 * @(#)New_Task_Sum.java 1.0  31/12/2023
 * Copy right (c) 2023 IUH. All rights reserved
 */

/*
 *@description:
 *@Athour:   TranMinhTien
 *@MSSV:     21010611
 *@DATE:     31/12/2023
 *@Version:  1.0
 */


public class New_Task_Sum implements Runnable {


    @Override
    public void run()  {
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            sum = sum + i;

        }
        System.out.println("\nClass  New_Task_Sum is: "+sum);
    }
}
