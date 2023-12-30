package com.prameswaradev.OrderService.external.client;

import com.prameswaradev.ProductService.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.UUID;

@FeignClient(name = "PRODUCT-SERVICE/restapi/merchant")
public interface ProductService {

    @GetMapping(value = "{/id}")
    public Product getProductById(@PathVariable UUID idProduct);

}
