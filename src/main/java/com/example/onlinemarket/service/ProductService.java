package com.example.onlinemarket.service;

import com.example.onlinemarket.controller.vm.ProductVm;
import com.example.onlinemarket.entity.Category;
import com.example.onlinemarket.entity.Product;
import com.example.onlinemarket.dto.ProductDto;
import com.example.onlinemarket.dto.ProductEditDto;
import com.example.onlinemarket.exception.category.CategoryNotFoundByIdException;
import com.example.onlinemarket.exception.product.ProductExistByNameException;
import com.example.onlinemarket.exception.product.ProductNotFoundByIdException;
import com.example.onlinemarket.exception.product.ProductNotFoundByNameException;
import com.example.onlinemarket.repository.CategoryRepository;
import com.example.onlinemarket.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public List<ProductVm> getProducts() {
        return productRepository.findAll().stream().map(Product::from).toList();
    }

    public ProductVm getProductById(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundByIdException(id)).from();
    }

    public ProductVm getProductByName(String name) {
        return productRepository.findByNameIgnoreCase(name).orElseThrow(() -> new ProductNotFoundByNameException(name)).from();
    }

    public List<ProductVm> getProductsByCategoryId(Integer categoryId) {
        return productRepository.findAllByCategoryId(categoryId).stream().map(Product::from).toList();
    }

    public ProductVm create(ProductDto dto) {
        boolean existsByName = productRepository.existsByName(dto.name());
        if (existsByName) {
            throw new ProductExistByNameException(dto.name());
        }
        Optional<Category> optionalCategory = categoryRepository.findById(dto.categoryId());
        if (optionalCategory.isEmpty()) {
            throw new CategoryNotFoundByIdException(dto.categoryId());
        }
        Product product = Product.of(dto, optionalCategory.get());
        return productRepository.save(product).from();
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    public void update(@Valid ProductEditDto dto, Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundByIdException(id);
        }
        if (productRepository.existsByNameAndIdNot(dto.name(), id)) {
            throw new ProductExistByNameException(dto.name());
        }
        Product product = optionalProduct.get();
        product.update(dto);
        productRepository.save(product);
    }
}
