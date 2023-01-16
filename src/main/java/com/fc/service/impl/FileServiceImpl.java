package com.fc.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.fc.common.FcResult;
import com.fc.config.file.UploadConfig;
import com.fc.dao.SysAttachmentMapper;
import com.fc.domain.SysAttachment;
import com.fc.domain.SysAttachmentExample;
import com.fc.service.FileService;
import com.fc.utils.IdUtils;
import com.fc.utils.file.FileUtils;
import com.fc.utils.file.UploadUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.List;


/**
 * 文件上传服务
 */
@Service
public class FileServiceImpl implements FileService {
    @Resource
    private SysAttachmentMapper sysAttachmentMapper;


    /**
     * 上传文件
     * @param md5
     * @param file
     */
    public FcResult upload(String name,
                       String md5,
                       MultipartFile file)  {
        FcResult res = FcResult.success("上传成功！", null);
        try {
            String path = UploadConfig.uploadPath + FileUtils.generateFileName();
            FileUtils.write(path, file.getInputStream());
            sysAttachmentMapper.insert(new SysAttachment(IdUtils.getUlid(), name, md5, path, new Date(), new Date(), null));
        }catch (Exception e){
            res.setMsg("系统异常" + e.getMessage());
        }

        return res;
    }

    /**
     * 分块上传文件
     * @param md5
     * @param size
     * @param chunks
     * @param chunk
     * @param file
     * @throws IOException
     */
    public FcResult uploadWithBlock(String name,
                                String md5,
                                Long size,
                                Integer chunks,
                                Integer chunk,
                                MultipartFile file) {

        FcResult res = FcResult.success("上传成功！", null);
        try {
            String fileName = UploadUtils.getFileName(md5, chunks);
            FileUtils.writeWithBlok(UploadConfig.uploadPath + fileName, size, file.getInputStream(), file.getSize(), chunks, chunk);
            UploadUtils.addChunk(md5,chunk);
            if (UploadUtils.isUploaded(md5)) {
                UploadUtils.removeKey(md5);
                sysAttachmentMapper.insert(new SysAttachment(IdUtils.getUlid(), name, md5, UploadConfig.uploadPath + fileName, new Date(), new Date(), null));

            }
        }catch (Exception e){
            res.setMsg("系统异常" + e.getMessage());
        }

        return res;
    }

    /**
     * 检查Md5判断文件是否已上传
     * @param md5
     * @return
     */
    public FcResult checkMd5(String md5) {
        SysAttachmentExample example = new SysAttachmentExample();
        example.createCriteria().andMd5EqualTo(md5);
        List<SysAttachment> attachmentList = sysAttachmentMapper.selectByExample(example);
        return FcResult.success("", CollectionUtil.isEmpty(attachmentList));
    }
}
