/*
 * @(#)ListSumAppArr.java 1.0  19/2/2024
 * Copy right (c) 2024 IUH. All rights reserved
 */

/*
 *@description:
 *@Athour:   TranMinhTien
 *@MSSV:     21010611
 *@DATE:     19/2/2024
 *@Version:  1.0 Performance
 */


package org.example;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;
public class ListSumAppArr extends RecursiveTask<Long> {

    public static void main(String[] args) {
//        long startTime = System.currentTimeMillis();
         final long sum = new ListSumAppArr(0, 2000000000 ).compute();
//        long endTime = System.currentTimeMillis();
//        final long elapsedTime = endTime - startTime;
        System.out.println("KQ: "+sum);
        //KQ: 2290382465257531519

//        System.out.println(sum + " was computed in " + elapsedTime + " milliseconds.");
    }
    private static final int THRESHOLD = 10 ;
    private final int start;
    private final int end;

    public ListSumAppArr(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - start > THRESHOLD) {
            return ForkJoinTask.invokeAll(createSubtasks())
                    .stream()
                    .mapToLong(ForkJoinTask::join)
                    .sum();
        }
        return LongStream.rangeClosed(start, end).sum();
    }

    private Collection<RecursiveTask<Long>> createSubtasks() {
        final List<RecursiveTask<Long>> dividedTasks = new ArrayList<>();
        int mid = (start + end) / 2;
        dividedTasks.add(new ListSumAppArr(start, mid));
        dividedTasks.add(new ListSumAppArr(mid + 1, end));
        return dividedTasks;
    }
}
