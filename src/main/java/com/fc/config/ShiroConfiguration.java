//package com.fc.config;
//
//import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
//import org.apache.shiro.spring.LifecycleBeanPostProcessor;
//import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.filter.authc.LogoutFilter;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.DependsOn;
//
//import javax.servlet.Filter;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//
///**
// * shiro配置类
// * Created by cdyoue on 2016/10/21.
// */
//@Configuration
//public class ShiroConfiguration {
//    /**
//     * LifecycleBeanPostProcessor，这是个DestructionAwareBeanPostProcessor的子类，
//     * 负责org.apache.shiro.util.Initializable类型bean的生命周期的，初始化和销毁。
//     * 主要是AuthorizingRealm类的子类，以及EhCacheManager类。
//     */
//    @Bean(name = "lifecycleBeanPostProcessor")
//    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
//        return new LifecycleBeanPostProcessor();
//    }
//
//    /**
//     * HashedCredentialsMatcher，这个类是为了对密码进行编码的，
//     * 防止密码在数据库里明码保存，当然在登陆认证的时候，
//     * 这个类也负责对form里输入的密码进行编码。
//     */
//    @Bean(name = "hashedCredentialsMatcher")
//    public HashedCredentialsMatcher hashedCredentialsMatcher() {
//        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
//        credentialsMatcher.setHashAlgorithmName("MD5");
//        credentialsMatcher.setHashIterations(2);
//        credentialsMatcher.setStoredCredentialsHexEncoded(true);
//        return credentialsMatcher;
//    }
//
//    /**
//     * ShiroRealm，这是个自定义的认证类，继承自AuthorizingRealm，
//     * 负责用户的认证和权限的处理，可以参考JdbcRealm的实现。
//     */
//    @Bean(name = "shiroRealm")
//    @DependsOn("lifecycleBeanPostProcessor")
//    public MyShiroRelam shiroRealm() {
//        MyShiroRelam realm = new MyShiroRelam();
//        // 自定义密码校验方式
////        realm.setCredentialsMatcher(hashedCredentialsMatcher());
//        return realm;
//    }
//
////    /**
////     * EhCacheManager，缓存管理，用户登陆成功后，把用户信息和权限信息缓存起来，
////     * 然后每次用户请求时，放入用户的session中，如果不设置这个bean，每个请求都会查询一次数据库。
////     */
////    @Bean(name = "ehCacheManager")
////    @DependsOn("lifecycleBeanPostProcessor")
////    public EhCacheManager ehCacheManager() {
////        return new EhCacheManager();
////    }
//
//    /**
//     * SecurityManager，权限管理，这个类组合了登陆，登出，权限，session的处理，是个比较重要的类。
//     * //
//     */
//    @Bean(name = "securityManager")
//    public DefaultWebSecurityManager securityManager() {
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        securityManager.setRealm(shiroRealm());
////        securityManager.setCacheManager(ehCacheManager());
//        return securityManager;
//    }
//
//    /**
//     * ShiroFilterFactoryBean，是个factorybean，为了生成ShiroFilter。
//     * 它主要保持了三项数据，securityManager，filters，filterChainDefinitionManager。
//     */
//    @Bean(name = "shiroFilter")
//    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        // 必须设置 SecurityManager
//        shiroFilterFactoryBean.setSecurityManager(securityManager());
//        // setLoginUrl 如果不设置值，默认会自动寻找Web工程根目录下的"/login.jsp"页面 或 "/login" 映射
//        shiroFilterFactoryBean.setLoginUrl("/auth/notLogin");
//        // 设置无权限时跳转的 url;
//        shiroFilterFactoryBean.setUnauthorizedUrl("/auth/notRole");
//
//        /**
//         * Shiro 内置过滤器，过滤链定义，从上向下顺序执行
//         *  常用的过滤器：
//         *      anon:无需认证（登录）可以访问
//         *      authc:必须认证才可以访问
//         *      user:只要登录过，并且记住了密码，如果设置了rememberMe的功能可以直接访问
//         *      perms:该资源必须得到资源权限才可以访问
//         *      role:该资源必须得到角色的权限才可以访问
//         */
//        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
//        filterChainDefinitionMap.put("/api/auth/**", "anon");
//        filterChainDefinitionMap.put("/doc.html", "anon");
//        filterChainDefinitionMap.put("/static/**", "anon");
//        filterChainDefinitionMap.put("/api/**", "authc");
//        filterChainDefinitionMap.put("/**", "anon");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//        return shiroFilterFactoryBean;
//    }
//
//    /**
//     * DefaultAdvisorAutoProxyCreator，Spring的一个bean，由Advisor决定对哪些类的方法进行AOP代理。
//     */
//    @Bean
//    @ConditionalOnMissingBean
//    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
//        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
//        defaultAAP.setProxyTargetClass(true);
//        return defaultAAP;
//    }
//
//    /**
//     * AuthorizationAttributeSourceAdvisor，shiro里实现的Advisor类，
//     * 内部使用AopAllianceAnnotationsAuthorizingMethodInterceptor来拦截用以下注解的方法。
//     */
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
//        AuthorizationAttributeSourceAdvisor aASA = new AuthorizationAttributeSourceAdvisor();
//        aASA.setSecurityManager(securityManager());
//        return aASA;
//    }
//
//
//}
//
//
//
//
//
