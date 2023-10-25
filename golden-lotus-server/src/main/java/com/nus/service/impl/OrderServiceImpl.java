package com.nus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.nus.constant.MessageConstant;
import com.nus.context.BaseContext;
import com.nus.dto.OrdersCancelDTO;
import com.nus.dto.OrdersConfirmDTO;
import com.nus.dto.OrdersPageQueryDTO;
import com.nus.dto.OrdersSubmitDTO;
import com.nus.entity.Address;
import com.nus.entity.OrderDetail;
import com.nus.entity.Orders;
import com.nus.entity.ShoppingCart;
import com.nus.exception.AddressBookBusinessException;
import com.nus.exception.ShoppingCartBusinessException;
import com.nus.mapper.AddressMapper;
import com.nus.mapper.OrderDetailMapper;
import com.nus.mapper.OrderMapper;
import com.nus.mapper.ShoppingCartMapper;
import com.nus.result.PageResult;
import com.nus.service.OrderService;
import com.nus.utils.WeChatPayUtil;
import com.nus.vo.OrderSubmitVO;
import com.nus.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

//    @Override
//    public OrderSubmitVO orderSubmit(OrdersSubmitDTO ordersSubmitDTO) {
//        Orders orders = new Orders();
//        BeanUtils.copyProperties(ordersSubmitDTO, orders);
//    }

    @Override
    public OrderSubmitVO orderSubmit(OrdersSubmitDTO ordersSubmitDTO) {

        Address address = addressMapper.getById(ordersSubmitDTO.getAddressBookId());
        if(address == null){
            throw new AddressBookBusinessException(MessageConstant.ADDRESS_BOOK_IS_NULL);
        }

        Long userId = BaseContext.getCurrentId();

        // If address exists, then query the information of shopping cart
       List<ShoppingCart> shoppingCartList = shoppingCartMapper.getByUserId(userId);
       if(shoppingCartList == null || shoppingCartList.size() == 0){
           throw new ShoppingCartBusinessException(MessageConstant.SHOPPING_CART_IS_NULL);
       }

       Orders orders = new Orders();
       BeanUtils.copyProperties(ordersSubmitDTO,orders);
       orders.setOrderTime(LocalDateTime.now());
       orders.setPayStatus(Orders.UN_PAID);
       orders.setStatus(Orders.PENDING_PAYMENT);
       orders.setAddress(address.getDetailLocation());
       orders.setPhone(address.getPhone());
       orders.setUserId(userId);
       orders.setConsignee(address.getConsignee());
       orders.setNumber(String.valueOf(System.currentTimeMillis()));

       orderMapper.insert(orders);

       List<OrderDetail> orderDetailList = new ArrayList<>();
       for (ShoppingCart cart:shoppingCartList){
           OrderDetail orderDetail = new OrderDetail();
           BeanUtils.copyProperties(cart,orderDetail);
           orderDetail.setOrderId(orders.getId());
           orderDetailList.add(orderDetail);
       }
       orderDetailMapper.insertDetailList(orderDetailList);
       shoppingCartMapper.deleteById(userId);
       OrderSubmitVO orderSubmitVO = OrderSubmitVO.builder()
               .id(orders.getId())
               .orderTime(orders.getOrderTime())
               .orderNumber(orders.getNumber())
               .orderAmount(orders.getAmount())
               .build();
       return orderSubmitVO;
    }

    @Override
    public OrderVO checkOrderDetail(Long id) {
        List<OrderDetail> orderDetails = orderDetailMapper.queryByOrderId(id);
        OrderVO orderVO = new OrderVO();
        orderVO.setOrderDetailList(orderDetails);
        String dishes = "";
        for (OrderDetail od : orderDetails) {
            dishes = od.getName() + "\n";
        }
        orderVO.setOrderDishes(dishes);
        return orderVO;
    }


    /**
     * @See: WeChatPayUtil
     * The wechat payment has not finished,
     * this function
     **/
    @Override
    public void cancelOrder(OrdersCancelDTO ordersCancelDTO) throws Exception {
        Orders ordersDB = orderMapper.getById(ordersCancelDTO.getId());
        Integer payStatus = ordersDB.getPayStatus();

        if (payStatus == 1) {
            // The customer has paid, and the payment should be refund
            String refund = WeChatPayUtil.refund(
                    ordersDB.getNumber(),
                    ordersDB.getNumber(),
                    new BigDecimal(0.01),
                    new BigDecimal(0.01));
            log.info("申请退款：{}", refund);
        }
        Orders orders = new Orders();
        orders.setId(ordersCancelDTO.getId());
        orders.setStatus(Orders.CANCELLED);
        orders.setCancelReason(ordersCancelDTO.getCancelReason());
        orders.setCancelTime(LocalDateTime.now());
        orderMapper.update(orders);
    }

    @Override
    public PageResult conditionSearchOrders(OrdersPageQueryDTO ordersPageQueryDTO) {
        PageHelper.startPage(ordersPageQueryDTO.getPage(), ordersPageQueryDTO.getPageSize());
        Page<Orders> page = orderMapper.queryConditionSearchOrders(ordersPageQueryDTO);
        List<OrderVO> orderDetails = GetOrderDetails(page);
        return new PageResult(page.getTotal(), orderDetails);
        //return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void confirm(OrdersConfirmDTO ordersConfirmDTO) {
        Long id = ordersConfirmDTO.getId();
        Integer status = ordersConfirmDTO.getStatus();
        orderMapper.confirmOrder(id, status);
    }

    private List<OrderVO> GetOrderDetails(Page<Orders> page) {
        List<OrderVO> orderVOList = new ArrayList<>();
        for (Orders orders : page) {
            OrderVO orderVO = new OrderVO();
            //List<OrderDetail> detailList = orderDetailMapper.queryByOrderId(orders.getId());
            BeanUtils.copyProperties(orders, orderVO);
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
