package com.nus.mapper;

import com.nus.pojo.entity.Dish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishMapper {
    @Select("select * from dish where id = #{id}")
    Dish getById(Long id);
}
