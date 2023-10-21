package com.nus.service;

import com.nus.dto.DishDTO;
import com.nus.dto.DishPageDTO;
import com.nus.result.PageResult;
import com.nus.vo.DishVO;

import java.util.List;

public interface DishService {
    List<DishVO> showAllDishesOfChef(Long chefId);

    void addNewDish(DishDTO dishDTO);

    void updateDish(DishDTO dishDTO);

    void deleteById(Long id);

    void enableDishById(Long id);

    void disableDishById(Long id);

    PageResult pageQuery(DishPageDTO dishPageDTO);
}
