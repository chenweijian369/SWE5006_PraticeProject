package com.nus.mapper;

import com.nus.entity.People;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChefMapper {
    People getByUserName(String username);
}
