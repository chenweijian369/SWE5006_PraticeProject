package com.nus.service.impl;

import com.github.pagehelper.Page;
import com.nus.mapper.DishMapper;
import com.nus.pojo.dto.DishDTO;
import com.nus.pojo.dto.DishPageDTO;
import com.nus.pojo.entity.Dish;
import com.nus.pojo.vo.DishVO;
import com.nus.result.PageResult;
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

public class DishServiceImplTest {

    @InjectMocks
    private DishServiceImpl dishService;

    @Mock
    private DishMapper dishMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testShowAllDishesOfChef() {
        Long chefId = 1L;
        Dish dish1 = new Dish();
        dish1.setId(1L);
        dish1.setName("Dish 1");

        Dish dish2 = new Dish();
        dish2.setId(2L);
        dish2.setName("Dish 2");

        List<Dish> dishList = new ArrayList<>();
        dishList.add(dish1);
        dishList.add(dish2);

        when(dishMapper.getByChefId(chefId)).thenReturn(dishList);

        List<DishVO> result = dishService.showAllDishesOfChef(chefId);

        // Verify if the dishes were correctly copied to DishVO objects
        assertEquals(2, result.size());
        assertEquals("Dish 1", result.get(0).getName());
        assertEquals("Dish 2", result.get(1).getName());
    }

    @Test
    public void testAddNewDish() {
        DishDTO dishDTO = new DishDTO();
        dishDTO.setName("New Dish");

        assertDoesNotThrow(() -> dishService.addNewDish(dishDTO));
        verify(dishMapper).insert(Mockito.any(Dish.class));
    }

    @Test
    public void testUpdateDish() {
        DishDTO dishDTO = new DishDTO();
        dishDTO.setId(1L);
        dishDTO.setName("Updated Dish");

        assertDoesNotThrow(() -> dishService.updateDish(dishDTO));
        verify(dishMapper).update(Mockito.any(Dish.class));
    }

    @Test
    public void testDeleteById() {
        Long dishId = 1L;
        assertDoesNotThrow(() -> dishService.deleteById(dishId));
        verify(dishMapper).deleteById(dishId);
    }

    @Test
    public void testEnableDishById() {
        Long dishId = 1L;
        assertDoesNotThrow(() -> dishService.enableDishById(dishId));
        verify(dishMapper).update(Mockito.any(Dish.class));
    }

    @Test
    public void testDisableDishById() {
        Long dishId = 1L;
        assertDoesNotThrow(() -> dishService.disableDishById(dishId));
        verify(dishMapper).update(Mockito.any(Dish.class));
    }

    @Test
    public void testPageQuery() {
        DishPageDTO dishPageDTO = new DishPageDTO();
        dishPageDTO.setPage(1);
        dishPageDTO.setPageSize(10);

        // Create a list of DishVO objects for the test
        DishVO dishVO1 = new DishVO();
        dishVO1.setId(1L);
        dishVO1.setName("Dish 1");

        DishVO dishVO2 = new DishVO();
        dishVO2.setId(2L);
        dishVO2.setName("Dish 2");

        Page<DishVO> dishVOList = new Page<>();
        dishVOList.add(dishVO1);
        dishVOList.add(dishVO2);

        when(dishMapper.pageQuery(dishPageDTO)).thenReturn(dishVOList);

        PageResult result = dishService.pageQuery(dishPageDTO);

        // Verify if the page query result matches the mock data
        assertEquals(0, result.getTotal());
        assertEquals(2, result.getRecords().size());
        assertEquals("Dish 1", ((DishVO) result.getRecords().get(0)).getName());
        assertEquals("Dish 2", ((DishVO) result.getRecords().get(1)).getName());
    }

    @Test
    public void testGetDishById() {
        Long dishId = 1L;
        Dish dish = new Dish();
        dish.setId(dishId);
        dish.setName("Dish 1");

        when(dishMapper.getById(dishId)).thenReturn(dish);

        DishVO result = dishService.getDishById(dishId);

        // Verify if the Dish object was correctly copied to DishVO
        assertEquals("Dish 1", result.getName());
    }
}
