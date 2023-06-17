package com.example.onlinemarket.controller;

import com.example.onlinemarket.controller.vm.FavouriteProductVm;
import com.example.onlinemarket.dto.ResponseData;
import com.example.onlinemarket.entity.Product;
import com.example.onlinemarket.dto.FavouriteProductDto;
import com.example.onlinemarket.service.FavouriteProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/favourite-products")
public class FavouriteProductController {
    private final FavouriteProductService favouriteProductService;

    @GetMapping("/user")
    public ResponseData<List<Product>> getUserFavouriteProducts() {
        List<Product> userFavouriteProducts = favouriteProductService.getUserFavouriteProducts();
        return ResponseData.of(userFavouriteProducts);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseData<FavouriteProductVm> create(@RequestBody FavouriteProductDto dto) {
        FavouriteProductVm favouriteProductVm = favouriteProductService.create(dto);
        return ResponseData.of(favouriteProductVm);
    }


    @DeleteMapping("/{id}")
    public void deleteFavouriteProductById(@PathVariable Integer id) {
        favouriteProductService.deleteFavouriteProductById(id);
    }


    @DeleteMapping("/user/{productId}")
    public void deleteFavouriteProductByUserIdAndProductId(@PathVariable Integer productId) {
        favouriteProductService.deleteFavouriteProductByUserAndProductId(productId);
    }
}
