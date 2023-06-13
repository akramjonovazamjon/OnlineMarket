package com.example.onlinemarket.entity;

import com.example.onlinemarket.controller.vm.ProductVm;
import com.example.onlinemarket.dto.ProductDto;
import com.example.onlinemarket.dto.ProductEditDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String name;
    private String info;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private Integer quantity;
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;
    @CreatedBy
    private Integer createdBy;
    @LastModifiedBy
    private Integer updatedBy;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;

    public static Product of(ProductDto dto, Category category) {
        return Product.builder()
                .name(dto.name())
                .info(dto.info())
                .price(dto.price())
                .quantity(dto.quantity())
                .category(category)
                .build();
    }

    public ProductVm from() {
        return new ProductVm(id, name, info, price, quantity, createdAt, updatedAt, createdBy, updatedBy, category.getId());
    }

    public void update(ProductEditDto dto) {
        setName(dto.name());
        setInfo(dto.info());
        setPrice(dto.price());
        setQuantity(dto.quantity());
    }

}
