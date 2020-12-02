package com.marian.service.Impl;

import com.marian.dao.OrderDao;
import com.marian.dao.RoomDao;
import com.marian.dao.UserDao;
import com.marian.domain.DateSearch;
import com.marian.entity.Order;
import com.marian.entity.UserEntity;
import com.marian.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;
    private UserDao userDao;
    private RoomDao roomDao;

@Autowired
    public OrderServiceImpl(OrderDao orderDao, UserDao userDao, RoomDao roomDao) {
        this.orderDao = orderDao;
        this.userDao = userDao;
        this.roomDao = roomDao;
    }

    @Override
    public void saveOrder(Principal principal, DateSearch dateSearch, int roomId) {
        Order order = new Order();
        order.setUserEntity(userDao.getUserByEmail(principal.getName()));
        order.setDateOfSettlement(dateSearch.getDate1());
        order.setDepartureDate(dateSearch.getDate2());
        order.setRoom(roomDao.getById(roomId));
        orderDao.save(order);
    }

    @Override
    public List<Order> getAllOrderByUser(UserEntity user) {
       return orderDao.getAllOrderByUser(user);
    }

    @Override
    public void delete(int orderId) {
        orderDao.delete(orderDao.getById(orderId));
    }
}
