package com.kishore.commandline;

import com.kishore.dao.*;
import com.kishore.model.*;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class UserCommandLine implements CommandLineRunner {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
     User user_check = userRepository.findByUserName("kishore");
     if(user_check == null)
     {
         Role role = roleRepository.findByName("Admin");
         User user = new User();
         user.setUserName("kishore");
         user.setPassword("9047446861");
         user.setEmail("kishorekce124@gmail.com");
         user.setPhone_number("7010950016");
         user.setCreate_at(new Date());
         user.setUpdated_at(new Date());
         user.setRole(role);
         userRepository.save(user);
         run(user);
     }
    }

    // for runing batch job for user registration
    public void run(User user) throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .addString("userName", user.getUserName()).addString("email", user.getEmail())
                .toJobParameters();
        jobLauncher.run(job, params);
    }
}
