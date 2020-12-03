package com.marian.dao;

import com.marian.entity.Hotel;
import com.marian.entity.Room;
import com.marian.entity.TypeRoom;

import java.util.Date;
import java.util.List;

public interface RoomDao {
    void save(Room room);
    List<Room> getRoomByHotel(Hotel hotel);
    Room getById(int id);
    void update(Room room);
    void delete(Room room);


}
