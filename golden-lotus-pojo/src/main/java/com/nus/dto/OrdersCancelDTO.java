package com.nus.dto;

/**
 * @ClassName OrderCancelDTO
 * @Description TODO
 * @Author @O_o
 * @Date 2023-10-21 15:32
 * @Version 1.0
 */
import lombok.Data;

import java.io.Serializable;

@Data
public class OrdersCancelDTO implements Serializable {

    private Long id;
    //订单取消原因
    private String cancelReason;

}
