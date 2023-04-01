package com.example.onlinemarket.service;

import com.example.onlinemarket.controller.vm.CategoryVm;
import com.example.onlinemarket.entity.Category;
import com.example.onlinemarket.dto.CategoryDto;
import com.example.onlinemarket.exception.category.CategoryExistByNameException;
import com.example.onlinemarket.exception.category.CategoryNotFoundByIdException;
import com.example.onlinemarket.exception.category.CategoryNotFoundByNameException;
import com.example.onlinemarket.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryVm> getCategories() {
        return categoryRepository.findAll().stream().map(Category::from).toList();
    }

    public CategoryVm getCategory(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundByIdException(id)).from();
    }

    public CategoryVm getCategoryByName(String name) {
        return categoryRepository.findByNameIgnoreCase(name).orElseThrow(() -> new CategoryNotFoundByNameException(name)).from();
    }

    public CategoryVm create(CategoryDto dto) {
        boolean existsByName = categoryRepository.existsByName(dto.name());
        if (existsByName) {
            throw new CategoryExistByNameException(dto.name());
        }
        Category category = Category.of(dto);
        return categoryRepository.save(category).from();
    }

    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }

    public CategoryVm update(Integer id, CategoryDto dto) {

        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            throw new CategoryNotFoundByIdException(id);
        }

        boolean existsByNameAndIdNot = categoryRepository.existsByNameAndIdNot(dto.name(), id);
        if (existsByNameAndIdNot) {
            throw new CategoryExistByNameException(dto.name());
        }

        Category category = optionalCategory.get();
        category.setName(dto.name());
        return categoryRepository.save(category).from();
    }
}
