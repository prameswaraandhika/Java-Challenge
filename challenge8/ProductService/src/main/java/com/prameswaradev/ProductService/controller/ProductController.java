package com.prameswaradev.ProductService.controller;

import com.prameswaradev.ProductService.model.Product;
import com.prameswaradev.ProductService.model.dtos.ProductNewDto;
import com.prameswaradev.ProductService.model.dtos.ProductNotFound;
import com.prameswaradev.ProductService.model.dtos.ProductUpdateDto;
import com.prameswaradev.ProductService.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping(value = {"/restapi/merchants/{merchantId}/products"})
@RestController
public class ProductController {
    private final ProductService productService;

    @GetMapping(value = "{/id}")
    public Product getProductById(@PathVariable UUID idProduct) throws ProductNotFound {
        return productService.findProductById(idProduct);
    }
    @GetMapping
    public ResponseEntity<?> getProductsForMerchant(@PathVariable UUID merchantId){
        return productService.findAllById(merchantId);
    }

    @GetMapping(value = "/high-priced/{id}")
    public ResponseEntity<?> getHighPricedProductsForMerchant(@PathVariable UUID merchantId){
        return productService.findHighPricedProductByMerchantId(merchantId);
    }



    @PostMapping
    public ResponseEntity<?> createProductForMerchant(@PathVariable UUID merchantId,
                                                      @RequestBody ProductNewDto productDto){
        return productService.create(merchantId, productDto);
    }

    @PutMapping
    public ResponseEntity<?> updateProductForMerchant(@PathVariable UUID merchantId,
                                                      @RequestBody ProductUpdateDto productUpdateDto) throws ProductNotFound {
        return productService.update(merchantId, productUpdateDto);
    }

    @DeleteMapping(value = "/{productId}")
    public ResponseEntity<?> deleteProductForMerchant(@PathVariable UUID merchantId, @PathVariable UUID productId) throws ProductNotFound {
        return productService.deleteById(merchantId, productId);
    }
}
