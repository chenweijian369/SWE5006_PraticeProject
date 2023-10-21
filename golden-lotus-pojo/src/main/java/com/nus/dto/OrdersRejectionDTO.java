package com.nus.dto;

/**
 * @ClassName OrdersRejectionDTO
 * @Description TODO
 * @Author @O_o
 * @Date 2023-10-21 15:33
 * @Version 1.0
 */
import lombok.Data;

import java.io.Serializable;

@Data
public class OrdersRejectionDTO implements Serializable {

    private Long id;

    //订单拒绝原因
    private String rejectionReason;

}
