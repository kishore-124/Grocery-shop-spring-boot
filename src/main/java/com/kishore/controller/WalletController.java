package com.kishore.controller;


import com.kishore.model.Wallet;
import com.kishore.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class WalletController {

    @Autowired
    private WalletService walletService;

    @PutMapping("/wallet")
    public String wallet(@RequestBody Wallet wallet)
    {
         walletService.updateWallet(wallet);
        return "Wallet amount entered successfully";
    }
}
