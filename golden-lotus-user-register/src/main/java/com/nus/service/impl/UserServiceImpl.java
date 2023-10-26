package com.nus.service.impl;

import com.nus.mapper.UserMapper;
import com.nus.pojo.dto.UserDTO;
import com.nus.pojo.entity.User;
import com.nus.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

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
}
