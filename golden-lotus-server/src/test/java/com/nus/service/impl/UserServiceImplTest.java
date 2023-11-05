package com.nus.service.impl;

import com.nus.mapper.ChefMapper;
import com.nus.mapper.DishMapper;
import com.nus.pojo.entity.Chef;
import com.nus.pojo.vo.ChefVO;
import com.nus.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @InjectMocks
    private UserService userService = new UserServiceImpl();

    @Mock
    private ChefMapper chefMapper;

    @Mock
    private DishMapper dishMapper;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetByDishName() {
        // 模拟 dishMapper 返回的假数据
        List<Long> mockChefIds = new ArrayList<>();
        mockChefIds.add(1L);
        mockChefIds.add(2L);

        // 模拟 chefMapper 返回的假数据
        Chef chef1 = new Chef();
        chef1.setId(1L);
        chef1.setName("Chef 1");
        chef1.setStatus(1);

        Chef chef2 = new Chef();
        chef2.setId(2L);
        chef2.setName("Chef 2");
        chef2.setStatus(1);

        // 设置模拟的行为
        when(dishMapper.getChefIdsByDishName("TestDish")).thenReturn(mockChefIds);
        when(chefMapper.getById(1L)).thenReturn(chef1);
        when(chefMapper.getById(2L)).thenReturn(chef2);

        // 调用被测试的方法
        List<ChefVO> result = userService.getByDishName("TestDish");

        // 验证结果
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("Chef 1", result.get(0).getName());
        Assertions.assertEquals("Chef 2", result.get(1).getName());
    }
}
