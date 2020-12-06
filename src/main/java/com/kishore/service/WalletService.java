package com.kishore.service;

import com.kishore.dao.WalletRepository;
import com.kishore.model.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WalletService {

    @Autowired
    public WalletRepository walletRepository;

    public void saveWallet(Wallet wallet)
    {
        wallet.setTotalAmount(wallet.getAmountEntered());
        wallet.setAmountAvailable(wallet.getAmountEntered());
        wallet.setAmountUsed(0.0f);
        wallet.setCreated_at(new Date());
        wallet.setUpdated_at(new Date());
        walletRepository.save(wallet);
    }

    public List<Wallet> listWallet()
    {
        return   walletRepository.findAll();
    }

    public Wallet getWallet(int id)
    {
        return   walletRepository.findById(id).orElse(null);
    }

    public Wallet updateWallet(Wallet wallet)
    {
        Wallet exists =  walletRepository.findById(wallet.getId()).orElse(null);
        float amount_available = exists.getAmountAvailable() +  wallet.getAmountEntered();
        float total_amount = exists.getTotalAmount() + wallet.getAmountEntered();

        exists.setAmountAvailable(amount_available);
        exists.setAmountEntered(wallet.getAmountEntered());
        exists.setTotalAmount(total_amount);
        exists.setUpdated_at(new Date());
        return  walletRepository.save(exists);
    }


}
