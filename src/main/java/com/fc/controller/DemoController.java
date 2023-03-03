package com.fc.controller;

import com.fc.common.FcResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yfc <1263578411@qq.com>
 * @Description
 * @Since 1.8
 * @date 2022/8/24 18:00
 */
@RestController
@RequestMapping("hello")
public class DemoController {

//    @NacosValue("${config.name}")
//    private String name;

    @GetMapping("hello")
    public Object get(){
        return FcResult.success("ok", "hello !!!");
    }
}
