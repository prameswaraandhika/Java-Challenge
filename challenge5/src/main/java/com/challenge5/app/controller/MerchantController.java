package com.challenge5.app.controller;

import com.challenge5.app.model.dtos.MerchanDto;
import com.challenge5.app.model.dtos.MerchantUpdateStatusDto;
import com.challenge5.app.service.MerchantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = {"/restapi/merchant"})
public class MerchantController {

    private final MerchantService merchantService;

    @GetMapping(value = "/all")
    public ResponseEntity<?> getMerchants(){
        return merchantService.listMerchants();
    }

    @GetMapping(value = {"/by-location"})
    public ResponseEntity<?> getMerchantsByLocation(@RequestParam  String location){
        return merchantService.filterMerchantsByLocation(location);
    }

    @PostMapping
    public ResponseEntity<?> createMerchant(@RequestBody MerchanDto merchanDto){
        return merchantService.createMerchant(merchanDto);
    }

    @PutMapping(value = {"/{id}"})
    public ResponseEntity<?> updateMerchantStatus(@PathVariable UUID id, @RequestBody MerchantUpdateStatusDto merchantUpdateStatusDto){
        log.info("Updating status for merchant with to: {}", merchantUpdateStatusDto.isOpen());
        return merchantService.updateMerchantStatus(id, merchantUpdateStatusDto);
    }

    @DeleteMapping(value = {"/{id}"})
    public ResponseEntity<?> deleteMerchantId(@PathVariable UUID id){
        return merchantService.deleteMerchant(id);
    }




}
