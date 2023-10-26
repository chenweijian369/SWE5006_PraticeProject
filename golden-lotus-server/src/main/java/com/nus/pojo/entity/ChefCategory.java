package com.nus.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChefCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long chefId;

    private Long categoryId;
}
