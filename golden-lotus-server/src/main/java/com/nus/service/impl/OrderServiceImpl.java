package com.nus.service.impl;

import com.nus.constant.MessageConstant;
import com.nus.constant.OrderStatus;
import com.nus.dto.OrderSubmitDTO;
import com.nus.entity.Address;
import com.nus.entity.Order;
import com.nus.entity.OrderDetail;
import com.nus.entity.ShoppingCart;
import com.nus.exception.AddressBookBusinessException;
import com.nus.exception.ShoppingCartBusinessException;
import com.nus.mapper.AddressMapper;
import com.nus.mapper.OrderDetailMapper;
import com.nus.mapper.OrderMapper;
import com.nus.mapper.ShoppingCartMapper;
import com.nus.service.OrderService;
import com.nus.vo.OrderVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    @Transactional
    public void submitOrder(OrderSubmitDTO ordersSubmitDTO) {

        // check if address is existed
        Address address = addressMapper.getById(ordersSubmitDTO.getAddressId());

        if (address == null) {
            throw new AddressBookBusinessException(MessageConstant.ADDRESS_BOOK_IS_NULL);
        }

        Long userId = 1L;

        // If address exists, then query the information of shopping cart
        List<ShoppingCart> shoppingCartList = shoppingCartMapper.getByUserId(userId);
        if (shoppingCartList == null || shoppingCartList.isEmpty()) {
            throw new ShoppingCartBusinessException(MessageConstant.SHOPPING_CART_IS_NULL);
        }

        Order order = new Order();
        BeanUtils.copyProperties(ordersSubmitDTO, order);

        order.setUserId(userId);
        order.setNumber(String.valueOf(System.currentTimeMillis()));
        order.setStatus(OrderStatus.UNFINISHED);
        order.setAddress(address.getDetailLocation());
        order.setCreateTime(LocalDateTime.now());
        order.setPhone(address.getPhone());
        order.setConsignee(address.getConsignee());

        orderMapper.insert(order);

        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (ShoppingCart cart : shoppingCartList) {
            OrderDetail orderDetail = new OrderDetail();
            BeanUtils.copyProperties(cart, orderDetail);
            orderDetail.setOrderId(order.getId());
            orderDetailList.add(orderDetail);
        }

        orderDetailMapper.insertBatch(orderDetailList);
        shoppingCartMapper.deleteByUserId(userId);
    }

    @Override
    public List<OrderVO> getOrderDetails(Long userId) {
        List<Order> orderList = orderMapper.getByUserId(userId);
        List<OrderVO> orderVOList = new ArrayList<>();
        for (Order order : orderList) {
            List<OrderDetail> orderDetails = orderDetailMapper.getByOrderId(order.getId());
            OrderVO orderVO = new OrderVO();
            BeanUtils.copyProperties(order, orderVO);
            orderVO.setOrderDetailList(orderDetails);
            orderVOList.add(orderVO);
        }
        return orderVOList;
    }
}