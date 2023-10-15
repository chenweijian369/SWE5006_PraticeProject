package com.nus.mapper;

import com.nus.entity.Dish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DishMapper {
    @Select("select * from dish where id = #{id}")
    Dish getById(Long id);
}
