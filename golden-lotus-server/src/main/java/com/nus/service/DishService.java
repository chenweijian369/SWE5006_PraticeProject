package com.nus.service;

import com.nus.entity.Dish;

import java.util.List;

public interface DishService {
    List<Dish> showAllDishesOfChef(Long chefId);
}
