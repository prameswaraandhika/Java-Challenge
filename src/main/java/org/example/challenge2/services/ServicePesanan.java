package org.example.challenge2.services;

import org.example.challenge2.model.MenuMakanan;
import org.example.challenge2.model.Pesanan;

import java.util.List;

public interface ServicePesanan {

    List<MenuMakanan> findAllMenus();

    List<Pesanan> findAllPesanan();
    void savePesanan(Pesanan pesanan);

    void saveMenuMakanan(MenuMakanan menuMakanan);

    void update(Pesanan pesanan);
    void deleteByID(long id);
}
