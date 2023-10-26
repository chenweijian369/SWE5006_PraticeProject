package com.nus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.nus.constant.PasswordConstant;
import com.nus.constant.StatusConstant;
import com.nus.context.BaseContext;
import com.nus.mapper.ChefMapper;
import com.nus.pojo.dto.ChefDTO;
import com.nus.pojo.dto.ChefPageDTO;
import com.nus.pojo.entity.Chef;
import com.nus.result.PageResult;
import com.nus.service.ChefService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;

@Service
public class ChefServiceImpl implements ChefService {

    @Autowired
    private ChefMapper chefMapper;

    @Override
    public void save(ChefDTO chefDTO) {
        Chef chef = new Chef();
        BeanUtils.copyProperties(chefDTO, chef);

        // default is active
        chef.setStatus(StatusConstant.ENABLE);
        // default is not occupied
        chef.setIsOccupied(0);
        // default password is 123456
        chef.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));
        chef.setCreateTime(LocalDateTime.now());
        chef.setUpdateTime(LocalDateTime.now());
        chef.setCreateUser(BaseContext.getCurrentId());
        chef.setUpdateUser(BaseContext.getCurrentId());
        chefMapper.insert(chef);
    }

    @Override
    public void update(ChefDTO chefDTO) {
        Chef chef = new Chef();
        BeanUtils.copyProperties(chefDTO,chef);
        chef.setUpdateUser(BaseContext.getCurrentId());
        chef.setUpdateTime(LocalDateTime.now());

        chefMapper.update(chef);
    }

    @Override
    public PageResult page(ChefPageDTO chefPageDTO) {
        PageHelper.startPage(chefPageDTO.getPage(),chefPageDTO.getPageSize());
        Page<Chef> page = chefMapper.pageQuery(chefPageDTO);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void startOrStop(Integer status, Long id) {
        Chef chef = new Chef();
        chef.setId(id);
        chef.setStatus(status);
        chefMapper.update(chef);
    }

    @Override
    public Chef getById(Long id) {
        Chef chef = chefMapper.getById(id);
        // Masking password
        chef.setPassword("****");
        return chef;
    }
}
