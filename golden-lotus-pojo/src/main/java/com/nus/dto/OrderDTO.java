package com.nus.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderDTO implements Serializable {

    // id
    private Long id;

    //订单号
    private Long number;

}
