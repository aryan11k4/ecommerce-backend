package com.aryan.backend.service;

import com.aryan.backend.dto.category.CategoryRequestDto;
import com.aryan.backend.dto.category.CategoryResponseDto;
import com.aryan.backend.entity.Category;
import com.aryan.backend.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository){
        this.repository = repository;
    }

    public CategoryResponseDto createCategory(CategoryRequestDto dto) {
        Category category = new Category();
        category.setName(dto.getName());

        category = repository.save(category);

        CategoryResponseDto response = new CategoryResponseDto();
        response.setId(category.getId());
        response.setName(category.getName());

        return response;
    }

    public List<CategoryResponseDto> getAllCategories() {
        List<Category> categories = repository.findAll();

        List<CategoryResponseDto> response = new ArrayList<>();

        for(Category c : categories){
            CategoryResponseDto dto = new CategoryResponseDto();
            dto.setName(c.getName());
            dto.setId(c.getId());

            response.add(dto);
        }

        return response;
    }
}
