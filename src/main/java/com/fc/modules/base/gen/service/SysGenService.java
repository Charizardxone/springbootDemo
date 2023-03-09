package com.fc.modules.base.gen.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fc.modules.base.gen.entity.InfoRmationSchema;
import com.fc.modules.base.gen.entity.MakerConfigEntity;

public interface SysGenService extends IService<InfoRmationSchema> {

    IPage<InfoRmationSchema> queryTableList(IPage page, QueryWrapper<InfoRmationSchema> entityWrapper);

    void generatorCode(MakerConfigEntity config);
}
