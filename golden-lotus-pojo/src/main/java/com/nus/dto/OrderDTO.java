package com.nus.dto;

import com.nus.entity.Address;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/**
 * @author NIEWENYU
 * */
@Data
public class OrderDTO implements Serializable {

    private Long id;

    private AddressDTO address;

    private Long addressId;

    private Double amount;

    private String checkoutTime;

    //TODO 不确定是否 是String 还是 User
    private String consignee;

    private Integer number;

    private String order_time;

    private String phone;

    private String remark;

    private Integer status;

    private Integer userId;

    private String username;

}
