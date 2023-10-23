package com.nus.service;


import com.nus.dto.*;
import com.nus.result.PageResult;
import com.nus.vo.OrderPaymentVO;
import com.nus.vo.OrderStatisticsVO;
import com.nus.vo.OrderSubmitVO;
import com.nus.vo.OrderVO;

/**
 * @Date: 23.10.23
 * @See: OrderController
 * */

public interface OrderService {
//    OrderSubmitVO orderSubmit(OrdersSubmitDTO ordersSubmitDTO);
//
//    OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO) throws Exception;


//    void paySuccess(String outTradeNo);

//    PageResult checkHistoryOrders(OrdersPageQueryDTO ordersPageQueryDTO);

//    OrderVO checkOrderDetail(Long id);

    void cancelOrder(Long id);
//
//    void orderAgain(Long id);

    PageResult conditionSearchOrders(OrdersPageQueryDTO ordersPageQueryDTO);
//
//    void cancelOrderBackend(OrdersCancelDTO ordersCancelDTO);
//
//    void rejectionOrders(OrdersRejectionDTO ordersRejectionDTO) throws Exception;
//
//    void confirm(OrdersConfirmDTO ordersConfirmDTO);
//
//    void delivery(Long id);
//
//    void complete(Long id);
//
//    OrderStatisticsVO queryOrderStatistics();
//
//    void reminder(Long id);
}
