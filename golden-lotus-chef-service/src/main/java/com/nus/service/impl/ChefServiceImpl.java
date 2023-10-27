package com.nus.service.impl;


import com.nus.context.BaseContext;
import com.nus.mapper.ChefCategoryMapper;
import com.nus.mapper.ChefMapper;
import com.nus.pojo.dto.ChefAccountDTO;
import com.nus.pojo.entity.Chef;
import com.nus.pojo.entity.ChefCategory;
import com.nus.service.ChefService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChefServiceImpl implements ChefService {

    @Autowired
    private ChefMapper chefMapper;

    @Autowired
    private ChefCategoryMapper chefCategoryMapper;

    @Override
    public Chef getById(Long id) {
        Chef chef = chefMapper.getById(id);
        // Masking password
        chef.setPassword("****");
        return chef;
    }

    @Override
    public void classifyChefCategoryById(Long categoryId) {
        List<ChefCategory> list = chefCategoryMapper.getByChefIdAndCategoryId(BaseContext.getCurrentId(), categoryId);

        if (!list.isEmpty()){
            return;
        }

        ChefCategory chefCategory = new ChefCategory();
        chefCategory.setChefId(BaseContext.getCurrentId());
        chefCategory.setCategoryId(categoryId);
        chefCategoryMapper.insert(chefCategory);
    }

    @Override
    public void deleteChefCategoryById(Long id) {
        chefCategoryMapper.deleteByCategoryId(id);
    }

    @Override
    public void updateAccount(ChefAccountDTO chefAccountDTO) {
        Chef chef = new Chef();
        BeanUtils.copyProperties(chefAccountDTO, chef);
        chef.setPassword(DigestUtils.md5DigestAsHex(chefAccountDTO.getPassword().getBytes()));
        chef.setUpdateTime(LocalDateTime.now());
        chef.setUpdateUser(BaseContext.getCurrentId());

        chefMapper.update(chef);
    }
}
