package com.nus.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Done by CHEN WEIJIAN
 */
@Data
public class AccountLoginDTO implements Serializable {

    private String username;

    private String password;

    private String type;
}
