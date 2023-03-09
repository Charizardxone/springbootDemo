package com.fc.modules.base.sys.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.fc.modules.base.sys.entity.SysMenuNew;
import com.fc.modules.base.sys.vo.SysMenuNewVO;

import java.util.List;

/**
 * 菜单管理
 *
 * @author czx
 * @email object_czx@163.com
 */
public interface SysMenuNewService extends IService<SysMenuNew> {

	List<SysMenuNewVO> getUserMenu();
}
