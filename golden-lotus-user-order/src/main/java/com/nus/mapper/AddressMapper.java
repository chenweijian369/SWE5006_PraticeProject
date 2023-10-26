package com.nus.mapper;

import com.nus.pojo.entity.Address;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AddressMapper {

    @Select("select * from address where id = #{id}")
    Address getById(Long id);
}
