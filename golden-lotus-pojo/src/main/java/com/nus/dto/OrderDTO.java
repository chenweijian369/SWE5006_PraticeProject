package com.nus.dto;

import com.nus.entity.Address;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author NIEWENYU
 * */
@Data
public class OrderDTO implements Serializable {

    private Long id;

    private Long chefId;

    private String address;

    //Store all dishes
    private List<DishDTO> dishDTOList;

    private Long addressId;

    private Double amount;

    private String checkoutTime;

    //TODO 不确定是否 是String 还是 User
    private String consignee;

    private Integer number;

    private LocalDateTime order_time;

    private String phone;

    private String remark;

    private Integer status;

    private Integer userId;

    private String username;

}
