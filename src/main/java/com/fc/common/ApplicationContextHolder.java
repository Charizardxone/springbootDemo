/**
 * @Copyright: 云南背包客信息技术有限公司  All rights reserved.
 */
package com.fc.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;


@Service
public class ApplicationContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    /**
     * 获取applicationContext对象
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        if (applicationContext == null)
            throw new IllegalStateException(
                    "'applicationContext' property is null,ApplicationContextHolder not yet init.");
        return applicationContext;
    }

    /**
     * 根据bean的id来查找对象
     * @param beanName
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanName) {
        return (T) getApplicationContext().getBean(beanName);
    }

    /**
     * 根据bean的class来查找对象
     * @param c
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static <T> T getBean(Class c) {
        return (T) getApplicationContext().getBean(c);
    }

    /**
     * 获取spring.profiles.active
     * @return spring.profiles.active
     */
    public static String getActiveProfile() {

        String[] profiles = getApplicationContext().getEnvironment().getActiveProfiles();

        if (profiles.length != 0) {

            return profiles[0];
        }

        return "";
    }

    /**
     * 获取application.yml配置信息
     * @param key                           配置项
     * @return String
     */
    public static String getEnvProperty(String key) {
        return getApplicationContext().getEnvironment().getProperty(key);
    }
}
