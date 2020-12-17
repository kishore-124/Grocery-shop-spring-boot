package com.kishore.commandline;

import com.kishore.dao.UserRepository;
import com.kishore.dao.WalletRepository;
import com.kishore.model.*;
import com.kishore.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class WalletCommandLine implements CommandLineRunner {

    @Autowired
    private WalletService walletService;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        Wallet check_wallet = walletRepository.findByTotalAmount(10.0f);
        if (check_wallet == null) {
            Wallet wallet = new Wallet();
            wallet.setAmountEntered(10.0f);
            wallet.setAmountAvailable(10.0f);
            wallet.setCurrency("INR");
            walletService.saveWallet(wallet);
        }

        User user = userRepository.findByUserName("kishore");
        if (user.getWallet() == null) {
            Wallet wallet = walletRepository.findByTotalAmount(10.0f);
            user.setWallet(wallet);
            userRepository.save(user);
        }
    }
}
