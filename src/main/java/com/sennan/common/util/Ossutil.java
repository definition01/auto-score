package com.sennan.common.util;

import com.aliyun.oss.*;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.model.PutObjectRequest;
import com.sennan.common.constant.FileUploadConstant;
import com.sennan.common.exception.FileUploadException;
import com.sennan.common.properties.OssProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * Oss上传文件
 */

@Component
public class Ossutil {

    @Autowired
    OssProperties ossProperties;


    public String fileUpload(MultipartFile file) throws Exception {
        // OSS配置信息
        String endpoint = ossProperties.getEndPoint(); // OSS服务的Endpoint
        String bucketName = ossProperties.getBucketName(); // Bucket名称
        String region = ossProperties.getRegion(); // Bucket所在地域

        // 从环境变量中获取访问凭证
        EnvironmentVariableCredentialsProvider credentialsProvider =
                CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();

        // 创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, credentialsProvider);

        try {
            // 获取文件输入流
            InputStream inputStream = file.getInputStream();

            // 生成唯一的文件名
            String originalFilename = file.getOriginalFilename();
            String objectName = UUID.randomUUID().toString() + "-" + originalFilename;

            // 上传文件到OSS
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, inputStream);
            ossClient.putObject(putObjectRequest);

            // 生成文件的访问URL
            String fileUrl = "https://" + bucketName + "." + endpoint + "/" + objectName;
            System.out.println("文件上传成功，访问URL: " + fileUrl);

            return fileUrl;
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileUploadException(FileUploadConstant.UPLOAD_ERROR);
        } finally {
            // 关闭OSSClient
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

    }
}

