package com.nus.service.impl;

import com.nus.mapper.CategoryMapper;
import com.nus.service.CategoryService;
import com.nus.pojo.vo.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryVO> showAll() {
        return categoryMapper.showAll();
    }
}
