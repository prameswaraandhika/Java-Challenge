package org.example.challenge1;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Main {
//    List untuk menuMakanan
    static final String line1 = "================================================================";
    static final String line2 = "---------------------------------------------------------------+\n";
    static final String inputArrow = "\n=> ";
    static final String formatPesanan = "%-14s %5d %15s\n";

    static final Scanner scan = new Scanner(System.in);
    static final List<MenuMakanan> menuMakanan = List.of(
                new MenuMakanan(1,"Nasi Goreng", 15000),
                new MenuMakanan(2,"Mie Goreng", 13000),
                new MenuMakanan(3,"Nasi + Ayam", 18000),
                new MenuMakanan(4,"Es Teh Manis", 3000),
                new MenuMakanan(5,"Es Jeruk", 5000));
    static List<Pesanan> menuPesanan = new ArrayList<>();

    public static void main(String[] args) {

    }

    private static void menuUtamaTampilan() {
//        Buat tampilan cli yang akan di ulang hingga selama user tidak memilih keluar dari aplikasi dan tidak menyelesaikan pembayaran
//        karna switch 1 sd 5 adalah fitur memesan makanan, jadi saya buat satu scope
//        ketika user memilih keluar dari aplikasi maka loop akan berhenti dan program akan keluar
        while (true) {
            System.out.println(line1);
            System.out.println("Selamat datang di BinarFud");
            System.out.println(line1);
            System.out.println("\nSilahkan pilih makanan :");
            for (MenuMakanan makanan : menuMakanan) {
                System.out.printf("%d. %-20s | %s \n", makanan.id(), makanan.nama(), rupiahKonversi(makanan.harga()));
            }
            System.out.println("99. Pesan dan Bayar");
            System.out.println("0. Keluar aplikasi");
            System.out.print(inputArrow);
            if (scan.hasNextByte()){
                byte pilihan = scan.nextByte();
                switch (pilihan) {
                    case 1, 2, 3, 4, 5 -> konfirmasiPesananTampilan(pilihan);
                    case 99 -> pembayaranTampilan();
                    case 0 ->
                            System.exit(0);
                    default -> System.out.println("Tolong input dengan benar");
                }
            } else {
                System.out.println("Tolong input dengan benar");            }
        }
    }

    private static void pembayaranTampilan() {
        System.out.println(line1);
        System.out.println("Konfirmasi & Pembayaran");
        System.out.println(line1);
        int jumlahTotal = 0;
        long jumlahHarga = 0;
        for (Pesanan objek: menuPesanan) {
            System.out.printf(formatPesanan, objek.nama(), objek.jumlah(), rupiahKonversi(objek.harga()));
            jumlahTotal += objek.jumlah();
            jumlahHarga += objek.harga();
        }
        System.out.println(line2);
        System.out.printf(formatPesanan, "total", jumlahTotal, rupiahKonversi(jumlahHarga));
        System.out.println("1. Konfirmasi dan Bayar");
        System.out.println("2. Kembali ke Menu Utama");
        System.out.println("0. Keluar Aplikasi");
        System.out.print("\n=> ");
        byte pilihan = new Scanner(System.in).nextByte();
        switch (pilihan) {
            case 1 -> cetakInvoice(jumlahTotal, jumlahHarga);
            case 2 -> System.out.println("Kembali ke menu Utama");
            case 0 -> System.exit(0);
            default -> System.out.println("Tolong input dengan benar");
        }
    }


    public static void cetakInvoice(int jumlahTotal, long jumlahHarga) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("invoice_pembayaran.txt"))) {
            writer.write(line1);
            writer.write("\nBinarFud\n");
            writer.write(line1);
            writer.write("\nTerima kasih sudah memesan\ndi BinarFud");
            writer.write("\n\nDibawah ini pesanan anda\n\n");
            for (Pesanan objek : menuPesanan) {
                writer.write(String.format("%-14s %5d %15s\n", objek.nama(), objek.jumlah(), rupiahKonversi(objek.harga())));
            }
            writer.write(line2);
            writer.write(String.format("%-14s %5d %15s\n\n", "total", jumlahTotal, rupiahKonversi(jumlahHarga)));
            writer.write("Pembayaran : Binar Cash\n");
            writer.write(line1);
            writer.write("\nSimpan struk ini sebagai\n");
            writer.write("Bukti pembayaran\n");
            writer.write(line1);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

        public static void konfirmasiPesananTampilan(byte pilihan){
            System.out.println(line1);
            System.out.println("Berapa pesanan anda");
            System.out.println(line1);
            System.out.println(" " + menuMakanan.get(pilihan - 1).nama() + "  |  " + menuMakanan.get(pilihan - 1));
            System.out.println("Input 0 untuk kembali");
            System.out.print("\nqty => ");
            String nama = menuMakanan.get(pilihan - 1).nama();
            byte jumlah = new Scanner(System.in).nextByte();
            long harga = menuMakanan.get(pilihan - 1).harga() * jumlah;
            for (Pesanan pesanan1 : menuPesanan) {
                if (pesanan1.nama().equalsIgnoreCase(nama)) {
                    jumlah += pesanan1.jumlah();
                    harga += pesanan1.harga();
                    menuPesanan.remove(pesanan1);
                    break;
                }
            }
//        lalu data akan ditambahkan ulang, untuk mencegah duplikasi menu pada listPesanan
            menuPesanan.add(new Pesanan(nama, jumlah, harga));
        }


    public static String rupiahKonversi(long harga){
        DecimalFormat pattern = new DecimalFormat("#.###");
        return pattern.format(harga);
    }
}