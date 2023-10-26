package com.nus.mapper;

import com.github.pagehelper.Page;
import com.nus.pojo.dto.DishPageDTO;
import com.nus.pojo.entity.Dish;
import com.nus.pojo.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishMapper {
    @Select("select * from dish where id = #{id}")
    Dish getById(Long id);

    @Select("select * from dish where chef_id = #{chefId} and status = 1")
    List<Dish> getByChefId(Long chefId);

    List<Long> getChefIdsByDishName(String dishName);

    @Insert("insert into dish (chef_id, name, price, image, description, status, create_time, update_time) " +
            "values (#{chefId},#{name},#{price},#{image},#{description},#{status},#{createTime},#{updateTime})")
    void insert(Dish dish);

    void update(Dish dish);

    @Delete("delete from dish where id = #{id}")
    void deleteById(Long id);

    Page<DishVO> pageQuery(DishPageDTO dishPageDTO);
}
