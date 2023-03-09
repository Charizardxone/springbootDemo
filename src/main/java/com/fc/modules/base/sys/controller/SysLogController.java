package com.fc.modules.base.sys.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fc.common.FcResult;
import com.fc.modules.base.sys.entity.SysLog;
import com.fc.modules.base.sys.entity.SysLoginLog;
import com.fc.modules.base.sys.service.SysLogService;
import com.fc.modules.base.sys.service.SysLoginLogService;
import com.fc.utils.PageUtils;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * 系统日志
 *
 * @author czx
 * @email object_czx@163.com
 */
@RestController
@Api(value = "SysLogController", tags = "系统日志")
@RequestMapping("/sys/log")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    @Autowired
    private SysLoginLogService sysLoginLogService;

    /**
     * 列表
     */
    @GetMapping(value = "/list")
    public FcResult list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        QueryWrapper<SysLog> queryWrapper = new QueryWrapper<>();
        final String keyword = (String) params.get("keyword");
        if (StrUtil.isNotEmpty(keyword)) {
            queryWrapper
                    .lambda()
                    .like(SysLog::getUsername, keyword)
                    .or()
                    .like(SysLog::getOperation, keyword);
        }
        queryWrapper.lambda().orderByDesc(SysLog::getCreateDate);
        IPage<SysLog> listPage = sysLogService.page(PageUtils.<SysLog>pageParamConvert(params), queryWrapper);
        return FcResult.success(listPage);
    }

    /**
     * 列表
     */
    @GetMapping(value = "/loginList")
    public FcResult loginList(@RequestParam Map<String, Object> params) {
        //查询列表数据
        QueryWrapper<SysLoginLog> queryWrapper = new QueryWrapper<>();
        final String keyword = (String) params.get("keyword");
        if (StrUtil.isNotEmpty(keyword)) {
            queryWrapper
                    .lambda()
                    .like(SysLoginLog::getUsername, keyword)
                    .or()
                    .like(SysLoginLog::getOptionName, keyword);
        }
        queryWrapper.lambda().orderByDesc(SysLoginLog::getOptionTime);
        IPage<SysLoginLog> listPage = sysLoginLogService.page(PageUtils.<SysLoginLog>pageParamConvert(params), queryWrapper);
        return FcResult.success(listPage);
    }

}
