package com.fc.config;

import com.fc.domain.SysUserEntity;
import com.fc.service.UserService;
import com.fc.utils.IdUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yfc
 * @date 2022/10/18 14:51
 */
public class MyShiroRelam extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 授权
     *
     * @param principals the primary identifying principals of the AuthorizationInfo that should be retrieved.
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }


    /**
     * 认证
     *
     * @param authenticationToken the authentication token containing the user's principal and credentials.
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();

        SysUserEntity user = userService.getByUsername(token.getUsername()).getData();
        if (user != null) {
//            byte[] salt = Encodes.decodeHex(user.getSalt());
//            ShiroUser shiroUser=new ShiroUser(user.getId(), user.getLoginName(), user.getName());
            // 设置用户session
            Session session = SecurityUtils.getSubject().getSession();
            user.setToken(IdUtils.getUlid());
            session.setAttribute("user", user);
            //参数realmName: 当前 realm对象的name.调用父类的getName()方法即可
            return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        } else {
            return null;
        }
    }
}
