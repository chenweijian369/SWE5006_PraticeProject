package com.nus.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String dishName;

    private String dishImage;

    private String chefName;

    private String ChefImage;

    private Long orderId;

    private Long dishId;

    private Long chefId;

    private Integer number;

    private Double amount;
}