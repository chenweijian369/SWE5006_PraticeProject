package com.nus.service.impl;

import com.nus.context.BaseContext;
import com.nus.pojo.dto.ShoppingCartDTO;
import com.nus.pojo.entity.Chef;
import com.nus.pojo.entity.Dish;
import com.nus.pojo.entity.ShoppingCart;
import com.nus.mapper.ChefMapper;
import com.nus.mapper.DishMapper;
import com.nus.mapper.ShoppingCartMapper;
import com.nus.service.ShoppingCartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private ChefMapper chefMapper;

    private Map<Long, Double> map = new HashMap<>();

    /**
     * Done by CHEN WEIJIAN
     * @param shoppingCartDTO
     */
    @Override
    public void addToShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart cart = shoppingCartMapper.findDishInCart(shoppingCartDTO, BaseContext.getCurrentId());

        // If the dish is existed, then add the number
        if (cart != null){
            if (map.isEmpty()){
                map.put(cart.getDishId(), cart.getAmount() / cart.getNumber());
            }
            cart.setNumber(cart.getNumber() + 1);
            cart.setAmount(map.get(cart.getDishId()) * cart.getNumber());
            shoppingCartMapper.updateById(cart.getId(), cart.getNumber(), cart.getAmount());
        }
        // if the dish is not existed, then create the dish and add to shopping cart
        else {
            Long dishId = shoppingCartDTO.getDishId();
            Dish dish = dishMapper.getById(dishId);

            Long chefId = shoppingCartDTO.getChefId();
            Chef chef = chefMapper.getById(chefId);

            map.put(dishId, dish.getPrice());

            // Cope data to ShoppingCart entity
            ShoppingCart shoppingCart = copyData(shoppingCartDTO);
            shoppingCart.setDishName(dish.getName());
            shoppingCart.setDishImage(dish.getImage());
            shoppingCart.setChefName(chef.getName());
            shoppingCart.setChefImage(chef.getImage());
            shoppingCart.setNumber(1);
            shoppingCart.setAmount(dish.getPrice());
            shoppingCart.setCreateTime(LocalDateTime.now());
            shoppingCartMapper.insert(shoppingCart);
        }
    }

    /**
     * Done by CHEN WEIJIAN
     * @param shoppingCartDTO
     */
    @Override
    public void removeFromShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart cart = shoppingCartMapper.findDishInCart(shoppingCartDTO, BaseContext.getCurrentId());

        // Normally the shopping cart is not null when deleting dish
        if (cart != null){
            // number == 1, the dish is removed from shopping cart and its database
            if (cart.getNumber() == 1){
                shoppingCartMapper.deleteById(cart.getId());
            }
            // number != 1, change the number = number - 1
            else {
                cart.setNumber(cart.getNumber() - 1);
                cart.setAmount(map.get(cart.getDishId()) * cart.getNumber());
                shoppingCartMapper.updateById(cart.getId(), cart.getNumber(), cart.getAmount());
            }
        }

    }

    /**
     * Done by CHEN WEIJIAN
     * @return
     */
    @Override
    public List<ShoppingCart> showAll() {
        List<ShoppingCart> list = shoppingCartMapper.getByUserId(BaseContext.getCurrentId());

        return list;
    }

    /**
     * Done by CHEN WEIJIAN
     */
    @Override
    public void emptyShoppingCart() {
        shoppingCartMapper.deleteByUserId(BaseContext.getCurrentId());
    }

    public ShoppingCart copyData(ShoppingCartDTO shoppingCartDTO){
        // Cope data to ShoppingCart entity
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO, shoppingCart);
        shoppingCart.setUserId(BaseContext.getCurrentId());

        return shoppingCart;
    }

    @Override
    public Double calculateTotal() {
        List<Double> list = shoppingCartMapper.getAmountByUserId(BaseContext.getCurrentId());
        Double total = 0.0;
        for (double amout: list) {
            total += amout;
        }
        return total;
    }
}
