package com.kishore.controller;

import com.kishore.dao.UserRepository;
import com.kishore.model.*;
import com.kishore.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;


@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/order")
    public HashMap<String, String> createOrder(@RequestParam("product_id") int product_id, @RequestParam("user_id") int user_id, @RequestParam("quantity") int quantity, @RequestParam("delivery_date") int days, @RequestParam("delivery_mode") String delivery_mode) {
        User user = userRepository.findById(user_id).orElse(null);
        Product product = productService.getProduct(product_id);
        product.setQuantity(product.getQuantity() - quantity);
        product.setUpdated_at(new Date());
        productService.updateProduct(product, product_id);
        Date date = new Date();
        Date delivery_date = new Date(date.getTime() + TimeUnit.DAYS.toMillis( days ));

        Order order = new Order();
        order.setOrder_no(UUID.randomUUID().toString());
        order.setStatus("created");
        order.setDelivery_mode(delivery_mode);
        order.setDelivery_date_time(delivery_date);
        order.setOrder_date(new Date());
        order.setUser(user);
        order.setProduct(product);
        order.setAmount(product.getPrice());

        orderService.saveOrder(order);
        HashMap<String, String> return_message = new HashMap<String, String>();
        return_message.put("message", "Order created Successfully");
        return return_message;
    }

    @GetMapping("/order/products")
    public List<Product> OrderProducts(@RequestParam("username") String username)
    {
        User user = userRepository.findByUserName(username);
        List<Product> productList = new ArrayList<Product>();
        for(Order order: user.getOrders())
        {
            productList.add(order.getProduct());
        }
        return productList;
    }

    @DeleteMapping("/order/{id}")
    public HashMap<String, String> deleteOrder(@PathVariable("id") int id, @RequestParam("product_id") int product_id)
    {
        Order order = orderService.getOrder(id);
        Product product = productService.getProduct(product_id);
        product.setQuantity(product.getQuantity() + product.getQuantity());
        product.setUpdated_at(new Date());
        productService.updateProduct(product, product_id);
        orderService.deleteOrder(id);
        HashMap<String, String> return_message = new HashMap<String, String>();
        return_message.put("message", "Order deleted Successfully");
        return return_message;
    }
}
