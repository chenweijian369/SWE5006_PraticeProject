package com.nus.service.impl;

import com.nus.entity.Chef;
import com.nus.entity.People;
import com.nus.mapper.ChefMapper;
import com.nus.service.ChefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChefServiceImpl implements ChefService {
    @Autowired
    private ChefMapper chefMapper;

    public List<Chef> getAllChefs() {
        return chefMapper.getAllChefs();
    }


}
