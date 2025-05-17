package test;

import dao.impl.RoomDao;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestDaoRoom {

    private RoomDao roomDao;

    @BeforeAll
    public void init(){
        roomDao = new RoomDao();
    }

    @Test
    public void testListRooms(){
        System.out.println(roomDao.listRooms("Single", 1500, 2200));
    }

    @Test
    public void testListRoomTypes(){
        System.out.println(roomDao.listRoomTypes("King Bed"));
    }

    @AfterAll
    public void destroy(){
        roomDao = null;
    }
}
