package com.nus.mapper;

import com.nus.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderDetailMapper {


    void insertDetailList(List<OrderDetail> orderDetailList);

    @Select("select * from order_detail where order_id= #{id}")
    List<OrderDetail> queryByOrderId(Long id);

    @Select("select * from order_detail where dish_id= #{id}")
    List<OrderDetail> queryByDishId(Long id);

    void cancelOrder(Long order_id);

   List<OrderDetail> checkOrderDetail(Long order_id);


}
