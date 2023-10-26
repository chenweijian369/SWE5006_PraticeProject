package com.nus.mapper;

import com.nus.pojo.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OrderMapper {

    void insert(Order order);

    @Select("select * from `order` where user_id = #{userId}")
    List<Order> getByUserId(Long userId);

    @Select("select * from `order` o left join order_detail od on o.id = od.order_id where chef_id = #{chefId}")
    List<Order> getByChefId(Long chefId);

    @Update("update `order` set status = 1 where id = #{id}")
    void completeOrderById(Long id);
}
