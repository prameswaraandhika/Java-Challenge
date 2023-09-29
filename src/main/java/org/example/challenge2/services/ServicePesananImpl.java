package org.example.challenge2.services;

import org.example.challenge2.model.MenuMakanan;
import org.example.challenge2.model.Pesanan;
import org.example.challenge2.model.Databases;

import java.util.List;

public class ServicePesananImpl implements ServicePesanan {

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
        pesanan.setHarga(pesanan.getHarga() * pesanan.getJumlah());
        for (Pesanan object: Databases.listPesanan) {
            if (object.getNama().equalsIgnoreCase(pesanan.getNama())){
                pesanan.setJumlah(object.getJumlah() + pesanan.getJumlah());
                pesanan.setHarga(object.getHarga() + pesanan.getHarga());
                Databases.listPesanan.remove(object);
                break;
            }
        }
        Databases.listPesanan.add(pesanan);
        System.out.println(Databases.listPesanan);
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
