package com.example.onlinemarket.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
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
    @Column(nullable = false)
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    @ManyToOne
    private Category category;
    @JsonIgnore
    @OneToOne(mappedBy = "product", cascade = CascadeType.REMOVE)
    private Attachment attachment;

}
