package com.nus.mapper;

import com.nus.pojo.entity.Address;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AddressMapper {

    @Select("select * from address where user_id = #{userId}")
    List<Address> getByUserId(Long userId);

    @Insert("insert into address (user_id, consignee, sex, phone, postal_code, detail_location, label, is_default) " +
            "values (#{userId}, #{consignee}, #{sex}, #{phone}, #{postalCode}, #{detailLocation}, #{label}, #{isDefault})")
    void insert(Address address);

    void update(Address address);

    @Delete("delete from address where id = #{id}")
    void deleteById(Long id);

    @Update("update address set is_default = 0 where user_id = #{userId}")
    void updateDefaultByUserId(Long userId);

    @Update("update address set is_default = 1 where id = #{id}")
    void updateDefaultById(Long id);

    @Update("update address set label = #{label} where id = #{id}")
    void updateLabel(Address address);

    @Select("select * from address where id = #{id}")
    Address getById(Long id);
}
