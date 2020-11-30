package com.marian.service;

import com.marian.entity.Hotel;
import com.marian.entity.Room;

import java.util.List;

public interface RoomService {
    void save(Room room);
    List<Room> getRoomByHotel(Hotel hotel);
}
