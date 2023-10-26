package com.nus.service.impl;

import com.nus.mapper.OrderDetailMapper;
import com.nus.mapper.OrderMapper;
import com.nus.pojo.entity.Order;
import com.nus.pojo.entity.OrderDetail;
import com.nus.pojo.vo.OrderVO;
import com.nus.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public List<OrderVO> getOrderDetailsByChefId(Long chefId) {
        List<Order> orderList = orderMapper.getByChefId(chefId);
        List<OrderVO> orderVOList = new ArrayList<>();
        for (Order order : orderList) {
            List<OrderDetail> orderDetails = orderDetailMapper.getByOrderIdAndChefId(order.getId(), chefId);
            OrderVO orderVO = new OrderVO();
            BeanUtils.copyProperties(order, orderVO);
            orderVO.setOrderDetailList(orderDetails);
            orderVOList.add(orderVO);
        }
        return orderVOList;
    }

    @Override
    public void completeOrder(Long id) {
        orderMapper.completeOrderById(id);
    }
}