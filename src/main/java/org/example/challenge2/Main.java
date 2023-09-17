package org.example.challenge2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
//    List untuk menuMakanan
    static final String LINE_EQUALS = "================================================================";
    static final String LINE_DASH = "---------------------------------------------------------------+\n";
    static final String INPUT_ARROW = "\n=> ";
    static final String FORMAT_PESANAN = "%-14s %5d %15s\n";

    static final Scanner scan = new Scanner(System.in);
    static final List<MenuMakanan> menuMakanan = List.of(
                new MenuMakanan(1,"Nasi Goreng", 15000),
                new MenuMakanan(2,"Mie Goreng", 13000),
                new MenuMakanan(3,"Nasi + Ayam", 18000),
                new MenuMakanan(4,"Es Teh Manis", 3000),
                new MenuMakanan(5,"Es Jeruk", 5000));
    static List<Pesanan> listPesanan = new ArrayList<>();

    public static void main(String[] args) {
        menuUtamaTampilan();
    }

    private static void menuUtamaTampilan() {
//        Buat tampilan cli yang akan di ulang hingga selama user tidak memilih keluar dari aplikasi dan tidak menyelesaikan pembayaran
//        karna switch 1 sd 5 adalah fitur memesan makanan, jadi saya buat satu scope
//        ketika user memilih keluar dari aplikasi maka loop akan berhenti dan program akan keluar
        loop:
        while (true) {
            System.out.println(LINE_EQUALS);
            System.out.println("Selamat datang di BinarFud");
            System.out.println(LINE_EQUALS);
            System.out.println("\nSilahkan pilih makanan :");
            menuMakananTampilan();
            System.out.println("99. Pesan dan Bayar");
            System.out.println("0. Keluar aplikasi");
            System.out.print(INPUT_ARROW);
            if (scan.hasNextByte()){
                byte pilihan = scan.nextByte();
                switch (pilihan) {
                    case 1, 2, 3, 4, 5 -> konfirmasiPesananTampilan(pilihan);
                    case 99 -> pembayaranTampilan();
                    case 0 -> {
                        break loop;
                    }
                    default -> konfirmasiUserTampilan();
                }
            } else {
                konfirmasiUserTampilan();
            }
        }
    }

    private static void menuMakananTampilan() {
        for (MenuMakanan makanan : menuMakanan) {
            System.out.printf("%d. %-20s | %s \n", makanan.id(), makanan.nama(), rupiahKonversi(makanan.harga()));
        }
    }

    private static void pembayaranTampilan() {
        if (!listPesanan.isEmpty()){
            System.out.println(LINE_EQUALS);
            System.out.println("Konfirmasi & Pembayaran");
            System.out.println(LINE_EQUALS);
            int jumlahTotal = 0;
            long jumlahHarga = 0;
            for (Pesanan objek: listPesanan) {
                System.out.printf(FORMAT_PESANAN, objek.nama(), objek.jumlah(), rupiahKonversi(objek.harga()));
                jumlahTotal += objek.jumlah();
                jumlahHarga += objek.harga();
            }
            System.out.println(LINE_DASH);
            System.out.printf(FORMAT_PESANAN, "total", jumlahTotal, rupiahKonversi(jumlahHarga));
            System.out.println("1. Konfirmasi dan Bayar");
            System.out.println("2. Kembali ke Menu Utama");
            System.out.println("0. Keluar Aplikasi");
            System.out.print(INPUT_ARROW);
            byte pilihan = new Scanner(System.in).nextByte();
            switch (pilihan) {
                case 1 -> cetakInvoice(jumlahTotal, jumlahHarga);
                case 2 -> System.out.println("Kembali ke menu Utama");
                case 0 -> System.exit(0);
                default -> konfirmasiUserTampilan();
            }
        } else {
            System.out.println(LINE_EQUALS);
            System.out.println("MINIMAL 1 JUMLAH PESANAN");
            System.out.println(LINE_EQUALS);
            System.out.println("\n\n ");
        }
    }

    private static void konfirmasiUserTampilan() {
        System.out.println(LINE_EQUALS);
        System.out.println("Mohon masukan input pilihan anda ");
        System.out.println(LINE_EQUALS);
        System.out.println("(Y) untuk lanjut");
        System.out.println("(N) untuk keluar");
        System.out.print(INPUT_ARROW);
        String jawaban = scan.nextLine();
        scan.nextLine();
        if (jawaban.equalsIgnoreCase("n")){
            System.exit(0);
        }
    }

    private static void cetakInvoice(int jumlahTotal, long jumlahHarga) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("invoice_pembayaran.txt"))) {
            writer.write(LINE_EQUALS);
            writer.write("\nBinarFud\n");
            writer.write(LINE_EQUALS);
            writer.write("\nTerima kasih sudah memesan\ndi BinarFud");
            writer.write("\n\nDibawah ini pesanan anda\n\n");
            for (Pesanan objek : listPesanan) {
                writer.write(String.format(FORMAT_PESANAN, objek.nama(), objek.jumlah(), rupiahKonversi(objek.harga())));
            }
            writer.write(LINE_DASH);
            writer.write(String.format("%-14s %5d %15s\n\n", "total", jumlahTotal, rupiahKonversi(jumlahHarga)));
            writer.write("Pembayaran : Binar Cash\n");
            writer.write(LINE_EQUALS);
            writer.write("\nSimpan struk ini sebagai\n");
            writer.write("Bukti pembayaran\n");
            writer.write(LINE_EQUALS);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private static void konfirmasiPesananTampilan(byte pilihan) {
        System.out.println(LINE_EQUALS);
        System.out.println("Berapa pesanan anda");
        System.out.println(LINE_EQUALS);
        System.out.println(" " + menuMakanan.get(pilihan-1).nama() + "  |  " + menuMakanan.get(pilihan -1));
        System.out.println("Input 0 untuk kembali");
        System.out.print("\nqty => ");
        String nama = menuMakanan.get(pilihan - 1 ).nama();
        byte jumlah = new Scanner(System.in).nextByte();
        long harga = menuMakanan.get(pilihan -1).harga() * jumlah;
        for (Pesanan pesanan1: listPesanan) {
//            Membandingkan nama pesanan saat ini dan data sebelumnya
//            Jika ada nama yang sama dari list dengan nama saat ini maka,
//            ambil harga dan jumlah data yang sama, dan sum ke harga dan jumlah pesanan saat ini
//            setelah itu remove
            if (pesanan1.nama().equalsIgnoreCase(nama)){
                jumlah += pesanan1.jumlah();
                harga += pesanan1.harga();
                listPesanan.remove(pesanan1);
                break;
            }
        }
//        lalu data akan ditambahkan ulang, untuk mencegah duplikasi menu pada listPesanan
        listPesanan.add(new Pesanan(nama, jumlah, harga));
    }

    private static String rupiahKonversi(long harga){
        DecimalFormat pattern = new DecimalFormat("#.###");
        return pattern.format(harga);
    }

}