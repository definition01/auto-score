package com.sennan.server.service.impl;

import com.sennan.common.util.Ossutil;
import com.sennan.server.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    Ossutil ossutil;


    /**
     * 上传头像
     * @param file
     * @return
     */
    public String upload(MultipartFile file) throws Exception {

        String url = ossutil.fileUpload(file);
        return url;

    }
}
