package com.example.onlinemarket.controller;

import com.example.onlinemarket.controller.vm.ProductVm;
import com.example.onlinemarket.dto.ResponseData;
import com.example.onlinemarket.dto.ProductDto;
import com.example.onlinemarket.dto.ProductEditDto;
import com.example.onlinemarket.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseData<List<ProductVm>> getProducts() {
        List<ProductVm> products = productService.getProducts();
        return ResponseData.of(products);
    }


    @GetMapping("/{id}")
    public ResponseData<ProductVm> getProductById(@PathVariable Integer id) {
        ProductVm productById = productService.getProductById(id);
        return ResponseData.of(productById);
    }


    @GetMapping("/category/{categoryId}")
    public ResponseData<List<ProductVm>> getProductsByCategoryId(@PathVariable Integer categoryId) {
        List<ProductVm> productsByCategoryId = productService.getProductsByCategoryId(categoryId);
        return ResponseData.of(productsByCategoryId);
    }


    @GetMapping("/name/{name}")
    public ResponseData<ProductVm> getProductById(@PathVariable String name) {
        ProductVm productByName = productService.getProductByName(name);
        return ResponseData.of(productByName);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseData<ProductVm> create(@Valid @RequestBody ProductDto dto) {
        ProductVm productVm = productService.create(dto);
        return ResponseData.of(productVm);
    }


    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
    }


    @PatchMapping("/{id}")
    public void update(@RequestBody ProductEditDto dto, @PathVariable Integer id) {
        productService.update(dto, id);
    }

}
