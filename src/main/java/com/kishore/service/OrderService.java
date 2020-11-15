package com.kishore.service;

import com.kishore.dao.OrderRepository;
import com.kishore.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    public OrderRepository orderRepository;

    public void saveOrder(Order order)
    {
        orderRepository.save(order);
    }

    public List<Order> listOrder()
    {
        return   orderRepository.findAll();
    }

    public void deleteOrder(int id)
    {
        orderRepository.deleteById(id);
    }

    public Order getOrder(int id)
    {
        return   orderRepository.findById(id).orElse(null);
    }

    public Order updateOrder(Order order, int id)
    {
        Order exists =   orderRepository.findById(id).orElse(null);
        exists.setAmount(order.getAmount());
        exists.setDelivery_date_time(order.getDelivery_date_time());
        exists.setDelivery_mode(order.getDelivery_mode());
        exists.setUser(order.getUser());
        exists.setStatus(order.getStatus());
        exists.setOrder_no(order.getOrder_no());
        exists.setOrder_date(order.getOrder_date());
        return  orderRepository.save(exists);
    }
}
