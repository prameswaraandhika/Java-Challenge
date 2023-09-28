package org.example.challenge2.controller;

import org.example.challenge2.model.MenuMakanan;
import org.example.challenge2.model.Pesanan;
import org.example.challenge2.services.ServiceBinarFud;
import org.example.challenge2.services.ServiceBinarFudImpl;
import org.example.challenge2.util.Utils;
import org.example.challenge2.view.View;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static org.example.challenge2.model.Databases.listPesanan;
import static org.example.challenge2.view.View.*;

public class ControllerBinarFud {
    ServiceBinarFud serviceBinarFud;
    private  List<MenuMakanan> listMenuMakanan;

    private int inputPilihan = 0 ;

    public ControllerBinarFud() {
        this.serviceBinarFud = new ServiceBinarFudImpl();
        listMenuMakanan = serviceBinarFud.findAllMenus();
    }

    public void inputPilihan(){
        try {
            inputPilihan = Utils.scan.nextByte();
            Utils.scan.nextLine();
            switch (inputPilihan) {
                case 1, 2, 3, 4, 5 -> konfirmasiPesananTampilan();
//                case 99 -> pembayaranTampilan();
                case 0 -> {
                    System.exit(0);
                }
                default ->{
                    konfirmasiInputTidakDikenalTampilan();
                System.out.println("masuk sini");
                }
            }
        } catch (InputMismatchException ignored){
          konfirmasiPesananTampilan();
        }
    }


    public void inputKonfirmasiPesanan(){
        try {
        int jumlah = Utils.scan.nextByte();
        long harga = getHargaPesanan();
        String namaPesanan = getNamaPesanan();
        if (jumlah > 0){
            serviceBinarFud.savePesanan(new Pesanan(namaPesanan, jumlah, harga));
        } else {
            System.out.println("MINIMAL 1 JUMLAH PESANAN YA");
        }
        }catch (InputMismatchException exception){
            konfirmasiInputTidakDikenalTampilan();
        }
    }
    public void inputKonfirmasiUser(){
        Utils.scan.nextLine();
        String jawaban = Utils.scan.nextLine();
        if (!jawaban.equalsIgnoreCase("y")){
            System.out.println("Goodbye!");
            System.exit(0);
        }
    }
    public List<MenuMakanan> getListMenuMakanan() {
        return serviceBinarFud.findAllMenus();
    }

    public void setListMenuMakanan(List<MenuMakanan> listMenuMakanan) {
        this.listMenuMakanan = listMenuMakanan;
    }

    public String getNamaPesanan() {
       return listMenuMakanan.get(inputPilihan-1).getNama();
    }

    public long getHargaPesanan() {
        return listMenuMakanan.get(inputPilihan-1).getHarga();
    }

    public int getInputPilihan() {
        return inputPilihan;
    }

    public void setInputPilihan(int inputPilihan) {
        this.inputPilihan = inputPilihan;
    }


}
