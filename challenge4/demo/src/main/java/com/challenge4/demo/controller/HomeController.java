package com.challenge4.demo.controller;

import com.challenge4.demo.model.Merchant;
import com.challenge4.demo.view.HomeView;
import com.challenge4.demo.view.MerchantView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@Component
public class HomeController {
    @Autowired
    MerchantController merchantController;
   public void run(){
       HomeView.HomeMenu();
       inputPilihFiturHome();
   }
    public void inputPilihFiturHome() {
        System.out.print("Masukan input: ");
        int input = new Scanner(System.in).nextInt();
        switch (input){
            case 1:
                System.out.println("Kelola Merchant");
                List<Merchant> merchants = merchantController.getAllMerchantsOpen();
                MerchantView.showTableMerchantOpen(merchants);
                MerchantView.menuMerchant();
                inputPilihFiturMerchant();
                break;
            case 2:
                System.out.println("Kelola Product");
                break;
            case 3:
                System.out.println("Kelola User");
                break;
            case 4:
                System.out.println("Order");
                break;
        }
    }
    public void inputPilihFiturMerchant(){
        System.out.print("Masukan input: ");
        int input = new Scanner(System.in).nextInt();
        switch (input){
            case 1:
                Merchant merchantAddSaved = MerchantView.formAddMerchant();
                merchantController.create(merchantAddSaved);
                break;
            case 2:
                int i = 1;
                List<Merchant> merchants = merchantController.getAllMerchants();
                MerchantView.showTableMerchantOpen(merchants);
                System.out.println("Silahkan pilih merchant: ");
                int merchantSelected = new Scanner(System.in).nextInt();
                System.out.println("Tutup/buka: ");
                boolean isOpen = new Scanner(System.in).nextBoolean();
                Merchant merchant = merchants.get(merchantSelected - 1);
                merchantController.update(merchant.getId(), isOpen);
                merchants = merchantController.getAllMerchants();
                MerchantView.showTableMerchantOpen(merchants);
            case 3:
        }
    }
}
