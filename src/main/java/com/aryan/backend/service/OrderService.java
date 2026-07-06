package com.aryan.backend.service;

import com.aryan.backend.dto.order.OrderItemResponseDto;
import com.aryan.backend.dto.order.OrderResponseDto;
import com.aryan.backend.entity.*;
import com.aryan.backend.repository.CartItemRepository;
import com.aryan.backend.repository.OrderRepository;
import com.aryan.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepo;
    private final UserRepository userRepo;
    private final CartItemRepository cartItemRepo;

    public OrderService(OrderRepository orderRepo, UserRepository userRepo, CartItemRepository cartItemRepo) {
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
        this.cartItemRepo = cartItemRepo;
    }

    @Transactional
    public OrderResponseDto checkout(String email) {
        User u = userRepo.findByEmail(email);
        Cart cart = u.getCart();

        if (cart.getCartItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order order = new Order();
        order.setUser(u);

        BigDecimal total = BigDecimal.ZERO;
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem ci : cart.getCartItems()) {
            OrderItem oi = new OrderItem();
            oi.setOrder(order);
            oi.setProduct(ci.getProduct());
            oi.setQuantity(ci.getQuantity());
            oi.setProductPrice(ci.getProductPrice());
            orderItems.add(oi);

            total = total.add(ci.getProductPrice().multiply(BigDecimal.valueOf(ci.getQuantity())));
        }

        order.setOrderItems(orderItems);
        order.setTotalPrice(total);
        orderRepo.save(order);

        cartItemRepo.deleteAll(cart.getCartItems());
        cart.getCartItems().clear();

        return toDto(order);
    }

    public List<OrderResponseDto> getAllOrders(String email) {
        User u = userRepo.findByEmail(email);
        List<Order> orders = orderRepo.findByUserIdOrderByOrderDateDesc(u.getId());

        List<OrderResponseDto> result = new ArrayList<>();
        for (Order o : orders) {
            result.add(toDto(o));
        }
        return result;
    }

    private OrderResponseDto toDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(order.getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setTotalPrice(order.getTotalPrice());

        List<OrderItemResponseDto> items = new ArrayList<>();
        for (OrderItem oi : order.getOrderItems()) {
            OrderItemResponseDto i = new OrderItemResponseDto();
            i.setId(oi.getId());
            i.setProductId(oi.getProduct().getId());
            i.setProductName(oi.getProduct().getName());
            i.setQuantity(oi.getQuantity());
            i.setProductPrice(oi.getProductPrice());
            items.add(i);
        }
        dto.setItems(items);

        return dto;
    }
}