package com.kishore.controller;

import com.kishore.dao.StoreRepository;
import com.kishore.model.Product;
import com.kishore.model.Store;
import com.kishore.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
public class StoreController {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StoreService storeService;

    @PostMapping(value = "/store")
    public  String addStore(@RequestBody Store store) {
        storeRepository.save(store);
        return "store successfully added";
    }

    @GetMapping("/stores")
    public List<Store> ListStore()
    {
        return storeRepository.findAll();
    }

    @GetMapping("/store/{id}")
    public Store FindById(@PathVariable int id)
    {
        return storeService.getStore(id);
    }

    @PutMapping("/store/{id}")
    public String updateStore(@RequestBody Store store,@PathVariable int id)
    {
        storeService.updateStore(store,id);
        return "store updated successfully";
    }

    @DeleteMapping("/store/{id}")
    public String DeleteStore(@PathVariable int id)
    {
        storeService.deleteStore(id);
        return "store deleted successfully";
    }
}
