package com.challenge4.demo.service;

import com.challenge4.demo.model.Merchant;

import java.util.List;
import java.util.UUID;

public interface MerchantService {
    // Menambahkan merchant baru
    Merchant addMerchant(Merchant merchant);

    // Mengedit status buka/tutup merchant
    Merchant updateMerchantStatus(UUID merchantId, boolean isOpen);

    // Menampilkan daftar semua merchant
    List<Merchant> getAllMerchants();

    // Menampilkan daftar merchant yang sedang buka
    List<Merchant> getOpenMerchants();
}
