package com.kishore.dao;

import com.kishore.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository  extends JpaRepository<Wallet, Integer> {
}
