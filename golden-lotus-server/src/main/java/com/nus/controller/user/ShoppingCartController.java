package com.nus.controller.user;

import com.nus.dto.ShoppingCartDTO;
import com.nus.entity.ShoppingCart;
import com.nus.result.Result;
import com.nus.service.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/shoppingcart")
@Slf4j
@Api(tags = "ShoppingCart Relevant Interface")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @PutMapping("/add")
    @ApiOperation(value = "add")
    public Result add(@RequestBody ShoppingCartDTO shoppingCartDTO){
        log.info("User adds dish to shopping cart");
        shoppingCartService.addToShoppingCart(shoppingCartDTO);
        return Result.success();
    }

    @ApiOperation(value = "remove")
    @PutMapping("/remove")
    public Result remove(@RequestBody ShoppingCartDTO shoppingCartDTO){
        log.info("User remove dish from shopping cart");
        shoppingCartService.removeFromShoppingCart(shoppingCartDTO);
        return Result.success();
    }

    @GetMapping("/show")
    @ApiOperation(value = "show all dishes")
    public Result<List<ShoppingCart>> show(){
        log.info("Show all dishes to user");
        List<ShoppingCart> list = shoppingCartService.showAllDishesInShoppingCart();
        return Result.success(list);
    }

    @DeleteMapping
    @ApiOperation(value = "clear")
    public Result clear(){
        log.info("User starts to check out");
        shoppingCartService.emptyShoppingCart();
        return Result.success();
    }
}
