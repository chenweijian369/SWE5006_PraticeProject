package com.nus.mapper;

import com.nus.annotation.AutoFill;
import com.nus.entity.User;
import com.nus.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from user where username = #{username}")
    User getByUserName(String username);

    @Insert("insert into user (name, username, password, phone, sex, id_number, create_time)" +
            "values " +
            "(#{name}, #{username}, #{password}, #{phone}, #{sex}, #{idNumber}, #{createTime})")
    void insert(User user);
}
