package com.nus.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Done by CHEN WEIJIAN
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountLoginVO implements Serializable {

    private Long id;

    private String userName;

    private String name;

    // jwt token
    private String token;

}
