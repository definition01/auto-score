package com.sennan.server.controller;

import com.sennan.common.result.ApiResponse;
import com.sennan.common.util.WebOcrUtil;
import com.sennan.server.service.impl.TextExtractionImplService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

@RestController
@RequestMapping("/text")
@Api(tags = "获取图片上的文字")
public class TextExtractionController {

    @Autowired
    WebOcrUtil webOcrUtil;

    @Autowired
    TextExtractionImplService textExtractionImplService;


    @ApiOperation("获得文字")
    @PutMapping("/obtainText")
    public ApiResponse obtain(@RequestParam("file") MultipartFile file) {
        String content = textExtractionImplService.obtain(file);
        return ApiResponse.success(content);

    }
}
