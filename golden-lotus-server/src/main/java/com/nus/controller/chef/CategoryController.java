package com.nus.controller.chef;

import com.nus.result.Result;
import com.nus.service.CategoryService;
import com.nus.service.ChefService;
import com.nus.pojo.vo.CategoryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("chefCategoryController")
@RequestMapping("/chef/category")
@Slf4j
@Api(tags = "Category Relevant Interface")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ChefService chefService;

    /**
     * Show All Category to Chef
     * @return
     */
    @GetMapping
    @ApiOperation(value = "Show all categories in filter")
    public Result<List<CategoryVO>> showAll(){
        log.info("Show all filers");
        List<CategoryVO> list = categoryService.showAll();
        return Result.success(list);
    }

    /**
     * Chef Sets His Category
     * @param categoryId
     * @return
     */
    @PostMapping("/add")
    @ApiOperation(value = "chef set category of himself")
    public Result<String> classifyChef(Long categoryId){
        log.info("Chef classifying himself");
        chefService.classifyChefCategoryById(categoryId);
        return Result.success();
    }

    /**
     * Chef Deletes His Category
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    @ApiOperation(value = "chef delete category of himself")
    public Result<String> deleteClassification(Long id){
        log.info("Chef deleting classification of himself");
        chefService.deleteChefCategoryById(id);
        return Result.success();
    }
}
