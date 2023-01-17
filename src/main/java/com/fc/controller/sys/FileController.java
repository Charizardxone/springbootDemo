package com.fc.controller.sys;

import com.fc.common.FcResult;
import com.fc.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author yfc
 * @date 2023/1/4 15:20
 */
@RestController
@CrossOrigin
@RequestMapping("api/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public FcResult upload(String name,
                           String md5,
                           Long size,
                           Integer chunks,
                           Integer chunk,
                           MultipartFile file) throws IOException {
        if (chunks != null && chunks != 0) {
            return fileService.uploadWithBlock(name, md5,size,chunks,chunk,file);
        } else {
            return fileService.upload(name, md5,file);
        }
    }

}
