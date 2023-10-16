package com.nus.service.impl;

import com.nus.entity.Dish;
import com.nus.mapper.DishMapper;
import com.nus.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;

    @Override
    public List<Dish> showAllDishesOfChef(Long chefId) {
        List<Dish> list = dishMapper.getByChefId(chefId);
        return list;
    }
}
