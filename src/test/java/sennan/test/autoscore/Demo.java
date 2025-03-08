package sennan.test.autoscore;

import com.aliyun.oss.*;
import com.aliyun.oss.common.auth.*;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class Demo {


    public static void main(String[] args) throws IOException {
        String filePath = "C:\\Users\\森南\\Downloads\\成都大学实验报告书（排定）全校通用2016.3.doc";
        FileInputStream fis = new FileInputStream(filePath);
        HWPFDocument document = new HWPFDocument(fis);

        WordExtractor extractor = new WordExtractor(document);
        String[] fileData = extractor.getParagraphText();

        for (String paragraph : fileData) {
            if (paragraph != null) {
                System.out.println(paragraph);
            }
        }

        extractor.close();
        fis.close();
    }
}