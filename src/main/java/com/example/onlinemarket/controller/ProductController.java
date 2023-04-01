package com.example.onlinemarket.controller;

import com.example.onlinemarket.controller.vm.ProductVm;
import com.example.onlinemarket.dto.ResponseData;
import com.example.onlinemarket.dto.ProductDto;
import com.example.onlinemarket.dto.ProductEditDto;
import com.example.onlinemarket.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

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


    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN') and hasAuthority('CREATE')")
    @PostMapping
    public ResponseData<ProductVm> addProduct(@Valid @RequestBody ProductDto dto) {
        ProductVm productVm = productService.create(dto);
        return ResponseData.of(productVm);
    }


    @PreAuthorize("hasAnyRole('SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN') and hasAuthority('UPDATE')")
    @PatchMapping("/{id}")
    public void editProduct(@RequestBody ProductEditDto productEditDto, @PathVariable Integer id) {
        productService.update(productEditDto, id);
    }

}
