package com.prameswaradev.ProductService.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.UUID;

@FeignClient(name = "MERCHANT-SERVICE/restapi/merchant")
public interface MerchantService {
    @GetMapping(value = "/{id}")
    ResponseEntity<?> getMerchantById(@PathVariable UUID id);


}
