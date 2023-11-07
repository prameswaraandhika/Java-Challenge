package com.challenge4.demo.view;

import com.challenge4.demo.model.Merchant;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class MerchantView {
    public static void showTableMerchantOpen(List<Merchant> merchants){
        int i = 1;
        for (Merchant merchant:
             merchants) {
            System.out.println(i++ + ". "+merchant);
        }
    }

    public static void menuMerchant(){
        System.out.println("Pilih merchant");
        System.out.println("1. Create Merchant");
        System.out.println("2. Uodate Merchant");
        System.out.println("3. Delete Merchant");
    }

    public static Merchant formAddMerchant() {
        Scanner scanner = new Scanner(System.in);

        Merchant merchant = new Merchant();

        System.out.println("Enter Merchant Name:");
        String name = scanner.nextLine();
        merchant.setMerchantName(name);

        System.out.println("Enter Merchant Location:");
        String location = scanner.nextLine();
        merchant.setMerchantLocation(location);

        System.out.println("Is the Merchant Open? (true/false):");
        boolean open = scanner.nextBoolean();
        scanner.nextLine();  // Consume newline left-over
        merchant.setOpen(open);

        // Generate a random UUID for the id
        UUID id = UUID.randomUUID();
        merchant.setId(id);

        return merchant;
    }

    public static Merchant formUpdateMerchant(){
        Merchant merchant = new Merchant();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter new Merchant Name:");
        String name = scanner.nextLine();
        merchant.setMerchantName(name);

        System.out.println("Enter new Merchant Location:");
        String location = scanner.nextLine();
        merchant.setMerchantLocation(location);

        System.out.println("Is the Merchant Open? (true/false):");
        boolean isOpen = scanner.nextBoolean();
        scanner.nextLine();  // Consume newline left-over
        merchant.setOpen(isOpen);

        return merchant;
    }
}
