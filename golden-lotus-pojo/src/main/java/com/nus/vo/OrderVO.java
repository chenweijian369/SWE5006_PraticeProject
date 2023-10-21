package com.nus.vo;

/**
 * @ClassName OrderVO
 * @Description TODO
 * @Author @O_o
 * @Date 2023-10-21 15:27
 * @Version 1.0
 */
import com.nus.entity.OrderDetail;
import com.nus.entity.Orders;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderVO extends Orders implements Serializable {

    //订单菜品信息
    private String orderDishes;

    //订单详情
    private List<OrderDetail> orderDetailList;

}
