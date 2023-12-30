package com.prameswaradev.OrderService.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Table(name = "tbl_order_detail")
@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID idOrder;
    private UUID idProduct;
    private Integer quantity;
    private Double totalPrice;
//    @ManyToOne(targetEntity = Product.class)
//    private Product product;
//
//    @ManyToOne(targetEntity = Order.class)
//    @JsonIgnore
//    private Order order;
}
