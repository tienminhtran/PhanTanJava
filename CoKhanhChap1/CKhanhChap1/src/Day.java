/*
 * @ (#) Day.java       02/01/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

/*
 * @description
 * @author: Khanh Nguyen
 * @date:   02/01/2024
 */
public enum Day {
    SUNDAY("Sunday"), MONDAY("Monday"), TUESDAY("Tuesday") ;
    private String name;

    private Day(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
