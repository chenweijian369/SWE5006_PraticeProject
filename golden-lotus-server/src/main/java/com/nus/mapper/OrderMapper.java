package com.nus.mapper;


import com.nus.entity.Order;
import com.nus.entity.OrderDetails;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @see
 */
@Mapper
public interface OrderMapper {

    List<Order> getUserOrder();

    List<Order> getOrdersByUsername(String userName);

    void saveOrderDetails(OrderDetails orderDetails);

    void placeOrder(Order order);
}
