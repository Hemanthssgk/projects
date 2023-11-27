package com.BlogApplication.BlogApplicaiton.rest.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileService {
    public void uploadImage(String path, MultipartFile file) throws IOException;
    public byte[] downloadImage(String fileName, String path) throws IOException;
}
