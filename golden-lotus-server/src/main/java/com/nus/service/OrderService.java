package com.nus.service;

import com.nus.pojo.dto.OrderSubmitDTO;
import com.nus.pojo.vo.OrderVO;

import java.util.List;

public interface OrderService {
    void submitOrder(OrderSubmitDTO ordersSubmitDTO);

    List<OrderVO> getOrderDetailsByUserId(Long userId);

    List<OrderVO> getOrderDetailsByChefId(Long chefId);

    void completeOrder(Long id);

}
