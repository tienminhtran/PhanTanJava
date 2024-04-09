/*
 * @ (#) ParkingGarageApp.java       09/01/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package iuh.fit;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;

/*
 * @description
 * @author: Khanh Nguyen Thi Hoang
 * @date:   09/01/2024
 */
public class ParkingGarageApp2 {
    private static final int CAPACITY = 5;
    private static final int NUMBER_OF_THREADS = 4;

    private static ParkingGarage2 parkingGarage = new ParkingGarage2(CAPACITY);

    public static void main(String[] args) throws InterruptedException {

        Runnable taskEnter = () -> {
            try {
                parkingGarage.enter();
                System.out.println("A car entered, available spaces: " + parkingGarage.getAvailable());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Callable<Integer> taskLeave = () -> {
            parkingGarage.leave();
            System.out.println("A car left, available spaces: " + parkingGarage.getAvailable());
            return parkingGarage.getAvailable();
        };
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        
        // 5 cars enter
        for(int i = 0; i < 5; i++)
            executorService.execute(taskEnter);

        Thread.sleep(1000);

        executorService.submit(taskEnter);

        Future<Integer> fu = executorService.submit(taskLeave);

        executorService.submit(taskEnter);

        try {
            System.out.println("Available spaces: " + fu.get(6000, TimeUnit.MILLISECONDS));
        } catch (Exception e) {
//            e.printStackTrace();
            System.exit(0);
        }

        executorService.shutdown();
    }
}

class ParkingGarage2{
    private int capacity; // Maximum number of cars that can be parked
    private int available; // Number of available parking spaces

    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public ParkingGarage2(int capacity) {
        this.capacity = capacity;
        this.available = capacity; // Initially, all spaces are available
    }

    public void enter() throws InterruptedException {
        lock.lock();
        try {
            while(available == 0) {
                System.out.println("Parking is full, please wait");
//                condition.await(); // Wait until other cars leave
                condition.await(2000, TimeUnit.MILLISECONDS); // Wait until other cars leave
            }

            available--;
        } finally {
            lock.unlock();
        }
    }

    public void leave(){
        lock.lock();
        try {
            if(available == capacity)
                System.out.println("Parking is empty");
            else {
                available++;
//                condition.signal(); // Notify to all waiting threads
                condition.signalAll(); // Notify to all waiting threads
            }
        } finally {
            lock.unlock();
        }
    }

    public int getAvailable() {
        return available;
    }

    public int getCapacity() {
        return capacity;
    }
}