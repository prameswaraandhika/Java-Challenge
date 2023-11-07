package com.challenge4.demo.controller;

import com.challenge4.demo.model.Merchant;
import com.challenge4.demo.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    public List<Merchant> getAllMerchantsOpen(){
        return merchantService.getOpenMerchants();
    }
    public List<Merchant> getAllMerchants(){
        return merchantService.getAllMerchants();
    }

    public void create(Merchant merchant){
        merchantService.addMerchant(merchant);
    }

    public void update(UUID uuid, boolean status){
        merchantService.updateMerchantStatus(uuid, status);
    }

}
