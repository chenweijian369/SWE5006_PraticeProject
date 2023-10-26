package com.nus.service;

import com.nus.pojo.dto.ChefAccountDTO;
import com.nus.pojo.dto.ChefDTO;
import com.nus.pojo.dto.ChefPageDTO;
import com.nus.pojo.entity.Chef;
import com.nus.result.PageResult;
import com.nus.pojo.vo.ChefVO;

import java.util.List;

public interface ChefService {
    List<ChefVO> showAllChefsOfCategory(Long categoryId);

    void save(ChefDTO chefDTO);

    void update(ChefDTO chefDTO);

    PageResult page(ChefPageDTO chefPageDTO);

    void startOrStop(Integer status, Long id);

    Chef getById(Long id);

    void classifyChefCategoryById(Long categoryId);

    void deleteChefCategoryById(Long id);

    void updateAccount(ChefAccountDTO chefAccountDTO);

    List<ChefVO> showAll();

}
