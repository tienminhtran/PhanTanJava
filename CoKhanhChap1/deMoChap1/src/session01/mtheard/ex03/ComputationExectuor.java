/*
 * @(#)ComputationExectuor.java 1.0  9/1/2024
 * Copy right (c) 2024 IUH. All rights reserved
 */

/*
 *@description:
 *@Athour:   TranMinhTien
 *@MSSV:     21010611
 *@DATE:     9/1/2024
 *@Version:  1.0
 */


package session01.mtheard.ex03;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ComputationExectuor {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Long> call = new ComputationTask("Task 1");
        FutureTask<Long> ft1 = new FutureTask<>(call);
        new Thread(ft1).start();

        long result = ft1.get();
        System.out.println("Result = " + result);
    }
}
