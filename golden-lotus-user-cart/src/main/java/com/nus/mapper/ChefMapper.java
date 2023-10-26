package com.nus.mapper;

import com.nus.pojo.entity.Chef;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ChefMapper {
    @Select("select * from chef where id = #{id}")
    Chef getById(Long id);
}
