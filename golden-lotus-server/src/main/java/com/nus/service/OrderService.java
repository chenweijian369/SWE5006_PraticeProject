package com.nus.service;

import com.nus.entity.Order;

import java.util.List;

public interface OrderService {

    List<Order> getUserOrder();

    List<Order> getOrdersByUsername(String userName);
}
