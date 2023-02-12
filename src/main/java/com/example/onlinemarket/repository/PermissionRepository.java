package com.example.onlinemarket.repository;

import com.example.onlinemarket.entity.Permission;
import com.example.onlinemarket.model.PermissionEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    Permission findByPermissionEnum(PermissionEnum permissionEnum);
    List<Permission> findAllByPermissionEnumIn(Collection<PermissionEnum> permissionEnum);
}
