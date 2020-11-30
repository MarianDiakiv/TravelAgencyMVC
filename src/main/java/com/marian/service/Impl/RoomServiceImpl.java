package com.marian.service.Impl;

import com.marian.dao.RoomDao;
import com.marian.entity.Hotel;
import com.marian.entity.Room;
import com.marian.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    private RoomDao roomDao;

    @Autowired
    public RoomServiceImpl(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @Override
    public List<Room> getRoomByHotel(Hotel hotel) {
        return roomDao.getRoomByHotel(hotel);
    }

    @Override
    public void save(Room room) {
        roomDao.save(room);
    }
}
