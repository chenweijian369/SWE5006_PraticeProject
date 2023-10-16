package com.nus.controller.user;

import com.nus.entity.Address;
import com.nus.entity.Chef;
import com.nus.entity.People;
import com.nus.result.Result;
import com.nus.service.ChefService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author NIEWENYU
 * @time 2023/10/16
 *
 * */

@RestController
@RequestMapping("/user/chef")
@Slf4j
@Api(tags = "Chefs Relevant Interface")
public class ChefController {
    @Autowired
    private ChefService chefService;

    @GetMapping("/show")
    @ApiOperation(value = "show all addresses")
    public Result<List<Chef>> showAllChefs(){
        log.info("Show all addresses to user");
        List<Chef> list = chefService.getAllChefs();
        return Result.success(list);
    }}
