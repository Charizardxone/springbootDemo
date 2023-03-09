package com.fc.modules.base.sys.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fc.common.FcResult;
import com.fc.define.Constant;
import com.fc.modules.base.sys.entity.SysUser;
import com.fc.modules.base.sys.service.SysMenuNewService;
import com.fc.modules.base.sys.service.SysUserService;
import com.fc.modules.base.sys.vo.RouterInfo;
import com.fc.modules.base.sys.vo.SysMenuNewVO;
import com.fc.modules.base.sys.vo.UserInfoVO;
import com.fc.utils.HttpContextUtils;
import com.fc.utils.IPUtils;
import com.fc.utils.PageUtils;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.fc.utils.UserUtil.getUserId;

/**
 * 系统用户
 *
 * @author czx
 * @email object_czx@163.com
 */
@RestController
@RequestMapping("/sys/user")
@Api(value = "SysUserController", tags = "系统用户")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SysMenuNewService sysMenuNewService;

    /**
     * 所有用户列表
     */
    @GetMapping(value = "/list")
    public FcResult list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();

        //只有超级管理员，才能查看所有管理员列表
        if (!getUserId().equals(Constant.SUPER_ADMIN)) {
            queryWrapper.lambda().eq(SysUser::getCreateUserId, getUserId());
        }

        final String keyword = (String) params.get("keyword");
        if (StrUtil.isNotEmpty(keyword)) {
            queryWrapper
                    .lambda()
                    .and(func -> func.like(SysUser::getUsername, keyword)
                            .or()
                            .like(SysUser::getMobile, keyword));
        }
        IPage<SysUser> listPage = sysUserService.page(PageUtils.<SysUser>pageParamConvert(params), queryWrapper);
        listPage.getRecords().forEach(sysUser -> sysUser.setPassword(null));
        return FcResult.success(listPage);
    }

    /**
     * 获取登录的用户信息和菜单信息
     */
    @GetMapping(value = "/sysInfo")
    public FcResult sysInfo() {
        // 用户菜单
        final List<SysMenuNewVO> userMenu = sysMenuNewService.getUserMenu();

        RouterInfo routerInfo = new RouterInfo();
        routerInfo.setMenus(userMenu);

        // 用户信息
        final SysUser sysUser = sysUserService.getById(getUserId());

        UserInfoVO userInfo = new UserInfoVO();
        userInfo.setUserId(sysUser.getUserId());
        userInfo.setUserName(sysUser.getUsername());
        userInfo.setName(sysUser.getName());
        userInfo.setLoginIp(IPUtils.getIpAddr(HttpContextUtils.getHttpServletRequest()));
        final String photo = sysUser.getPhoto();
        userInfo.setPhoto(photo == null ? "https://img0.baidu.com/it/u=1833472230,3849481738&fm=253&fmt=auto?w=200&h=200" : photo);
        userInfo.setRoles(new String[]{sysUser.getUserId().equals(Constant.SUPER_ADMIN) ? "admin" : "common"});
        userInfo.setTime(DateUtil.now());
        userInfo.setAuthBtnList(new String[]{"btn.add", "btn.del", "btn.edit", "btn.link"});
        routerInfo.setUserInfo(userInfo);
        return FcResult.success(routerInfo);
    }

    /**
     * 修改登录用户密码
     */
    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public FcResult password(String password, String newPassword) {
        if (StrUtil.isEmpty(newPassword)) {
            return FcResult.error("新密码不为能空");
        }
        password = passwordEncoder.encode(password);
        newPassword = passwordEncoder.encode(newPassword);

        SysUser user = sysUserService.getById(getUserId());
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return FcResult.error("原密码不正确");
        }
        //更新密码
        sysUserService.updatePassword(getUserId(), password, newPassword);
        return FcResult.success();
    }


    /**
     * 保存用户
     */
    @PostMapping(value = "/save")
    public FcResult save(@RequestBody @Validated SysUser user) {
        user.setCreateUserId(getUserId());
        sysUserService.saveUserRole(user);
        return FcResult.success();
    }

    /**
     * 修改用户
     */
    @PostMapping(value = "/update")
    public FcResult update(@RequestBody @Validated SysUser user) {
        sysUserService.updateUserRole(user);
        return FcResult.success();
    }

    /**
     * 删除用户
     */
    @PostMapping(value = "/delete")
    public FcResult delete(@RequestBody SysUser user) {
        if (user == null || user.getUserId() == null) {
            return FcResult.error("参数错误");
        }

        if (user.getUserId().equals(Constant.SUPER_ADMIN)) {
            return FcResult.error("系统管理员不能删除");
        }

        if (user.getUserId().equals(getUserId())) {
            return FcResult.error("当前用户不能删除");
        }
        sysUserService.removeById(user.getUserId());
        return FcResult.success();
    }
}
