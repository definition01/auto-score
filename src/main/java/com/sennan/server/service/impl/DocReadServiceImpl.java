package com.sennan.server.service.impl;

import com.sennan.common.util.DocUtil;
import com.sennan.server.service.DocReadService;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DocReadServiceImpl implements DocReadService {

    @Autowired
    DocUtil docUtil;

    /**
     * 文档解析
     * @param multipartFile
     * @return
     */
    public List<String> docRead(MultipartFile multipartFile) throws Exception {
        List<String> content = docUtil.readDoc(multipartFile);
        return content;
    }
}
