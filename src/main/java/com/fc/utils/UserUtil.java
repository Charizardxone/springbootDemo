package com.fc.utils;

import com.fc.auth.detail.CustomUserDetailsUser;
import lombok.SneakyThrows;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Description //TODO $
 * @Date 10:20
 * @Author yzcheng90@qq.com
 **/
public final class UserUtil {

    public static CustomUserDetailsUser getUser() {
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(object != null){
            return (CustomUserDetailsUser) object;
        }
        return null;
    }

    @SneakyThrows
    public static String getUserId() {
        return getUser() == null ? null :getUser().getUserId();
    }

}
