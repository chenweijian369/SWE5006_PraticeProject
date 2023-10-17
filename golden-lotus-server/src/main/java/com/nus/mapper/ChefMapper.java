package com.nus.mapper;

import com.nus.entity.Chef;
import com.nus.entity.People;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ChefMapper {
    @Select("select * from chef where username = #{username}")
    People getByUserName(String username);

    @Select("select * from chef where id = #{id}")
    Chef getById(Long id);
}
