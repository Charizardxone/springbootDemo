package com.fc.controller;

import com.fc.common.PageBounds;
import com.fc.domain.SysUserEntity;
import com.fc.service.UserService;
import com.fc.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author yfc
 * @date 2022/9/6 15:18
 */
@Slf4j
@RestController
@RequestMapping("api/user")
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public Object get(@PathVariable String id){
        return userService.getById(id);

    }

    @GetMapping("page")
    public Object page(@RequestBody Map<String, Object> params)  {
        try {
            PageBounds pageBounds = PageUtils.getPageBounds(params);
            Object page = userService.page(params, pageBounds);
            return page;
        } catch (Exception e) {
            log.error("分页", e);
            throw new RuntimeException(e);
        }
    }

    @PostMapping("")
    public Object save(@RequestBody SysUserEntity user){
        return userService.saveUser(user);
    }
}
