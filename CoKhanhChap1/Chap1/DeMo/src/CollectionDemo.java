/*
 * @ (#) CollectionDemo.java       02/01/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package iuh.fit;

import java.util.*;

/*
 * @description
 * @author: Khanh Nguyen
 * @date:   02/01/2024
 */
public class CollectionDemo {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(); // not thread-safe
        List<Integer> list2 = new Vector<>(); // thread-safe
    }
}
