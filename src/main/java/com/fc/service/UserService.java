package com.fc.service;

import com.fc.common.FcResult;
import com.fc.common.PageBounds;
import com.fc.domain.SysUserEntity;

import java.util.Map;

/**
 * @author yangfucheng
 * @date 2022/9/6 15:12
 */
public interface UserService {
    SysUserEntity getById(String id);

    Object saveUser(SysUserEntity user);

    Object page(Map<String, Object> params, PageBounds pageBounds) throws Exception;

    FcResult<SysUserEntity> getByUsername(String username);
}
