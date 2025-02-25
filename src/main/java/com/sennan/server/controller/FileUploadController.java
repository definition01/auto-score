package com.sennan.server.controller;

import com.sennan.common.result.ApiResponse;
import com.sennan.server.service.impl.FileUploadServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片上传
 */
@RestController
@RequestMapping("/upload")
@Api(tags = "图片上传")
public class FileUploadController {

    @Autowired
    FileUploadServiceImpl uploadService;



    @PutMapping("/avatar")
    @ApiOperation("头像上传")
    public ApiResponse avatarUpload(MultipartFile file) throws Exception {

        String url = uploadService.upload(file);

        return ApiResponse.success(url);
    }


}
