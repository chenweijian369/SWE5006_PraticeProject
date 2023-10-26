package com.nus.service;

import com.nus.pojo.vo.OrderVO;

import java.util.List;

public interface OrderService {

    List<OrderVO> getOrderDetailsByChefId(Long chefId);

    void completeOrder(Long id);

}
