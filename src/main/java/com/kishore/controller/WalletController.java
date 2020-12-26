package com.kishore.controller;


import com.kishore.model.Wallet;
import com.kishore.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class WalletController {

    @Autowired
    private WalletService walletService;

    @PutMapping("/wallet")
    public HashMap<String, String> wallet(@RequestBody Wallet wallet) {
        walletService.updateWallet(wallet);
        HashMap<String, String> return_message = new HashMap<String, String>();
        return_message.put("message", "Wallet amount entered successfully");
        return return_message;
    }
}
