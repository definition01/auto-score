package com.sennan.server.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


public interface TextExtractionService {


    /**
     * 将图片转为字节
     * @param file
     * @return
     */
    String obtain(MultipartFile file);
}
