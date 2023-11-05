package com.nus.service.impl;

import com.github.pagehelper.Page;
import com.nus.context.BaseContext;
import com.nus.mapper.ChefCategoryMapper;
import com.nus.mapper.ChefMapper;
import com.nus.pojo.dto.ChefAccountDTO;
import com.nus.pojo.dto.ChefDTO;
import com.nus.pojo.dto.ChefPageDTO;
import com.nus.pojo.entity.Chef;
import com.nus.pojo.entity.ChefCategory;
import com.nus.pojo.vo.ChefVO;
import com.nus.result.PageResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ChefServiceImplTest {

    @Mock
    private ChefMapper chefMapper;

    @Mock
    private ChefCategoryMapper chefCategoryMapper;

    @InjectMocks
    private ChefServiceImpl chefService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testShowAllChefsOfCategory() {
        // 模拟输入的categoryId
        Long categoryId = 1L;

        // 模拟ChefMapper的getByCategoryId方法返回的数据
        List<ChefVO> mockChefs = new ArrayList<>();
        ChefVO chef1 = new ChefVO();
        chef1.setId(1L);
        chef1.setName("Chef 1");
        ChefVO chef2 = new ChefVO();
        chef2.setId(2L);
        chef2.setName("Chef 2");
        mockChefs.add(chef1);
        mockChefs.add(chef2);

        // 使用Mockito来模拟chefMapper.getByCategoryId方法的行为
        when(chefMapper.getByCategoryId(categoryId)).thenReturn(mockChefs);

        // 调用被测试的方法
        List<ChefVO> result = chefService.showAllChefsOfCategory(categoryId);

        // 验证chefMapper.getByCategoryId方法是否被调用
        verify(chefMapper).getByCategoryId(categoryId);

        // 验证结果是否与模拟数据一致
        Assertions.assertEquals(mockChefs, result);
    }

    @Test
    public void testShowAll() {
        // 模拟ChefMapper的showAllEnableChefs方法返回的数据
        List<ChefVO> mockChefs = new ArrayList<>();
        ChefVO chef1 = new ChefVO();
        chef1.setId(1L);
        chef1.setName("Chef 1");
        ChefVO chef2 = new ChefVO();
        chef2.setId(2L);
        chef2.setName("Chef 2");
        mockChefs.add(chef1);
        mockChefs.add(chef2);

        // 使用Mockito来模拟chefMapper.showAllEnableChefs方法的行为
        when(chefMapper.showAllEnableChefs()).thenReturn(mockChefs);

        // 调用被测试的方法
        List<ChefVO> result = chefService.showAll();

        // 验证chefMapper.showAllEnableChefs方法是否被调用
        verify(chefMapper).showAllEnableChefs();

        // 验证结果是否与模拟数据一致
        Assertions.assertEquals(mockChefs, result);
    }

    @Test
    public void testUpdateChef() {
        ChefDTO chefDTO = new ChefDTO();
        // 设置chefDTO的属性

//        Mockito.when(BaseContext.getCurrentId()).thenReturn(1L); // 模拟BaseContext.getCurrentId()返回1L
        chefDTO.setId(BaseContext.getCurrentId());
        chefService.update(chefDTO);

        // 使用Mockito验证chefMapper的update方法是否被调用
        Mockito.verify(chefMapper, Mockito.times(1)).update(Mockito.any(Chef.class));
    }

    @Test
    public void testPageQuery() {
        ChefPageDTO chefPageDTO = new ChefPageDTO();
        // 设置chefPageDTO的属性

        // 模拟chefMapper的pageQuery方法返回一个空的Page对象
        Page<Chef> emptyPage = new Page<>();
        Mockito.when(chefMapper.pageQuery(chefPageDTO)).thenReturn(emptyPage);

        PageResult result = chefService.page(chefPageDTO);

        // 使用断言验证返回的PageResult是否符合预期
        Assertions.assertEquals(0, result.getTotal());
        Assertions.assertTrue(result.getRecords().isEmpty());
    }

    @Test
    public void testStartOrStopChef() {
        Integer status = 1;
        Long chefId = 1L;

        chefService.startOrStop(status, chefId);

        Chef chef = new Chef();
        chef.setId(chefId);
        chef.setStatus(status);

        // 使用Mockito验证chefMapper的update方法是否被调用，并检查传递给update方法的参数
        Mockito.verify(chefMapper, Mockito.times(1)).update(Mockito.eq(chef));
    }

    @Test
    public void testGetChefById() {
        Long chefId = 1L;
        Chef chef = new Chef();
        // 设置chef的属性

        // 模拟chefMapper的getById方法返回一个特定的Chef对象
        Mockito.when(chefMapper.getById(chefId)).thenReturn(chef);

        Chef result = chefService.getById(chefId);

        // 使用断言验证返回的Chef对象是否符合预期
        Assertions.assertEquals(chef, result);
    }

    @Test
    public void testGetById() {
        Long chefId = 1L;
        Chef chef = new Chef();
        chef.setId(chefId);
        chef.setPassword("myPassword"); // Password should be masked
        when(chefMapper.getById(chefId)).thenReturn(chef);

        Chef result = chefService.getById(chefId);

        // Ensure password is masked
        assertEquals("****", result.getPassword());
    }

    @Test
    public void testClassifyChefCategoryById() {
        Long categoryId = 1L;
        when(chefCategoryMapper.getByChefIdAndCategoryId(1L, categoryId)).thenReturn(new ArrayList<>());

        assertDoesNotThrow(() -> chefService.classifyChefCategoryById(categoryId));

        verify(chefCategoryMapper).insert(Mockito.any(ChefCategory.class));
    }

    @Test
    public void testDeleteChefCategoryById() {
        Long categoryId = 1L;
        assertDoesNotThrow(() -> chefService.deleteChefCategoryById(categoryId));

        verify(chefCategoryMapper).deleteByCategoryId(categoryId);
    }

    @Test
    public void testUpdateAccount() {
        ChefAccountDTO chefAccountDTO = new ChefAccountDTO();
        chefAccountDTO.setId(1L);
        chefAccountDTO.setPassword("newPassword");

        assertDoesNotThrow(() -> chefService.updateAccount(chefAccountDTO));

        verify(chefMapper).update(Mockito.any(Chef.class));
    }
}
