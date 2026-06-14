package com.aryan.backend.controller.admin;


import com.aryan.backend.dto.product.ProductRequestDto;
import com.aryan.backend.dto.product.ProductResponseDto;
import com.aryan.backend.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin/product")
public class AdminProductController {
    private ProductService service;

    public AdminProductController(ProductService service){
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity<ProductResponseDto> addProduct(@RequestBody ProductRequestDto dto){
        ProductResponseDto response = service.addProduct(dto);

//        return (response == null) ?

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts(){
        List<ProductResponseDto> responseDtos = service.getAllProducts();

        return ResponseEntity.ok(responseDtos);
    }

}
