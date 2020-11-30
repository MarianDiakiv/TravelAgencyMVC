package com.marian.service;

import com.marian.domain.DateSearch;
import com.marian.entity.Order;

import java.security.Principal;

public interface OrderService {
    void saveOrder(Principal principal, DateSearch dateSearch, int roomId);
}
