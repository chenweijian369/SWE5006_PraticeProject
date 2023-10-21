package com.nus.mapper;


import com.nus.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @see
 */
@Mapper
public interface OrderMapper {


    List<Order> getUserOrder();
}
