package org.example.challenge2.view;

import org.example.challenge2.model.MenuMakanan;
import org.example.challenge2.model.Pesanan;
import org.example.challenge2.controller.ControllerBinarFud;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

import static org.example.challenge2.Main.*;

public class View {
    public static final String LINE_EQUALS = "================================================================";
    public static final String LINE_DASH = "---------------------------------------------------------------+\n";
    public static final String INPUT_ARROW = "\n=> ";
    public static final String FORMAT_PESANAN = "%-14s %5d %15s\n";


    private static ControllerBinarFud controllerBinarFud = new ControllerBinarFud();


    public static void menuUtamaTampilan() {
//        Buat tampilan cli yang akan di ulang hingga selama user tidak memilih keluar dari aplikasi dan tidak menyelesaikan pembayaran
//        karna switch 1 sd 5 adalah fitur memesan makanan, jadi saya buat satu scope
//        ketika user memilih keluar dari aplikasi maka loop akan berhenti dan program akan keluar
        while (true) {
            System.out.println(LINE_EQUALS);
            System.out.println("Selamat datang di BinarFud");
            System.out.println(LINE_EQUALS);
            System.out.println("\nSilahkan pilih makanan :");
            menuMakananTampilan();
            System.out.println("99. Pesan dan Bayar");
            System.out.println("0. Keluar aplikasi");
            System.out.print(INPUT_ARROW);
            controllerBinarFud.inputPilihan();
        }
    }

    public static void konfirmasiPesananTampilan() {
        System.out.println("Berapa pesanan anda");
        System.out.println(" " + controllerBinarFud.getNamaPesanan() + "  |  " + controllerBinarFud.getHargaPesanan());
        System.out.println("Input 0 untuk kembali");
        System.out.print("\nqty => ");
        controllerBinarFud.inputKonfirmasiPesanan();
    }

    public static void menuMakananTampilan() {
        List<MenuMakanan> menuMakanan = controllerBinarFud.getListMenuMakanan();
        for (MenuMakanan makanan : menuMakanan) {
            System.out.printf("%d. %-20s | %s \n", makanan.getId(), makanan.getNama(), rupiahKonversi(makanan.getHarga()));
        }
    }

//    public static void pembayaranTampilan() {
//        if (!listPesanan.isEmpty()){
//            System.out.println(LINE_EQUALS);
//            System.out.println("Konfirmasi & Pembayaran");
//            System.out.println(LINE_EQUALS);
//            int jumlahTotal = 0;
//            long jumlahHarga = 0;
//            for (Pesanan objek: listPesanan) {
//                System.out.printf(FORMAT_PESANAN, objek.nama(), objek.jumlah(), rupiahKonversi(objek.harga()));
//                jumlahTotal += objek.jumlah();
//                jumlahHarga += objek.harga();
//            }
//            System.out.println(LINE_DASH);
//            System.out.printf(FORMAT_PESANAN, "total", jumlahTotal, rupiahKonversi(jumlahHarga));
//            System.out.println("1. Konfirmasi dan Bayar");
//            System.out.println("2. Kembali ke Menu Utama");
//            System.out.println("0. Keluar Aplikasi");
//            System.out.print(INPUT_ARROW);
//            byte pilihan = new Scanner(System.in).nextByte();
//            switch (pilihan) {
//                case 1 -> cetakInvoice(jumlahTotal, jumlahHarga);
//                case 2 -> System.out.println("Kembali ke menu Utama");
//                case 0 -> System.exit(0);
//                default -> konfirmasiUserTampilan();
//            }
//        } else {
//            System.out.println(LINE_EQUALS);
//            System.out.println("MINIMAL 1 JUMLAH PESANAN");
//            System.out.println(LINE_EQUALS);
//            System.out.println("\n\n ");
//        }
//    }

    public static void konfirmasiInputTidakDikenalTampilan() {
        System.out.println(LINE_EQUALS);
        System.out.println("Mohon masukan input pilihan anda ");
        System.out.println(LINE_EQUALS);
        System.out.println("(Y) untuk lanjut");
        System.out.println("(N) untuk keluar");
        System.out.print(INPUT_ARROW);
        controllerBinarFud.inputKonfirmasiUser();
    }
//    public static void konfirmasiPesananTampilan(int pilihan) {
//        controllerBinarFud.setInputPilihan(pilihan-1);
//
//        System.out.println(LINE_EQUALS);
//        System.out.println("Berapa pesanan anda");
//        System.out.println(LINE_EQUALS);
//        System.out.println(" " + controllerBinarFud.getNamaPesanan() + "  |  " + controllerBinarFud.getHargaPesanan());
//        System.out.println("Input 0 untuk kembali");
//        System.out.print("\nqty => ");
//        controllerBinarFud.inputKonfirmasiPesanan();
//
//    }
    public static String rupiahKonversi(long harga){
        DecimalFormat pattern = new DecimalFormat("#.###");
        return pattern.format(harga);
    }
}
