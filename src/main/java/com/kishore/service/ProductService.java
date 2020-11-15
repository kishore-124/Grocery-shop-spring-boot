package com.kishore.service;

import com.kishore.dao.ProductRepository;
import com.kishore.model.Product;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    public ProductRepository productRepository;

    public void saveProduct(Product product)
    {
        productRepository.save(product);
    }

    public List<Product> listProduct(String keyword)
    {
        if(keyword != null)
        {
           return productRepository.findAll(keyword);
        }
        return   productRepository.findAll();
    }

    public void deleteProduct(int id)
    {
        productRepository.deleteById(id);
    }

    public Product getProduct(int id)
    {
        return   productRepository.findById(id).orElse(null);
    }

    public Product updateProduct(Product product, int id)
    {
        Product exists =   productRepository.findById(id).orElse(null);
        exists.setProductName(product.getProductName());
        exists.setImage(product.getImage());
        exists.setPrice(product.getPrice());
        exists.setQuantity(product.getQuantity());
        exists.setCreated_at(product.getCreated_at());
        exists.setUpdated_at(product.getUpdated_at());
        return  productRepository.save(exists);
    }
}
