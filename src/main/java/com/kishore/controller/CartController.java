package com.kishore.controller;

import com.kishore.dao.CartRepository;
import com.kishore.dao.UserRepository;
import com.kishore.model.*;
import com.kishore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/addcart")
    public String addCart(@RequestParam("quantity") String quantity, @RequestParam("id") String id, @RequestParam("username") String username )
    {
        Product product = productService.getProduct(Integer.parseInt(id));
        product.setQuantity(product.getQuantity() - Integer.parseInt(quantity));
        product.setUpdated_at(new Date());
        productService.updateProduct(product, Integer.parseInt(id));
        User user = userRepository.findByUserName(username);

        Cart cart = new Cart();
        cart.setProduct(product);
        cart.setCart_quantity(Integer.parseInt(quantity));
        cartRepository.save(cart);
        user.setCart(cart);

        userRepository.save(user);

        return "Item added successfully";
    }
}
