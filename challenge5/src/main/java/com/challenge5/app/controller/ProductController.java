package com.challenge5.app.controller;

import com.challenge5.app.model.dtos.MerchanDto;
import com.challenge5.app.model.dtos.ProductDto;
import com.challenge5.app.model.dtos.ProductUpdateDto;
import com.challenge5.app.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = {"/restapi/merchants/{merchantId}/products"})
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<?> getProductsForMerchant(@PathVariable UUID merchantId){
        return productService.findAllById(merchantId);
    }

    @GetMapping(value = "/high-priced")
    public ResponseEntity<?> getHighPricedProductsForMerchant(@PathVariable UUID merchantId){
        return productService.findHighPricedProductByMerchantId(merchantId);
    }



    @PostMapping
    public ResponseEntity<?> createProductForMerchant(@PathVariable UUID merchantId,
                                           @RequestBody ProductDto productDto){
        return productService.create(merchantId, productDto);
    }

    @PutMapping
    public ResponseEntity<?> updateProductForMerchant(@PathVariable UUID merchantId,
                                                       @RequestBody ProductUpdateDto productUpdateDto){
        return productService.update(merchantId, productUpdateDto);
    }

    @DeleteMapping(value = "/{productId}")
    public ResponseEntity<?> deleteProductForMerchant(@PathVariable UUID merchantId, @PathVariable UUID productId){
        return productService.deleteById(merchantId, productId);
    }
}
