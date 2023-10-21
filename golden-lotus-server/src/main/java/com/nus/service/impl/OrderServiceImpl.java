package com.nus.service.impl;

import com.nus.entity.Order;
import com.nus.mapper.OrderMapper;
import com.nus.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @see com.nus.mapper.OrderMapper
 *
 * */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Order> getUserOrder() {
        return orderMapper.getUserOrder();
    }
}
