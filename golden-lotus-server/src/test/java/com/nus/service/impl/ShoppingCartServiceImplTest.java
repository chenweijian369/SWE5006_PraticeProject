package com.nus.service.impl;

import com.nus.context.BaseContext;
import com.nus.mapper.ChefMapper;
import com.nus.mapper.DishMapper;
import com.nus.mapper.ShoppingCartMapper;
import com.nus.pojo.dto.ShoppingCartDTO;
import com.nus.pojo.entity.Chef;
import com.nus.pojo.entity.Dish;
import com.nus.pojo.entity.ShoppingCart;
import com.nus.service.ShoppingCartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class ShoppingCartServiceImplTest {

    @InjectMocks
    private ShoppingCartService shoppingCartService = new ShoppingCartServiceImpl();

    @Mock
    private ShoppingCartMapper shoppingCartMapper;

    @Mock
    private DishMapper dishMapper;

    @Mock
    private ChefMapper chefMapper;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    // Test for addToShoppingCart
    @Test
    public void testAddToShoppingCart() {
        ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
        when(shoppingCartMapper.findDishInCart(shoppingCartDTO, BaseContext.getCurrentId())).thenReturn(null);
        when(dishMapper.getById(shoppingCartDTO.getDishId())).thenReturn(new Dish());
        when(chefMapper.getById(shoppingCartDTO.getChefId())).thenReturn(new Chef());

        shoppingCartService.addToShoppingCart(shoppingCartDTO);

        verify(shoppingCartMapper, times(1)).insert(any(ShoppingCart.class));
    }

    // Test for showAll
    @Test
    public void testShowAll() {
        when(shoppingCartMapper.getByUserId(BaseContext.getCurrentId())).thenReturn(new ArrayList<>());

        List<ShoppingCart> shoppingCartList = shoppingCartService.showAll();

        verify(shoppingCartMapper, times(1)).getByUserId(BaseContext.getCurrentId());
    }

    // Test for emptyShoppingCart
    @Test
    public void testEmptyShoppingCart() {
        shoppingCartService.emptyShoppingCart();

        verify(shoppingCartMapper, times(1)).deleteByUserId(BaseContext.getCurrentId());
    }
}
