package com.nus.controller.user;

import com.nus.result.Result;
import com.nus.service.DishService;
import com.nus.pojo.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("userDishController")
@RequestMapping("/user/chef/dish")
@Slf4j
@Api(tags = "Dish Relevant Interface")
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * Show All Enable Dishes of Chef to User
     * @param chefId
     * @return
     */
    @GetMapping("/show")
    @ApiOperation("show all available dishes")
    public Result<List<DishVO>> showByChefId(Long chefId){
        log.info("Show all dishes of the chef");
        // set redis key with format likes dish_1"
        String key = "dish_ChefId" + chefId;
        // search result in redis
        List<DishVO> list = (List<DishVO>) redisTemplate.opsForValue().get(key);
        // if found, then return
        if (list != null){
            return Result.success(list);
        }
        // if not found, search in MySQL database
        list = dishService.showAllDishesOfChef(chefId);
        // set redis key and value
        redisTemplate.opsForValue().set(key, list);
        return Result.success(list);
    }
}
