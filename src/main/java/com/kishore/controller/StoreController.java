package com.kishore.controller;

import com.kishore.dao.StoreRepository;
import com.kishore.exception.RecordNotFound;
import com.kishore.model.Store;
import com.kishore.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class StoreController {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StoreService storeService;

    @PostMapping(value = "/store")
    public String addStore(@RequestBody Store store) {
        store.setCreated_at(new Date());
        store.setUpdated_at(new Date());
        storeRepository.save(store);
        return "store successfully added";
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
    public String updateStore(@RequestBody Store store, @PathVariable int id) {
        storeService.updateStore(store, id);
        return "store updated successfully";
    }

    @DeleteMapping("/store/{id}")
    public String DeleteStore(@PathVariable int id) {
        storeService.deleteStore(id);
        return "store deleted successfully";
    }
}
