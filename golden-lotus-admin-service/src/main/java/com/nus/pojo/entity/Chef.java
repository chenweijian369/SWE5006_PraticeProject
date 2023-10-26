package com.nus.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chef extends People {

    private String image;

    private String description;

    private Integer status;

    private Integer isOccupied;

    private LocalDateTime updateTime;

    private Long createUser;

    private Long updateUser;
}
