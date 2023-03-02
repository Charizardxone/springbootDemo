//package com.fc.controller.sys;
//
//import com.fc.common.FcResult;
//import com.fc.domain.LoginDto;
//import com.fc.domain.SysUserEntity;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.*;
//import org.apache.shiro.subject.Subject;
//import org.springframework.web.bind.annotation.*;
//
//import static com.fc.define.ResultCodeEnum.*;
//
///**
// * 登录Controller
// *
// * @author yfc
// * @date 2022/10/18 11:29
// */
//@Slf4j
//@RestController
//@RequestMapping("api/auth")
//public class ShiroLoginController {
//
//    @PostMapping("login")
//    public FcResult login(@RequestBody LoginDto loginDto) {
//
//        FcResult res = new FcResult(CODE_SUCCESS.getCode(), "登录成功");
//        String username = loginDto.getUsername();
//        String password = loginDto.getPassword();
//        boolean rememberMe = loginDto.isRememberMe();
//
//        Subject subject = SecurityUtils.getSubject();
//        // 已经登录过
//        if (subject.isAuthenticated()) {
//            SysUserEntity user = (SysUserEntity) subject.getPrincipal();
//            res.setData(user);
//            return res;
//        }
//        // 勾选了记住我
//        if (subject.isRemembered()) {
//            SysUserEntity user = (SysUserEntity) subject.getPrincipal();
//            res.setData(user);
//            return res;
//        }
//
//        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password, rememberMe);
//        usernamePasswordToken.setRememberMe(rememberMe);
//        try {
//            subject.login(usernamePasswordToken);
//            SysUserEntity user = (SysUserEntity) subject.getPrincipal();
//            res.setData(user);
//        } catch (UnknownAccountException e) {
//            // 用户名不存在
//            res = new FcResult(USER_NOTFOUND.getCode(), USER_NOTFOUND.getName());
//        } catch (IncorrectCredentialsException e) {
//            // 用户名或密码错误
//            res = new FcResult(USERNAME_OR_PASSWORD_ERROR.getCode(), USERNAME_OR_PASSWORD_ERROR.getName());
//        } catch (LockedAccountException e) {
//            // 账户被锁定
//            res = new FcResult(ACCOUNT_LOCK.getCode(), ACCOUNT_LOCK.getName());
//        } catch (ExcessiveAttemptsException e) {
//            // 登录失败次数超过系统最大次数,请稍后重试
//            res = new FcResult(LOGIN_ERROR_NUM_EXCEEDED.getCode(), LOGIN_ERROR_NUM_EXCEEDED.getName());
//        } catch (DisabledAccountException e) {
//            // 验证未通过,帐号已经禁止登录
//            res = new FcResult(CODE_FAIL.getCode(), CODE_FAIL.getName());
//        } catch (AuthenticationException e) {
//            // 出现其他异常
//            res = FcResult.fail("登录失败！");
//        }
//
//        return res;
//    }
//
//    @GetMapping(value = "notLogin")
//    public FcResult notLogin() {
//        return new FcResult(NOT_LOGIN.getCode(), NOT_LOGIN.getName());
//    }
//
//    @GetMapping(value = "notRole")
//    public FcResult notRole() {
//        return FcResult.error("您没有权限！");
//    }
//
//    @PostMapping(value = "logout")
//    public FcResult logout() {
//        Subject subject = SecurityUtils.getSubject();
//        subject.logout();
//        return FcResult.success("成功注销！", null);
//    }
//
//    @GetMapping(value = "userInfo")
//    public FcResult userInfo() {
//
//        Subject subject = SecurityUtils.getSubject();
//        // 已经登录过
//        if (subject.isAuthenticated()) {
//            SysUserEntity user = (SysUserEntity) subject.getPrincipal();
//            String usertoken = user.getToken();
//        }
//        return FcResult.success("获取用户信息成功！", null);
//    }
//
//    @GetMapping(value = "menu")
//    public FcResult menu() {
//
//        Subject subject = SecurityUtils.getSubject();
//        // 已经登录过
//        if (subject.isAuthenticated()) {
//            SysUserEntity user = (SysUserEntity) subject.getPrincipal();
//            String usertoken = user.getToken();
//        }
//
//        return FcResult.success("获取目录成功！", null);
//    }
//
//}
