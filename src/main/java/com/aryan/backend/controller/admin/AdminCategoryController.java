package com.aryan.backend.controller.admin;

import com.aryan.backend.dto.category.CategoryRequestDto;
import com.aryan.backend.dto.category.CategoryResponseDto;
import com.aryan.backend.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminCategoryController {

    private final CategoryService service;

    public AdminCategoryController(CategoryService service){
        this.service = service;
    }

    @PostMapping("/category")
    public ResponseEntity<CategoryResponseDto> createCategory(@RequestBody CategoryRequestDto dto){
        CategoryResponseDto response = service.createCategory(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories(){
        List<CategoryResponseDto> response = service.getAllCategories();
        return ResponseEntity.ok(response);
    }
}
