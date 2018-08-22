//package com.sncj.contract.controller;
//
//import com.qcloud.cos.COSClient;
//import com.qcloud.cos.ClientConfig;
//import com.qcloud.cos.auth.BasicCOSCredentials;
//import com.qcloud.cos.auth.COSCredentials;
//import com.qcloud.cos.exception.CosClientException;
//import com.qcloud.cos.exception.CosServiceException;
//import com.qcloud.cos.model.PutObjectRequest;
//import com.qcloud.cos.model.PutObjectResult;
//import com.qcloud.cos.model.StorageClass;
//import com.qcloud.cos.region.Region;
//import org.springframework.stereotype.Controller;
//
//import java.io.File;
//import java.net.URL;
//import java.util.Date;
//
///**
// * Created by Danny on 2018/8/22.
// */
//@Controller
//public class uploadController {
//    public static void SimpleUploadFileFromLocal() {
//        // 1 初始化用户身份信息(secretId, secretKey)
//        COSCredentials cred = new BasicCOSCredentials("1256777784","AKIDY6aIyVgGOK4c3Wp00qbpvrgKp8nKLdUR", "3BIDFCVggUJPRJ81zycl0FaxMlDObq5K");
//        // 2 设置bucket的区域, COS地域的简称请参照 https://www.qcloud.com/document/product/436/6224
//        ClientConfig clientConfig = new ClientConfig(new Region("ap-shanghai"));
//        // 3 生成cos客户端
//        COSClient cosclient = new COSClient(cred, clientConfig);
//        // bucket名需包含appid
//        String bucketName = "wffff-1256777784";
//
//        String key = "cc.png";
//        File localFile = new File("C:\\Users\\asus\\Desktop\\aaa.png");
//        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
//        // 设置存储类型, 默认是标准(Standard), 低频(standard_ia)
//        putObjectRequest.setStorageClass(StorageClass.Standard);
//        try {
//            PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest);
//            // putobjectResult会返回文件的etag
//            String etag = putObjectResult.getETag();
////            Date expiration = new Date(new Date().getTime() + 5 * 60 * 10000);
////            URL url = cosclient.generatePresignedUrl(bucketName, key,expiration);
////            System.out.println(url);
//        } catch (CosServiceException e) {
//            e.printStackTrace();
//        } catch (CosClientException e) {
//            e.printStackTrace();
//        }
//
//        // 关闭客户端
//        cosclient.shutdown();
//
//    }
//
//    public static void main(String[] args) {
//        SimpleUploadFileFromLocal();
//    }
//}
