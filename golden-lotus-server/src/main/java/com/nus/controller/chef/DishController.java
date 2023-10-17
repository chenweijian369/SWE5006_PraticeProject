package com.nus.controller.chef;

import com.nus.dto.DishDTO;
import com.nus.dto.DishPageDTO;
import com.nus.result.PageResult;
import com.nus.result.Result;
import com.nus.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("chefDishController")
@RequestMapping("/chef/dish")
@Slf4j
@Api(tags = "Dish Relevant Interface")
public class DishController {

    @Autowired
    private DishService dishService;

    @PostMapping("/add")
    @ApiOperation(value = "chef add dish")
    public Result add(@RequestBody DishDTO dishDTO){
        log.info("Chef adding dish");
        dishService.addNewDish(dishDTO);
        return Result.success();
    }

    @PutMapping("/modify")
    @ApiOperation(value = "chef update dish")
    public Result modify(@RequestBody DishDTO dishDTO){
        log.info("Chef modifying dish");
        dishService.updateDish(dishDTO);
        return Result.success();
    }

    @DeleteMapping("/remove")
    @ApiOperation(value = "chef remove dish")
    public Result remove(@RequestParam Long id){
        log.info("chef deleting dish");
        dishService.deleteById(id);
        return Result.success();
    }

    @PostMapping("/status/enable")
    @ApiOperation(value = "chef enable dish")
    public Result enableDish(Long id){
        log.info("chef enabling dish");
        dishService.enableDishById(id);
        return Result.success();
    }

    @PostMapping("/status/disable")
    @ApiOperation(value = "chef disable dish")
    public Result disableDish(Long id){
        log.info("chef disabling dish");
        dishService.disableDishById(id);
        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation(value = "page of dish")
    public Result<PageResult> page(DishPageDTO dishPageDTO){
        log.info("Show dish in page");
        PageResult pageResult = dishService.pageQuery(dishPageDTO);
        return Result.success(pageResult);
    }
}
