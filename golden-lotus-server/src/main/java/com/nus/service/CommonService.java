package com.nus.service;

import com.nus.dto.AccountLoginDTO;
import com.nus.dto.UserDTO;
import com.nus.entity.People;

public interface CommonService {
    People login(AccountLoginDTO accountLoginDTO);

    void save(UserDTO userDTO);
}
