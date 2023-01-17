package com.fc.config.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author yfc
 * @date 2023/1/4 15:26
 */
@Configuration
public class UploadConfig {


    public static String uploadPath;

    @Value("${upload.uploadPath}")
    public void setPath(String path) {
        UploadConfig.uploadPath = path;
    }
}
