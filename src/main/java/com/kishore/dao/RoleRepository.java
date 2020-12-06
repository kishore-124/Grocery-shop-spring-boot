package com.kishore.dao;

import com.kishore.model.Role;
import com.kishore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
