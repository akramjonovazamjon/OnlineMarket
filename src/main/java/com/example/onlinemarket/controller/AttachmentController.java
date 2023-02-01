package com.example.onlinemarket.controller;

import com.example.onlinemarket.entity.Attachment;
import com.example.onlinemarket.payload.ApiResponse;
import com.example.onlinemarket.service.AttachmentService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;

@RestController
@RequestMapping("/api/attachment")
public class AttachmentController {
    private final AttachmentService attachmentService;

    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    /**
     * GET PRODUCT PICTURE
     *
     * @param productId INTEGER
     * @param response  HttpServletResponse
     */
    @GetMapping("/{productId}")
    public void getAttachmentByProductId(@PathVariable Integer productId, HttpServletResponse response) {
        Attachment attachment = attachmentService.getAttachmentByProductId(productId);
        if (attachment==null){
            response.setStatus(409);
            return;
        }
        response.setHeader("Content-Disposition",
                "attachment; filename=\"" + attachment.getFileOriginalName() + "\"");
        response.setContentType(attachment.getContentType());
        try {
            FileCopyUtils.copy(attachment.getMainContent(), response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ADD PRODUCT PICTURE
     *
     * @param productId INTEGER
     * @param request   MultipartHttpServletRequest
     * @return ApiResponse
     */
    @PostMapping("/{productId}")
    public HttpEntity<ApiResponse> addAttachment(@PathVariable Integer productId, MultipartHttpServletRequest request) {
        ApiResponse apiResponse = attachmentService.addAttachment(request, productId);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * UPDATE PRODUCT PICTURE
     *
     * @param productId INTEGER
     * @param request   MultipartHttpServletRequest
     * @return ApiResponse
     */
    @PutMapping("/{productId}")
    public HttpEntity<ApiResponse> editAttachment(@PathVariable Integer productId, MultipartHttpServletRequest request) {
        ApiResponse apiResponse = attachmentService.editAttachment(productId, request);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * DELETE ATTACHMENT BY ID
     *
     * @param id INTEGER
     * @return ApiResponse
     */
    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> deleteAttachmentById(@PathVariable Integer id) {
        ApiResponse apiResponse = attachmentService.deleteAttachmentById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }
}
