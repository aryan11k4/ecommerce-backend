package com.aryan.backend.dto.order;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderItemResponseDto {
    private Integer id;
    private Integer productId;
    private String productName;
    private int quantity;
    private BigDecimal productPrice;
}