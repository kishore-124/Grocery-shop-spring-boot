package com.kishore.commandline;

import com.kishore.dao.RoleRepository;
import com.kishore.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RoleCommandLine implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        Role check_admin = roleRepository.findByName("Admin");
        if (check_admin == null) {
            Role admin = new Role();
            admin.setName("Admin");
            admin.setCreated_at(new Date());
            admin.setUpdated_at(new Date());
            roleRepository.save(admin);
        }

        Role check_user = roleRepository.findByName("User");
        if (check_user == null) {
            Role user = new Role();
            user.setName("User");
            user.setCreated_at(new Date());
            user.setUpdated_at(new Date());
            roleRepository.save(user);
        }
    }
}
