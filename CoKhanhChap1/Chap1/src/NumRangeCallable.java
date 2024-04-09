/*
 * @(#)NumRangeCallable.java 1.0  31/12/2023
 * Copy right (c) 2023 IUH. All rights reserved
 */

/*
 *@description:
 *@Athour:   TranMinhTien
 *@MSSV:     21010611
 *@DATE:     31/12/2023
 *@Version:  1.0
 */


import java.util.concurrent.Callable;

public class NumRangeCallable implements Callable<Long> {
    private long a;
    private long b;

    public NumRangeCallable(long a, long b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public Long call() throws Exception {

        long s = 0l;
        for (long i = a; i < b; i++) {
            s = s + i;
        }

        return s;
    }
}
