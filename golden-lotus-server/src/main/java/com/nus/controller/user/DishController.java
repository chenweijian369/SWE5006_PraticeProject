package com.nus.controller.user;

import com.nus.entity.Dish;
import com.nus.result.Result;
import com.nus.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/dish")
@Slf4j
@Api(tags = "Dish Relevant Interface")
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private RedisTemplate redisTemplate;

    // TODO
    @GetMapping("/show")
    @ApiOperation("show all available dishes")
    public Result<List<Dish>> show(Long chefId){
        log.info("Show all dishes of the chef");
        List<Dish> list = dishService.showAllDishesOfChef(chefId);
        return Result.success(list);
    }
}
