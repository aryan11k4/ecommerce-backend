package com.aryan.backend.dto.product;

import com.aryan.backend.entity.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {
    private String name;
    private Integer categoryId;
    private float price;
}
