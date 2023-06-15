package com.example.onlinemarket.controller;

import com.example.onlinemarket.controller.vm.CategoryVm;
import com.example.onlinemarket.dto.ResponseData;
import com.example.onlinemarket.dto.CategoryDto;
import com.example.onlinemarket.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseData<List<CategoryVm>> getCategories() {
        List<CategoryVm> categories = categoryService.getCategories();
        return ResponseData.of(categories);
    }


    @GetMapping("/{id}")
    public ResponseData<CategoryVm> getCategoryById(@PathVariable Integer id) {
        CategoryVm category = categoryService.getCategory(id);
        return ResponseData.of(category);
    }


    @GetMapping("/name/{name}")
    public ResponseData<CategoryVm> getCategoryByName(@PathVariable String name) {
        CategoryVm categoryVm = categoryService.getCategoryByName(name);
        return ResponseData.of(categoryVm);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public ResponseData<CategoryVm> create(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryVm categoryVm = categoryService.create(categoryDto);
        return ResponseData.of(categoryVm);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        categoryService.delete(id);
    }


    @PutMapping("/{id}")
    public ResponseData<CategoryVm> update(@PathVariable Integer id, @RequestBody CategoryDto categoryDto) {
        CategoryVm categoryVm = categoryService.update(id, categoryDto);
        return ResponseData.of(categoryVm);
    }
}
