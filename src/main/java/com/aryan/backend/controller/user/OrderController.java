package com.aryan.backend.controller.user;

import com.aryan.backend.dto.order.OrderResponseDto;
import com.aryan.backend.security.UserPrincipal;
import com.aryan.backend.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/checkout")
    public ResponseEntity<OrderResponseDto> checkout(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        OrderResponseDto response = orderService.checkout(userPrincipal.getUsername());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<OrderResponseDto>> getAllOrders(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        List<OrderResponseDto> response = orderService.getAllOrders(userPrincipal.getUsername());
        return ResponseEntity.ok(response);
    }
}