package com.nus.mapper;

import com.nus.entity.Dish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishMapper {
    @Select("select * from dish where id = #{id}")
    Dish getById(Long id);

    @Select("select * from dish where chef_id = #{chefId} and status = 1")
    List<Dish> getByChefId(Long chefId);
}
