package com.nus.service.impl;

import com.nus.mapper.ChefMapper;
import com.nus.pojo.vo.ChefVO;
import com.nus.service.ChefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChefServiceImpl implements ChefService {

    @Autowired
    private ChefMapper chefMapper;

    @Override
    public List<ChefVO> showAllChefsOfCategory(Long categoryId) {
        return chefMapper.getByCategoryId(categoryId);
    }

    @Override
    public List<ChefVO> showAll() {
        return chefMapper.showAllEnableChefs();
    }
}
