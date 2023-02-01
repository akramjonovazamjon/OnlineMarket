package com.example.onlinemarket.repository;

import com.example.onlinemarket.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Integer id);

    Optional<Category> findByNameIgnoreCase(String name);
}
