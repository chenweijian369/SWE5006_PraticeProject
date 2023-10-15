package com.nus.entity;

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
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String image;

    private Long userId;

    private Long dishId;

    private Long chefId;

    private Integer number;

    private Double amount;

    private LocalDateTime createTime;
}
