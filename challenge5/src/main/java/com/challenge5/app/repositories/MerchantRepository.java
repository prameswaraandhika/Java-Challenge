package com.challenge5.app.repositories;

import com.challenge5.app.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, UUID> {
    public List<Merchant> findByMerchantLocation(String location);
}
