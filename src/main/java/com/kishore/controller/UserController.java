package com.kishore.controller;


import com.kishore.dao.RoleRepository;
import com.kishore.dao.UserRepository;
import com.kishore.exception.RecordNotFound;
import com.kishore.model.AuthRequest;
import com.kishore.model.Role;
import com.kishore.model.User;
import com.kishore.model.Wallet;
import com.kishore.service.MyUserDetailsService;
import com.kishore.util.JwtUtil;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
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

    @PostMapping("/register")
    public String saveUser(@RequestBody User user) throws Exception {
        Wallet wallet = new Wallet();
        Role role = roleRepository.findByName("User");
        user.setRole(role);

        myUserDetailsService.save(user);
        run(user);
        return "user saved";
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
        if(user==null)
        {
            throw new RecordNotFound("Record Not Found");
        }
        return ResponseEntity.ok().body(user);
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
