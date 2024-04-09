/*
 * @ (#) PrintCharTask.java       26/12/2023
 *
 * Copyright (c) 2023 IUH. All rights reserved.
 */

/*
 * @description
 * @author: Khanh Nguyen
 * @date:   26/12/2023
 */
public class PrintCharTask implements  Runnable{
    private char charToPrint;
    private int times;

    public PrintCharTask(char charToPrint, int times) {
        this.charToPrint = charToPrint;
        this.times = times;
    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++) {
            Thread.yield();
            System.out.println(charToPrint);
        }
    }
}
