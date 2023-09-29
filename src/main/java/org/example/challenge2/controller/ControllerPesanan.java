package org.example.challenge2.controller;

import org.example.challenge2.model.MenuMakanan;
import org.example.challenge2.model.Pesanan;
import org.example.challenge2.services.ServicePesanan;
import org.example.challenge2.services.ServicePesananImpl;
import org.example.challenge2.util.Utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;

import static org.example.challenge2.view.View.*;

public class ControllerPesanan {
    ServicePesanan servicePesanan;
    private  List<MenuMakanan> listMenuMakanan;
    private  List<Pesanan> listPesanan;

    private static int inputPilihan = 0 ;
    private int total;
    private long totalHarga;
     private static boolean isExit = false;

    public ControllerPesanan() {
        this.servicePesanan = new ServicePesananImpl();
        listMenuMakanan = servicePesanan.findAllMenus();
        listPesanan = servicePesanan.findAllPesanan();
    }
    public void menuUtamaTampilan() {
        while (!isExit) {
            System.out.println(Utils.LINE_EQUALS);
            System.out.println("Selamat datang di BinarFud");
            System.out.println(Utils.LINE_EQUALS);
            System.out.println("\nSilahkan pilih makanan :");
            menuMakananTampilan();
            System.out.println("99. Pesan dan Bayar");
            System.out.println("0. Keluar aplikasi");
            System.out.print(Utils.INPUT_ARROW);
            inputPilihan();
        }
    }

    public void inputPilihan(){
        try {
            inputPilihan = Utils.scan.nextByte();
            Utils.scan.nextLine();
            switch (inputPilihan) {
                case 1, 2, 3, 4, 5 -> konfirmasiPesananTampilan();
                case 99 -> pembayaranTampilan();
                case 0 -> {
                    isExit = true;
                    System.exit(0);
                }
                default ->{
                    konfirmasiInputTidakDikenalTampilan();
                System.out.println("masuk sini");
                }
            }
        } catch (InputMismatchException ignored){
            konfirmasiInputTidakDikenalTampilan();
        }
    }

    public void inputKonfirmasiPesanan(){
        try {
        int jumlah = Utils.scan.nextByte();
        long harga = getHargaPesanan();
        String namaPesanan = getNamaPesanan();
        if (jumlah > 0){
            servicePesanan.savePesanan(new Pesanan(namaPesanan, jumlah, harga));
        } else {
            System.out.println("MINIMAL 1 JUMLAH PESANAN YA");
        }
        }catch (InputMismatchException ignored){
            konfirmasiInputTidakDikenalTampilan();
        }
    }

    public void inputKonfirmasiPembayaran() {
        byte pilihan = Utils.scan.nextByte();
        switch (pilihan) {
            case 1 -> {
                cetakInvoice();
                System.out.println("Terimakasih telah berbelanja di Binar Fud! ");
                System.exit(0);
            }
            case 2 -> System.out.println("Kembali ke menu Utama");
            case 0 -> System.exit(0);
            default -> konfirmasiInputTidakDikenalTampilan();
        }
    }

    public void inputKonfirmasiUser() {
            Utils.scan.nextLine();
            String jawaban = Utils.scan.nextLine();
            if (!jawaban.equalsIgnoreCase("y")) {
                System.out.println("Goodbye!");
                System.exit(0);
            }
    }

    private void cetakInvoice (){
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("invoice_pembayaran.txt"))) {
                writer.write(Utils.LINE_EQUALS);
                writer.write("\nBinarFud\n");
                writer.write(Utils.LINE_EQUALS);
                writer.write("\nTerima kasih sudah memesan\ndi BinarFud");
                writer.write("\n\nDibawah ini pesanan anda\n\n");
                for (Pesanan objek : listPesanan) {
                    writer.write(String.format("%-14s %5d %15s\n", objek.getNama(), objek.getJumlah(), rupiahKonversi(objek.getHarga())));
                }
                writer.write(Utils.LINE_DASH);
                writer.write(String.format("%-14s %5d %15s\n\n", "total", total, rupiahKonversi(totalHarga)));
                writer.write("Pembayaran : Binar Cash\n");
                writer.write(Utils.LINE_EQUALS);
                writer.write("\nSimpan struk ini sebagai\n");
                writer.write("Bukti pembayaran\n");
                writer.write(Utils.LINE_DASH);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        public List<MenuMakanan> getListMenuMakanan () {
            return servicePesanan.findAllMenus();
        }

        public void setListMenuMakanan (List < MenuMakanan > listMenuMakanan) {
            this.listMenuMakanan = listMenuMakanan;
        }

        public String getNamaPesanan () {
            return listMenuMakanan.get(inputPilihan - 1).getNama();
        }

        public long getHargaPesanan () {
            return listMenuMakanan.get(inputPilihan - 1).getHarga();
        }


        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public long getTotalHarga() {
            return totalHarga;
        }

        public void setTotalHarga(long totalHarga) {
            this.totalHarga = totalHarga;
        }
}


