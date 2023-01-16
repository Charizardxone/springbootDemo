package com.fc.service;

import com.fc.common.FcResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author yangfucheng
 * @date 2023/1/4 15:22
 */
public interface FileService {

    public FcResult upload(String name,
                       String md5,
                       MultipartFile file) throws IOException;



    public FcResult uploadWithBlock(String name,
                                    String md5,
                                    Long size,
                                    Integer chunks,
                                    Integer chunk,
                                    MultipartFile file) throws IOException;
}
