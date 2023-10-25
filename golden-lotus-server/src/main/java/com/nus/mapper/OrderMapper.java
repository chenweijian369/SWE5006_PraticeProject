package com.nus.mapper;

import com.github.pagehelper.Page;
import com.nus.dto.OrdersPageQueryDTO;
import com.nus.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper

public interface OrderMapper {
    Page<Orders> queryConditionSearchOrders(OrdersPageQueryDTO ordersPageQueryDTO);

    /**
     * Insert order into table orders
     * @param orders
     * */
    void insert(Orders orders);
    void cancelOrder(Long id);

    Orders getById(Long id);
    void confirmOrder(Long id, Integer status);

    /**
     * Modify the information of orders
     * */
    void update(Orders orders);
}
