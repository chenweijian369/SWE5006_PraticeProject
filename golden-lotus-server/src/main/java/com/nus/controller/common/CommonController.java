package com.nus.controller.common;

import com.nus.dto.AccountLoginDTO;
import com.nus.dto.UserDTO;
import com.nus.entity.People;
import com.nus.properties.JwtProperties;
import com.nus.result.Result;
import com.nus.service.CommonService;
import com.nus.utils.JwtUtil;
import com.nus.vo.AccountLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/common")
@Api(tags = "Common Relevent Interface")
@Slf4j
/**
 * Common Service
 * Done by CHEN WEIJIAN
 */
public class CommonController {

    @Autowired
    private CommonService commonService;

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * Login
     * Done by CHEN WEIJIAN
     * @param accountLoginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "login")
    public Result<AccountLoginVO> login(@RequestBody AccountLoginDTO accountLoginDTO){
        log.info("Login Start: {}",accountLoginDTO);
        //login
        People people = commonService.login(accountLoginDTO);

        //if login successfully, generate JWT token
        Map<String, Object> claims = new HashMap<>();

        claims.put(accountLoginDTO.getType(), people.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getSecretKey(),
                jwtProperties.getTtl(),
                claims);

        AccountLoginVO accountLoginVO = AccountLoginVO.builder()
                .id(people.getId())
                .name(people.getName())
                .userName(people.getName())
                .token(token)
                .build();

        return Result.success(accountLoginVO);
    }

    /**
     * User Logout
     * Done by CHEN WEIJIAN
     */
    @PostMapping("/logout")
    @ApiOperation(value = "logout")
    public Result<String> logout(){
        log.info("User Logout...");
        return Result.success();
    }

    /**
     * User Register
     * Done by CHEN WEIJIAN
     * @param userDTO
     * @return
     */
    @PostMapping("/register")
    @ApiOperation(value = "register")
    public Result register(@RequestBody UserDTO userDTO){
        log.info("User Register: {}", userDTO);

        commonService.save(userDTO);

        return Result.success();
    }
}
