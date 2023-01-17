package com.fc.intercept;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * myatis拦截器
 * @author yfc
 * @date 2022/12/1 10:10
 */
@Component
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = { MappedStatement.class ,Object.class }),
})
public class MybatisIntercept implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object object = invocation.getArgs()[1];
        //sql类型
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        if (SqlCommandType.INSERT.equals(sqlCommandType)) {
            Field fieldCreate = object.getClass().getDeclaredField("createTime");
            fieldCreate.setAccessible(true);
            fieldCreate.set(object, new Date());
        }else{
            if (SqlCommandType.UPDATE.equals(sqlCommandType)) {
                //
            }
        }
        return invocation.proceed();
    }

}
