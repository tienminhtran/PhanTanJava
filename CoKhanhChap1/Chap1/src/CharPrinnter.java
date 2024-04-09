/*
 * @(#)CharPrinnter.java 1.0  31/12/2023
 * Copy right (c) 2023 IUH. All rights reserved
 */

/*
 *@description:
 *@Athour:   TranMinhTien
 *@MSSV:     21010611
 *@DATE:     31/12/2023
 *@Version:  1.0
 */


public class CharPrinnter implements Runnable{
    private char ch;
    private int num;



    public CharPrinnter(char ch, int num) {
        this.ch = ch;
        this.num = num;
    }

    @Override
    public void run(){
        for (int i = 0 ; i < num; i++) {
            System.out.print(ch +" ");
        }
        System.out.printf("%s print %s\n",Thread.currentThread(),ch);

    }
}
