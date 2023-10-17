package com.nus.service;

import com.nus.dto.UserDTO;
import com.nus.vo.ChefVO;

import java.util.List;

public interface UserService {
    List<ChefVO> getByDishName(String dishName);

    void save(UserDTO userDTO);
}
