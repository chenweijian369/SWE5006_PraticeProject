package com.nus.entity;

import com.nus.dto.AddressDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    private Long id;

//    private AddressDTO address;

    private String address;

    private Long addressId;

    private Double amount;

    private String checkoutTime;

    //TODO 不确定是否 是String 还是 User
    private String consignee;

    private LocalDateTime deliveryTime;

    private Integer number;

    private LocalDateTime orderTime;

    private String phone;

    private String remark;

    private Integer status;

    private Integer userId;

    private String username;
}
