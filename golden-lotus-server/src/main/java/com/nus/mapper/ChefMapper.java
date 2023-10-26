package com.nus.mapper;

import com.github.pagehelper.Page;
import com.nus.pojo.dto.ChefPageDTO;
import com.nus.pojo.entity.Chef;
import com.nus.pojo.entity.People;
import com.nus.pojo.vo.ChefVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChefMapper {
    @Select("select * from chef where username = #{username}")
    People getByUserName(String username);

    @Select("select * from chef where id = #{id}")
    Chef getById(Long id);

    @Select("select c.id, c.name, c.sex, c.image, c.description from chef_category cc left join chef c " +
            "on cc.chef_id = c.id where category_id = #{categoryId} and status = 1")
    List<ChefVO> getByCategoryId(Long categoryId);

    @Insert("insert into chef " +
            "(name, username, password, phone, sex, id_number, image, description, status, is_occupied, create_time, update_time, create_user, update_user)"
            +"VALUES" +
            "(#{name}, #{username}, #{password}, #{phone}, #{sex}, #{idNumber}, #{image}, #{description}, #{status}, #{isOccupied}, #{createTime},#{updateTime},#{createUser}, #{updateUser})")
    void insert(Chef chef);

    void update(Chef chef);

    Page<Chef> pageQuery(ChefPageDTO chefPageDTO);

    @Select("select * from chef where status = 1")
    List<ChefVO> showAllEnableChefs();

}
