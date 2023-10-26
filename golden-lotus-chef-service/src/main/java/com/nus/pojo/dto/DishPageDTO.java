package com.nus.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DishPageDTO implements Serializable {

    private int page;

    private Long chefId;

    private int pageSize;

    private String name;

    private Integer status;

}
