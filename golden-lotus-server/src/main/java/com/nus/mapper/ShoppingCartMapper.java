package com.nus.mapper;

import com.nus.dto.ShoppingCartDTO;
import com.nus.entity.ShoppingCart;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {

    ShoppingCart findDishInCart(ShoppingCartDTO shoppingCartDTO,Long userId);

    @Update("update shopping_cart set number = #{number}, amount = #{amount} where id = #{id}")
    void updateById(Long id, Integer number, Double amount);

    @Insert("insert into shopping_cart(name, image, user_id, dish_id, chef_id, number, amount, create_time)" +
            "values (#{name},#{image},#{userId},#{dishId}, #{chefId}, #{number},#{amount},#{createTime})")
    void insert(ShoppingCart shoppingCart);

    @Select("select * from shopping_cart where user_id = #{userId}")
    List<ShoppingCart> getByUserId(Long userId);

    @Delete("delete from shopping_cart where user_id = #{userId}")
    void deleteByUserId(Long userId);

    @Delete("delete from shopping_cart where id = #{id}")
    void deleteById(Long id);
}
