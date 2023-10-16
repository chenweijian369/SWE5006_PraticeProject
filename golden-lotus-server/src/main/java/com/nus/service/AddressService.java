package com.nus.service;

import com.nus.dto.AddressDTO;
import com.nus.entity.Address;

import java.util.List;

public interface AddressService {
    List<Address> showAllAddresses();

    void addNewAddress(AddressDTO addressDTO);

    void updateExistedAddress(AddressDTO addressDTO);

    void deleteById(Long id);

    void setDefaultAddress(AddressDTO addressDTO);

    void setLabelNameOfAddress(AddressDTO addressDTO, String label);
}
