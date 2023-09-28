package org.example.challenge2.services;

import org.example.challenge2.model.MenuMakanan;
import org.example.challenge2.model.Pesanan;
import org.example.challenge2.model.Databases;

import java.util.List;

public class ServiceBinarFudImpl implements ServiceBinarFud {

    @Override
    public List<MenuMakanan> findAllMenus() {
        return Databases.menuMakanan;
    }

    @Override
    public List<Pesanan> findAllPesanan() {
        return Databases.listPesanan;
    }

    @Override
    public void savePesanan(Pesanan pesanan) {
        for (Pesanan object: Databases.listPesanan) {
            if (object.getNama().equalsIgnoreCase(pesanan.getNama())){
                pesanan.setJumlah(object.getJumlah());
                pesanan.setHarga(object.getHarga());
                Databases.listPesanan.remove(object);
                break;
            }
        }
        pesanan.setHarga(pesanan.getHarga() * pesanan.getJumlah());
        Databases.listPesanan.add(pesanan);
    }

    @Override
    public void saveMenuMakanan(MenuMakanan menuMakanan) {

    }

    @Override
    public void update(Pesanan pesanan) {

    }

    @Override
    public void deleteByID(long id) {

    }
}
