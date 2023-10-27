package com.challenge4.demo.controller;

import com.challenge4.demo.view.HomeView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class HomeController {

    @Autowired
    MerchantController merchantController;
   public void run(){
       HomeView.welcome();
       inputPilihFitur();
   }
    public static void inputPilihFitur() {
        System.out.print("Masukan input: ");
        int input = new Scanner(System.in).nextInt();
        switch (input){
            case 1:
                System.out.println("Kelola Merchant");

            case 2:
                System.out.println("Kelola Product");
            case 3:
                System.out.println("Kelola User");
            case 4:
                System.out.println("Order");
        }
    }
}
