package com.fc.modules.base.sys.controller;

import cn.hutool.core.io.IoUtil;
import com.fc.common.FcResult;
import com.fc.define.Constant;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

/**
 * 登录相关
 *
 * @author czx
 * @email object_czx@163.com
 */
@Slf4j
@RestController
@Api(value = "SysLoginController", tags = "登录相关")
public class SysLoginController {

    @Autowired
    private  RedisTemplate redisTemplate;

//    @AuthIgnore
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public FcResult hello() {
        return FcResult.success("hello welcome", null);
    }

    /**
     * 验证码
     */
//    @AuthIgnore
    @SneakyThrows
    @RequestMapping(value = "/sys/code/{time}", method = RequestMethod.GET)
    public void captcha(@PathVariable("time") String time, HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        Producer producer = new DefaultKaptcha();
        //生成文字验证码
        String text = producer.createText();
        log.info("验证码:{}", text);
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //redis 60秒
        redisTemplate.opsForValue().set(Constant.NUMBER_CODE_KEY + time, text, 60, TimeUnit.SECONDS);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IoUtil.close(out);
    }

    /**
     * 退出
     */
//    @AuthIgnore
    @GetMapping(value = "/sys/logout")
    public void logout() {

    }
}
