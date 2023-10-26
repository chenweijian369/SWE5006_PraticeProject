package com.nus.controller.chef;

import com.nus.result.Result;
import com.nus.service.OrderService;
import com.nus.pojo.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("chefOrderController")
@RequestMapping("/chef/order")
@Slf4j
@Api(tags = "Order Relevant Interface")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @GetMapping("/orderDetail/{chefId}")
    @ApiOperation("check order details")
    public Result<List<OrderVO>> showOrderDetailsByChefId(@PathVariable("chefId") Long chefId) {
        log.info("show order details");
        List<OrderVO> list = orderService.getOrderDetailsByChefId(chefId);
        return Result.success(list);
    }

    @PutMapping("/complete/{id}")
    @ApiOperation("chef finish order")
    public Result finishOrder(@PathVariable("id") Long id){
        log.info("确认订单");
        orderService.completeOrder(id);
        return Result.success();
    }
}
