package com.challenge4.demo.view;

import com.challenge4.demo.model.Merchant;
import com.challenge4.demo.model.Product;
import com.challenge4.demo.model.Product;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ProductView {
    public static void showTableProducts(List<Product> products){
        int i = 1;
        for (Product product:
             products) {
            System.out.println((i++) + ". "+product);
        }
    }

    public static void menuProduct(){
        System.out.println("Pilih Product");
        System.out.println("1. Create ");
        System.out.println("2. Uodate Product");
        System.out.println("3. Delete Product");
    }

    public static Product formAddProduct(Merchant merchant) {
        Scanner scanner = new Scanner(System.in);
        Product product = new Product();
        product.setMerchant(merchant);
        System.out.println("Enter Product Name:");
        String name = scanner.nextLine();
        product.setName(name);
        System.out.println("Enter Product price:");
        Long price = scanner.nextLong();
        product.setPrice(price);
        return product;
    }

    public static Product formUpdateProduct(){
        Product product = new Product();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new Product Name:");
        String name = scanner.nextLine();
        product.setName(name);
        System.out.println("Enter Product price:");
        Long price = scanner.nextLong();
        product.setPrice(price);
        return product;
    }

    public static UUID formDeleteProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter ID Product:");
        String idProductStr = scanner.nextLine();
        return UUID.fromString(idProductStr);
    }

}
