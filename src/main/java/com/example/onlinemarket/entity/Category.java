package com.example.onlinemarket.entity;

import com.example.onlinemarket.controller.vm.CategoryVm;
import com.example.onlinemarket.dto.CategoryDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
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
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String name;
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;
    @CreatedBy
    private Integer createdBy;
    @LastModifiedBy
    private Integer updatedBy;

    public static Category of(CategoryDto dto) {
        return Category.builder()
                .name(dto.name())
                .build();
    }

    public CategoryVm from() {
        return new CategoryVm(id, name, createdAt, updatedAt, createdBy, updatedBy);
    }
}
