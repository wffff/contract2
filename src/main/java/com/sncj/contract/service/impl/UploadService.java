package com.sncj.contract.service.impl;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.model.StorageClass;
import com.qcloud.cos.region.Region;
import com.sncj.contract.service.IUploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Danny on 2018/8/22.
 */
@Service
public class UploadService implements IUploadService {
    private static String accessKeyId = "AKIDY6aIyVgGOK4c3Wp00qbpvrgKp8nKLdUR";
    private static String accessKeySecret = "3BIDFCVggUJPRJ81zycl0FaxMlDObq5K";
    private static String bucketName = "wffff-1256777784";
    private static String region = "ap-shanghai";

    @Override
    public URL upload(MultipartFile file) {

        if (null != file && null != file.getOriginalFilename()) {

            COSCredentials cred = new BasicCOSCredentials(accessKeyId, accessKeySecret);
            ClientConfig clientConfig = new ClientConfig(new Region(region));
            COSClient cosclient = new COSClient(cred, clientConfig);
            try {
                InputStream inputStream = file.getInputStream();
                ObjectMetadata meta = new ObjectMetadata();
                meta.setContentLength(inputStream.available());
                if (inputStream != null) {
                    String key = new Date().getTime() + file.getOriginalFilename();
                    PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, inputStream, meta);
                    putObjectRequest.setStorageClass(StorageClass.Standard);
                    PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest);
                    String etag = putObjectResult.getETag();
                    Date expiration = new Date(new Date().getTime() + 5 * 60 * 10000);
                    URL url = cosclient.generatePresignedUrl(bucketName, key, expiration);
                    return url;
                }
            } catch (Exception e) {
            } finally {
                if (cosclient != null) {
                    try {
                        cosclient.shutdown();
                    } catch (Exception ex) {
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Map uploadImg(MultipartFile file) {
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取后缀
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 文件上传的路径
        String filePath = "f:/upload/";
        // fileName处理
        fileName = filePath+ UUID.randomUUID()+fileName;
        // 文件对象
        File dest = new File(fileName);
        // 创建路径
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdir();
        }

        try {
            file.transferTo(dest);
            String url=fileName.substring(fileName.lastIndexOf("/")+1,fileName.length());
            Map m = new HashMap();
            m.put("code", 0);
            m.put("uploaded", 1);
            m.put("fileName", file.getOriginalFilename());
            m.put("url", "http://localhost:8080/file/download/"+url);
            return m;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
