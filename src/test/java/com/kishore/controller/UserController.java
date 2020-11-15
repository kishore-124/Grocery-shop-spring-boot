package com.kishore.controller;

import com.kishore.dao.UserRepository;
import com.kishore.model.Role;
import com.kishore.model.User;
import com.kishore.model.Wallet;
import com.kishore.service.MyUserDetailsService;
import com.sendgrid.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserController {

//    @Autowired
//    private MyUserDetailsService userDetailsService;

    @Test
    public void saveUser(User user) {
        Wallet wallet = new Wallet();
        Role role = new Role();
        role.setName("User");
        user.setRole(role);
        wallet.setAmountEntered(10.0f);
        wallet.setAmountAvailable(10.0f);
        wallet.setCurrency("INR");
        user.setWallet(wallet);
//        userDetailsService.save(user);
        Assertions.assertEquals("user saved", "user");
    }
}
