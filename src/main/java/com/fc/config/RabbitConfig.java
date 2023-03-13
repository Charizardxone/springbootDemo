package com.fc.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yfc
 * @date 2023/2/21 10:21
 */
@Configuration
public class RabbitConfig {

    public static final String EXCHANGE_NAME = "demo_exchange";
    public static final String QUEUE_NAME = "demo_queue";


    public static final String EXCHANGE_NAME1 = "demo_exchange1";

    public static final String QUEUE_NAME1 = "demo_queue1";


    @Bean("demoExchange")
    public Exchange demoExchange(){
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(false).build();
    }

    @Bean("demoExchange1")
    public Exchange demoExchange1(){
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME1).durable(false).build();
    }

    @Bean("demoQueue")
    public Queue demoQueue(){
        return QueueBuilder.durable(QUEUE_NAME).build();
    }

    @Bean("demoQueue1")
    public Queue demoQueue1(){
        return QueueBuilder.durable(QUEUE_NAME1).build();
    }

    @Bean
    public Binding bindExchangeQueue(@Qualifier("demoExchange") Exchange exchange, @Qualifier("demoQueue") Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with("demo.#").noargs();
    }

    @Bean
    public Binding bindExchangeQueue1(@Qualifier("demoExchange1") Exchange exchange, @Qualifier("demoQueue1") Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with("demo1.#").noargs();
    }

}
