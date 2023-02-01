package com.example.onlinemarket.controller;

import com.example.onlinemarket.entity.User;
import com.example.onlinemarket.payload.ApiResponse;
import com.example.onlinemarket.payload.UserLoginDto;
import com.example.onlinemarket.payload.UserDto;
import com.example.onlinemarket.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * GET ALL USERS
     *
     * @return LIST
     */
    @GetMapping
    public HttpEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    /**
     * GET USER BY ID
     *
     * @param id INTEGER
     * @return USER
     */
    @GetMapping("/{id}")
    public HttpEntity<?> getUserById(@PathVariable Integer id) {
        User userById = userService.getUserById(id);
        return ResponseEntity.ok(Objects.isNull(userById) ? "User not found" : userById);
    }

    /**
     * REGISTER USER
     *
     * @param userDto UserRegisterDto
     * @return ApiResponse
     */
    @PostMapping("/register")
    public HttpEntity<ApiResponse> register(@Valid @RequestBody UserDto userDto) {
        ApiResponse response = userService.registerUser(userDto);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(response);
    }

    /**
     * USER LOGIN
     *
     * @param userLoginDto UserLoginDto
     * @return ApiResponse
     */
    @GetMapping("/login")
    public HttpEntity<ApiResponse> login(@Valid @RequestBody UserLoginDto userLoginDto) {
        ApiResponse login = userService.login(userLoginDto);
        return ResponseEntity.status(login.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(login);
    }

    /**
     * DELETE USER BY ID
     *
     * @param id Integer
     * @return ApiResponse
     */
    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> deleteUser(@PathVariable Integer id) {
        ApiResponse deleted = userService.deleteUser(id);
        return ResponseEntity.status(deleted.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(deleted);
    }

    /**
     * UPDATE USER
     *
     * @param userDto UserDto
     * @param id      Integer
     * @return ApiResponse
     */
    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> editUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer id) {
        ApiResponse apiResponse = userService.editUser(id, userDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

}
