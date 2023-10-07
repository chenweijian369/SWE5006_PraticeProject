package com.nus.service.impl;

import com.nus.constant.LoginConstant;
import com.nus.constant.MessageConstant;
import com.nus.dto.AccountLoginDTO;
import com.nus.dto.UserDTO;
import com.nus.entity.People;
import com.nus.entity.User;
import com.nus.exception.AccountNotFoundException;
import com.nus.exception.PasswordErrorException;
import com.nus.mapper.AdminMapper;
import com.nus.mapper.ChefMapper;
import com.nus.mapper.UserMapper;
import com.nus.service.CommonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private ChefMapper chefMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * Login
     * Done by CHEN WEIJIAN
     *
     * @param accountLoginDTO
     * @return
     */
    @Override
    public People login(AccountLoginDTO accountLoginDTO) {
        // Get username and password
        String username = accountLoginDTO.getUsername();
        String password = accountLoginDTO.getPassword();
        String type = accountLoginDTO.getType();

        People people = null;

        if (type.equals(LoginConstant.ADMIN_LOGIN)){
            people = adminMapper.getByUserName(username);
        }

        if (type.equals(LoginConstant.USER_LOGIN)){
            people = userMapper.getByUserName(username);
        }

        if (type.equals(LoginConstant.CHEF_LOGIN)){
            people = chefMapper.getByUserName(username);
        }

        //if null, this user is not in database
        //if not null, the user is in database
        if (people == null){
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //check password
        if (!password.equals(people.getPassword())){
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        return people;
    }

    /**
     * User Register
     * Done by CHEN WEIJIAN
     * @param userDTO
     */
    @Override
    public void save(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        user.setCreateTime(LocalDateTime.now());
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));

        userMapper.insert(user);
    }
}
