package com.challenge4.demo.service;

import com.challenge4.demo.model.Merchant;
import com.challenge4.demo.repositori.MerchanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MerchantServiceImpl implements MerchantService{

    @Autowired
    MerchanRepository merchanRepository;
    @Override
    public Merchant addMerchant(Merchant merchant) {
        return merchanRepository.save(merchant);
    }

    @Override
    public Merchant updateMerchantStatus(UUID merchantId, boolean isOpen) {
        Merchant merchant = merchanRepository.findById(merchantId).orElse(null);
        if (!(merchant == null)){
            merchant.setOpen(isOpen);
            return merchanRepository.save(merchant);
        }
        return null;
    }

    @Override
    public List<Merchant> getAllMerchants() {
        return merchanRepository.findAll();
    }

    @Override
    public List<Merchant> getOpenMerchants() {
        return merchanRepository.findByIsOpen(true);
    }
}
