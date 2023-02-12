package com.example.onlinemarket.service;

import com.example.onlinemarket.entity.Permission;
import com.example.onlinemarket.entity.Role;
import com.example.onlinemarket.entity.User;
import com.example.onlinemarket.model.PermissionEnum;
import com.example.onlinemarket.model.RoleEnum;
import com.example.onlinemarket.payload.AdminRegisterDto;
import com.example.onlinemarket.payload.ApiResponse;
import com.example.onlinemarket.payload.UserDto;
import com.example.onlinemarket.payload.UserLoginDto;
import com.example.onlinemarket.repository.PermissionRepository;
import com.example.onlinemarket.repository.RoleRepository;
import com.example.onlinemarket.repository.UserRepository;
import com.example.onlinemarket.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender javaMailSender;
    private final JwtProvider jwtProvider;

    public ApiResponse register(UserDto userDto) {
        boolean existsByEmail = userRepository.existsByEmail(userDto.getEmail());
        if (existsByEmail) {
            return new ApiResponse("User already exist with this email", false);
        }
        Role role = roleRepository.findByRoleEnum(RoleEnum.ROLE_USER);
        Permission permission = permissionRepository.findByPermissionEnum(PermissionEnum.READ);
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmailCode(UUID.randomUUID().toString());
        user.setRoles(List.of(role));
        user.setPermissions(List.of(permission));
        userRepository.save(user);
        sendEmail(user.getEmail(), user.getEmailCode());
        return new ApiResponse("You are successfully registered. We have sent email to you to activate your account.", true);
    }

    public Boolean sendEmail(String sendingEmail, String emailCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("akramjonovazamjon57@gmail.com");
        message.setTo(sendingEmail);
        message.setSubject("Activate profile");
        message.setText("You can activate your profile via this link ⬇️\n" +
                "http://localhost:8080/api/auth/validate?email=" + sendingEmail + "&emailCode=" + emailCode);
        javaMailSender.send(message);
        return true;
    }

    public ApiResponse validateAccount(String sendingEmail, String emailCode) {
        Optional<User> optionalUser = userRepository.findByEmailAndEmailCode(sendingEmail, emailCode);
        if (optionalUser.isEmpty()) {
            return new ApiResponse("User not found", false);
        }
        User user = optionalUser.get();
        user.setEnabled(true);
        user.setEmailCode(null);
        userRepository.save(user);
        return new ApiResponse("Your Account activated", true);
    }

    public ApiResponse login(UserLoginDto userLoginDto) {
        Optional<User> optionalUser = userRepository.findByEmail(userLoginDto.getEmail());
        if (optionalUser.isEmpty()) {
            return new ApiResponse("User not found", false);
        }
        User user = optionalUser.get();
        if (passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword())) {
            String token = jwtProvider.generateToken(user.getEmail());
            return new ApiResponse("Successfully logged in", true, "Bearer " + token);
        }
        return new ApiResponse("User not found", false);
    }

    public ApiResponse registerAdmin(AdminRegisterDto adminRegisterDto) {
        boolean existsByEmail = userRepository.existsByEmail(adminRegisterDto.getEmail());
        if (existsByEmail){
            return new ApiResponse("User already exist", false);
        }
        List<Role> roles = roleRepository.findAllByRoleEnumIn(adminRegisterDto.getRoles());
        List<Permission> permissions = permissionRepository.findAllByPermissionEnumIn(adminRegisterDto.getPermissions());
        User user = new User();
        user.setFirstName(adminRegisterDto.getFirstName());
        user.setLastName(adminRegisterDto.getLastName());
        user.setEmail(adminRegisterDto.getEmail());
        user.setPassword(passwordEncoder.encode(adminRegisterDto.getPassword()));
        user.setRoles(roles);
        user.setPermissions(permissions);
        user.setEnabled(true);
        userRepository.save(user);
        return new ApiResponse("Admin created", true);
    }
}
