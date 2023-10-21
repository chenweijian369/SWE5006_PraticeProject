package com.nus.service.impl;

import com.nus.context.BaseContext;
import com.nus.dto.OrderDTO;
import com.nus.entity.Order;
import com.nus.entity.OrderDetails;
import com.nus.mapper.OrderMapper;
import com.nus.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Override
    public List<Order> getOrdersByUsername(String userName) {
        return orderMapper.getOrdersByUsername(userName);
    }

    @Override
    public void saveOrderDetails(OrderDetails orderDetails) {

    }

    @Override
    public void placeOrder(OrderDTO orderDTO){
        Order order = new Order();
        BeanUtils.copyProperties(orderDTO,order);
        order.setId(BaseContext.getCurrentId());
        order.setOrderTime(LocalDateTime.now());
        order.setDeliveryTime(LocalDateTime.now());
        orderMapper.placeOrder(order);
    }
}
