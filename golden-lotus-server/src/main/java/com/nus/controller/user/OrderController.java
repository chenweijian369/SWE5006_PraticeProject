package com.nus.controller.user;

import com.nus.entity.Order;
import com.nus.result.Result;
import com.nus.service.OrderService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/order")
@Slf4j
@Api(tags = "Order Relevant interface")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/show")
    private Result<List<Order>> show(){
        log.info("Show all orders");
        List<Order> list = orderService.getUserOrder();
        return Result.success(list);
    }
}
