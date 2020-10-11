package com.kishore.controller;


import com.kishore.dao.UserRepository;
import com.kishore.model.AuthRequest;
import com.kishore.model.Role;
import com.kishore.model.User;
import com.kishore.service.MyUserDetailsService;
import com.kishore.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
public class UserController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @GetMapping("/")
    public String map() {
        return "welocome";
    }

    @PostMapping("/register")
    public String saveUser(@RequestBody User user) {
        Role role = new Role();
        role.setName("User");
        user.setRole(role);
        myUserDetailsService.save(user);
        return "user saved";
    }

    @GetMapping("/user")
    public User getUser(@RequestParam("username") String userName) throws IOException {
        User user = userRepository.findByUserName(userName);
        return user;
    }

    @PutMapping(value = "/user/{id}", consumes = {"multipart/form-data"})
    public String updateUser(@RequestParam("file") MultipartFile file, @PathVariable int id) throws IOException {
        User user = userRepository.findById(id).orElse(null);


        user.setData(file.getBytes());

        userRepository.save(user);
        return "user updated successfully";
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        System.out.println(authRequest.getUserName());
        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("Invalid username/password");
        }

        return jwtUtil.generateToken(authRequest.getUserName());
    }
}
