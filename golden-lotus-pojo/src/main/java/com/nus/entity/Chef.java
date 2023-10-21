package com.nus.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chef extends People {
    private Long id;

    private String username;

    private String name;

    private String phone;

    private String sex;

    private String image;

    private String description;

    private Integer status;

    private Integer isOccupied;
    //idNumber是NRIC
    private String idNumber;

    private LocalDateTime updateTime;

    private Long createUser;

    private Long updateUser;
    private String password;
}
