package com.marian.dao;

import com.marian.entity.Order;
import com.marian.entity.UserEntity;

import java.util.Date;
import java.util.List;

public interface OrderDao {
    List<Order> getAllOrdered(Date date1);
    void save(Order order);
    List<Order> getAllOrderByUser(UserEntity userEntity);
    void delete(Order order);
    Order getById(int id);
}
