/*
 * @(#)DoUuTien_Priority.java 1.0  2/1/2024
 * Copy right (c) 2024 IUH. All rights reserved
 */

/*
 *@description:
 *@Athour:   TranMinhTien
 *@MSSV:     21010611
 *@DATE:     2/1/2024
 *@Version:  1.0
 */


public class DoUuTien_Priority {
    public static void main(String[] args) {
        System.out.println("Do uu tien main: " + Thread.currentThread().getPriority());
        Runnable r1 = () -> {
            System.out.println("HELLO ");

        };

        Thread t1 = new Thread(r1,"T1");
        t1.setPriority(7);
        Thread.currentThread().setPriority(8);
        System.out.println("in: "+Thread.currentThread().getName()+"         "+Thread.currentThread().getPriority());
        System.out.println("in: "+t1.getName()+"         "+t1.getPriority());
        t1.start();

    }

}
