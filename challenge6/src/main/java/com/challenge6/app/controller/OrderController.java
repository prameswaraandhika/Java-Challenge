package com.challenge6.app.controller;

import com.challenge6.app.model.dtos.OrderNewDto;
import com.challenge6.app.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = {"/restapi/order"})
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrdersById(@PathVariable UUID id){
        return orderService.listOrdersBy(id);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> doOrder(@PathVariable UUID id, @RequestBody OrderNewDto orderNewDto){
        return orderService.order(id, orderNewDto);
    }






}
