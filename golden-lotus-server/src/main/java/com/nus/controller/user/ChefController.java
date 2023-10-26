package com.nus.controller.user;

import com.nus.result.Result;
import com.nus.service.ChefService;
import com.nus.pojo.vo.ChefVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("userChefController")
@RequestMapping("/user/chef")
@Slf4j
@Api(tags = "Chef Relevant Interface")
public class ChefController {
    @Autowired
    private ChefService chefService;

    @GetMapping("/{categoryId}")
    @ApiOperation(value = "filter chef by category id")
    public Result<List<ChefVO>> showByCategoryId(@PathVariable Long categoryId){
        log.info("User Using Filter");
        List<ChefVO> list = chefService.showAllChefsOfCategory(categoryId);
        return Result.success(list);
    }


    @GetMapping
    @ApiOperation(value = "show all chefs")
    public Result<List<ChefVO>> showAll(){
        log.info("Show all chefs to user");
        List<ChefVO> list = chefService.showAll();
        return Result.success(list);
    }
}
