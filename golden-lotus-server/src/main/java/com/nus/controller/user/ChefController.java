package com.nus.controller.user;

import com.nus.result.Result;
import com.nus.service.ChefService;
import com.nus.vo.ChefVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/chef")
@Slf4j
@Api(tags = "Chef Relevant Interface")
public class ChefController {
    @Autowired
    private ChefService chefService;

    @GetMapping
    @ApiOperation(value = "filter chef by category id")
    private Result<List<ChefVO>> showByCategoryId(Long categoryId){
        log.info("User Using Filter");
        List<ChefVO> list = chefService.showAllChefsOfCategory(categoryId);
        return Result.success(list);
    }

}
