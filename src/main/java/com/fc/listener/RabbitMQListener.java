package com.fc.listener;

import com.alibaba.fastjson2.JSONObject;
import com.fc.config.RabbitConfig;
import com.fc.domain.SysUserEntity;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.annotation.RabbitListener;


/**
 * @author yfc
 * @date 2023/2/21 10:34
 */
@Component
public class RabbitMQListener {

//    @Resource
//    private ElasticsearchUserDao elasticsearchUserDao;

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void ListenerDemoQueue(Message message){
        System.out.println("获取消息：" + new String(message.getBody()));
        SysUserEntity user = JSONObject.parseObject(new String(message.getBody()), SysUserEntity.class);
//        elasticsearchUserDao.save(user);
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME1)
    public void ListenerDemoQueue1(Message message){
        System.out.println("获取消息1：" + new String(message.getBody()));
    }

}
