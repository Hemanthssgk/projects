package com.BlogApplication.BlogApplicaiton.rest.services;

import com.BlogApplication.BlogApplicaiton.rest.services.interfaces.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public void uploadImage(String path, MultipartFile multipartFile) throws IOException {
        String filename = multipartFile.getOriginalFilename();

        String pathToCopyFiles = path + File.separator + filename;

        File file = new File(path);
        if (!file.exists())
        {
            file.mkdir();
        }

        Files.copy(multipartFile.getInputStream(), Path.of(pathToCopyFiles), REPLACE_EXISTING);
    }

    @Override
    public byte[] downloadImage(String fileName, String path) throws IOException {
        // as image is binary format, we are reading the image into byte array...
        Path filePath = Path.of(path+File.separator+fileName);
        if (Files.exists(filePath)) {
            return Files.readAllBytes(filePath);
        }

        throw new NoSuchFileException("No File Found with File Name:"+fileName);
    }
}
