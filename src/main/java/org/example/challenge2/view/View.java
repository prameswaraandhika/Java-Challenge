package org.example.challenge2.view;

import org.example.challenge2.model.MenuMakanan;
import org.example.challenge2.controller.ControllerPesanan;
import org.example.challenge2.model.Pesanan;
import org.example.challenge2.util.Utils;

import java.text.DecimalFormat;
import java.util.List;

import static org.example.challenge1.Main.cetakInvoice;
import static org.example.challenge2.model.Databases.listPesanan;

public class View {


    private static ControllerPesanan controllerPesanan = new ControllerPesanan();


    public static void execute(){
        controllerPesanan.menuUtamaTampilan();
    }

    public static void konfirmasiPesananTampilan() {
        System.out.println("Berapa pesanan anda");
        System.out.println(" " + controllerPesanan.getNamaPesanan() + "  |  " + controllerPesanan.getHargaPesanan());
        System.out.println("Input 0 untuk kembali");
        System.out.print("\nqty => ");
        controllerPesanan.inputKonfirmasiPesanan();
    }

    public static void menuMakananTampilan() {
        List<MenuMakanan> menuMakanan = controllerPesanan.getListMenuMakanan();
        for (MenuMakanan makanan : menuMakanan) {
            System.out.printf("%d. %-20s | %s \n", makanan.getId(), makanan.getNama(), rupiahKonversi(makanan.getHarga()));
        }
    }

    public static void pembayaranTampilan() {
        if (!listPesanan.isEmpty()) {
            System.out.println(Utils.LINE_EQUALS);
            System.out.println("Konfirmasi & Pembayaran");
            System.out.println(Utils.LINE_EQUALS);
            int total = 0;
            long totalHarga = 0;
            for (Pesanan objek : listPesanan) {
                System.out.printf(Utils.FORMAT_PESANAN, objek.getNama(), objek.getJumlah(), rupiahKonversi(objek.getHarga()));
                total += objek.getJumlah();
                totalHarga += objek.getHarga();
            }
            System.out.println(Utils.LINE_DASH);
            System.out.printf(Utils.FORMAT_PESANAN, "total", total, rupiahKonversi(totalHarga));
            System.out.println("1. Konfirmasi dan Bayar");
            System.out.println("2. Kembali ke Menu Utama");
            System.out.println("0. Keluar Aplikasi");
            System.out.print(Utils.INPUT_ARROW);
            controllerPesanan.setTotal(total);
            controllerPesanan.setTotalHarga(totalHarga);
            controllerPesanan.inputKonfirmasiPembayaran();
        } else {
            System.out.println(Utils.LINE_EQUALS);
            System.out.println("MINIMAL 1 JUMLAH PESANAN");
            System.out.println(Utils.LINE_EQUALS);
            System.out.println("\n\n ");
        }
    }

    public static void konfirmasiInputTidakDikenalTampilan() {
        System.out.println(Utils.LINE_EQUALS);
        System.out.println("Mohon masukan input pilihan anda ");
        System.out.println(Utils.LINE_EQUALS);
        System.out.println("(Y) untuk lanjut");
        System.out.println("(N) untuk keluar");
        System.out.print(Utils.INPUT_ARROW);
        controllerPesanan.inputKonfirmasiUser();
    }

    public static String rupiahKonversi(long harga){
        DecimalFormat pattern = new DecimalFormat("#.###");
        return pattern.format(harga);
    }


}
