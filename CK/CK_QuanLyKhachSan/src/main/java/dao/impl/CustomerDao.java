package dao.impl;

import dao.CustomerDaoImpl;
import entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class CustomerDao implements CustomerDaoImpl {
    final EntityManager em;

    final EntityTransaction et;

    public CustomerDao() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ck_quanlykhachsan");
        this.em = emf.createEntityManager();
        this.et = em.getTransaction();
    }


    @Override
    public List<Customer> listCustoersByCheckMonthYear(String type, int month, int year) {
        String sql = "select c.* from bookings b\n" +
                "join customers c\n" +
                "on b.customer_id = c.customer_id\n" +
                "join bookings_rooms br\n" +
                "on br.booking_id = b.booking_id\n" +
                "join rooms r\n" +
                "on r.room_id = br.room_id\n" +
                "where r.type =? and  month(b.start_date) = ? and year(b.start_date) = ? ";

        try{
            et.begin();
            List<Customer> list = em.createNativeQuery(sql, Customer.class)
                    .setParameter(1, type)
                    .setParameter(2, month)
                    .setParameter(3, year)
                    .getResultList();
            et.commit();
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
