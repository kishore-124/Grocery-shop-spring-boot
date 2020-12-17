package com.kishore.service;

import com.kishore.dao.StoreRepository;
import com.kishore.model.Product;
import com.kishore.model.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    public void deleteStore(int id) {
        storeRepository.deleteById(id);
    }

    public Store getStore(int id) {
        return storeRepository.findById(id).orElse(null);
    }

    public Store updateStore(Store store, int id) {
        Store exists = storeRepository.findById(id).orElse(null);
        exists.setName(store.getName());
        exists.setEmail(store.getEmail());
        exists.setContact_number(store.getContact_number());
        exists.setUpdated_at(new Date());
        return storeRepository.save(exists);
    }
}
