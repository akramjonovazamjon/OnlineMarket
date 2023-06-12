package com.example.onlinemarket.repository;

import com.example.onlinemarket.entity.Role;
import com.example.onlinemarket.model.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRoleEnum(RoleEnum roleEnum);
    List<Role> findAllByRoleEnumIn(Collection<RoleEnum> roleEnum);
}
