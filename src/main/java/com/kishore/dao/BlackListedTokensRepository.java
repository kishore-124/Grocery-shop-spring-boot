package com.kishore.dao;

import com.kishore.model.BlackListedTokens;
import com.kishore.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.BadLocationException;

public interface BlackListedTokensRepository extends JpaRepository<BlackListedTokens, Integer> {
    BlackListedTokens findByToken(String token);
}
