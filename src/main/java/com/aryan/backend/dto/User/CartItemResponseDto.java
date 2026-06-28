package com.aryan.backend.dto.User;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CartItemResponseDto {
    private Integer id;
    private Integer quantity;
    private BigDecimal productPrice;

    private Integer productId;
    private String productName;
}
