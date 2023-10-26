package com.nus.controller.chef;

import com.nus.pojo.dto.DishDTO;
import com.nus.pojo.dto.DishPageDTO;
import com.nus.result.PageResult;
import com.nus.result.Result;
import com.nus.service.DishService;
import com.nus.pojo.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController("chefDishController")
@RequestMapping("/chef/dish")
@Slf4j
@Api(tags = "Dish Relevant Interface")
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * Chef Add New Dish
     * @param dishDTO
     * @return
     */
    @PostMapping("/add")
    @ApiOperation(value = "chef add dish")
    public Result add(@RequestBody DishDTO dishDTO){
        log.info("Chef adding dish");
        dishService.addNewDish(dishDTO);
        // clean redis data
        //cleanRedisCache();
        return Result.success();
    }

    /**
     * Chef Modify Dish
     * @param dishDTO
     * @return
     */
    @PutMapping("/modify")
    @ApiOperation(value = "chef update dish")
    public Result modify(@RequestBody DishDTO dishDTO){
        log.info("Chef modifying dish");
        dishService.updateDish(dishDTO);
        // clean redis data
        //cleanRedisCache();
        return Result.success();
    }

    /**
     * Chef Delete Dish
     * @param id
     * @return
     */
    @DeleteMapping("/remove")
    @ApiOperation(value = "chef remove dish")
    public Result remove(@RequestParam Long id){
        log.info("chef deleting dish");
        dishService.deleteById(id);
        // clean redis data
        //cleanRedisCache();
        return Result.success();
    }

    /**
     * Chef Enable Dish
     * @param id
     * @return
     */
    @PostMapping("/status/enable")
    @ApiOperation(value = "chef enable dish")
    public Result enableDish(Long id){
        log.info("chef enabling dish");
        dishService.enableDishById(id);
        // clean redis data
        //cleanRedisCache();
        return Result.success();
    }

    /**
     * Chef Disable Dish
     * @param id
     * @return
     */
    @PostMapping("/status/disable")
    @ApiOperation(value = "chef disable dish")
    public Result disableDish(Long id){
        log.info("chef disabling dish");
        dishService.disableDishById(id);
        // clean redis data
        //cleanRedisCache();
        return Result.success();
    }

    /**
     * Show All Dishes In a Page to Chef
     * @param dishPageDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation(value = "page of dish")
    public Result<PageResult> page(DishPageDTO dishPageDTO){
        log.info("Show dish in page");
        PageResult pageResult = dishService.pageQuery(dishPageDTO);
        return Result.success(pageResult);
    }

    @GetMapping("/show/{id}")
    @ApiOperation(value = "show dish by id")
    public Result<DishVO> showById(@PathVariable Long id){
        log.info("Show dish by id");
        DishVO dishVO = dishService.getDishById(id);
        return Result.success(dishVO);
    }

    /**
     * Clean Cache in Redis
     */
//    private void cleanRedisCache(){
//        String key = "dish_ChefId" + BaseContext.getCurrentId();
//        Set keys = redisTemplate.keys(key);
//        redisTemplate.delete(keys);
//    }
}
