package com.example.onlinemarket.service;

import com.example.onlinemarket.entity.Attachment;
import com.example.onlinemarket.entity.Product;
import com.example.onlinemarket.payload.ApiResponse;
import com.example.onlinemarket.repository.AttachmentRepository;
import com.example.onlinemarket.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

@Service
public class AttachmentService {
    private final AttachmentRepository attachmentRepository;
    private final ProductRepository productRepository;

    public AttachmentService(AttachmentRepository attachmentRepository, ProductRepository productRepository) {
        this.attachmentRepository = attachmentRepository;
        this.productRepository = productRepository;
    }

    public Attachment getAttachmentByProductId(Integer id) {
        return attachmentRepository.findByProductId(id).orElse(null);
    }

    public ApiResponse addAttachment(MultipartHttpServletRequest request, Integer productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            return new ApiResponse("Product not found", false);
        }
        try {
            Iterator<String> fileNames = request.getFileNames();
            MultipartFile picture = request.getFile(fileNames.next());
            if (picture != null) {
                byte[] mainContent = picture.getBytes();
                String contentType = picture.getContentType();
                String originalFilename = picture.getOriginalFilename();
                Attachment attachment = Attachment.builder()
                        .contentType(contentType)
                        .mainContent(mainContent)
                        .fileOriginalName(originalFilename)
                        .product(optionalProduct.get())
                        .build();
                attachmentRepository.save(attachment);
                return new ApiResponse("Attachment successfully added", true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ApiResponse("Attachment not added", false);
    }

    public ApiResponse editAttachment(Integer productId, MultipartHttpServletRequest request) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findByProductId(productId);
        if (optionalAttachment.isEmpty()) {
            return new ApiResponse("Attachment not found", false);
        }
        Attachment attachment = optionalAttachment.get();
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile multipartFile = request.getFile(fileNames.next());
        if (multipartFile != null) {
            String contentType = multipartFile.getContentType();
            String originalFilename = multipartFile.getOriginalFilename();
            try {
                byte[] mainContent = multipartFile.getBytes();
                attachment.setContentType(contentType);
                attachment.setFileOriginalName(originalFilename);
                attachment.setMainContent(mainContent);
                attachmentRepository.save(attachment);
                return new ApiResponse("Attachment successfully updated", true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ApiResponse("Attachment not updated because of some reasons", false);
    }

    public ApiResponse deleteAttachmentById(Integer id) {
        try {
            attachmentRepository.deleteById(id);
            return new ApiResponse("Attachment deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Not found or some error arise", false);
        }
    }
}
