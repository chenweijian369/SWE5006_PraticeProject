package com.nus.pojo.entity;

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

    private Long userId;

    private Long dishId;

    private Long chefId;

    private String dishName;

    private String dishImage;

    private String chefName;

    private String chefImage;

    private Integer number;

    private Double amount;

    private LocalDateTime createTime;
}
