package com.nus.controller.user;

import com.nus.pojo.dto.AddressDTO;
import com.nus.pojo.entity.Address;
import com.nus.result.Result;
import com.nus.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Done by CHEN WEIJIAN
 */
@RestController
@RequestMapping("/user/address")
@Slf4j
@Api(tags = "Address Relevant Interface")
public class AddressController {
    @Autowired
    private AddressService addressService;

    /**
     * show addresses
     * @return
     */
    @GetMapping("/show")
    @ApiOperation(value = "show all addresses")
    public Result<List<Address>> showAll(){
        log.info("Show all addresses to user");
        List<Address> list = addressService.showAllAddresses();
        return Result.success(list);
    }

    /**
     * add new address
     * @param addressDTO
     * @return
     */
    @PostMapping("/add")
    @ApiOperation(value = "insert new address")
    public Result insert(@RequestBody AddressDTO addressDTO){
        log.info("User adds new address");
        addressService.addNewAddress(addressDTO);
        return Result.success();
    }

    /**
     * modify address
     * @param addressDTO
     * @return
     */
    @PutMapping("/modify")
    @ApiOperation(value = "modify address")
    public Result update(@RequestBody AddressDTO addressDTO){
        log.info("User modifies existed address");
        addressService.updateExistedAddress(addressDTO);
        return Result.success();
    }

    /**
     * delete address
     * @param id
     * @return
     */
    @DeleteMapping("/remove")
    @ApiOperation(value = "remove address")
    public Result remove(Long id){
        log.info("User removes address");
        addressService.deleteById(id);
        return Result.success();
    }

    /**
     * set default address
     * @param addressDTO
     * @return
     */
    @PutMapping("/default")
    @ApiOperation(value = "set default address")
    public Result selectDefaultAddress(@RequestBody AddressDTO addressDTO){
        log.info("User selects an address as default");
        addressService.setDefaultAddress(addressDTO);
        return Result.success();
    }

    /**
     * set label
     * @param addressDTO
     * @param label
     * @return
     */
    @PutMapping("/label")
    @ApiOperation(value = "set label")
    public Result setLabelName(@RequestBody AddressDTO addressDTO, @RequestParam String label){
        log.info("User labels address");
        addressService.setLabelNameOfAddress(addressDTO, label);
        return Result.success();
    }

}
