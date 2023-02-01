package com.example.onlinemarket.controller;

import com.example.onlinemarket.entity.Product;
import com.example.onlinemarket.payload.ApiResponse;
import com.example.onlinemarket.payload.ProductDto;
import com.example.onlinemarket.payload.ProductEditDto;
import com.example.onlinemarket.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * GET ALL PRODUCTS
     *
     * @return LIST
     */
    @GetMapping
    public HttpEntity<List<Product>> getProducts() {
        List<Product> products = productService.getProducts();
        return ResponseEntity.ok(products);
    }

    /**
     * GET PRODUCT BY ID
     *
     * @param id INTEGER
     * @return PRODUCT
     */
    @GetMapping("/{id}")
    public HttpEntity<Product> getProductById(@PathVariable Integer id) {
        Product productById = productService.getProductById(id);
        return ResponseEntity.status(Objects.isNull(productById) ? HttpStatus.NOT_FOUND : HttpStatus.OK).body(productById);
    }

    /**
     * GET PRODUCTS BY CATEGORY ID
     *
     * @param categoryId INTEGER
     * @return LIST
     */
    @GetMapping("/category/{categoryId}")
    public HttpEntity<List<Product>> getProductsByCategoryId(@PathVariable Integer categoryId) {
        List<Product> productsByCategoryId = productService.getProductsByCategoryId(categoryId);
        return ResponseEntity.ok(productsByCategoryId);
    }

    /**
     * GET PRODUCT BY NAME
     *
     * @param name STRING
     * @return PRODUCT
     */
    @GetMapping("/name/{name}")
    public HttpEntity<Product> getProductById(@PathVariable String name) {
        Product productByName = productService.getProductByName(name);
        return ResponseEntity.status(Objects.isNull(productByName) ? HttpStatus.NOT_FOUND : HttpStatus.OK).body(productByName);
    }

    /**
     * ADD PRODUCT
     *
     * @param productDto PRODUCT DTO
     * @return ApiResponse
     */
    @PostMapping
    public HttpEntity<ApiResponse> addProduct(@Valid @RequestBody ProductDto productDto) {
        ApiResponse apiResponse = productService.addProduct(productDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * DELETE PRODUCT BY ID
     *
     * @param id INTEGER
     * @return ApiResponse
     */
    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> deleteProduct(@PathVariable Integer id) {
        ApiResponse apiResponse = productService.deleteProduct(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * UPDATE PRODUCT
     *
     * @param productEditDto ProductEditDto
     * @param id             INTEGER
     * @return ApiResponse
     */
    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> editProduct(@RequestBody ProductEditDto productEditDto, @PathVariable Integer id) {
        ApiResponse apiResponse = productService.editProduct(productEditDto, id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

}
