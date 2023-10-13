package com.nus.controller.user;

import com.nus.result.Result;
import com.nus.service.ShoppingCartService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping
//@Slf4j
//public class ShoppingCartController {
//    @Autowired
//    private ShoppingCartService shoppingCartService;
//
//    @PostMapping("/add")
//    public Result add(){
//
//
//        return Result.success();
//    }
//}
