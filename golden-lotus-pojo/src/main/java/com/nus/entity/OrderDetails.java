package com.nus.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderDetails implements Serializable {
    private Long id;

    private Double amount;

    private Long chefId;

    private Long dishId;

    private String image;

    private String name;

    private Integer number;

    private Long orderId;
}
