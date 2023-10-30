package com.nus.controller;

import com.nus.pojo.vo.ChefVO;
import com.nus.result.Result;
import com.nus.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
@CrossOrigin
@Api(tags = "User Relevant Interface")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @ApiOperation(value = "user searches chef")
    private Result<List<ChefVO>> search(String dishName){
        log.info("User using search function");
        List<ChefVO> list = userService.getByDishName(dishName);
        return Result.success(list);
    }
}
