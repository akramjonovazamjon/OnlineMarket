package com.example.onlinemarket.service;

import com.example.onlinemarket.entity.Attachment;
import com.example.onlinemarket.entity.Product;
import com.example.onlinemarket.exception.attachment.AttachmentNotFoundException;
import com.example.onlinemarket.exception.product.ProductNotFoundByIdException;
import com.example.onlinemarket.repository.AttachmentRepository;
import com.example.onlinemarket.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttachmentService {
    private final AttachmentRepository attachmentRepository;
    private final ProductRepository productRepository;


    public Attachment getAttachmentByProductId(Integer id) {
        return attachmentRepository.findByProductId(id).orElse(null);
    }

    public String addAttachment(MultipartHttpServletRequest request, Integer productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundByIdException(productId);
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
                return "Attachment successfully added";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Attachment not added";
    }

    public void update(Integer productId, MultipartHttpServletRequest request) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findByProductId(productId);
        if (optionalAttachment.isEmpty()) {
            throw new AttachmentNotFoundException();
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteAttachmentById(Integer id) {
        attachmentRepository.deleteById(id);
    }
}
