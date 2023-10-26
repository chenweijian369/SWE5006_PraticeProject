package com.nus.mapper;

import com.nus.pojo.dto.ShoppingCartDTO;
import com.nus.pojo.entity.ShoppingCart;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {

    ShoppingCart findDishInCart(ShoppingCartDTO shoppingCartDTO,Long userId);

    @Update("update shopping_cart set number = #{number}, amount = #{amount} where id = #{id}")
    void updateById(Long id, Integer number, Double amount);

    @Insert("insert into shopping_cart(user_id, dish_id, chef_id, dish_name, dish_image, chef_name, chef_image, number, amount, create_time)" +
            "values (#{userId}, #{dishId}, #{chefId}, #{dishName}, #{dishImage}, #{chefName}, #{chefImage}, #{number}, #{amount}, #{createTime})")
    void insert(ShoppingCart shoppingCart);

    @Select("select * from shopping_cart where user_id = #{userId}")
    List<ShoppingCart> getByUserId(Long userId);

    @Delete("delete from shopping_cart where user_id = #{userId}")
    void deleteByUserId(Long userId);

    @Delete("delete from shopping_cart where id = #{id}")
    void deleteById(Long id);

    @Select("select amount from shopping_cart where user_id = #{userId}")
    List<Double> getAmountByUserId(Long userId);
}
