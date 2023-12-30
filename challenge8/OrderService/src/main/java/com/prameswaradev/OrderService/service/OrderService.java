package com.prameswaradev.OrderService.service;

import com.prameswaradev.OrderService.external.client.ProductService;
import com.prameswaradev.OrderService.model.Order;
import com.prameswaradev.OrderService.model.OrderDetail;
import com.prameswaradev.OrderService.model.dtos.OrderDetailNewDto;
import com.prameswaradev.OrderService.repository.OrderDetailRepository;
import com.prameswaradev.OrderService.repository.OrderRepository;
import com.prameswaradev.ProductService.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ProductService productService;


    public ResponseEntity<?> listOrdersByEmail(String email) {
        List<Order> orders = orderRepository.findAllByEmail(email);
        return ResponseEntity.ok(orders);
    }


    @Transactional
    public ResponseEntity<?> saveToWishlist(String email, List<OrderDetailNewDto> orderDetailNewDtoList) {
        try {
            Order order = Order.builder()
                    .email(email)
                    .time(LocalDate.now())
                    .address("JAKARTA")
                    .complete(false)
                    .build();
            orderRepository.save(order);
            for (OrderDetailNewDto orderDetail :
                    orderDetailNewDtoList) {
                Product product = productService.getProductById(orderDetail.idProduct());
                orderDetailRepository.save(
                        OrderDetail.builder()
                                .idOrder(order.getId())
                                .idProduct(product.getId())
                                .quantity(orderDetail.quantity())
                                .totalPrice(product.getPrice() * orderDetail.quantity())
                                .build());
            }
            return ResponseEntity.ok(orderDetailNewDtoList);
        } catch (Exception ex) {
            return ResponseEntity.noContent().build();
        }

    }
    public ResponseEntity<?> order(UUID idOrder){
            Optional<Order> orderOptional = orderRepository.findById(idOrder);
            orderOptional.ifPresent(order -> order.setComplete(true));
            return ResponseEntity.ok(orderRepository.save(orderOptional.get()));
    }
}