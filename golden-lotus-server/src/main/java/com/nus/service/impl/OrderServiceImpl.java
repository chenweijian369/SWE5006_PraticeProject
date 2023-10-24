package com.nus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.nus.dto.OrdersPageQueryDTO;
import com.nus.dto.OrdersSubmitDTO;
import com.nus.entity.OrderDetail;
import com.nus.entity.Orders;
import com.nus.mapper.OrderDetailMapper;
import com.nus.mapper.OrderMapper;
import com.nus.result.PageResult;
import com.nus.service.OrderService;
import com.nus.vo.OrderSubmitVO;
import com.nus.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName OrderServiceImpl
 * @Description TODO
 * @Author @O_o
 * @Date 2023-10-21 15:46
 * @Version 1.0
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;


    @Override
    public OrderVO checkOrderDetail(Long id) {
        List<OrderDetail> orderDetails = orderDetailMapper.queryByOrderId(id);
        OrderVO orderVO = new OrderVO();
        orderVO.setOrderDetailList(orderDetails);
        String dishes = "";
        for (OrderDetail od: orderDetails){
            dishes = od.getName() + "\n";
        }
        orderVO.setOrderDishes(dishes);
        return orderVO;
    }

    @Override
    public void cancelOrder(Long id){
        orderMapper.cancelOrder(id);
        orderDetailMapper.cancelOrder(id);
    }

    @Override
    public PageResult conditionSearchOrders(OrdersPageQueryDTO ordersPageQueryDTO) {
        PageHelper.startPage(ordersPageQueryDTO.getPage(),ordersPageQueryDTO.getPageSize());
        Page<Orders> page=orderMapper.queryConditionSearchOrders(ordersPageQueryDTO);
        List<OrderVO> orderDetails= GetOrderDetails(page);
        return new PageResult(page.getTotal(),orderDetails);
        //return new PageResult(page.getTotal(),page.getResult());
    }
    private List<OrderVO> GetOrderDetails(Page<Orders> page){
        List<OrderVO> orderVOList=new ArrayList<>();
        for (Orders orders : page) {
            OrderVO orderVO = new OrderVO();
            //List<OrderDetail> detailList = orderDetailMapper.queryByOrderId(orders.getId());
            BeanUtils.copyProperties(orders,orderVO);
            //orderVO.setOrderDetailList(detailList);
            String orderDishes = getOrderDishesStr(orders);
            orderVO.setOrderDishes(orderDishes);
            orderVOList.add(orderVO);
        }
        return orderVOList;
    }
    private String getOrderDishesStr(Orders orders) {
        // 查询订单菜品详情信息（订单中的菜品和数量）
        List<OrderDetail> orderDetailList = orderDetailMapper.queryByOrderId(orders.getId());
        // 将每一条订单菜品信息拼接为字符串（格式：宫保鸡丁*3；）
        List<String> orderDishList = orderDetailList.stream().map(x -> {
            String orderDish = x.getName() + "*" + x.getNumber() + ";";
            return orderDish;
        }).collect(Collectors.toList());
        // 将该订单对应的所有菜品信息拼接在一起
        return String.join("", orderDishList);
    }



}
