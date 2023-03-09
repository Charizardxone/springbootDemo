package com.fc.modules.base.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fc.common.FcResult;
import com.fc.modules.base.sys.entity.SysMenuNew;
import com.fc.modules.base.sys.service.SysMenuNewService;
import com.fc.modules.base.sys.vo.SysMenuNewVO;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 系统菜单
 *
 * @author czx
 * @email object_czx@163.com
 */
@Slf4j
@RestController
@RequestMapping("/sys/menu")
@Api(value = "SysMenuController", tags = "系统菜单")
public class SysMenuController {

    @Autowired
    private SysMenuNewService sysMenuNewService;

    /**
     * 所有菜单列表
     */
    @GetMapping(value = "/list")
    public FcResult list(@RequestParam Map<String, Object> params) {
        final List<SysMenuNewVO> userMenu = sysMenuNewService.getUserMenu();
        return FcResult.success(userMenu);
    }

    /**
     * 保存
     */
    @PostMapping(value = "/save")
    public FcResult save(@RequestBody SysMenuNew menu) {
        return FcResult.success(sysMenuNewService.save(menu));
    }

    /**
     * 修改
     */
    @PostMapping(value = "/update")
    public FcResult update(@RequestBody SysMenuNew menu) {
        return sysMenuNewService.updateById(menu)?FcResult.success("更新成功"):FcResult.fail("更新失败");
    }

    /**
     * 删除
     */
    @PostMapping(value = "/delete")
    public FcResult delete(@RequestBody SysMenuNew menu) {
        if(menu == null){
            return FcResult.error("参数错误");
        }
        Long menuId = menu.getMenuId();
        if(menuId == null){
            return FcResult.error("ID为空");
        }
        //判断是否有子菜单或按钮
        QueryWrapper<SysMenuNew> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysMenuNew::getParentId, menuId);
        List<SysMenuNew> menuList = sysMenuNewService.list(queryWrapper);
        if (menuList.size() > 0) {
            return FcResult.error("请先删除子菜单");
        }
        sysMenuNewService.removeById(menuId);
        return FcResult.success();
    }
}
