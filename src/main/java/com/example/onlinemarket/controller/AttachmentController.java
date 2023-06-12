package com.example.onlinemarket.controller;

import com.example.onlinemarket.dto.ResponseData;
import com.example.onlinemarket.entity.Attachment;
import com.example.onlinemarket.service.AttachmentService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/attachment")
public class AttachmentController {
    private final AttachmentService attachmentService;


    @GetMapping("/{productId}")
    public void getAttachmentByProductId(@PathVariable Integer productId, HttpServletResponse response) {
        Attachment attachment = attachmentService.getAttachmentByProductId(productId);

        response.setHeader("Content-Disposition",
                "attachment; filename=\"" + attachment.getFileOriginalName() + "\"");
        response.setContentType(attachment.getContentType());
        try {
            FileCopyUtils.copy(attachment.getMainContent(), response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @PostMapping("/{productId}")
    public ResponseData<String > addAttachment(@PathVariable Integer productId, MultipartHttpServletRequest request) {
        String result = attachmentService.addAttachment(request, productId);
        return ResponseData.of(result);
    }


    @PutMapping("/{productId}")
    public void update(@PathVariable Integer productId, MultipartHttpServletRequest request) {
        attachmentService.update(productId, request);
    }


    @DeleteMapping("/{id}")
    public void deleteAttachmentById(@PathVariable Integer id) {
        attachmentService.deleteAttachmentById(id);
    }
}
