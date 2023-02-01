package com.example.onlinemarket.repository;

import com.example.onlinemarket.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
    Optional<Attachment> findByProductId(Integer product_id);
}
