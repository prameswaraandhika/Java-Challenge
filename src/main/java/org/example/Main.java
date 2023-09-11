package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
//    List untuk menuMakanan
    static List<MenuMakanan> menuMakanan = List.of(
                new MenuMakanan(1,"Nasi Goreng", 15000),
                new MenuMakanan(2, "Mie Goreng", 13000),
                new MenuMakanan(3,"Nasi + Ayam", 18000),
                new MenuMakanan(4,"Es Teh Manis", 3000),
                new MenuMakanan(5,"Es Jeruk", 5000));
//    List untuk pesananMakanan
    static List<Pesanan> menuPesanan = new ArrayList<Pesanan>();

    public static void main(String[] args) {
        menuUtamaTampilan();
    }

    private static void menuUtamaTampilan() {

//        Buat tampilan cli yang akan di ulang hingga selama user tidak memilih keluar dari aplikasi dan tidak menyelesaikan pembayaran
        dance:
        while (true) {
            System.out.println("===========================");
            System.out.println("Selamat datang di BinarFud");
            System.out.println("===========================");
            System.out.println("\nSilahkan pilih makanan :");
            for (MenuMakanan makanan : menuMakanan) {
                System.out.printf("%d. %-20s | %s \n", makanan.id(), makanan.nama(), rupiahKonversi(makanan.harga()));
            }
            System.out.println("99. Pesan dan Bayar");
            System.out.println("0. Keluar aplikasi");
            System.out.print("\n=> ");
            byte pilihan = new Scanner(System.in).nextByte();
//            karna switch 1 sd 5 adalah fitur memesan makanan, jadi saya buat satu scope
            switch (pilihan) {
                case 1, 2, 3, 4, 5:
                    konfirmasiTampilan(pilihan);
                    break;
                case 99:
                    pembayaranTampilan();
                    break;
                case 0:
//              ketika user memilih keluar dari aplikasi maka loop akan berhenti dan program akan keluar
                    break dance;
            }
        }
    }

    private static void pembayaranTampilan() {
        System.out.println("===========================");
        System.out.println("Konfirmasi & Pembayaran");
        System.out.println("=========================================");
        int jumlahTotal = 0;
        long jumlahHarga = 0;
        for (Pesanan objek: menuPesanan) {
            System.out.printf("%-14s %5d %15s\n", objek.nama(), objek.jumlah(), rupiahKonversi(objek.harga()));
            jumlahTotal += objek.jumlah();
            jumlahHarga += objek.harga();
        }
        System.out.println("---------------------------------------+");
        System.out.printf("%-14s %5d %15s\n", "total", jumlahTotal, rupiahKonversi(jumlahHarga));
        System.out.println("1. Konfirmasi dan Bayar");
        System.out.println("2. Kembali ke Menu Utama");
        System.out.println("0. Keluar Aplikasi");
        System.out.print("\n=> ");
        byte pilihan = new Scanner(System.in).nextByte();
        switch (pilihan) {
            case 1 -> {
                try {
                    cetakInvoice(jumlahTotal, jumlahHarga);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                System.exit(0);
            }
            case 3 -> System.exit(0);
        }
    }

    private static void cetakInvoice(int jumlahTotal, long jumlahHarga) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("invoice_pembayaran.txt"));
        writer.write("=====================================");
        writer.write("\nBinarFud\n");
        writer.write("=====================================");
        writer.write("\nTerima kasih sudah memesan\ndi BinarFud");
        writer.write("\n\nDibawah ini pesanan anda\n\n");
        for (Pesanan objek : menuPesanan) {
            writer.write(String.format("%-14s %5d %15s\n", objek.nama(), objek.jumlah(), rupiahKonversi(objek.harga())));
        }
        writer.write("---------------------------------------------------------------+\n");
        writer.write(String.format("%-14s %5d %15s\n\n", "total", jumlahTotal, rupiahKonversi(jumlahHarga)));
        writer.write("Pembayaran : Binar Cash\n");
        writer.write("================================================================\n");
        writer.write("Simpan struk ini sebagai \n");
        writer.write("Bukti pembayaran\n");
        writer.write("=================================================================");
        writer.close();
    }

    private static void konfirmasiTampilan(byte pilihan) {
        System.out.println("===========================");
        System.out.println("Berapa pesanan anda");
        System.out.println("===========================");
        System.out.printf(" %s | %s \n", menuMakanan.get(pilihan-1).nama(), rupiahKonversi(menuMakanan.get(pilihan-1).harga()));
        System.out.println("Input 0 untuk kembali");
        System.out.print("\nqty => ");
        String nama = menuMakanan.get(pilihan - 1 ).nama();
        byte jumlah = new Scanner(System.in).nextByte();
        long harga = menuMakanan.get(pilihan -1).harga() * jumlah;
        for (Pesanan pesanan1: menuPesanan) {
//            Membandingkan nama pesanan saat ini dan data sebelumnya
//            Jika ada nama yang sama dari list dengan nama saat ini maka,
//            ambil harga dan jumlah data yang sama, dan sum ke harga dan jumlah pesanan saat ini
//            setelah itu remove
            if (pesanan1.nama().equalsIgnoreCase(nama)){
                jumlah += pesanan1.jumlah();
                harga += pesanan1.harga();
                menuPesanan.remove(pesanan1);
                break;
            }
        }
//        lalu data akan ditambahkan ulang, untuk mencegah duplikasi menu pada listPesanan
        menuPesanan.add(new Pesanan(nama, jumlah, harga));
        System.out.println(menuPesanan);
    }

    private static String rupiahKonversi(long harga){
        DecimalFormat pattern = new DecimalFormat("#,###");
        return pattern.format(harga);
    }

}