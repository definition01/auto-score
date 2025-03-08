package com.sennan.common.util;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文档内容解析工具
 */
@Component
public class DocUtil {

    public List<String> readDoc(MultipartFile multipartFile) throws Exception {

        List<String> paragraphs = new ArrayList<>();
        try {
            HWPFDocument document = new HWPFDocument(multipartFile.getInputStream());
            WordExtractor extractor = new WordExtractor(document);
            String[] fileData = extractor.getParagraphText();

            for (String paragraph : fileData) {
                if (paragraph != null && !paragraph.trim().isEmpty()) {
                    paragraphs.add(paragraph);
                }
            }
            extractor.close();
        }catch (IOException e) {
            e.printStackTrace();
            return null; // 或者考虑抛出自定义异常
        }
        List<String> result = paragraphs.stream()
                .map(s -> s.replaceAll("\n", "")
                        .replaceAll("\r","")
                        .replaceAll("\u0007",""))
                .collect(Collectors.toList());
        return result;
    }
}
