package com.nus.service.impl;

import com.nus.constant.LoginConstant;
import com.nus.exception.AccountNotFoundException;
import com.nus.exception.PasswordErrorException;
import com.nus.mapper.AdminMapper;
import com.nus.mapper.ChefMapper;
import com.nus.mapper.UserMapper;
import com.nus.pojo.dto.AccountLoginDTO;
import com.nus.pojo.entity.Admin;
import com.nus.pojo.entity.Chef;
import com.nus.pojo.entity.People;
import com.nus.pojo.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.util.DigestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class CommonServiceImplTest {

    @InjectMocks
    private CommonServiceImpl commonService;

    @Mock
    private AdminMapper adminMapper;

    @Mock
    private ChefMapper chefMapper;

    @Mock
    private UserMapper userMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLoginAdmin() {
        AccountLoginDTO accountLoginDTO = new AccountLoginDTO();
        accountLoginDTO.setUsername("adminUser");
        accountLoginDTO.setPassword("adminPassword");
        accountLoginDTO.setType(LoginConstant.ADMIN_LOGIN);

        Admin admin = new Admin();
        admin.setUsername("adminUser");
        admin.setPassword(DigestUtils.md5DigestAsHex("adminPassword".getBytes()));

        when(adminMapper.getByUserName(accountLoginDTO.getUsername())).thenReturn(admin);

        People people = commonService.login(accountLoginDTO);

        assertNotNull(people);
        assertEquals(admin.getUsername(), people.getUsername());
    }

    @Test
    public void testLoginUser() {
        AccountLoginDTO accountLoginDTO = new AccountLoginDTO();
        accountLoginDTO.setUsername("userUser");
        accountLoginDTO.setPassword("userPassword");
        accountLoginDTO.setType(LoginConstant.USER_LOGIN);

        User user = new User();
        user.setUsername("userUser");
        user.setPassword(DigestUtils.md5DigestAsHex("userPassword".getBytes()));

        when(userMapper.getByUserName(accountLoginDTO.getUsername())).thenReturn(user);

        People people = commonService.login(accountLoginDTO);

        assertNotNull(people);
        assertEquals(user.getUsername(), people.getUsername());
    }

    @Test
    public void testLoginChef() {
        AccountLoginDTO accountLoginDTO = new AccountLoginDTO();
        accountLoginDTO.setUsername("chefUser");
        accountLoginDTO.setPassword("chefPassword");
        accountLoginDTO.setType(LoginConstant.CHEF_LOGIN);

        Chef chef = new Chef();
        chef.setUsername("chefUser");
        chef.setPassword(DigestUtils.md5DigestAsHex("chefPassword".getBytes()));

        when(chefMapper.getByUserName(accountLoginDTO.getUsername())).thenReturn(chef);

        People people = commonService.login(accountLoginDTO);

        assertNotNull(people);
        assertEquals(chef.getUsername(), people.getUsername());
    }

    @Test
    public void testLoginAccountNotFound() {
        AccountLoginDTO accountLoginDTO = new AccountLoginDTO();
        accountLoginDTO.setUsername("nonExistentUser");
        accountLoginDTO.setPassword("password");
        accountLoginDTO.setType(LoginConstant.ADMIN_LOGIN);

        when(adminMapper.getByUserName(accountLoginDTO.getUsername())).thenReturn(null);

        assertThrows(AccountNotFoundException.class, () -> commonService.login(accountLoginDTO));
    }

    @Test
    public void testLoginPasswordError() {
        AccountLoginDTO accountLoginDTO = new AccountLoginDTO();
        accountLoginDTO.setUsername("userUser");
        accountLoginDTO.setPassword("wrongPassword");
        accountLoginDTO.setType(LoginConstant.USER_LOGIN);

        User user = new User();
        user.setUsername("userUser");
        user.setPassword(DigestUtils.md5DigestAsHex("userPassword".getBytes()));

        when(userMapper.getByUserName(accountLoginDTO.getUsername())).thenReturn(user);

        assertThrows(PasswordErrorException.class, () -> commonService.login(accountLoginDTO));
    }
}
