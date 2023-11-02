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
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AddressServiceImplTest {

    @Mock
    private AddressMapper addressMapper;

    @InjectMocks
    private AddressService addressService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testShowAllAddress(){

        // Create a test user ID
        Long userId = 2L;
        BaseContext.setCurrentId(userId);

        // Create a list of addresses to return from the mock
        List<Address> mockAddressList = new ArrayList<>();
        when(addressMapper.getByUserId(userId)).thenReturn(mockAddressList);

        // Call the service method
        List<Address> result = addressService.showAllAddresses();

        // Verify that the service method returned the expected list
        assertSame(mockAddressList, result, "Return Wrong Address");

        // Verify that the getByUserId method was called with the expected user ID
        verify(addressMapper).getByUserId(userId);

    }

    @Test
    public void testAddNewAddress() {
        // Arrange
        AddressDTO addressDTO = new AddressDTO(); // Initialize your DTO with data
        when(BaseContext.getCurrentId()).thenReturn(1L);

        // Act
        addressService.addNewAddress(addressDTO);

        // Assert
        // Verify that the 'insert' method of 'addressMapper' is called with the expected 'Address' object.
        verify(addressMapper, times(1)).insert(any(Address.class));
    }

    // Test for updateExistedAddress
    @Test
    public void testUpdateExistedAddress() {
        // Arrange
        AddressDTO addressDTO = new AddressDTO(); // Initialize your DTO with data

        // Act
        addressService.updateExistedAddress(addressDTO);

        // Assert
        // Verify that the 'update' method of 'addressMapper' is called with the expected 'Address' object.
        verify(addressMapper, times(1)).update(any(Address.class));
    }

    // Test for deleteById
    @Test
    public void testDeleteById() {
        // Arrange
        Long addressId = 1L;

        // Act
        addressService.deleteById(addressId);

        // Assert
        // Verify that the 'deleteById' method of 'addressMapper' is called with the expected 'addressId'.
        verify(addressMapper, times(1)).deleteById(addressId);
    }

    // Test for setDefaultAddress
    @Test
    public void testSetDefaultAddress() {
        // Arrange
        AddressDTO addressDTO = new AddressDTO(); // Initialize your DTO with data
        when(BaseContext.getCurrentId()).thenReturn(1L);

        // Act
        addressService.setDefaultAddress(addressDTO);

        // Assert
        // Verify that the 'updateDefaultByUserId' method and 'updateDefaultById' method of 'addressMapper' are called.
        verify(addressMapper, times(1)).updateDefaultByUserId(1L);
        verify(addressMapper, times(1)).updateDefaultById(addressDTO.getId());
    }

    // Test for setLabelNameOfAddress
    @Test
    public void testSetLabelNameOfAddress() {
        // Arrange
        AddressDTO addressDTO = new AddressDTO(); // Initialize your DTO with data
        String label = "NewLabel";

        // Act
        addressService.setLabelNameOfAddress(addressDTO, label);

        // Assert
        // Verify that the 'updateLabel' method of 'addressMapper' is called with the expected 'Address' object.
        verify(addressMapper, times(1)).updateLabel(any(Address.class));
    }

}
