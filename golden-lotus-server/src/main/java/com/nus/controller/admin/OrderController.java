package com.nus.controller.admin;

import com.nus.dto.OrdersCancelDTO;
import com.nus.dto.OrdersConfirmDTO;
import com.nus.dto.OrdersPageQueryDTO;
import com.nus.dto.OrdersRejectionDTO;
import com.nus.result.PageResult;
import com.nus.result.Result;
import com.nus.service.OrderService;
import com.nus.vo.OrderStatisticsVO;
import com.nus.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName OrderController
 * @Description TODO
 * @Author @wrj
 * @Date 2023-10-21 15:21
 * @Version 1.0
 */

@Slf4j
@Api("订单管理接口")
@RestController
@RequestMapping("/admin/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/conditionSearch")
    public Result<PageResult> conditionSearchOrders(OrdersPageQueryDTO ordersPageQueryDTO){
        log.info("订单搜索");
        PageResult pageResult=orderService.conditionSearchOrders(ordersPageQueryDTO);
        return Result.success(pageResult);
    }

}
