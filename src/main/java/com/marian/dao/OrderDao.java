package com.marian.dao;

import com.marian.entity.Order;

import java.util.Date;
import java.util.List;

public interface OrderDao {
    List<Order> getAllOrdered(Date date1);
    void save(Order order);
}
