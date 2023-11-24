package com.challenge6.app.controller;

import com.challenge6.app.model.dtos.MerchanDto;
import com.challenge6.app.model.dtos.MerchantFilterRevenueDto;
import com.challenge6.app.model.dtos.MerchantUpdateStatusDto;
import com.challenge6.app.service.InvoiceService;
import com.challenge6.app.service.MerchantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = {"/restapi/merchant"})
public class MerchantController {

    private final MerchantService merchantService;
    private final InvoiceService invoiceService;


    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMerchants(){
        return merchantService.listMerchants();
    }

    @GetMapping(value = {"/by-location"})
    public ResponseEntity<?> getMerchantsByLocation(@RequestParam  String location){
        return merchantService.filterMerchantsByLocation(location);
    }

    @PostMapping
    public ResponseEntity<?> createMerchant(@RequestBody MerchanDto merchanDto){
        return ResponseEntity.ok(merchanDto);
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



    @GetMapping("/revenue/{id}")
    public ResponseEntity<?> getMerchantRevenue(
            @PathVariable UUID id,
            @RequestParam(name = "start") String start,
            @RequestParam(name = "end") String end) {

        // Convert start and end strings to LocalDate
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);

        // Create MerchantFilterRevenueDto
        MerchantFilterRevenueDto filterRevenueDto = new MerchantFilterRevenueDto(startDate, endDate);

        // Your logic to process the request using filterRevenueDto
        // For now, let's just return a message
        return invoiceService.getMerchantReport(id, filterRevenueDto);
    }


}





