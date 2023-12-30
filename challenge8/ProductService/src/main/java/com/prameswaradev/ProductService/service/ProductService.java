package com.prameswaradev.ProductService.service;


import com.prameswaradev.ProductService.external.client.MerchantService;
import com.prameswaradev.ProductService.model.Product;
import com.prameswaradev.ProductService.model.dtos.ProductNewDto;
import com.prameswaradev.ProductService.model.dtos.ProductNotFound;
import com.prameswaradev.ProductService.model.dtos.ProductUpdateDto;
import com.prameswaradev.ProductService.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final MerchantService merchantService;

    @Autowired
    public ProductService(ProductRepository productRepository, MerchantService merchantService) {
        this.productRepository = productRepository;
        this.merchantService = merchantService;
    }


    public Product findProductById(UUID idProduct) throws ProductNotFound {
        return productRepository.findById(idProduct).orElseThrow(
                () -> new ProductNotFound(String.format("Product with id %s not found", idProduct))
        );
    }

    public ResponseEntity<?> create(UUID merchantId, ProductNewDto productDto){
        isMerchantExist(merchantId);
        Product product = new Product(productDto.name(), productDto.price());
        product.setMerchantId(merchantId);
        productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    public ResponseEntity<?> update(UUID merchantId, ProductUpdateDto productUpdateDto) throws ProductNotFound {
        isMerchantExist(merchantId);
        Optional<Product> productOptional = productRepository.findById(productUpdateDto.idProduct());
        if (productOptional.isEmpty()){
            throw new ProductNotFound(String.format("Product with id %s not found", productUpdateDto.idProduct()));
        }
        Product product = productOptional.get();
        product.setName(productUpdateDto.name());
        product.setPrice(productUpdateDto.price());
        Product productSaved = productRepository.save(product);
        return  ResponseEntity.ok(productSaved);
    }

    public ResponseEntity<?> deleteById(UUID merchantId, UUID productId) throws ProductNotFound {
        isMerchantExist(merchantId);
        Optional<Product> productDelete = productRepository.findById(productId);
        if (productDelete.isEmpty()){
            throw new ProductNotFound(String.format("Product with id %s not found", productId));
        }
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> findAllById(UUID merchantId){
        List<Product> products = productRepository.findAllByMerchantId(merchantId);
        return ResponseEntity.ok(products);
    }

    public ResponseEntity<?> findHighPricedProductByMerchantId(UUID merchantId) {
        List<Product> products = productRepository.findAllByMerchantIdOrderByPriceDesc(merchantId);
        return ResponseEntity.ok(products);
    }

    private void isMerchantExist(UUID id) {
        merchantService.getMerchantById(id);
    }
}
