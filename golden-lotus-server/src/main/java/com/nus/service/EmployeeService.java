package com.nus.service;

import com.nus.dto.EmployeeDTO;
import com.nus.dto.EmployeeLoginDTO;
import com.nus.dto.EmployeePageQueryDTO;
import com.nus.entity.Employee;
import com.nus.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    void save(EmployeeDTO employeeDTO);

    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    void startOrStop(Integer status, Long id);

    Employee getByid(Long id);

    void update(EmployeeDTO employeeDTO);
}
