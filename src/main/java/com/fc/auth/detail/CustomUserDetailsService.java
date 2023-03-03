package com.fc.auth.detail;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.fc.domain.SysUserEntity;
import com.fc.service.impl.UserServiceImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description
 * @Date 21:09
 * @Author yzcheng90@qq.com
 **/
public class CustomUserDetailsService implements UserDetailsService {

    @Resource
    private UserServiceImpl sysUserService;

//    @Autowired
//    private PermissionsService permissionsService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserEntity sysUser = sysUserService.getByUsername(username).getData();
        if (ObjectUtil.isNull(sysUser)) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return getDetail(sysUser);
    }

    public UserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        SysUserEntity sysUser = sysUserService.getById(userId);
        if (ObjectUtil.isNull(sysUser)) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return getDetail(sysUser);
    }

    private UserDetails getDetail(SysUserEntity sysUser) {
//        Set<String> permissions = permissionsService.getUserPermissions(sysUser.getUserId());
        Set<String> permissions = new HashSet<>();
        String[] roles = new String[0];
        if (CollUtil.isNotEmpty(permissions)) {
            roles = permissions.stream().map(role -> "ROLE_" + role).toArray(String[]::new);
        }
        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(roles);
        CustomUserDetailsUser customUserDetailsUser = new CustomUserDetailsUser(sysUser.getId(), sysUser.getUsername(), sysUser.getPassword(), authorities);
        return customUserDetailsUser;
    }
}
