package com.sennan.server.controller;


import com.sennan.common.result.ApiResponse;
import com.sennan.server.service.impl.DocReadServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user/doc")
@Api(tags = "doc文档读取")
public class DocReadController {

    @Autowired
    DocReadServiceImpl docReadService;

    @PostMapping("/upload")
    @ApiOperation("内容读取")
    public ApiResponse handleFileUpload(@RequestPart("document") MultipartFile file) throws Exception {

        List<String> content = docReadService.docRead(file);
        return ApiResponse.success(content);
    }
}
