package com.example.onlinemarket.exception.attachment;

public class AttachmentNotFoundException extends RuntimeException{
    public AttachmentNotFoundException() {
        super("error.not_found.attachment");
    }
}
