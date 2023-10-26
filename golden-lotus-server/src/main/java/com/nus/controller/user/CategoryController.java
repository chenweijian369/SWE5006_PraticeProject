package com.nus.controller.user;

import com.nus.result.Result;
import com.nus.service.CategoryService;
import com.nus.pojo.vo.CategoryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/category")
@Slf4j
@Api(tags = "Category Relevant Interface")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * Show All Categories to User
     * @return
     */
    @GetMapping("/show")
    @ApiOperation(value = "Show all categories in filter")
    public Result<List<CategoryVO>> showAll(){
        log.info("Show all filers");
        List<CategoryVO> list = categoryService.showAll();
        return Result.success(list);
    }

}
