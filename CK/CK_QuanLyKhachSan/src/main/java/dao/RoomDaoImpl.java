package dao;

import entity.Room;

import java.util.List;
import java.util.Set;

public interface RoomDaoImpl {

    public List<Room> listRooms(String type, double priceFrom, double priceTo);


    public Set<String> listRoomTypes(String bedOption);
}
