package com.nus.mapper;

import com.github.pagehelper.Page;
import com.nus.dto.EmployeePageQueryDTO;
import com.nus.entity.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);
    /**
     * 插入员工数据
     * @param employee
     * @return
     */
    @Insert("insert into chef"+"(name, username, password, phone, sex, id_number, status, create_time, update_time, create_user, update_user,image)" +"VALUES" +
            "(#{name}, #{username}, #{password}, #{phone}, #{sex}, #{idNumber}, #{status}, #{createTime},#{updateTime},#{createUser}, #{updateUser},#{image})")
    void insert(Employee employee);
    /**
     * 分页查询
     * @param employeePageQueryDTO
     */
    Page<Employee> pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    void update(Employee employee);

    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    @Select("select * from chef where id = #{id}")
    Employee getById(Long id);
}
