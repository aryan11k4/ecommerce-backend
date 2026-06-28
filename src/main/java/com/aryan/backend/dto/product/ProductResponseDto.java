package com.aryan.backend.dto.product;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductResponseDto {
    private Integer id;
    private String name;
    private String categoryName;
    private Integer categoryId;
    private BigDecimal price;
}
