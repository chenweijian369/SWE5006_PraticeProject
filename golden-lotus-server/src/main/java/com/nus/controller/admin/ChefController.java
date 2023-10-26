package com.nus.controller.admin;

import com.nus.pojo.dto.ChefDTO;
import com.nus.pojo.dto.ChefPageDTO;
import com.nus.pojo.entity.Chef;
import com.nus.result.PageResult;
import com.nus.result.Result;
import com.nus.service.ChefService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("adminChefController")
@RequestMapping("/admin/employee")
@Slf4j
@Api(tags = "Chef Relevant Interface")
public class ChefController {

    @Autowired
    private ChefService chefService;

    /**
     * Add New Chef
     * @param chefDTO
     * @return
     */
    @PostMapping
    @ApiOperation("add new chef")
    public Result save(@RequestBody ChefDTO chefDTO){
        log.info("add new chef:{}", chefDTO);
        chefService.save(chefDTO);
        return Result.success();
    }

    /**
     * Update Chef
     * @param chefDTO
     * @return
     */
    @PutMapping
    @ApiOperation("modify chef information")
    public Result<Chef> update(@RequestBody ChefDTO chefDTO){
        log.info("modify chef{}", chefDTO);
        chefService.update(chefDTO);
        return Result.success();
    }

    /**
     * Show All Chefs in Page
     * @param chefPageDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("show all chefs in page")
    public Result<PageResult> page(ChefPageDTO chefPageDTO){
        log.info("Page Query：{}", chefPageDTO);
        PageResult pageResult = chefService.page(chefPageDTO);
        return Result.success(pageResult);
    }

    /**
     * Enable or Disable Chef's Account
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("enable or disable chef's account")
    public Result<String>startOrStop(@PathVariable Integer status,Long id){
        log.info("Enable or Disable Chef's Account：{},{}",status,id);
        chefService.startOrStop(status,id);
        return Result.success();
    }

    /**
     * Show Chefs by ID
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("show chefs by id")
    public Result<Chef> getById(@PathVariable Long id){
        Chef chef = chefService.getById(id);
        return Result.success(chef);
    }
}