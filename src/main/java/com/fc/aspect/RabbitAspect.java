package com.fc.aspect;

import com.alibaba.fastjson2.JSONObject;
import com.fc.common.FcResult;
import com.fc.config.RabbitConfig;
import com.fc.modules.base.sys.entity.SysUser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static com.fc.define.ResultCodeEnum.CODE_SUCCESS;

/**
 * @author yfc
 * @date 2023/2/21 10:47
 */
@Aspect
@Component
public class RabbitAspect {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Pointcut("@annotation(com.fc.annotation.RabbitAspect)")
    public void rabbitPointcut(){}

    @AfterReturning(value = "rabbitPointcut()", returning = "obj")
    public void rabbitHandler(JoinPoint joinPoint, FcResult obj){

        System.out.println("推送消息：" + obj.toString());

        if(CODE_SUCCESS.getCode().equals(obj.getCode())){
            SysUser user = (SysUser) obj.getData();
            rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_NAME, "demo.haha", JSONObject.toJSONString(user));
        }
    }
}
