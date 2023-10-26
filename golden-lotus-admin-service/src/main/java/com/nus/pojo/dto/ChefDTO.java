package com.nus.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ChefDTO implements Serializable {

    private Long id;

    private String username;

    private String name;

    private String phone;

    private String sex;

    private String description;

    private String idNumber;

    private String image;

}
