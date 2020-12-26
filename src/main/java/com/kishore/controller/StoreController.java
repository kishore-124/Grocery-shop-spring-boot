package com.kishore.controller;

import com.kishore.dao.StoreRepository;
import com.kishore.exception.RecordNotFound;
import com.kishore.model.Store;
import com.kishore.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.*;

@RestController
public class StoreController {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StoreService storeService;

    @PostMapping(value = "/store")
    public HashMap<String, String> addStore(@Valid @RequestBody Store store) {
        store.setCreated_at(new Date());
        store.setUpdated_at(new Date());
        storeRepository.save(store);
        HashMap<String, String> return_message = new HashMap<String, String>();
        return_message.put("message", "Store created Successfully");
        return return_message;
    }

    @GetMapping("/stores")
    public List<Store> ListStore() {
        return storeRepository.findAll();
    }

    @GetMapping("/store/{id}")
    public ResponseEntity<Store> FindById(@PathVariable int id) throws RecordNotFound {
        Store store = storeService.getStore(id);

        if (store == null) {
            throw new RecordNotFound("Record Not Found");
        }

        return ResponseEntity.ok().body(store);
    }

    @PutMapping("/store/{id}")
    public HashMap<String, String> updateStore(@RequestBody Store store, @PathVariable int id) {
        storeService.updateStore(store, id);
        HashMap<String, String> return_message = new HashMap<String, String>();
        return_message.put("message", "Store updated successfully.");
        return return_message;
    }

    @DeleteMapping("/store/{id}")
    public HashMap<String, String> DeleteStore(@PathVariable int id) {
        storeService.deleteStore(id);
        HashMap<String, String> return_message = new HashMap<String, String>();
        return_message.put("message", "Store deleted successfully.");
        return return_message;
    }
}
