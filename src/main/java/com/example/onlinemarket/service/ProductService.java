package com.example.onlinemarket.service;

import com.example.onlinemarket.entity.Attachment;
import com.example.onlinemarket.entity.Category;
import com.example.onlinemarket.entity.Product;
import com.example.onlinemarket.payload.ApiResponse;
import com.example.onlinemarket.payload.ProductDto;
import com.example.onlinemarket.payload.ProductEditDto;
import com.example.onlinemarket.repository.CategoryRepository;
import com.example.onlinemarket.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product getProductByName(String name) {
        return productRepository.findByNameIgnoreCase(name).orElse(null);
    }

    public List<Product> getProductsByCategoryId(Integer categoryId) {
        return productRepository.findAllByCategoryId(categoryId);
    }

    public ApiResponse addProduct(ProductDto productDto) {
        boolean existsByName = productRepository.existsByName(productDto.getName());
        if (existsByName) {
            return new ApiResponse("This product already exist", false);
        }
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (optionalCategory.isEmpty()) {
            return new ApiResponse("Category not found", false);
        }
        Product product = Product.builder()
                .name(productDto.getName())
                .info(productDto.getInfo())
                .price(productDto.getPrice())
                .quantity(productDto.getQuantity())
                .category(optionalCategory.get())
                .build();
        productRepository.save(product);
        return new ApiResponse("Product successfully added", true);
    }

    public ApiResponse deleteProduct(Integer id) {
        try {
            productRepository.deleteById(id);
            return new ApiResponse("Product successfully deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Product not deleted or not found", false);
        }
    }

    public ApiResponse editProduct(@Valid ProductEditDto productDto, Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            return new ApiResponse("Product not found", false);
        }
        if (productRepository.existsByNameAndIdNot(productDto.getName(), id)) {
            return new ApiResponse("This product already exist", false);
        }
        Product product = optionalProduct.get();
        product.setName(productDto.getName());
        product.setInfo(productDto.getInfo());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        productRepository.save(product);
        return new ApiResponse("Product successfully edited", true);
    }
}
