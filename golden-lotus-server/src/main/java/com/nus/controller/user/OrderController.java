package com.nus.controller.user;

import com.nus.pojo.dto.OrderSubmitDTO;
import com.nus.result.Result;
import com.nus.service.OrderService;
import com.nus.pojo.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("userOrderController")
@RequestMapping("/user/order")
@Slf4j
@Api(tags = "Order Relevant interface")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * User Clears Cart
     * @param ordersSubmitDTO
     * @return
     */
    @PostMapping("/submit")
    @ApiOperation("User submits order")
    public Result submit(@RequestBody OrderSubmitDTO ordersSubmitDTO){
        log.info("user clears cart：{}",ordersSubmitDTO);
        orderService.submitOrder(ordersSubmitDTO);
        return Result.success();
    }

    /**
     * User Checks Order
     *
     * @param userId
     * @return
     */
    @GetMapping("/orderDetail/{userId}")
    @ApiOperation("check order details")
    public Result<List<OrderVO>> showOrderDetailsByUserId(@PathVariable("userId") Long userId) {
        log.info("show order details");
        List<OrderVO> list = orderService.getOrderDetailsByUserId(userId);
        return Result.success(list);
    }
}