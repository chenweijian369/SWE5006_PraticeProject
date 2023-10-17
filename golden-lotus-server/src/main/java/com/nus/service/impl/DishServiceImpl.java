package com.nus.service.impl;

import com.nus.entity.Dish;
import com.nus.mapper.DishMapper;
import com.nus.service.DishService;
import com.nus.vo.DishVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;

    @Override
    public List<DishVO> showAllDishesOfChef(Long chefId) {
        List<Dish> dishList = dishMapper.getByChefId(chefId);
        List<DishVO> dishVOList =new ArrayList<>();

        for (Dish d : dishList) {
            DishVO dishVO = new DishVO();
            BeanUtils.copyProperties(d, dishVO);
            dishVOList.add(dishVO);
        }
        
        return dishVOList;
    }
}
