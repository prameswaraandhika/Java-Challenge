package com.challenge5.app.service;

import com.challenge5.app.model.Order;
import com.challenge5.app.model.OrderDetail;
import com.challenge5.app.model.Product;
import com.challenge5.app.model.User;
import com.challenge5.app.model.dtos.OrderNewDto;
import com.challenge5.app.model.mappers.OrderMapper;
import com.challenge5.app.repositories.OrderDetailRepository;
import com.challenge5.app.repositories.OrderRepository;
import com.challenge5.app.repositories.ProductRepository;
import com.challenge5.app.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final OrderMapper orderMapper;

    @PersistenceContext
    private EntityManager entityManager;
    public ResponseEntity<?> listOrdersBy(UUID id) {
        List<Order> orders = orderRepository.findAllByUserId(id);
        return ResponseEntity.ok(orders);
    }


    @Transactional
    public ResponseEntity<Order> order(UUID id, OrderNewDto orderNewDto) {
        Order order = orderMapper.orderNewToOrderDto(orderNewDto);
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(order::setUser);
        for (OrderDetail orderDetail : order.getOrderDetails()) {
            UUID idProduct = orderDetail.getProduct().getId();
            Optional<Product> productOptional = productRepository.findById(idProduct);
            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                double price = product.getPrice();
                orderDetail.setTotalPrice(price * orderDetail.getQuantity());
                orderDetail.setOrder(order);
                entityManager.persist(orderDetail);
            }
        }

        // Save the order
        Order savedOrder = orderRepository.save(order);
        return ResponseEntity.ok(savedOrder);
    }

}
