package com.fc.utils;

import com.github.f4b6a3.ulid.UlidFactory;

/**
 * @author yangfucheng
 * @date 2022/11/29 10:56
 */
public class IdUtils {

    public static String getUlid(){
        return UlidFactory.newInstance().create().toString();
    }
}
