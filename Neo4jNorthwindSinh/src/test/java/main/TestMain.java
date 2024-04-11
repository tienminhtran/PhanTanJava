/**
 * @ (#) TestMain.java      3/5/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package main;

import dao.DaoCustomer;
import dao.DaoSupplier;
import entity.Supplier;
import util.AppUtil;

import java.util.Map;

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 3/5/2024
 */
public class TestMain {
    public static void main(String[] args) {
        DaoSupplier daoSupplier = new DaoSupplier();
        DaoCustomer dao = new DaoCustomer();

        Supplier s = daoSupplier.addSupplier(new Supplier("S1", "ABC", "John", "Manager", "123", "HCM"));
        System.out.println(s);

        //Get so luong khach hang cua cac thanh pho
        Map<String, Integer> params = dao.getNumberCustomerByCity();
        System.out.println("City         |        Sl cus");
        params.forEach((k, v) -> {
            System.out.println(k + ": " + v);
        });


    }
}
