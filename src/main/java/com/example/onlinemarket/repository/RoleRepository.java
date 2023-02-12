package com.example.onlinemarket.repository;

import com.example.onlinemarket.entity.Role;
import com.example.onlinemarket.model.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRoleEnum(RoleEnum roleEnum);
    List<Role> findAllByRoleEnumIn(Collection<RoleEnum> roleEnum);
}
