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
@Table(name = "attachments")
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fileOriginalName;
    private String contentType;
    private byte[] mainContent;
    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    public static Attachment of(String contentType, byte[] mainContent, String originalFileName, Product product) {
        return Attachment.builder()
                .contentType(contentType)
                .mainContent(mainContent)
                .fileOriginalName(originalFileName)
                .product(product)
                .build();
    }

    public void update(String fileOriginalName, String contentType, byte[] mainContent) {
        setFileOriginalName(fileOriginalName);
        setContentType(contentType);
        setMainContent(mainContent);
    }
}
