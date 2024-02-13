package com.challenge4.demo.view;

import com.challenge4.demo.model.User;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class UserView {
    public static void showTableUsers(List<User> users){
        int i = 1;
        for (User user:
                users) {
            System.out.println(i++ + ". "+user);
        }
    }

    public static void menuUser(){
        System.out.println("Pilih User");
        System.out.println("1. Create User ");
        System.out.println("2. Uodate User");
        System.out.println("3. Delete User");
    }

    public static User formAddUser() {
        Scanner scanner = new Scanner(System.in);
        User user = new User();
        System.out.println("Enter User Name:");
        String name = scanner.nextLine();
        user.setUsername(name);

        System.out.println("Enter User mail:");
        String mail = scanner.nextLine();
        user.setEmailAdress(mail);

        System.out.println("Enter User password:");
        String pass = scanner.nextLine();
        user.setPassword(pass);
        return user;
    }

    public static String formUpdatePasswordUser(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new User password:");
        return scanner.nextLine();
    }

    public static UUID formDeleteUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter ID User:");
        String idUserStr = scanner.nextLine();
        return UUID.fromString(idUserStr);
    }
}
