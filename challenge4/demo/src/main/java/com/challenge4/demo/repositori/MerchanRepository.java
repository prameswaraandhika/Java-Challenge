package com.challenge4.demo.repositori;

import com.challenge4.demo.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MerchanRepository extends JpaRepository<Merchant, UUID> {
    List<Merchant> findByIsOpen(boolean isOpen);
}
