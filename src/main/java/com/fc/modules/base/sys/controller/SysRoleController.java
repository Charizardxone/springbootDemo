package com.fc.modules.base.sys.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fc.common.FcResult;
import com.fc.define.Constant;
import com.fc.modules.base.sys.entity.SysRole;
import com.fc.modules.base.sys.service.SysRoleService;
import com.fc.utils.PageUtils;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.fc.utils.UserUtil.getUserId;

/**
 * 角色管理
 *
 * @author czx
 * @email object_czx@163.com
 */
@RestController
@RequestMapping("/sys/role")
@Api(value = "SysRoleController", tags = "角色管理")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 角色列表
     */
    @GetMapping(value = "/list")
    public FcResult list(@RequestParam Map<String, Object> params) {
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        //如果不是超级管理员，则只查询自己创建的角色列表
        if (!getUserId().equals(Constant.SUPER_ADMIN)) {
            queryWrapper.lambda().eq(SysRole::getCreateUserId, getUserId());
        }

        //查询列表数据
        final String keyword = (String) params.get("keyword");
        if (StrUtil.isNotEmpty(keyword)) {
            queryWrapper
                    .lambda()
                    .and(func -> func.like(SysRole::getRoleName, keyword));
        }
        IPage<SysRole> listPage = sysRoleService.page(PageUtils.<SysRole>pageParamConvert(params), queryWrapper);
        return FcResult.success(listPage);
    }

    /**
     * 角色列表
     */
    @GetMapping(value = "/select")
    public FcResult select() {
        final List<SysRole> list = sysRoleService.list();
        return FcResult.success(list);
    }


    /**
     * 保存角色
     */
    @PostMapping(value = "/save")
    public FcResult save(@RequestBody SysRole role) {
        role.setCreateUserId(getUserId());
        sysRoleService.saveRoleMenu(role);
        return FcResult.success();
    }

    /**
     * 修改角色
     */
    @PostMapping(value = "/update")
    public FcResult update(@RequestBody SysRole role) {
        role.setCreateUserId(getUserId());
        sysRoleService.updateRoleMenu(role);

        return FcResult.success();
    }

    /**
     * 删除角色
     */
    @PostMapping(value = "/delete")
    public FcResult delete(@RequestBody SysRole role) {
        if (role == null || role.getRoleId() == null) {
            return FcResult.error("ID为空");
        }
        sysRoleService.deleteBath(role.getRoleId());
        return FcResult.success();
    }
}
