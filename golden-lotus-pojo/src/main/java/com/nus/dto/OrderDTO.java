package com.nus.dto;

import com.nus.entity.Address;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/**
 * @author NIEWENYU
 * */
@Data
public class OrderDTO implements Serializable {

    private Long id;

    private AddressDTO address;

    private Long addressId;

    private double amount;

    private Date checkoutTime;


}
