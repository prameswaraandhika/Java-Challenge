package com.prameswaradev.MerchantService.controller;


import com.prameswaradev.MerchantService.model.Merchant;
import com.prameswaradev.MerchantService.model.dtos.MerchantNewDto;
import com.prameswaradev.MerchantService.model.dtos.MerchantUpdateStatusDto;
import com.prameswaradev.MerchantService.service.MerchantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = {"/restapi/merchant"})
public class MerchantController {

    private final MerchantService merchantService;


    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMerchants(){
        return merchantService.listMerchants();
    }

    @GetMapping(value = {"/by-location"})
    public ResponseEntity<?> getMerchantsByLocation(@RequestParam  String location){
        return merchantService.filterMerchantsByLocation(location);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?>  getMerchantById(@PathVariable UUID id){
        return merchantService.getMerchantById(id);
    }

    @PostMapping
    public ResponseEntity<?> createMerchant(@RequestBody MerchantNewDto merchantNewDto){
        return merchantService.createMerchant(merchantNewDto);
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



//    @GetMapping("/revenue/{id}")
//    public ResponseEntity<?> getMerchantRevenue(
//            @PathVariable UUID id,
//            @RequestParam(name = "start") String start,
//            @RequestParam(name = "end") String end) {
//
//        // Convert start and end strings to LocalDate
//        LocalDate startDate = LocalDate.parse(start);
//        LocalDate endDate = LocalDate.parse(end);
//
//        // Create MerchantFilterRevenueDto
//        MerchantFilterRevenueDto filterRevenueDto = new MerchantFilterRevenueDto(startDate, endDate);
//
//        // Your logic to process the request using filterRevenueDto
//        // For now, let's just return a message
//        return invoiceService.getMerchantReport(id, filterRevenueDto);
//    }


}





