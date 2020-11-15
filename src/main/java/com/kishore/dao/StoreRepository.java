package com.kishore.dao;

import com.kishore.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository  extends JpaRepository<Store, Integer> {
}
