/*
 * @(#)main.java 1.0  23/2/2024
 * Copy right (c) 2024 IUH. All rights reserved
 */

/*
 *@description:
 *@Athour:   TranMinhTien
 *@MSSV:     21010611
 *@DATE:     23/2/2024
 *@Version:  1.0
 */


import iuh.se.stream.JsonHardler;
import iuh.se.Restaurant;

import java.util.List;

public class main {
    public static void main(String[] args) {
//        Restaurant p = JsonHardler.readFile("FILE_JSON/data/retaurant.json");
//            System.out.println(p);

        List<Restaurant> list = JsonHardler.readFileArr("FILE_JSON/data/res.json");
        for (Restaurant restaurant : list) {
            System.out.println(restaurant);
        }

    }
}
