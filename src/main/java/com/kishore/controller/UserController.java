package com.kishore.controller;

import com.kishore.dao.*;
import com.kishore.exception.RecordNotFound;
import com.kishore.model.*;
import com.kishore.service.MyUserDetailsService;
import com.kishore.util.JwtUtil;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
public class UserController {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private BlackListedTokensRepository blackListedTokensRepository;

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(UserRepository.class);

    @PostMapping("/register")
    public HashMap<String, String> saveUser(@Valid @RequestBody User user) throws Exception {
        HashMap<String, String> return_message = new HashMap<String, String>();
        try {
            Role role = roleRepository.findByName("User");
            user.setRole(role);

            myUserDetailsService.save(user);
            run(user);
            return_message.put("message", "User created successfully");
        } catch (Exception e) {
            return_message.put("errors", e.getMessage());
        }
        return return_message;
    }

    // for runing batch job for user registration
    public void run(User user) throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .addString("userName", user.getUserName()).addString("email", user.getEmail())
                .toJobParameters();
        jobLauncher.run(job, params);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUser(@RequestParam("username") String userName) throws RecordNotFound {
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            throw new RecordNotFound("Record Not Found");
        }
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/users")
    public List<User> users() {
        return userRepository.findAll();
    }

    @DeleteMapping("/user/{id}")
    public HashMap<String, String> deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
        HashMap<String, String> delete_message = new HashMap<String, String>();
        delete_message.put("message", "User Deleted Successfully");
        return delete_message;
    }

    @PostMapping("/user/login")
    public LinkedHashMap<String, String> generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("Invalid username/password");
        }
        User user = userRepository.findByUserName(authRequest.getUserName());
        LinkedHashMap<String, String> user_details = new LinkedHashMap<String, String>();
        user_details.put("json_token", jwtUtil.generateToken(authRequest.getUserName()));
        user_details.put("id", String.valueOf(user.getId()));
        user_details.put("name", user.getUserName());
        user_details.put("email", user.getEmail());
        user_details.put("phone_number", user.getPhone_number());
        return user_details;
    }

    @PostMapping("/user/logout")
    public HashMap<String, String> userLogout(@RequestHeader(name = "Authorization") String token, @RequestParam("username") String userName) {
        String[] jwt_token = token.split(" ");
        User user = userRepository.findByUserName(userName);
        BlackListedTokens blackListedToken = blackListedTokensRepository.findByToken(jwt_token[1]);
        if (blackListedToken == null) {
            BlackListedTokens blackListed = new BlackListedTokens();
            blackListed.setToken(jwt_token[1]);
            blackListed.setUser(user);
            blackListedTokensRepository.save(blackListed);
        }
        HashMap<String, String> logout_message = new HashMap<String, String>();
        logout_message.put("message", "User logged Successfully");
        return logout_message;
    }

    @PostMapping("/user/forgot_password")
    public HashMap<String, String> forgot_password() {
        HashMap<String, String> forgot_password_message = new HashMap<String, String>();
        forgot_password_message.put("message", "Reset token sent Successfully");
        return forgot_password_message;
    }
}
