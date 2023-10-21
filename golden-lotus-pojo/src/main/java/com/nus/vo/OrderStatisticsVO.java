package com.nus.vo;

/**
 * @ClassName OrderStatisticsVO
 * @Description TODO
 * @Author @O_o
 * @Date 2023-10-21 15:35
 * @Version 1.0
 */
import lombok.Data;
import java.io.Serializable;

@Data
public class OrderStatisticsVO implements Serializable {
    //待接单数量
    private Integer toBeConfirmed;

    //待派送数量
    private Integer confirmed;

    //派送中数量
    private Integer deliveryInProgress;
}
