package com.marian.service;

import com.marian.domain.DateSearch;
import com.marian.entity.Hotel;
import com.marian.entity.Room;

import java.util.List;

public interface RoomService {
    void save(Room room);
    List<Room> getRoomByHotel(Hotel hotel);
    List<Room> getFreeRoom(DateSearch dateSearch,int hotelId);
    Room getbyId(int id);
    void update(Room room);
}
