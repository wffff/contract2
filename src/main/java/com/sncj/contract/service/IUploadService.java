package com.sncj.contract.service;

import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.Map;

/**
 * Created by Danny on 2018/8/22.
 */
public interface IUploadService {
    URL upload(MultipartFile file);

    Map uploadImg(MultipartFile file);
}
