package com.challenge5.app.controller;

import com.challenge5.app.model.Merchant;
import com.challenge5.app.model.dtos.MerchantCreateDto;
import com.challenge5.app.model.dtos.MerchantUpdateStatusDto;
import com.challenge5.app.service.MerchantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;


    @GetMapping("/binarfud/merchants")
    public ResponseEntity<?> getMerchants(){
        return merchantService.listMerchants();
    }

    @GetMapping("/binarfud/merchants/by-location")
    public ResponseEntity<?> getMerchantsByLocation(@RequestParam  String location){
        return merchantService.filterMerchantsByLocation(location);
    }

    @PostMapping("/binarfud/merchants")
    public ResponseEntity<?> createMerchant(@RequestBody MerchantCreateDto merchantCreateDto){
        return merchantService.createMerchant(merchantCreateDto);
    }

    @PutMapping("/binarfud/merchants/{id}")
    public ResponseEntity<?> updateMerchantStatus(@PathVariable UUID id, @RequestBody MerchantUpdateStatusDto merchantUpdateStatusDto){
        return merchantService.updateMerchantStatus(id, merchantUpdateStatusDto);
    }

    @DeleteMapping("/binarfud/merchants/{id}")
    public ResponseEntity<?> deleteMerchantId(@PathVariable UUID id){
        return merchantService.deleteMerchant(id);
    }




}
