package org.example.challenge2.model;

import org.example.challenge2.model.Pesanan;

import java.util.ArrayList;
import java.util.List;

public class Databases {
    public static final List<MenuMakanan> menuMakanan = List.of(
            new MenuMakanan(1,"Nasi Goreng", 15000),
            new MenuMakanan(2,"Mie Goreng", 13000),
            new MenuMakanan(3,"Nasi + Ayam", 18000),
            new MenuMakanan(4,"Es Teh Manis", 3000),
            new MenuMakanan(5,"Es Jeruk", 5000));
    public static List<Pesanan> listPesanan = new ArrayList<Pesanan>();
}
