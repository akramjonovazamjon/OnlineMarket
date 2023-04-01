package com.example.onlinemarket.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fileOriginalName;
    private String contentType;
    private byte[] mainContent;
    @OneToOne@OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;
}
