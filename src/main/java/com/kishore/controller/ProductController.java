package com.kishore.controller;


import com.kishore.exception.RecordNotFound;
import com.kishore.model.*;

import java.io.IOException;
import java.util.*;

import com.kishore.service.ProductService;
import com.kishore.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private StoreService storeService;

    @PostMapping(value = "/product", consumes = {"multipart/form-data"})
    public HashMap<String, String> addProduct(@RequestParam("image") MultipartFile file, @RequestParam("productName") String productName, @RequestParam("quantity") int quantity, @RequestParam("price") float price, @RequestParam("store_id") String store_id) throws IOException {
        Store store = storeService.getStore(Integer.parseInt(store_id));
        Product product = new Product();
        product.setProductName(productName);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setImage(file.getBytes());
        product.setCreated_at(new Date());
        product.setUpdated_at(new Date());
        product.setStore(store);
        productService.saveProduct(product);

        HashMap<String, String> return_message = new HashMap<String, String>();
        return_message.put("message", "Product created successfully.");
        return return_message;
    }

    @GetMapping("/products")
    public List<Product> ListProduct(@Param("keyword") String keyword) {
        return productService.listProduct(keyword);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> FindById(@PathVariable int id) throws RecordNotFound {
        Product product = productService.getProduct(id);
        if (product == null) {
            throw new RecordNotFound("Record Not Found");
        }
        return ResponseEntity.ok().body(productService.getProduct(id));
    }

    @PutMapping("/product/{id}")
    public HashMap<String, String> updateProduct(@RequestBody Product product, @PathVariable int id) {
        productService.updateProduct(product, id);
        HashMap<String, String> return_message = new HashMap<String, String>();
        return_message.put("message", "Product updated successfully.");
        return return_message;
    }

    @DeleteMapping("/product/{id}")
    public HashMap<String, String> DeleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        HashMap<String, String> return_message = new HashMap<String, String>();
        return_message.put("message", "Product deleted successfully.");
        return return_message;
    }
}
