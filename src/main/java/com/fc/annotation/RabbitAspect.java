package com.fc.annotation;


import java.lang.annotation.*;

/**
 * @author yfc
 * @date 2023/2/21 10:54
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RabbitAspect {

}
