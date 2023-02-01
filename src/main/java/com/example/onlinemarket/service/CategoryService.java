package com.example.onlinemarket.service;

import com.example.onlinemarket.entity.Category;
import com.example.onlinemarket.payload.ApiResponse;
import com.example.onlinemarket.payload.CategoryDto;
import com.example.onlinemarket.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategory(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public Category getCategoryByName(String name) {
        return categoryRepository.findByNameIgnoreCase(name).orElse(null);
    }

    public ApiResponse addCategory(CategoryDto categoryDto) {
        boolean existsByName = categoryRepository.existsByName(categoryDto.getName());
        if (existsByName) {
            return new ApiResponse("This category already exist", false);
        }
        Category category = Category.builder()
                .name(categoryDto.getName())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
        categoryRepository.save(category);
        return new ApiResponse("Category successfully added", true);
    }

    public ApiResponse deleteCategory(Integer id) {
        try {
            categoryRepository.deleteById(id);
            return new ApiResponse("Category successfully deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Category not found", false);
        }
    }

    public ApiResponse editCategory(Integer id, CategoryDto categoryDto) {

        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            return new ApiResponse("Category not found", false);
        }

        boolean existsByNameAndIdNot = categoryRepository.existsByNameAndIdNot(categoryDto.getName(), id);
        if (existsByNameAndIdNot) {
            return new ApiResponse("This category already exist", false);
        }

        Category category = optionalCategory.get();
        category.setName(categoryDto.getName());
        category.setUpdatedDate(LocalDateTime.now());
        categoryRepository.save(category);
        return new ApiResponse("Category successfully edited", true);
    }
}
