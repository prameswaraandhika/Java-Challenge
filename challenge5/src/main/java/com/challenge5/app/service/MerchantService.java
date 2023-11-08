package com.challenge5.app.service;

import com.challenge5.app.exception.MerchantNotFound;
import com.challenge5.app.model.Merchant;
import com.challenge5.app.model.dtos.MerchanDto;
import com.challenge5.app.model.dtos.MerchantUpdateStatusDto;
import com.challenge5.app.model.mappers.MerchantMapper;
import com.challenge5.app.repositories.MerchantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Slf4j
@Service
@RequiredArgsConstructor
public class MerchantService {
    final MerchantRepository merchantRepository;
    final MerchantMapper merchantMapper;


    public ResponseEntity<?>  createMerchant(MerchanDto merchanDto){
        Merchant merchant = merchantMapper.merchantDtoToMerchant(merchanDto);
        Merchant merchantSaved = merchantRepository.save(merchant);
        return ResponseEntity.status(HttpStatus.CREATED).body(merchantSaved);
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
        return ResponseEntity.ok(merchants);
    }

    public ResponseEntity<List<Merchant>> filterMerchantsByLocation(String criteria){
        List<Merchant> filteredMerchants = merchantRepository.findByMerchantLocation(criteria);
        return ResponseEntity.ok(filteredMerchants);
    }

    public Merchant getMerchantById(UUID id){
        return null;
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
