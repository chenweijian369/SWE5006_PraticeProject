//package com.nus.controller.admin;
//
//import com.nus.constant.JwtClaimsConstant;
//import com.nus.dto.EmployeeDTO;
//import com.nus.dto.EmployeeLoginDTO;
//import com.nus.dto.EmployeePageQueryDTO;
//import com.nus.entity.Employee;
//import com.nus.properties.JwtProperties;
//import com.nus.result.PageResult;
//import com.nus.result.Result;
//import com.nus.service.EmployeeService;
//import com.nus.utils.JwtUtil;
//import com.nus.vo.EmployeeLoginVO;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 员工管理
// */
//@RestController
//@RequestMapping("/admin/employee")
//@Slf4j
//public class EmployeeController {
//
//    @Autowired
//    private EmployeeService employeeService;
//    @Autowired
//    private JwtProperties jwtProperties;
//
//    /**
//     * 登录
//     *
//     * @param employeeLoginDTO
//     * @return
//     */
//    @PostMapping("/login")
//    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
//        log.info("员工登录：{}", employeeLoginDTO);
//
//        Employee employee = employeeService.login(employeeLoginDTO);
//
//        //登录成功后，生成jwt令牌
//        Map<String, Object> claims = new HashMap<>();
//        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
//        String token = JwtUtil.createJWT(
//                jwtProperties.getSecretKey(),
//                jwtProperties.getTtl(),
//                claims);
//
//        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
//                .id(employee.getId())
//                .userName(employee.getUsername())
//                .name(employee.getName())
//                .token(token)
//                .build();
//
//        return Result.success(employeeLoginVO);
//    }
//
//    /**
//     * 退出
//     *
//     * @return
//     */
//    @PostMapping("/logout")
//    public Result<String> logout() {
//        return Result.success();
//    }
//
//    @PostMapping
//    public Result save(@RequestBody EmployeeDTO employeeDTO){
//        log.info("新增员工:{}",employeeDTO);
//        employeeService.save(employeeDTO);
//        return Result.success();
//    }
//    @GetMapping("/page")
//    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO){
//        log.info("员工分页查询，参数为{}",employeePageQueryDTO);
//        PageResult pageResult = employeeService.pageQuery(employeePageQueryDTO);
//        return Result.success(pageResult);
//    }
//
//    @GetMapping("/{id}")
//    public Result<Employee> getByid(@PathVariable Long id){
//        Employee employee = employeeService.getByid(id);
//        return Result.success(employee);
//    }
//
//    @PutMapping
//    public Result update(@RequestBody EmployeeDTO employeeDTO){
//        log.info("编辑员工信息");
//        employeeService.update(employeeDTO);
//        return Result.success();
//    }
//}
