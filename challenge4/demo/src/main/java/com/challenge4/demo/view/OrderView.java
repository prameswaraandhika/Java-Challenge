package com.challenge4.demo.view;

import com.challenge4.demo.model.OrderDetail;
import com.challenge4.demo.model.Product;
import com.challenge4.demo.util.Utils;

import java.util.List;

import static com.challenge4.demo.util.Utils.rupiahKonversi;

public class OrderView {
//    public static void showTableOrder(List<Order> Orders){
//        int i = 1;
//        for (Order Order:
//                Orders) {
//            System.out.println(i++ + ". "+Order);
//        }
//    }

    public static void menuOrder(){
        System.out.println("Pilih Order");
        System.out.println("1. Create Order");
        System.out.println("2. Update Order");
        System.out.println("3. Delete Order");
        System.out.println("99. Pesan dan Bayar");
        System.out.println("0. Keluar App");
    }

    public static void  confirmationOrderView(Product product) {
        System.out.println(product.getName() + " | " + product.getPrice());
        System.out.println("Berapa pesanan anda?\n");
        System.out.print("Qty => ");
    }

    public static void  confirmationPaymentView(List<OrderDetail> orderDetails) {
        System.out.println("Konfirmasi dan Pembayaran");
        System.out.println(Utils.LINE_EQUALS);
        System.out.println("Konfirmasi & Pembayaran");
        System.out.println(Utils.LINE_EQUALS);
        int totalProduct = 0;
        long totalPrice = 0;
        for (OrderDetail orderDetail:
                orderDetails) {
            System.out.printf(Utils.FORMAT_PESANAN, orderDetail.getProduct(), orderDetail.getQuantity(), rupiahKonversi(orderDetail.getTotalPrice()));
            totalProduct += orderDetail.getQuantity();
            totalPrice += orderDetail.getTotalPrice();
        }
        System.out.println(Utils.LINE_DASH);
        System.out.printf(Utils.FORMAT_PESANAN, "total", totalProduct, rupiahKonversi(totalPrice));
        System.out.println("1. Konfirmasi dan Bayar");
        System.out.println("2. Kembali ke Menu Utama");
        System.out.println("0. Keluar Aplikasi");
        System.out.print(Utils.INPUT_ARROW);

        System.out.println("Total product: " + totalProduct);
        System.out.println("Total price: " + totalPrice);

    }

//    public static Order formAddOrder() {
//        Scanner scanner = new Scanner(System.in);
//
//        Order Order = new Order();
//
//        System.out.println("Enter Order Name:");
//        String name = scanner.nextLine();
//        Order.setOrderName(name);
//
//        System.out.println("Enter Order Location:");
//        String location = scanner.nextLine();
//        Order.setOrderLocation(location);
//
//        System.out.println("Is the Order Open? (true/false):");
//        boolean open = scanner.nextBoolean();
//        scanner.nextLine();  // Consume newline left-over
//        Order.setOpen(open);
//
//        // Generate a random UUID for the id
//        UUID id = UUID.randomUUID();
//        Order.setId(id);
//
//        return Order;
//    }
//
//    public static Order formUpdateOrder(){
//        Order Order = new Order();
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Enter new Order Name:");
//        String name = scanner.nextLine();
//        Order.setOrderName(name);
//
//        System.out.println("Enter new Order Location:");
//        String location = scanner.nextLine();
//        Order.setOrderLocation(location);
//
//        System.out.println("Is the Order Open? (true/false):");
//        boolean isOpen = scanner.nextBoolean();
//        scanner.nextLine();  // Consume newline left-over
//        Order.setOpen(isOpen);
//        return Order;
//    }

}
