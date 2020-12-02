package com.marian.service.Impl;

import com.marian.dao.HotelDao;
import com.marian.dao.OrderDao;
import com.marian.dao.RoomDao;
import com.marian.entity.Country;
import com.marian.entity.Hotel;
import com.marian.entity.Order;
import com.marian.entity.Room;
import com.marian.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {


    private HotelDao hotelDao;
    private RoomDao roomDao;
    private OrderDao orderDao;

    @Autowired
    public HotelServiceImpl(HotelDao hotelDao, RoomDao roomDao,OrderDao orderDao) {
        this.hotelDao = hotelDao;
        this.roomDao = roomDao;
        this.orderDao = orderDao;
    }



    @Override
    public void save(Hotel hotel) {
        hotelDao.save(hotel);
    }

    @Override
    public List<Hotel> getByCountry(Country country) {
        return hotelDao.getbuCountry(country);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelDao.getAll();
    }

    @Override
    public Hotel getById(int id) {
        return hotelDao.getById(id);
    }

    @Override
    public void update(Hotel hotel, int id) {
        Hotel temp = hotelDao.getById(id);
        hotel.setId(id);
        hotel.setCountry(temp.getCountry());
        hotelDao.update(hotel);
    }

    @Override
    public boolean delete(Hotel hotel) {
        List<Room> roomList = roomDao.getRoomByHotel(hotel);
        for (Room room:roomList){
            List<Order> orders = orderDao.getAllOrderByRoom(room);
            for (Order order:orders){
                orderDao.delete(order);
            }
            roomDao.delete(room);
        }
        hotelDao.delete(hotel);
        return false;
    }
//    private boolean checkOrderInRoom(Hotel hotel){
//       List<Room> roomList =  roomDao.getRoomByHotel(hotel);
//
//
//
//    }
}
