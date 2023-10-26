package com.nus.controller.user;

import com.nus.pojo.dto.UserDTO;
import com.nus.result.Result;
import com.nus.service.UserService;
import com.nus.pojo.vo.ChefVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
@Api(tags = "User Relevant Interface")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * User Register
     * Done by CHEN WEIJIAN
     * @param userDTO
     * @return
     */
    @PostMapping("/register")
    @ApiOperation(value = "register")
    public Result register(@RequestBody UserDTO userDTO){
        log.info("User Register: {}", userDTO);
        userService.save(userDTO);
        return Result.success();
    }

    @GetMapping
    @ApiOperation(value = "user searches chef")
    private Result<List<ChefVO>> search(String dishName){
        log.info("User using search function");
        List<ChefVO> list = userService.getByDishName(dishName);
        return Result.success(list);
    }
}
