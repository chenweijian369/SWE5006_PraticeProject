package com.nus.service;

import com.nus.pojo.dto.ChefAccountDTO;
import com.nus.pojo.entity.Chef;

public interface ChefService {

    Chef getById(Long id);

    void classifyChefCategoryById(Long categoryId);

    void deleteChefCategoryById(Long id);

    void updateAccount(ChefAccountDTO chefAccountDTO);

}
