package com.challenge5.app.repositories;

import com.challenge5.app.model.Merchant;
import com.challenge5.app.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    public List<Product> findAllByMerchantId(UUID id);
    List<Product> findAllByMerchantIdOrderByPriceDesc(UUID id);

}
