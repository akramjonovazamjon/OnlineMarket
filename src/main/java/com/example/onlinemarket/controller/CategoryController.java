package com.example.onlinemarket.controller;

import com.example.onlinemarket.entity.Category;
import com.example.onlinemarket.payload.ApiResponse;
import com.example.onlinemarket.payload.CategoryDto;
import com.example.onlinemarket.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * GET ALL CATEGORIES
     *
     * @return LISt
     */
    @GetMapping
    public HttpEntity<List<Category>> getCategories() {
        List<Category> categories = categoryService.getCategories();
        return ResponseEntity.ok(categories);
    }

    /**
     * GET CATEGORY BY ID
     *
     * @param id INTEGER
     * @return CATEGORY
     */
    @GetMapping("/{id}")
    public HttpEntity<Category> getCategoryById(@PathVariable Integer id) {
        Category category = categoryService.getCategory(id);
        return ResponseEntity.status(Objects.isNull(category) ? HttpStatus.CONFLICT : HttpStatus.OK).body(category);
    }

    /**
     * GET CATEGORY BY NAME
     *
     * @param name STRING
     * @return CATEGORY
     */
    @GetMapping("/name/{name}")
    public HttpEntity<Category> getCategoryByName(@PathVariable String name) {
        Category category = categoryService.getCategoryByName(name);
        return ResponseEntity.status(Objects.isNull(category) ? HttpStatus.NOT_FOUND : HttpStatus.OK).body(category);
    }

    /**
     * ADD CATEGORY
     *
     * @param categoryDto CategoryDto
     * @return ApiResponse
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN') and hasAuthority('CREATE')")
    @PostMapping()
    public HttpEntity<ApiResponse> addCategory(@Valid @RequestBody CategoryDto categoryDto) {
        ApiResponse categoryAdded = categoryService.addCategory(categoryDto);
        return ResponseEntity.status(categoryAdded.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(categoryAdded);
    }

    /**
     * DELETE CATEGORY
     *
     * @param id INTEGER
     * @return ApiResponse
     */
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> deleteCategory(@PathVariable Integer id) {
        ApiResponse apiResponse = categoryService.deleteCategory(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    /**
     * UPDATE CATEGORY
     *
     * @param id          INTEGER
     * @param categoryDto CategoryDto
     * @return ApiResponse
     */
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN') and hasAuthority('UPDATE')")
    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> editCategory(@PathVariable Integer id, @RequestBody CategoryDto categoryDto) {
        ApiResponse apiResponse = categoryService.editCategory(id, categoryDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }
}
