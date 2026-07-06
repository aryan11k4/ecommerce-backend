package com.aryan.backend.dto.order;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderResponseDto {
    private Integer id;
    private LocalDateTime orderDate;
    private BigDecimal totalPrice;
    private List<OrderItemResponseDto> items;
}