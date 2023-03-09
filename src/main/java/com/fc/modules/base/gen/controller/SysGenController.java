package com.fc.modules.base.gen.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fc.common.FcResult;
import com.fc.modules.base.gen.entity.InfoRmationSchema;
import com.fc.modules.base.gen.entity.MakerConfigEntity;
import com.fc.modules.base.gen.service.SysGenService;
import com.fc.utils.PageUtils;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/sys/gen")
@Api(value = "SysGenController", tags = "代码生成")
public class SysGenController {

    @Resource
    private  SysGenService sysGenService;

    /**
     * 列表
     */
    @GetMapping(value = "/list")
    public FcResult list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        QueryWrapper<InfoRmationSchema> queryWrapper = new QueryWrapper<>();
        String keyword = (String) params.get("keyword");
        if (StrUtil.isNotEmpty(keyword)) {
            queryWrapper
                    .lambda()
                    .like(InfoRmationSchema::getTableName, keyword);
        }
        IPage<InfoRmationSchema> pageList = sysGenService.queryTableList(PageUtils.<InfoRmationSchema>pageParamConvert(params), queryWrapper);
        return FcResult.success(pageList);
    }

    /**
     * 生成代码
     *
     * @param makerConfigEntity
     */
    @PostMapping(value = "/create")
    public FcResult create(@RequestBody MakerConfigEntity makerConfigEntity) {
        sysGenService.generatorCode(makerConfigEntity);
        return FcResult.success("");
    }
}
