package com.fc.modules.base.sys.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fc.modules.base.sys.entity.SysLog;
import com.fc.modules.base.sys.mapper.SysLogMapper;
import com.fc.modules.base.sys.service.SysLogService;
import org.springframework.stereotype.Service;

@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

}
