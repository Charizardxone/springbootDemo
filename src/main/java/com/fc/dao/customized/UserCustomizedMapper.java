package com.fc.dao.customized;

import com.fc.common.PageBounds;
import com.fc.domain.SysUserEntity;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

/**
 * @author yfc
 * @date 2022/9/6 15:13
 */
public interface UserCustomizedMapper {

    SysUserEntity getById(String id);

    int insert(SysUserEntity user);

    int updateById(SysUserEntity user);

    Page<SysUserEntity> findPage(Map<String, Object> params, PageBounds pageBounds);
    Page<SysUserEntity> findList(Map<String, Object> params);
}
