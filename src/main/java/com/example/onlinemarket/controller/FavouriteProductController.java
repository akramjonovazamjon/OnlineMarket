package com.example.onlinemarket.controller;

import com.example.onlinemarket.entity.FavouriteProduct;
import com.example.onlinemarket.entity.Product;
import com.example.onlinemarket.payload.ApiResponse;
import com.example.onlinemarket.payload.FavouriteProductDto;
import com.example.onlinemarket.service.FavouriteProductService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favourite_product")
public class FavouriteProductController {
    private final FavouriteProductService favouriteProductService;

    public FavouriteProductController(FavouriteProductService favouriteProductService) {
        this.favouriteProductService = favouriteProductService;
    }

    /**
     * GET USER FAVOURITE PRODUCTS
     *
     * @param userId INTEGER
     * @return LIST
     */
    @GetMapping("/{userId}")
    public HttpEntity<List<Product>> getUserFavouriteProducts(@PathVariable Integer userId) {
        List<Product> userFavouriteProducts = favouriteProductService.getUserFavouriteProducts(userId);
        return ResponseEntity.ok(userFavouriteProducts);
    }

    /**
     * ADD USER FAVOURITE PRODUCT
     *
     * @param favouriteProductDto FavouriteProductDto
     * @return ApiResponse
     */
    @PostMapping
    public HttpEntity<ApiResponse> addFavouriteProduct(@RequestBody FavouriteProductDto favouriteProductDto) {
        ApiResponse apiResponse = favouriteProductService.addFavouriteProduct(favouriteProductDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * DELETE FAVOURITE PRODUCT BY ID
     *
     * @param id INTEGER
     * @return ApiResponse
     */
    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> deleteFavouriteProductById(@PathVariable Integer id) {
        ApiResponse apiResponse = favouriteProductService.deleteFavouriteProductById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * DELETE FAVOURITE PRODUCT BY USER_ID AND PRODUCT_ID
     *
     * @return ApiResponse
     */
    @DeleteMapping("/{userId}/{productId}")
    public HttpEntity<ApiResponse> deleteFavouriteProductByUserIdAndProductId(@PathVariable Integer productId, @PathVariable Integer userId) {
        ApiResponse apiResponse = favouriteProductService.deleteFavouriteProductByUserAndProductId(userId, productId);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }
}
