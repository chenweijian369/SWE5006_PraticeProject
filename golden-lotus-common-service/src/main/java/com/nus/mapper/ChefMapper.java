package com.nus.mapper;

import com.nus.pojo.entity.People;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ChefMapper {
    @Select("select * from chef where username = #{username}")
    People getByUserName(String username);

}
