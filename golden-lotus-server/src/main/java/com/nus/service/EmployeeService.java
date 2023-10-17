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

    /**
     * 分页查询
     * @param employeePageQueryDTO
     * @return
     */
    PageResult page(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 启用禁用
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    Employee getById(Long id);

    /**
     * 更新厨师信息
     * @param employeeDTO
     */
    void update(EmployeeDTO employeeDTO);
}
