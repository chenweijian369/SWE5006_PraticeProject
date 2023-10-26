package com.nus.service;

import com.nus.pojo.vo.DishVO;

import java.util.List;

public interface DishService {
    List<DishVO> showAllDishesOfChef(Long chefId);
}
