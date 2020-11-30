package com.marian.dao;

import com.marian.entity.Hotel;
import com.marian.entity.Room;
import com.marian.entity.TypeRoom;

import java.util.List;

public interface RoomDao {
    void save(Room room);
    List<Room> getRoomByHotel(Hotel hotel);

}
