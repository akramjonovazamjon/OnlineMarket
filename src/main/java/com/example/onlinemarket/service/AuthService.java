package com.example.onlinemarket.service;

import com.example.onlinemarket.entity.Permission;
import com.example.onlinemarket.entity.Role;
import com.example.onlinemarket.entity.User;
import com.example.onlinemarket.exception.user.UserExistByEmailException;
import com.example.onlinemarket.exception.user.UserNotFoundByEmailException;
import com.example.onlinemarket.exception.user.UserPasswordNoMatchesException;
import com.example.onlinemarket.model.PermissionEnum;
import com.example.onlinemarket.model.RoleEnum;
import com.example.onlinemarket.dto.*;
import com.example.onlinemarket.repository.PermissionRepository;
import com.example.onlinemarket.repository.RoleRepository;
import com.example.onlinemarket.repository.UserRepository;
import com.example.onlinemarket.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender javaMailSender;
    private final JwtProvider jwtProvider;

    public String  register(UserDto userDto) {
        boolean existsByEmail = userRepository.existsByEmail(userDto.email());
        if (existsByEmail) {
            throw new UserExistByEmailException(userDto.email());
        }
        Role role = roleRepository.findByRoleEnum(RoleEnum.ROLE_USER);
        Permission permission = permissionRepository.findByPermissionEnum(PermissionEnum.READ);
        User user = User.of(userDto, List.of(role), List.of(permission), passwordEncoder);
        userRepository.save(user);
        sendEmail(user.getEmail(), user.getEmailCode());
        return "You are successfully registered. We have sent email to you to activate your account.";
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

    public TokenResult validateAccount(String sendingEmail, String emailCode) {
        Optional<User> optionalUser = userRepository.findByEmailAndEmailCode(sendingEmail, emailCode);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundByEmailException(sendingEmail);
        }
        User user = optionalUser.get();
        user.setEnabled(true);
        user.setEmailCode(null);
        userRepository.save(user);
        String token = jwtProvider.generateToken(user.getEmail());
        return new TokenResult(token);
    }

    public TokenResult login(UserLoginDto dto) {
        Optional<User> optionalUser = userRepository.findByEmail(dto.email());
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundByEmailException(dto.email());
        }
        User user = optionalUser.get();
        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new UserPasswordNoMatchesException(dto.password());
        }
        String token = jwtProvider.generateToken(user.getEmail());
        return new TokenResult(token);
    }

    public String registerAdmin(AdminRegisterDto dto) {
        boolean existsByEmail = userRepository.existsByEmail(dto.email());
        if (existsByEmail){
            throw new UserExistByEmailException(dto.email());
        }
        List<Role> roles = roleRepository.findAllByRoleEnumIn(dto.roles());
        List<Permission> permissions = permissionRepository.findAllByPermissionEnumIn(dto.permissions());
        User user = User.of(new UserDto(dto.firstName(), dto.lastName(), dto.email(), dto.password()), roles, permissions, passwordEncoder);
        user.setEnabled(true);
        userRepository.save(user);
        return "User created";
    }
}
