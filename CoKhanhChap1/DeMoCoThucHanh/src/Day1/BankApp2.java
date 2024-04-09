/*
 * @ (#) BankName.java       02/01/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package Day1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/*
 * @description
 * @author: Khanh Nguyen
 * @date:   02/01/2024
 */
public class BankApp2 {

    private static BankAccount account = new BankAccount("Khanh", 10_000); // 0

    public static void main(String[] args) {

        Callable<Integer> task = () -> {
            return account.withdraw(100);
        };

        List<Future<Integer>> fus = new ArrayList<>();

        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            Future<Integer> fu = executor.submit(task);
            fus.add(fu);
        }

        fus.add(executor.submit(task));

        int money = fus.stream()
                .mapToInt(fu -> {
                    try {
                        return fu.get(6000, TimeUnit.MILLISECONDS);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.exit(0);
                    }
                    return 0;
                })
                .sum() ;

        System.out.println("Balance: " + account.getBalance()); // Expected: 0
        System.out.println("Money: " + money); // Money: 10_000

        executor.shutdown();



    }
}
