package com.nus.service.impl;

import com.nus.context.BaseContext;
import com.nus.mapper.AddressMapper;
import com.nus.pojo.dto.AddressDTO;
import com.nus.pojo.entity.Address;
import com.nus.service.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class AddressServiceImplTest {

    @Mock
    private AddressMapper addressMapper;

    @InjectMocks
    private AddressService addressService = new AddressServiceImpl();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testShowAllAddress(){

        Long userId = 2L;
        BaseContext.setCurrentId(userId);

        List<Address> mockAddressList = new ArrayList<>();
        when(addressMapper.getByUserId(userId)).thenReturn(mockAddressList);

        List<Address> result = addressService.showAllAddresses();

        assertSame(mockAddressList, result, "Return Wrong Address");

        verify(addressMapper).getByUserId(userId);

    }

    @Test
    public void testAddNewAddress() {
        AddressDTO addressDTO = new AddressDTO();

        addressService.addNewAddress(addressDTO);

        verify(addressMapper, times(1)).insert(any(Address.class));
    }

    // Test for updateExistedAddress
    @Test
    public void testUpdateExistedAddress() {
        AddressDTO addressDTO = new AddressDTO();

        addressService.updateExistedAddress(addressDTO);

        verify(addressMapper, times(1)).update(any(Address.class));
    }

    // Test for deleteById
    @Test
    public void testDeleteById() {
        Long addressId = 1L;

        addressService.deleteById(addressId);

        verify(addressMapper, times(1)).deleteById(addressId);
    }

    // Test for setDefaultAddress
    @Test
    public void testSetDefaultAddress() {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(123L); // 设置有效的地址ID

        // 模拟 addressMapper 的 updateDefaultByUserId 方法
        doNothing().when(addressMapper).updateDefaultByUserId(anyLong());

        // 执行 setDefaultAddress 方法
        addressService.setDefaultAddress(addressDTO);

        // 验证 updateDefaultByUserId 方法被调用一次，并且传递了正确的参数
        verify(addressMapper, times(1)).updateDefaultByUserId(BaseContext.getCurrentId());

        // 验证 updateDefaultById 方法被调用一次，并且传递了正确的参数
        verify(addressMapper, times(1)).updateDefaultById(addressDTO.getId());
    }

    // Test for setLabelNameOfAddress
    @Test
    public void testSetLabelNameOfAddress() {
        AddressDTO addressDTO = new AddressDTO();
        String label = "NewLabel";

        addressService.setLabelNameOfAddress(addressDTO, label);

        verify(addressMapper, times(1)).updateLabel(any(Address.class));
    }
}
