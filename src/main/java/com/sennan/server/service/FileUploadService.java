package com.sennan.server.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    /**
     * 上传头像
     * @param file
     * @return
     */
    String upload(MultipartFile file) throws Exception;
}
