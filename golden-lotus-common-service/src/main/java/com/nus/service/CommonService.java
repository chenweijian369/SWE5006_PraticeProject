package com.nus.service;

import com.nus.pojo.dto.AccountLoginDTO;
import com.nus.pojo.entity.People;

public interface CommonService {
    People login(AccountLoginDTO accountLoginDTO);
}
