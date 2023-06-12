package com.example.onlinemarket.repository;

import com.example.onlinemarket.entity.Permission;
import com.example.onlinemarket.model.PermissionEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    Permission findByPermissionEnum(PermissionEnum permissionEnum);
    List<Permission> findAllByPermissionEnumIn(Collection<PermissionEnum> permissionEnum);
}
