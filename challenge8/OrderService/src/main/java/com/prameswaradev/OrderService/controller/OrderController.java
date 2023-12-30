package com.prameswaradev.OrderService.controller;

import com.prameswaradev.OrderService.model.dtos.OrderDetailNewDto;
import com.prameswaradev.OrderService.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = {"/order"})
public class OrderController {

    private final OrderService orderService;

//    @GetMapping("/{email}")
    @PreAuthorize("hasAuthority('Customer')")
    @GetMapping("/{email}")
    public ResponseEntity<?> getOrdersById(@PathVariable String email){
        return orderService.listOrdersByEmail(email);
    }

    @PostMapping("/{idOrder}")
    public ResponseEntity<?> doOrder(UUID idOrder){
        return orderService.order(idOrder);
    }

    @PreAuthorize("hasAuthority('Customer')")
    @PostMapping("/{email}")
    public ResponseEntity<?> addToWishlist(@PathVariable String email, @RequestBody List<OrderDetailNewDto> orderDetailNewDtoList){
        return orderService.saveToWishlist(email, orderDetailNewDtoList);
    }





}
