/*
 * @(#)NumberRangeCallableDemo.java 1.0  31/12/2023
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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.stream.IntStream;

public class NumberRangeCallableDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        Callable<Long> task1 = new NumRangeCallable(0, 10000000000l);
//        Callable<Long> task2 = new NumRangeCallable(10000000000l, 20000000000l);
//        Callable<Long> task3 = new NumRangeCallable(20000000000l, 30000000000l);
//
//        FutureTask<Long> f1 = new FutureTask<>(task1);
//        FutureTask<Long> f2 = new FutureTask<>(task2);
//        FutureTask<Long> f3 = new FutureTask<>(task3);
//
//        Thread thread1 = new Thread(f1);
//        Thread thread2 = new Thread(f2);
//        Thread thread3 = new Thread(f3);
//
//
//        thread1.start();
//        thread2.start();
//        thread3.start();
//        long s = f1.get() + f2.get() + f3.get();
//
//        System.out.println("kq cuoi: "+ s);


        Callable<Integer> task = () -> {
            return IntStream.rangeClosed(1,100).sum();

        };
        FutureTask<Integer> f4 = new FutureTask<>(task);
        Thread t4 = new Thread(f4);
        System.out.println("AI THUC HIEN"+ t4.currentThread().getName() + "CON CHAY KHONG " + t4.isAlive());
        t4.start();
        System.out.println("AI THUC HIEN"+ t4.currentThread().getName() + "CON CHAY KHONG " + t4.isAlive());

        // kiem tra xem task thuc hien xong chua
//        while (t4.isAlive()==true){
//
//        }        System.out.println("After:  da thuc hien xong chua    "+t4.isAlive());

        t4.join();
        System.out.println("After:  da thuc hien xong chua (JOIN)    "+ "AI THUC HIEN: " +
                "" +t4.currentThread().getName() +t4.isAlive());

        try {
            int kq = f4.get();
            System.out.println("kq : "+ kq);

        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }


    }
}
