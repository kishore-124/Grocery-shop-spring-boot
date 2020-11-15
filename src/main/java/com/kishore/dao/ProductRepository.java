package com.kishore.dao;

import com.kishore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE CONCAT(p.productName, p.price) LIKE %?1%")
    public List<Product> findAll(String keyword);
}
