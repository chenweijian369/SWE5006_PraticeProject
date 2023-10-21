package com.nus.dto;

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

    private String NRIC;

    private String image;

}
