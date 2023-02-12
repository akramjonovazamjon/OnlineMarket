package com.example.onlinemarket.controller;

import com.example.onlinemarket.payload.AdminRegisterDto;
import com.example.onlinemarket.payload.ApiResponse;
import com.example.onlinemarket.payload.UserDto;
import com.example.onlinemarket.payload.UserLoginDto;
import com.example.onlinemarket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public HttpEntity<?> register(@RequestBody UserDto userDto) {
        ApiResponse apiResponse = userService.register(userDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @GetMapping("/validate")
    public HttpEntity<?> validateAccount(@RequestParam String email, @RequestParam String emailCode) {
        ApiResponse apiResponse = userService.validateAccount(email, emailCode);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody UserLoginDto userLoginDto) {
        ApiResponse apiResponse = userService.login(userLoginDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PostMapping("/register/admin")
    public HttpEntity<?> registerAdmin(@RequestBody AdminRegisterDto adminRegisterDto) {
        ApiResponse apiResponse = userService.registerAdmin(adminRegisterDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

}
