package com.nus.service.impl;

import com.nus.mapper.ChefMapper;
import com.nus.mapper.DishMapper;
import com.nus.pojo.entity.Chef;
import com.nus.pojo.vo.ChefVO;
import com.nus.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ChefMapper chefMapper;

    @Autowired
    private DishMapper dishMapper;

    @Override
    public List<ChefVO> getByDishName(String dishName) {
        List<Long> chefIds = dishMapper.getChefIdsByDishName(dishName);
        List<ChefVO> chefVOList = new ArrayList<>();

        for (Long id : chefIds) {
            ChefVO chefVO = new ChefVO();
            Chef chef = chefMapper.getById(id);
            if (chef.getStatus() == 1) {
                BeanUtils.copyProperties(chef, chefVO);
                chefVOList.add(chefVO);
            }
        }

        return chefVOList;
    }
}
