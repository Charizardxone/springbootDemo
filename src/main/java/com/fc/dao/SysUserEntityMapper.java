package com.fc.dao;

import com.fc.common.PageBounds;
import com.fc.domain.SysUserEntity;
import com.fc.domain.SysUserEntityExample;
import com.github.pagehelper.Page;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserEntityMapper {
    long countByExample(SysUserEntityExample example);

    int deleteByExample(SysUserEntityExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysUserEntity row);

    int insertSelective(SysUserEntity row);

    List<SysUserEntity> selectByExample(SysUserEntityExample example);

    SysUserEntity selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("row") SysUserEntity row, @Param("example") SysUserEntityExample example);

    int updateByExample(@Param("row") SysUserEntity row, @Param("example") SysUserEntityExample example);

    int updateByPrimaryKeySelective(SysUserEntity row);

    int updateByPrimaryKey(SysUserEntity row);

    Page<SysUserEntity> selectByExample(SysUserEntityExample example, PageBounds pageBounds);
}