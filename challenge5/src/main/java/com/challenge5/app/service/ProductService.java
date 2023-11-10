package com.challenge5.app.service;

import com.challenge5.app.exception.MerchantNotFound;
import com.challenge5.app.exception.ProductNotFound;
import com.challenge5.app.model.Merchant;
import com.challenge5.app.model.Product;
import com.challenge5.app.model.dtos.ProductDto;
import com.challenge5.app.model.dtos.ProductUpdateDto;
import com.challenge5.app.model.mappers.ProductMapper;
import com.challenge5.app.repositories.MerchantRepository;
import com.challenge5.app.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService {
    final ProductRepository productRepository;
    final MerchantRepository merchantRepository;
    final ProductMapper productMapper;

    public ResponseEntity<?> create(UUID merchantId, ProductDto productDto){
        Merchant merchantFound = isMerchantExist(merchantId);
        Product product = productMapper.productDtoToProduct(productDto);
        merchantFound.getProducts().add(product);
        merchantRepository.save(merchantFound);
        return ResponseEntity.status(HttpStatus.CREATED).body(merchantFound);
    }

    public ResponseEntity<?> update(UUID merchantId, ProductUpdateDto productUpdateDto){
        Merchant merchantFound = isMerchantExist(merchantId);
        Optional<Product> productOptional = productRepository.findById(productUpdateDto.idProduct());
        if (productOptional.isEmpty()){
            throw new ProductNotFound(String.format("Product with id %s not found", productUpdateDto.idProduct()));
        }
        Product product = productOptional.get();
        product.setName(productUpdateDto.productName());
        product.setPrice(productUpdateDto.price());
        product.setMerchant(merchantFound);
        Product productSaved = productRepository.save(product);
        return  ResponseEntity.ok(productSaved);
    }

    public ResponseEntity<?> deleteById(UUID merchantId, UUID productId){
        Merchant merchantFound = isMerchantExist(merchantId);
        Optional<Product> productDelete = productRepository.findById(productId);
        if (productDelete.isPresent()){
            merchantFound.getProducts().remove(productDelete.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> findAllById(UUID merchantId){
        List<Product> products = productRepository.findAllByMerchantId(merchantId);
        return ResponseEntity.ok(products);
    }
    private Merchant isMerchantExist(UUID id) {
        Optional<Merchant> optionalMerchant = merchantRepository.findById(id);
        if (optionalMerchant.isEmpty()){
            throw new MerchantNotFound(String.format("Merchant with id %s not found", id.toString()));
        }
        return optionalMerchant.get();
    }


    public ResponseEntity<?> findHighPricedProductByMerchantId(UUID merchantId) {
        List<Product> products = productRepository.findAllByMerchantIdOrderByPriceDesc(merchantId);
        return ResponseEntity.ok(products);    }
}
