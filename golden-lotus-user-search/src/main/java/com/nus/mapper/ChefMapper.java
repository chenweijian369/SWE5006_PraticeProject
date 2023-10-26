package com.nus.mapper;

import com.nus.pojo.entity.Chef;
import com.nus.pojo.vo.ChefVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChefMapper {

    @Select("select * from chef where id = #{id}")
    Chef getById(Long id);

    @Select("select c.id, c.name, c.sex, c.image, c.description from chef_category cc left join chef c " +
            "on cc.chef_id = c.id where category_id = #{categoryId} and status = 1")
    List<ChefVO> getByCategoryId(Long categoryId);


    @Select("select * from chef where status = 1")
    List<ChefVO> showAllEnableChefs();

}
