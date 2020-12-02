package com.marian.service;

import com.marian.domain.DateSearch;
import com.marian.entity.Order;
import com.marian.entity.UserEntity;

import java.security.Principal;
import java.util.List;

public interface OrderService {
    void saveOrder(Principal principal, DateSearch dateSearch, int roomId);
    List<Order> getAllOrderByUser(UserEntity user);
    void delete(int orderId);
}
