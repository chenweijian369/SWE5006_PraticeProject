package com.nus.service.impl;

import com.nus.mapper.CategoryMapper;
import com.nus.pojo.vo.CategoryVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CategoryServiceImplTest {

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testShowAll() {
        // 模拟CategoryMapper的showAll方法返回的数据
        List<CategoryVO> mockCategories = new ArrayList<>();
        CategoryVO category1 = new CategoryVO(1L, "Category 1");
        CategoryVO category2 = new CategoryVO(2L, "Category 2");
        mockCategories.add(category1);
        mockCategories.add(category2);

        // 使用Mockito来模拟categoryMapper.showAll方法的行为
        when(categoryMapper.showAll()).thenReturn(mockCategories);

        // 调用被测试的方法
        List<CategoryVO> result = categoryService.showAll();

        // 验证categoryMapper.showAll方法是否被调用
        verify(categoryMapper).showAll();

        // 验证结果是否与模拟数据一致
        Assertions.assertEquals(mockCategories, result);
    }
}
