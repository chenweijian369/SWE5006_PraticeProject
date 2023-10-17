package com.nus.service.impl;

import com.nus.mapper.ChefCategoryMapper;
import com.nus.mapper.ChefMapper;
import com.nus.service.ChefService;
import com.nus.vo.ChefVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChefServiceImpl implements ChefService {

    @Autowired
    private ChefMapper chefMapper;

    @Autowired
    private ChefCategoryMapper chefCategoryMapper;

    @Override
    public List<ChefVO> showAllChefsOfCategory(Long categoryId) {

        return chefMapper.getByCategoryId(categoryId);
    }
}
