package com.nus.mapper;

import com.github.pagehelper.Page;
import com.nus.pojo.dto.ChefPageDTO;
import com.nus.pojo.entity.Chef;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ChefMapper {

    @Select("select * from chef where id = #{id}")
    Chef getById(Long id);

    @Insert("insert into chef " +
            "(name, username, password, phone, sex, id_number, image, description, status, is_occupied, create_time, update_time, create_user, update_user)"
            +"VALUES" +
            "(#{name}, #{username}, #{password}, #{phone}, #{sex}, #{idNumber}, #{image}, #{description}, #{status}, #{isOccupied}, #{createTime},#{updateTime},#{createUser}, #{updateUser})")
    void insert(Chef chef);

    void update(Chef chef);

    Page<Chef> pageQuery(ChefPageDTO chefPageDTO);

}
