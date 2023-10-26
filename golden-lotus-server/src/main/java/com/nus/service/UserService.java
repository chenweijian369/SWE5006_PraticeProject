package com.nus.service;

import com.nus.pojo.dto.UserDTO;
import com.nus.pojo.vo.ChefVO;

import java.util.List;

public interface UserService {
    List<ChefVO> getByDishName(String dishName);

    void save(UserDTO userDTO);
}
