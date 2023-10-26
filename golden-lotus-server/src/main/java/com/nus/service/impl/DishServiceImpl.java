package com.nus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.nus.constant.StatusConstant;
import com.nus.context.BaseContext;
import com.nus.pojo.dto.DishDTO;
import com.nus.pojo.dto.DishPageDTO;
import com.nus.pojo.entity.Dish;
import com.nus.mapper.DishMapper;
import com.nus.result.PageResult;
import com.nus.service.DishService;
import com.nus.pojo.vo.DishVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;

    @Override
    public List<DishVO> showAllDishesOfChef(Long chefId) {
        List<Dish> dishList = dishMapper.getByChefId(chefId);
        List<DishVO> dishVOList = new ArrayList<>();

        for (Dish d : dishList) {
            DishVO dishVO = new DishVO();
            BeanUtils.copyProperties(d, dishVO);
            dishVOList.add(dishVO);
        }
        
        return dishVOList;
    }

    @Override
    public void addNewDish(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        dish.setChefId(BaseContext.getCurrentId());
        dish.setStatus(1);
        dish.setCreateTime(LocalDateTime.now());
        dish.setUpdateTime(LocalDateTime.now());
        dishMapper.insert(dish);
    }

    @Override
    public void updateDish(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        dish.setUpdateTime(LocalDateTime.now());
        dishMapper.update(dish);
    }

    @Override
    public void deleteById(Long id) {
        dishMapper.deleteById(id);
    }

    @Override
    public void enableDishById(Long id) {
        Dish dish = Dish.builder()
                .id(id)
                .status(StatusConstant.ENABLE)
                .build();
        dishMapper.update(dish);
    }

    @Override
    public void disableDishById(Long id) {
        Dish dish = Dish.builder()
                .id(id)
                .status(StatusConstant.DISABLE)
                .build();
        dishMapper.update(dish);
    }

    @Override
    public PageResult pageQuery(DishPageDTO dishPageDTO) {
        PageHelper.startPage(dishPageDTO.getPage(), dishPageDTO.getPageSize());
        Page<DishVO> page = dishMapper.pageQuery(dishPageDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public DishVO getDishById(Long id) {
        // select dish by id
        Dish dish = dishMapper.getById(id);

        // copy data to dishVO
        DishVO dishVO = new DishVO();
        BeanUtils.copyProperties(dish, dishVO);
        return dishVO;
    }
}
