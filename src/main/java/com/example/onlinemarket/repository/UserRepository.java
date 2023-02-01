package com.example.onlinemarket.repository;

import com.example.onlinemarket.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByPhoneNumberAndPassword(String phoneNumber, String password);
    boolean existsByPhoneNumberAndIdNot(String phoneNumber, Integer id);
}
