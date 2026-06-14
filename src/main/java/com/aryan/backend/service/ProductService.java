package com.aryan.backend.service;

import com.aryan.backend.dto.product.ProductRequestDto;
import com.aryan.backend.dto.product.ProductResponseDto;
import com.aryan.backend.entity.Category;
import com.aryan.backend.entity.Product;
import com.aryan.backend.repository.CategoryRepository;
import com.aryan.backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository repo, CategoryRepository categoryRepository){
        this.productRepository = repo;
        this.categoryRepository = categoryRepository;
    }

    public ProductResponseDto addProduct(ProductRequestDto dto) {
        Product prod = new Product();
        Category cat = categoryRepository
                .findById(dto.getCategoryId())
                        .orElseThrow(() -> new RuntimeException("Category not found"));

        prod.setName(dto.getName());
        prod.setCategory(cat);
        prod.setPrice(dto.getPrice());

        productRepository.save(prod);

        ProductResponseDto response = new ProductResponseDto();
        response.setName(prod.getName());
        response.setId(prod.getId());
        response.setCategoryId(cat.getId());
        response.setCategoryName(cat.getName());
        response.setPrice(prod.getPrice());

        return response;
    }

    public List<ProductResponseDto> getAllProducts() {
        List<ProductResponseDto> responseDtos = new ArrayList<>();
        List<Product> products = productRepository.findAll();

        for(Product prod : products){
            ProductResponseDto response = new ProductResponseDto();
            response.setName(prod.getName());
            response.setId(prod.getId());
            response.setCategoryId(prod.getCategory().getId());
            response.setCategoryName(prod.getCategory().getName());
            response.setPrice(prod.getPrice());

            responseDtos.add(response);
        }

        return responseDtos;
    }
}
