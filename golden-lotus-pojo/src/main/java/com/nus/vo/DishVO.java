package com.nus.vo;

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
public class DishVO implements Serializable {

    private Long id;

    private String name;

    private Long categoryId;

    private Long chefId;

    private Double price;

    private String image;

    private String description;

    private Integer status;

}
