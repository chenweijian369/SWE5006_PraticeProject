package com.nus.service;

import com.nus.pojo.dto.ChefDTO;
import com.nus.pojo.dto.ChefPageDTO;
import com.nus.pojo.entity.Chef;
import com.nus.result.PageResult;

public interface ChefService {

    void save(ChefDTO chefDTO);

    void update(ChefDTO chefDTO);

    PageResult page(ChefPageDTO chefPageDTO);

    void startOrStop(Integer status, Long id);

    Chef getById(Long id);

}
