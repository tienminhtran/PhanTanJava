package dao;

import entity.Customer;

import java.util.List;

public interface CustomerDaoImpl {
    public List<Customer> listCustoersByCheckMonthYear(String type, int month, int year);


}
