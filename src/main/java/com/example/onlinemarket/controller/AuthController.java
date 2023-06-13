package com.example.onlinemarket.controller;

import com.example.onlinemarket.dto.*;
import com.example.onlinemarket.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService userService;

    @PostMapping("/register")
    public ResponseData<String> register(@RequestBody UserDto dto) {
        String register = userService.register(dto);
        return ResponseData.of(register);
    }

    @GetMapping("/validate")
    public ResponseData<TokenResult> validateAccount(@RequestParam String email, @RequestParam String emailCode) {
        TokenResult result = userService.validateAccount(email, emailCode);
        return ResponseData.of(result);
    }

    @PostMapping("/login")
    public ResponseData<TokenResult> login(@RequestBody UserLoginDto dto) {
        TokenResult tokenResult = userService.login(dto);
        return ResponseData.of(tokenResult);
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PostMapping("/register/admin")
    public ResponseData<String> registerUserByAdmin(@RequestBody AdminRegisterDto dto) {
        String result = userService.registerAdmin(dto);
        return ResponseData.of(result);
    }

}
