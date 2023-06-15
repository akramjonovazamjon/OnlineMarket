package com.example.onlinemarket.config;

import com.example.onlinemarket.exception.basket.BasketItemNotFoundException;
import com.example.onlinemarket.exception.category.CategoryNotFoundByIdException;
import com.example.onlinemarket.exception.favourite_product.FavouriteProductExistException;
import com.example.onlinemarket.exception.product.ProductExistByNameException;
import com.example.onlinemarket.exception.product.ProductNotFoundByIdException;
import com.example.onlinemarket.exception.product.ProductNotFoundByNameException;
import com.example.onlinemarket.exception.user.UserExistByEmailException;
import com.example.onlinemarket.exception.user.UserNotFoundByEmailException;
import com.example.onlinemarket.exception.user.UserNotFoundByIdException;
import com.example.onlinemarket.exception.user.UserPasswordNoMatchesException;
import com.example.onlinemarket.dto.ResponseData;
import com.example.onlinemarket.exception.category.CategoryExistByNameException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserExistByEmailException.class)
    public ResponseData<Object> handleUserExistByEmailException(UserExistByEmailException e) {
        return ResponseData.errorOf(-3, e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserNotFoundByEmailException.class)
    public ResponseData<Object> handleUserNotFoundByEmailException(UserNotFoundByEmailException e) {
        return ResponseData.errorOf(-2, e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserPasswordNoMatchesException.class)
    public ResponseData<Object> handleUserPasswordNoMatchesException(UserPasswordNoMatchesException e) {
        return ResponseData.errorOf(-4, e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserNotFoundByIdException.class)
    public ResponseData<Object> handleUserNotFoundByIdException(UserNotFoundByIdException e) {
        return ResponseData.errorOf(-2, e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(CategoryExistByNameException.class)
    public ResponseData<Object> handleCategoryExistByNameException(CategoryExistByNameException e) {
        return ResponseData.errorOf(-3, e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(CategoryNotFoundByIdException.class)
    public ResponseData<Object> handleCategoryNotFoundByIdException(CategoryNotFoundByIdException e) {
        return ResponseData.errorOf(-2, e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ProductNotFoundByIdException.class)
    public ResponseData<Object> handleProductNotFoundByIdException(ProductNotFoundByIdException e) {
        return ResponseData.errorOf(-2, e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ProductNotFoundByNameException.class)
    public ResponseData<Object> handleProductNotFoundByNameException(ProductNotFoundByNameException e) {
        return ResponseData.errorOf(-2, e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ProductExistByNameException.class)
    public ResponseData<Object> handleProductExistByNameException(ProductExistByNameException e) {
        return ResponseData.errorOf(-3, e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(FavouriteProductExistException.class)
    public ResponseData<Object> handleFavouriteProductExistException(FavouriteProductExistException e) {
        return ResponseData.errorOf(-3, e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(BasketItemNotFoundException.class)
    public ResponseData<Object> handleBasketItemNotFoundException(BasketItemNotFoundException e) {
        return ResponseData.errorOf(-2, e.getMessage());
    }

}
