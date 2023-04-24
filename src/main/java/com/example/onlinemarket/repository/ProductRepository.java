package com.example.onlinemarket.repository;

import com.example.onlinemarket.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByNameIgnoreCase(String name);
    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Integer id);

    List<Product> findAllByCategoryId(Integer categoryId);

    List<Product> findAllByIdIn(Collection<Integer> id);

}
