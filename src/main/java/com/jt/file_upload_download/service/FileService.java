package com.jt.file_upload_download.service;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    public boolean uploadFile(MultipartFile file) throws IOException;

    File downloadFile(String fileName) throws IOException;
}
