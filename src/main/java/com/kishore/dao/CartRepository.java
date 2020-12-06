package com.kishore.dao;

import com.kishore.model.Cart;
import com.kishore.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
