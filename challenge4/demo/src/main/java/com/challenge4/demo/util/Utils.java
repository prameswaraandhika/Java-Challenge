package com.challenge4.demo.util;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Utils {
    public static final String LINE_EQUALS = "================================================================";
    public static final String LINE_DASH = "---------------------------------------------------------------+\n";
    public static final String INPUT_ARROW = "\n=> ";
    public static final String FORMAT_PESANAN = "%-14s %5d %15s\n";

    public static Scanner scan = new Scanner(System.in);

    public static boolean isExit = false;

    public static String rupiahKonversi(long harga){
        DecimalFormat pattern = new DecimalFormat("#.###");
        return pattern.format(harga);
    }
}
