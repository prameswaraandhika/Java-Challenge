package com.prameswaradev.MerchantService.service;


import com.prameswaradev.MerchantService.exception.MerchantNotFound;
import com.prameswaradev.MerchantService.model.Merchant;
import com.prameswaradev.MerchantService.model.dtos.MerchantNewDto;
import com.prameswaradev.MerchantService.model.dtos.MerchantUpdateStatusDto;
import com.prameswaradev.MerchantService.repository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@Service
@RequiredArgsConstructor
public class MerchantService {
    final MerchantRepository merchantRepository;


    public ResponseEntity<?>  createMerchant(MerchantNewDto merchantNewDto){
        Merchant merchant = new Merchant(merchantNewDto.name(), merchantNewDto.location());
        Merchant merchantSaved;
        merchantSaved = merchantRepository.save(merchant);
        return ResponseEntity.status(CREATED).body(merchantSaved);
    }

    public ResponseEntity<?> updateMerchantStatus(UUID id, MerchantUpdateStatusDto merchantUpdateStatusDto){
        Optional<Merchant> optionalMerchant = merchantRepository.findById(id);
        if (optionalMerchant.isEmpty()){
            throw new MerchantNotFound(String.format("Merchant with id %s not found", id.toString()));
        }
        Merchant merchant = optionalMerchant.get();
        merchant.setOpen(merchantUpdateStatusDto.isOpen());
        Merchant updatedMerchant = merchantRepository.save(merchant);
        return ResponseEntity.ok(updatedMerchant);
    }

    public ResponseEntity<?> listMerchants(){
        List<Merchant> merchants = merchantRepository.findAll();
        if (merchants.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(merchants);
    }


    public ResponseEntity<List<Merchant>> filterMerchantsByLocation(String criteria){
        List<Merchant> filteredMerchants = merchantRepository.findAllByLocation(criteria);
        return ResponseEntity.ok(filteredMerchants);
    }

    public ResponseEntity<?>  getMerchantById(UUID id){
        Optional<Merchant> merchant = merchantRepository.findById(id);
        if (merchant.isEmpty()){
            throw new MerchantNotFound(String.format("Merchant with id %s not found", id.toString()));
        }
        return ResponseEntity.ok(merchant.get());
    }

    public ResponseEntity<?> deleteMerchant(UUID id){
        Optional<Merchant> merchantDelete = merchantRepository.findById(id);
        if (merchantDelete.isPresent()){
             merchantRepository.deleteById(merchantDelete.get().getId());
             return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
