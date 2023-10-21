package com.nus.controller.chef;

import com.nus.dto.ChefAccountDTO;
import com.nus.dto.ChefDTO;
import com.nus.result.Result;
import com.nus.service.ChefService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("chefController")
@RequestMapping("/chef")
@Slf4j
@Api(tags = "Chef Relevant Controller")
public class ChefController {

    @Autowired
    private ChefService chefService;

    @PostMapping("/category/add")
    @ApiOperation(value = "chef set category of himself")
    public Result<String> classifyChef(Long categoryId){
        log.info("Chef classifying himself");
        chefService.classifyChefCategoryById(categoryId);
        return Result.success();
    }

    @DeleteMapping("/category/delete")
    @ApiOperation(value = "chef delete category of himself")
    public Result<String> deleteClassification(Long id){
        log.info("Chef deleting classification of himself");
        chefService.deleteChefCategoryById(id);
        return Result.success();
    }

    @PostMapping("/modify")
    @ApiOperation(value = "Chef modify information")
    public Result<String> modifyAccountInfo(ChefAccountDTO chefAccountDTO){
        log.info("Chef updating information");
        chefService.updateAccount(chefAccountDTO);
        return Result.success();
    }
}
