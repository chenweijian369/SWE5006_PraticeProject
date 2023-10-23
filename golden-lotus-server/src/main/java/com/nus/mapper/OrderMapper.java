package com.nus.mapper;

import com.github.pagehelper.Page;
import com.nus.dto.OrdersPageQueryDTO;
import com.nus.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper

public interface OrderMapper {
    Page<Orders> queryConditionSearchOrders(OrdersPageQueryDTO ordersPageQueryDTO);

    void cancelOrder(Long id);
}
