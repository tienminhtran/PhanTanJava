package dao.impl;

import dao.RoomDaoImpl;
import entity.Room;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Set;

public class RoomDao implements RoomDaoImpl{

    final EntityManager em;

    final EntityTransaction et;

    public RoomDao() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ck_quanlykhachsan");
        this.em = emf.createEntityManager();
        this.et = em.getTransaction();
    }
    @Override
    public List<Room> listRooms(String type, double priceFrom, double priceTo) {
        String sql = "Select r from Room r where r.type = :type and r.price >= :priceFrom and r.price <= :priceTo";
        try{
            et.begin();
            List<Room> list = em.createQuery(sql, Room.class)
                    .setParameter("type", type)
                    .setParameter("priceFrom", priceFrom)
                    .setParameter("priceTo", priceTo)
                    .getResultList();
            et.commit();
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Set<String> listRoomTypes(String bedOption) {
        String sql = "select r.type from rooms r\n" +
                "where r.bed_option = ?";
        try{
            et.begin();
            List<String> list = em.createNativeQuery(sql)
                    .setParameter(1, bedOption)
                    .getResultList();
            Set<String> set = Set.copyOf(list);
            et.commit();
            return set;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
