package com.sennan.server.service.impl;


import com.sennan.common.constant.FileUploadConstant;
import com.sennan.common.util.WebOcrUtil;
import com.sennan.server.service.TextExtractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

@Service
public class TextExtractionImplService implements TextExtractionService {
    @Autowired
    WebOcrUtil webOcrUtil;

    /**
     * 将文字转为字节
     * @param file
     * @return
     */
    public String obtain(MultipartFile file) {
        try {
            // 检查文件是否为空
            if (file.isEmpty()) {
                return FileUploadConstant.EMPTY_FILE;
            }

            // 将MultipartFile转换为byte数组
            byte[] imageByteArray = file.getBytes();

            // 直接使用byte数组而不是保存到本地再读取
            String imageBase64 = Base64.getEncoder().encodeToString(imageByteArray);

            // 调用webOcrUtil的recognize方法前，需要修改该方法以接受base64字符串而非文件路径
            // 假设我们修改了recognize方法，使其可以接受base64字符串作为输入
            String result = webOcrUtil.recognize(imageBase64, "cn|en", "false");

            // 返回识别结果
            return result;

        } catch (Exception e) {
            // 异常处理
            return e.getMessage();
        }
    }
}
