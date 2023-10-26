package com.nus.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ChefPageDTO implements Serializable {

    private String name;

    private int page;

    private int pageSize;

}
