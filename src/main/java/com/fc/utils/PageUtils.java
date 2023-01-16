package com.fc.utils;

import com.fc.common.PageBounds;

import java.util.Map;

/**
 * @author yangfucheng
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


}
