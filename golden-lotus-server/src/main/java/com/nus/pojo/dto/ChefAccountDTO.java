package com.nus.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChefAccountDTO implements Serializable {

    private Long id;

    private String phone;

    private String description;

    private String image;

    private String password;
}
