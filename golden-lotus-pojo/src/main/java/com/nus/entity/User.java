package com.nus.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Done by CHEN WEIJIAN
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends People {

    private LocalDateTime createTime;
}
