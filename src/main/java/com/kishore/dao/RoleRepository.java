package com.kishore.dao;

import com.kishore.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
