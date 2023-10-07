package com.nus.controller.admin;

import com.nus.constant.JwtClaimsConstant;
import com.nus.dto.AccountLoginDTO;
import com.nus.entity.Admin;
import com.nus.properties.JwtProperties;
import com.nus.result.Result;
import com.nus.service.CommonService;
import com.nus.utils.JwtUtil;
import com.nus.vo.AccountLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@Api(tags = "Admin Relevent Interface")

/**
 * Done by CHEN WEIJIAN
 */
public class adminController {

}
