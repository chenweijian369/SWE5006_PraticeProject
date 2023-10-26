package com.nus.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddressDTO implements Serializable {

    private Long id;

    private String consignee;

    private String phone;

    private String sex;

    private String postalCode;

    private String detailLocation;

}
