package com.nus.vo;

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
public class OrderVO implements Serializable {
    private Long id;

    private AddressDTO address;

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
