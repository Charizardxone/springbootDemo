package com.fc.utils;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fc.common.PageBounds;

import java.util.Map;

/**
 * @author yfc
 * @date 2022/12/5 14:43
 */
public class PageUtils {


    public static PageBounds getPageBounds(Map<String, Object> params){
        Integer page = params.containsKey("page")?(Integer) params.get("page"):null;
        Integer limit = params.containsKey("limit")?(Integer) params.get("limit"):null;
        return getPageBounds(page, limit);
    }

    public static PageBounds getPageBounds(Integer page, Integer limit){
        if( page == null || limit == null || page < 1){
            return new PageBounds();
        }
        return new PageBounds(page, limit, true);
    }

    public static  <T> IPage<T> pageParamConvert(Map<String, Object> param){
        int currPage = 1;
        int limit = 10;
        if(MapUtil.getInt(param,"current") != null){
            currPage = MapUtil.getInt(param,"current");
        }
        if(MapUtil.getInt(param,"limit") != null){
            limit = MapUtil.getInt(param,"limit");
        }
        IPage<T> page = new Page<>(currPage,limit);
        return page;
    }


}
