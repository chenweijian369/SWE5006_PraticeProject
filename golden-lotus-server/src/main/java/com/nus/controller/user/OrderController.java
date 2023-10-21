package com.nus.controller.user;

import com.nus.dto.OrderDTO;
import com.nus.entity.Order;
import com.nus.result.Result;
import com.nus.service.OrderService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/order")
@Slf4j
@Api(tags = "Order Relevant interface")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/show")
    private Result<List<Order>> show() {
        log.info("Show all orders");
        List<Order> list = orderService.getUserOrder();
        return Result.success(list);
    }

    @GetMapping("/showByUser")
    private Result<List<Order>> showByUsername(String userName) {
        log.info("Show all order by searching user");
        List<Order> list = orderService.getOrdersByUsername(userName);
        return Result.success(list);
    }

    @PostMapping("/place")
    private Result placeOrder(@RequestBody OrderDTO orderDTO){
        log.info("User placed an order");
        orderService.placeOrder(orderDTO);
        return Result.success();

    }

}

