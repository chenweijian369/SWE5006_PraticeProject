package com.nus.service.impl;

import com.nus.context.BaseContext;
import com.nus.pojo.dto.AddressDTO;
import com.nus.pojo.entity.Address;
import com.nus.mapper.AddressMapper;
import com.nus.service.AddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public List<Address> showAllAddresses() {
        return addressMapper.getByUserId(BaseContext.getCurrentId());
    }

    @Override
    public void addNewAddress(AddressDTO addressDTO) {
        Address address = new Address();
        BeanUtils.copyProperties(addressDTO, address);
        address.setUserId(BaseContext.getCurrentId());
        address.setIsDefault(0);
        addressMapper.insert(address);
    }

    @Override
    public void updateExistedAddress(AddressDTO addressDTO) {
        Address address = copyData(addressDTO);
        addressMapper.update(address);
    }

    @Override
    public void deleteById(Long id) {
        addressMapper.deleteById(id);
    }

    @Override
    public void setDefaultAddress(AddressDTO addressDTO) {
        // set all user's address as non-default
        addressMapper.updateDefaultByUserId(BaseContext.getCurrentId());


        // then set current address as default
        addressMapper.updateDefaultById(addressDTO.getId());
    }

    @Override
    public void setLabelNameOfAddress(AddressDTO addressDTO, String label) {
        Address address = copyData(addressDTO);
        address.setLabel(label);
        addressMapper.updateLabel(address);
    }

    public Address copyData(AddressDTO addressDTO){
        Address address = new Address();
        BeanUtils.copyProperties(addressDTO, address);
        return address;
    }
}
