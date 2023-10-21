package com.nus.service;

import com.nus.dto.OrderDTO;
import com.nus.entity.Order;
import com.nus.entity.OrderDetails;

import java.util.List;

public interface OrderService {

    List<Order> getUserOrder();

    List<Order> getOrdersByUsername(String userName);

    void saveOrderDetails(OrderDetails orderDetails);

    void placeOrder(OrderDTO orderDTO);
}
