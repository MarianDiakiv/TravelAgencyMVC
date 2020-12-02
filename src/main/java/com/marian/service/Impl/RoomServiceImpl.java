package com.marian.service.Impl;

import com.marian.dao.HotelDao;
import com.marian.dao.OrderDao;
import com.marian.dao.RoomDao;
import com.marian.domain.DateSearch;
import com.marian.entity.Hotel;
import com.marian.entity.Order;
import com.marian.entity.Room;
import com.marian.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    private RoomDao roomDao;
    private OrderDao orderDao;
    private HotelDao hotelDao;

    @Autowired
    public RoomServiceImpl(RoomDao roomDao, OrderDao orderDao, HotelDao hotelDao) {
        this.roomDao = roomDao;
        this.orderDao = orderDao;
        this.hotelDao = hotelDao;
    }

    @Override
    public List<Room> getRoomByHotel(Hotel hotel) {
        return roomDao.getRoomByHotel(hotel);
    }

    @Override
    public void save(Room room) {
        roomDao.save(room);
    }

    @Override
    public Room getbyId(int id) {
        return roomDao.getById(id);
    }

    @Override
    public void update(Room room) {
        roomDao.update(room);
    }

    @Override
    public List<Room> getFreeRoom(DateSearch dateSearch, int hotelId) {
        List<Order> order1 = orderDao.getAllOrdered(dateSearch.getDate1());
        List<Order> order2 = orderDao.getAllOrdered(dateSearch.getDate2());
        List<Room> rooms = roomDao.getRoomByHotel(this.hotelDao.getById(hotelId));
        List<Room> roomList = new ArrayList<>();
        for (Room r :rooms){
            boolean notExist = true;
            for (Order o :order1){
                //коли є співпадіння із замовленням та ід готелю відповідне
//                if(r.getId() == o.getRoom().getId() && o.getRoom().getHotel().getId() == hotelId) {
//                    notExist =false;
//                }
                notExist = checkIfRoonIsinHotelAndOrder(r,o,hotelId);
            }
            for (Order o :order2){
                //коли є співпадіння із замовленням та ід готелю відповідне
//                if(r.getId() == o.getRoom().getId() && o.getRoom().getHotel().getId() == hotelId) {
//                    notExist =false;
//                }
                notExist = checkIfRoonIsinHotelAndOrder(r,o,hotelId);
            }
            if(notExist) {
                //true
                roomList.add(r);
            }
        }
        return roomList;
    }

    @Override
    public boolean deleteRoom(Room room) {
        Date date = new Date();
        boolean deleting = checkRoomBeforeDeleting(room, date);
        if (deleting){
            List<Order> orders = orderDao.getAllOrderByRoom(room);
            for (Order o:orders){
                orderDao.delete(o);
            }
            roomDao.delete(room);
            return true;
        }

        return false;

    }

    private boolean checkRoomBeforeDeleting(Room room , Date date){
         return orderDao.getOrderByRoomAndDate(room,date).size()==0;

    }

    private boolean checkIfRoonIsinHotelAndOrder(Room room, Order order, int hotelId){
        if(room.getId() == order.getRoom().getId() && order.getRoom().getHotel().getId() == hotelId) {
            return  false;
        }else {
            return true;
        }
    }

}
