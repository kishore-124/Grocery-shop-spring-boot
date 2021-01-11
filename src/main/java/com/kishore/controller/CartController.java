package com.kishore.controller;

import com.kishore.dao.CartRepository;
import com.kishore.dao.UserRepository;
import com.kishore.model.*;
import com.kishore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/cart")
    public HashMap<String, String> addCart(@RequestParam("quantity") String quantity, @RequestParam("id") String id, @RequestParam("username") String username) {
        Product product = productService.getProduct(Integer.parseInt(id));
        product.setQuantity(product.getQuantity() - Integer.parseInt(quantity));
        product.setUpdated_at(new Date());
        productService.updateProduct(product, Integer.parseInt(id));
        User user = userRepository.findByUserName(username);

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setProduct(product);
        cart.setCart_quantity(Integer.parseInt(quantity));
        cartRepository.save(cart);
        userRepository.save(user);

        HashMap<String, String> return_message = new HashMap<String, String>();
        return_message.put("message", "Item added successfully");
        return return_message;
    }

    @GetMapping("/cart/products")
    public List<Product> listProduct(@RequestParam("username") String username) {
        User user = userRepository.findByUserName(username);
        List<Product> productList = new ArrayList<Product>();
        for(Cart cart: user.getCarts())
        {
            productList.add(cart.getProduct());
        }
        return productList;
    }

    @DeleteMapping("/cart/{id}")
    public HashMap<String, String> deleteCart(@PathVariable("id") int id, @RequestParam("product_id") int product_id)
    {
        Cart cart = cartRepository.findById(id).orElse(null);
        Product product = productService.getProduct(product_id);
        product.setQuantity(product.getQuantity() + cart.getCart_quantity());
        product.setUpdated_at(new Date());
        productService.updateProduct(product, product_id);
        cartRepository.deleteById(id);
        HashMap<String, String> return_message = new HashMap<String, String>();
        return_message.put("message", "Cart deleted Successfully");
        return return_message;
    }
}
