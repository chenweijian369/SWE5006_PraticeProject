package com.nus.pojo.vo;

import com.nus.pojo.entity.Order;
import com.nus.pojo.entity.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderVO extends Order implements Serializable {

    private List<OrderDetail> orderDetailList;

}