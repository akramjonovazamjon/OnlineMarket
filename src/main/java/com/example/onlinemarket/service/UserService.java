package com.example.onlinemarket.service;

import com.example.onlinemarket.entity.User;
import com.example.onlinemarket.payload.ApiResponse;
import com.example.onlinemarket.payload.UserLoginDto;
import com.example.onlinemarket.payload.UserDto;
import com.example.onlinemarket.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public ApiResponse registerUser(UserDto userRegisterDto) {
        boolean existsByPhoneNumber = userRepository.existsByPhoneNumber(userRegisterDto.getPhoneNumber());
        if (existsByPhoneNumber) {
            return new ApiResponse("User exist with this phone number", false);
        }
        User user = User.builder()
                .firstName(userRegisterDto.getFirstName())
                .lastName(userRegisterDto.getLastName())
                .phoneNumber(userRegisterDto.getPhoneNumber())
                .password(userRegisterDto.getPassword())
                .build();
        userRepository.save(user);
        return new ApiResponse("User successfully registered", true);
    }

    public ApiResponse login(UserLoginDto userLoginDto) {
        boolean exists = userRepository.existsByPhoneNumberAndPassword(userLoginDto.getPhoneNumber(), userLoginDto.getPassword());
        if (!exists) {
            return new ApiResponse("User not found", false);
        }
        return new ApiResponse("User is exist", true);
    }

    public ApiResponse deleteUser(Integer id) {
        try {
            userRepository.deleteById(id);
            return new ApiResponse("User successfully deleted", true);
        } catch (Exception e) {
            return new ApiResponse("User not found", false);
        }
    }

    public ApiResponse editUser(Integer id, UserDto userDto) {

        boolean existsByPhoneNumberAndIdNot = userRepository.existsByPhoneNumberAndIdNot(userDto.getPhoneNumber(), id);
        if (existsByPhoneNumberAndIdNot) {
            return new ApiResponse("User exist with this phone number", false);
        }

        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return new ApiResponse("User not found", false);
        }
        User user = optionalUser.get();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
        return new ApiResponse("User successfully edited", true);
    }
}
