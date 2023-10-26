package com.nus.service.impl;

import com.nus.pojo.dto.UserDTO;
import com.nus.pojo.entity.Chef;
import com.nus.pojo.entity.User;
import com.nus.mapper.ChefMapper;
import com.nus.mapper.DishMapper;
import com.nus.mapper.UserMapper;
import com.nus.service.UserService;
import com.nus.pojo.vo.ChefVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ChefMapper chefMapper;

    @Autowired
    private DishMapper dishMapper;

    /**
     * User Register
     * Done by CHEN WEIJIAN
     * @param userDTO
     */
    @Override
    public void save(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        user.setCreateTime(LocalDateTime.now());
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));

        userMapper.insert(user);
    }

    @Override
    public List<ChefVO> getByDishName(String dishName) {
        List<Long> chefIds = dishMapper.getChefIdsByDishName(dishName);
        List<ChefVO> chefVOList = new ArrayList<>();

        for (Long id : chefIds) {
            ChefVO chefVO = new ChefVO();
            Chef chef = chefMapper.getById(id);
            if (chef.getStatus() == 1) {
                BeanUtils.copyProperties(chef, chefVO);
                chefVOList.add(chefVO);
            }
        }

        return chefVOList;
    }
}
