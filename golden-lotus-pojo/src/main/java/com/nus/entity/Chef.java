package com.nus.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chef extends People {

    private Integer status;

    private Integer categoryId;

    private Integer isOccupied;

    private LocalDateTime updateTime;

    private Long createUser;

    private Long updateUser;
}
