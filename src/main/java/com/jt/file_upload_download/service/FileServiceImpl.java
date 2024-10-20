package com.jt.file_upload_download.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

    @Value("${file.upload.path}")
    private String uploadPath;

    @Override
    public boolean uploadFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        File saveFile = new File(uploadPath);

        if (!saveFile.exists()) {
            saveFile.mkdir();
        }
        String storePath = uploadPath.concat(fileName);
        long count = Files.copy(file.getInputStream(), Paths.get(storePath));

        if (count != 0) {
            return true;
        }
        return false;

    }

    @Override
    public File downloadFile(String fileName) throws IOException {

        String filePath = uploadPath + fileName;
        File file = new File(filePath);

        if (!file.exists()) {
            throw new IOException("File not found: " + fileName);
        }
        return file;
    }

}
