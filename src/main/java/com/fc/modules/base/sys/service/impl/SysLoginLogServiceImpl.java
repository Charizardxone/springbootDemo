package com.fc.modules.base.sys.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fc.modules.base.sys.entity.SysLoginLog;
import com.fc.modules.base.sys.mapper.SysLoginLogMapper;
import com.fc.modules.base.sys.service.SysLoginLogService;
import org.springframework.stereotype.Service;

@Service
public class SysLoginLogServiceImpl extends ServiceImpl<SysLoginLogMapper, SysLoginLog> implements SysLoginLogService {

}
