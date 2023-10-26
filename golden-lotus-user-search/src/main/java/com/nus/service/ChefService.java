package com.nus.service;

import com.nus.pojo.vo.ChefVO;

import java.util.List;

public interface ChefService {
    List<ChefVO> showAllChefsOfCategory(Long categoryId);

    List<ChefVO> showAll();

}
