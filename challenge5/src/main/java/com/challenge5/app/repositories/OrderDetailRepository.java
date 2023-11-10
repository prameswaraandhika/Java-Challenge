package com.challenge5.app.repositories;

import com.challenge5.app.model.Order;
import com.challenge5.app.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, UUID> {
}
