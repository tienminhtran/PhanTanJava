/*
 * @(#)ComputationTask.java 1.0  9/1/2024
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

public class ComputationTask implements Callable<Long> {

    private String taskName;

    public ComputationTask(String taskName) {
        this.taskName = taskName;
    }


    @Override
    public Long call() throws Exception {
        Long result = 0L;
        for (int i = 0; i < 1000; i++) {
            result = result + i;
            System.out.println(this.taskName + " result = " + i);
            Thread.sleep(10);
        }
        return result;
    }
}
