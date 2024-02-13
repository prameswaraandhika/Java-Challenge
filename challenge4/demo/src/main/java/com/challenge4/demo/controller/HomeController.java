package com.challenge4.demo.controller;

import com.challenge4.demo.model.Merchant;
import com.challenge4.demo.model.OrderDetail;
import com.challenge4.demo.model.Product;
import com.challenge4.demo.model.User;
import com.challenge4.demo.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import static com.challenge4.demo.view.HomeView.homeMenu;
import static com.challenge4.demo.view.MerchantView.*;
import static com.challenge4.demo.view.OrderView.*;
import static com.challenge4.demo.view.ProductView.*;
import static com.challenge4.demo.view.UserView.*;

@Component
public class HomeController {
    MerchantController merchantController;
    ProductController productController;
    OrderController orderController;

    UserController userController;

    @Autowired
    public HomeController(MerchantController merchantController, ProductController productController, OrderController orderController, UserController userController) {
        this.merchantController = merchantController;
        this.productController = productController;
        this.orderController = orderController;
        this.userController = userController;
    }

    public void run(){
       homeMenu();
       inputPilihFiturHome();
   }
    public void inputPilihFiturHome() {
        System.out.print("Masukan input: ");
        int input = new Scanner(System.in).nextInt();
        switch (input){
            case 1:
                System.out.println("Kelola Merchant");
                inputPilihFiturMerchant();
                break;
            case 2:
                System.out.println("Kelola Product");
                inputPIlihFiturProduct();
                break;
            case 3:
                System.out.println("Kelola User");
                inputPilihFiturUser();
                break;
            case 4:
                System.out.println("Order");
                inputPilihFiturOrder();
                break;
        }
    }

    private void inputPilihFiturOrder() {
        System.out.println("Selamat datang di BinarFud");
        System.out.println("Silahkan pilih makanan :");
        List<Product> products = productController.getAllProducts();
        showTableProducts(products);
        menuOrder();
        System.out.print("=>: ");
        int input = new Scanner(System.in).nextInt();
        if (input < 99){
            if (input == 0){
                System.exit(0);
            }
            confirmationOrderView(products.get(input - 1));
            int qty = new Scanner(System.in).nextInt();
            orderController.addOrderDetail(products.get(input - 1), qty);
            inputPilihFiturOrder();
        } else {
            List<OrderDetail> listOrderDetail = orderController.getListOrderDetail();
            confirmationPaymentView(listOrderDetail);
        }
    }




    private void inputPilihFiturUser() {
        List<User> users = userController.getAllUsers();
        showTableUsers(users);
        menuUser();
        System.out.print("Masukan input: ");
        int input = new Scanner(System.in).nextInt();
        switch (input){
            case 1:
                User userSaved = formAddUser();
                userController.create(userSaved);
                users = userController.getAllUsers();
                showTableUsers(users);
                break;
            case 2:
                System.out.print("Silahkan pilih user yang ingin diganti password: ");
                int userSelectedUpdate = new Scanner(System.in).nextInt();
                String passwordUser = formUpdatePasswordUser();
                userController.update(users.get(userSelectedUpdate - 1).getId(), passwordUser);
                users = userController.getAllUsers();
                showTableUsers(users);
                break;
            case 3:
                System.out.print("Silahkan pilih user yang ingin dihapus: ");
                int userSelectedDelete = new Scanner(System.in).nextInt();
                userController.delete(users.get(userSelectedDelete - 1).getId());
        }
    }

    private void inputPIlihFiturProduct() {
        List<Product> products = productController.getAllProducts();
        showTableProducts(products);
        menuProduct();
        System.out.print("Masukan input: ");
        int input = new Scanner(System.in).nextInt();
        switch (input){
            case 1:
                List<Merchant> merchants = merchantController.getAllMerchants();
                showTableMerchant(merchants);
                System.out.println("Silahkan pilih merchant untuk Product yang ingin ditambahkan: ");
                int merchantSelected = new Scanner(System.in).nextInt();
                Merchant merchant = merchants.get(merchantSelected - 1);
                Product productSaved = formAddProduct(merchant);
                productController.create(productSaved);
                break;
            case 2:
                System.out.println("Silahkan pilih product untuk diupdate name: ");
                int productSelectedUpdate = new Scanner(System.in).nextInt();
                System.out.println("Name: ");
                String name = new Scanner(System.in).nextLine();
                productController.update(products.get(productSelectedUpdate - 1).getId(), name);
                products = productController.getAllProducts();
                showTableProducts(products);
                break;
            case 3:
                System.out.println("Silahkan pilih product untuk dihapus: ");
                int productSelectedDelete = new Scanner(System.in).nextInt();
                productController.delete(products.get(productSelectedDelete - 1).getId());
                products = productController.getAllProducts();
                showTableProducts(products);
        }
    }

    public void inputPilihFiturMerchant(){
        List<Merchant> merchants = merchantController.getAllMerchants();
        showTableMerchant(merchants);
        menuMerchant();
        System.out.print("Masukan input: ");
        int input = new Scanner(System.in).nextInt();
        switch (input){
            case 1:
//                Create Merchant
                Merchant merchantAddSaved = formAddMerchant();
                merchantController.create(merchantAddSaved);
                break;
            case 2:
//                Update Status Merchant
                System.out.println("Silahkan pilih merchant: ");
                int merchantSelected = new Scanner(System.in).nextInt();
                System.out.println("Tutup/buka: ");
                boolean isOpen = new Scanner(System.in).nextBoolean();
                merchantController.update(merchants.get(merchantSelected - 1).getId(), isOpen);
                merchants = merchantController.getAllMerchants();
                showTableMerchant(merchants);
                break;
            case 3:
//                Delete Merchant
                System.out.println("Silahkan pilih merchant: ");
                int merchantSelectedDelete = new Scanner(System.in).nextInt();
                merchantController.delete(merchants.get(merchantSelectedDelete - 1).getId());
                merchants = merchantController.getAllMerchants();
                showTableMerchant(merchants);
                break;
        }
    }
}
