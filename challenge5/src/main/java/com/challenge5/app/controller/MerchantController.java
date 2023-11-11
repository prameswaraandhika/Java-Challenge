package com.challenge5.app.controller;

import com.challenge5.app.model.Merchant;
import com.challenge5.app.model.dtos.MerchanDto;
import com.challenge5.app.model.dtos.MerchantUpdateStatusDto;
import com.challenge5.app.service.InvoiceService;
import com.challenge5.app.service.MerchantService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = {"/restapi/merchant"})
public class MerchantController {

    private final MerchantService merchantService;
    private final InvoiceService invoiceService;

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


    @GetMapping(value = "/monthly-revenue/{id}")
    private ResponseEntity<?> generateInvoiceMonth(@PathVariable UUID id) throws JRException, IOException {
            return invoiceService.getMerchantReport(id);
    }


}





