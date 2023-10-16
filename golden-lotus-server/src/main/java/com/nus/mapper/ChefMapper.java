package com.nus.mapper;

import com.nus.entity.Chef;
import com.nus.entity.People;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChefMapper {
    @Select("select * from chef where username = #{username}")
    People getByUserName(String username);

    @Select("select * from chef")
    List<Chef> getAllChefs();
}
