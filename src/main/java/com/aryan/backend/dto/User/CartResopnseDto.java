package com.aryan.backend.dto.User;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CartResopnseDto {
    private Integer id;
    private int totalItems;
    private List<CartItemResponseDto> items;
    private BigDecimal totalPrice;
}
