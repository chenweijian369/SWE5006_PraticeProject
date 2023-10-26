package com.nus.mapper;

import com.nus.pojo.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderDetailMapper {

    @Select("select * from order_detail where order_id = #{orderId} and chef_id = #{chefId}")
    List<OrderDetail> getByOrderIdAndChefId(Long orderId, Long chefId);
}
