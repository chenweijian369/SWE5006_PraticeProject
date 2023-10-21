package com.nus.service;

import com.nus.dto.ShoppingCartDTO;
import com.nus.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    void addToShoppingCart(ShoppingCartDTO shoppingCartDTO);

    void removeFromShoppingCart(ShoppingCartDTO shoppingCartDTO);

    List<ShoppingCart> showAll();

    void emptyShoppingCart();

    Double calculateTotal();

}
