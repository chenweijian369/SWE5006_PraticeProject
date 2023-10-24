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
 * @Description TODO 取消订单，
 * @Author @wrj
 * @Date 2023-10-21 15:21
 * @Version 1.0
 */

@Slf4j
@Api("Order Interface")
@RestController
@RequestMapping("/admin/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/conditionSearch")
    public Result<PageResult> conditionSearchOrders(OrdersPageQueryDTO ordersPageQueryDTO) {
        log.info("订单搜索");
        PageResult pageResult = orderService.conditionSearchOrders(ordersPageQueryDTO);
        return Result.success(pageResult);
    }

    @GetMapping("/searchByOrderId")
    public Result<OrderVO> searchOrderDetailByOrderId(Long id) {
        log.info("通过 id 搜索订单");
        OrderVO orderVO = orderService.checkOrderDetail(id);
        return Result.success(orderVO);
    }

//    @PostMapping("/deleteByOrderId")
//    public Result deleteOrderDetailsByOrderId(Long orderId){
//         log.info("通过 OrderId 删除 Order detail 信息");
//         return Result.success();
//    }

    @PostMapping("/cancelOrderById")
    public Result cancelOrderById(Long id){
        log.info("取消订单");
        orderService.cancelOrder(id);
        return Result.success();
        
    }

}
