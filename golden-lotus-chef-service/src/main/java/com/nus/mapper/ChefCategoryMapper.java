package com.nus.mapper;

import com.nus.pojo.entity.ChefCategory;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChefCategoryMapper {

    @Select("select * from chef_category where chef_id = #{chefId} and category_id = #{categoryId}")
    List<ChefCategory> getByChefIdAndCategoryId(Long chefId, Long categoryId);

    @Insert("insert into chef_category(chef_id, category_id) values (#{chefId}, #{categoryId})")
    void insert(ChefCategory chefCategory);

    @Delete("delete from chef_category where id = #{id}")
    void deleteByCategoryId(Long id);

}
