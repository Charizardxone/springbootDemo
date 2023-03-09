package com.fc.modules.base.gen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fc.modules.base.gen.entity.ColumnEntity;
import com.fc.modules.base.gen.entity.InfoRmationSchema;
import com.fc.modules.base.gen.entity.MakerConfigEntity;
import com.fc.modules.base.gen.mapper.SysGenMapper;
import com.fc.modules.base.gen.service.SysGenService;
import com.fc.utils.GenUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysGenServiceImpl extends ServiceImpl<SysGenMapper, InfoRmationSchema> implements SysGenService {

    @Resource
    private  SysGenMapper sysGenMapper;

    @Resource
    private  GenUtils genUtils;

    @Override
    public IPage<InfoRmationSchema> queryTableList(IPage page, QueryWrapper<InfoRmationSchema> entityWrapper) {
        return sysGenMapper.queryTableList(page, entityWrapper);
    }

    @Override
    public void generatorCode(MakerConfigEntity config) {
        //查询表信息
        InfoRmationSchema table = sysGenMapper.queryTableList(new QueryWrapper<InfoRmationSchema>().eq("tableName", config.getTableName()));
        //查询列信息
        List<ColumnEntity> columns = sysGenMapper.queryColumns(new QueryWrapper<ColumnEntity>().eq("tableName", config.getTableName()));
        //生成代码
        genUtils.maker(config, table, columns);
    }
}
