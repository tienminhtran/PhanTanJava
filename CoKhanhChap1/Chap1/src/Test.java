/*
 * @(#)Test.java 1.0  31/12/2023
 * Copy right (c) 2023 IUH. All rights reserved
 */

/*
 *@description:
 *@Athour:   TranMinhTien
 *@MSSV:     21010611
 *@DATE:     31/12/2023
 *@Version:  1.0
 */


public class Test {
    public static void main(String[] args) {

        long startTime = System.nanoTime();

        long n = 30000000000l;
        //'l' cuối cùng ==> KHÔNG CÓ l là kiểu int  (INT 4 BYTE / LONG 8 BYTE => CHUYỂN NGẦM
        // long n = 0 => chuyển ngầm 0'l'  => chuyển 0 kiểu int => sang 0'l'

        long toal = 0l;
        for (long i = 0; i < n; i++) {

            toal = toal + i;
        }
        System.out.println("tong:  " + toal);
        // sẽ bị treo : cách ỨNG BIẾN: TÍNH TỔNG [A, B] TÁCH RA NHIỀU KHOẢNG NHỎ + SAU ĐÓ CỘNG LẠI

        long endTime = System.nanoTime();
        long tong = endTime - startTime;
        System.out.println("tong"+tong);
    }

}
