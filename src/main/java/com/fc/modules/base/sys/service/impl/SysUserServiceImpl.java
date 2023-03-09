package com.fc.modules.base.sys.service.impl;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fc.modules.base.sys.entity.SysUser;
import com.fc.modules.base.sys.entity.SysUserRole;
import com.fc.modules.base.sys.mapper.SysUserMapper;
import com.fc.modules.base.sys.mapper.SysUserRoleMapper;
import com.fc.modules.base.sys.service.SysUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 系统用户
 *
 * @author czx
 * @email object_czx@163.com
 * @date 2016年9月18日 上午9:46:09
 */
@Service
@AllArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    private final SysUserMapper sysUserMapper;
    private final SysUserRoleMapper sysUserRoleMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public void saveUserRole(SysUser user) {
        user.setCreateTime(new Date());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        sysUserMapper.insert(user);
        sysUserRoleMapper.delete(Wrappers.<SysUserRole>update().lambda().eq(SysUserRole::getUserId, user.getUserId()));
        //保存用户与角色关系
        saveUserRoleList(user);
    }

    @Override
    @Transactional
    public void updateUserRole(SysUser user) {
        if (StrUtil.isBlank(user.getPassword())) {
            user.setPassword(null);
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        baseMapper.updateById(user);
        sysUserRoleMapper.delete(Wrappers.<SysUserRole>update().lambda().eq(SysUserRole::getUserId, user.getUserId()));
        //保存用户与角色关系
        saveUserRoleList(user);
    }

    @Override
    public SysUser loadUserByUsername(String username) {
        SysUser sysUser = baseMapper.selectOne(Wrappers.<SysUser>query().lambda().eq(SysUser::getUsername, username));
        return sysUser;
    }


    @Override
    public int updatePassword(String userId, String password, String newPassword) {
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);
        sysUser.setPassword(newPassword);
        return sysUserMapper.updateById(sysUser);
    }

    public void saveUserRoleList(SysUser user) {
        if (CollUtil.isNotEmpty(user.getRoleIdList())) {
            user.getRoleIdList().forEach(roleId -> {
                SysUserRole userRole = new SysUserRole();
                userRole.setUserId(user.getUserId());
                userRole.setRoleId(roleId);
                sysUserRoleMapper.insert(userRole);
            });
        }
    }
}
