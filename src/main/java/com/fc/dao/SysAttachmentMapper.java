package com.fc.dao;

import com.fc.common.PageBounds;
import com.fc.domain.SysAttachment;
import com.fc.domain.SysAttachmentExample;
import com.github.pagehelper.Page;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

public interface SysAttachmentMapper {
    long countByExample(SysAttachmentExample example);

    int deleteByExample(SysAttachmentExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysAttachment row);

    int insertSelective(SysAttachment row);

    List<SysAttachment> selectByExample(SysAttachmentExample example);

    SysAttachment selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("row") SysAttachment row, @Param("example") SysAttachmentExample example);

    int updateByExample(@Param("row") SysAttachment row, @Param("example") SysAttachmentExample example);

    int updateByPrimaryKeySelective(SysAttachment row);

    int updateByPrimaryKey(SysAttachment row);

    Page<SysAttachment> selectByExample(SysAttachmentExample example, PageBounds pageBounds);
}
