package com.nus.service;

import com.nus.vo.ChefVO;

import java.util.List;

public interface ChefService {
    List<ChefVO> showAllChefsOfCategory(Long categoryId);
}
