package com.sennan.server.service;


import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocReadService {

    /**
     * 文档解析
     * @param multipartFile
     * @return
     */
    List<String> docRead(MultipartFile multipartFile) throws Exception;
}
