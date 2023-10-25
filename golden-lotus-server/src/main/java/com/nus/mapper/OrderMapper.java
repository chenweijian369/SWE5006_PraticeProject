package com.nus.mapper;

import com.nus.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper {

    void insert(Order order);

    @Select("select * from `order`where user_id = #{userId}")
    List<Order> getByUserId(Long userId);
}
