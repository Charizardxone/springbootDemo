package com.fc.common.impl;

import cn.hutool.core.util.StrUtil;
import com.fc.common.ApplicationContextHolder;
import com.fc.common.EntityService;
import com.fc.common.PageBounds;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

/**
 * @author yangfucheng
 * @date 2022/9/9 10:41
 */
@Service
public class EntityServiceImpl implements EntityService {


    public Integer countByExample(Class<?> entityClass, Object example) throws Exception {
        return (Integer)this.callMethod(entityClass, "countByExample", new Class[]{example.getClass()}, new Object[]{example});
    }

    public Integer deleteByExample(Class<?> entityClass, Object example) throws Exception {
        return (Integer)this.callMethod(entityClass, "deleteByExample", new Class[]{example.getClass()}, new Object[]{example});
    }

    public Integer deleteByPrimaryKey(Class<?> entityClass, Object key) throws Exception {
        return (Integer)this.callMethod(entityClass, "deleteByPrimaryKey", new Class[]{key.getClass()}, new Object[]{key});
    }

    public <T> Integer insert(Class<?> entityClass, T record) throws Exception {
        return (Integer)this.callMethod(entityClass, "insert", new Class[]{record.getClass()}, new Object[]{record});
    }

    public <T> Integer insertBatch(Class<?> entityClass, List<T> records) throws Exception {
        Iterator var4 = records.iterator();

        while(var4.hasNext()) {
            Object obj = var4.next();
        }

        return (Integer)this.callMethod(entityClass, "insertBatch", new Class[]{List.class}, new Object[]{records});
    }

    public <T> Integer insertSelective(Class<?> entityClass, T record) throws Exception {
        return (Integer)this.callMethod(entityClass, "insertSelective", new Class[]{record.getClass()}, new Object[]{record});
    }

    public <T> List<T> selectByExample(Class<T> entityClass, Object example) throws Exception {
        return (List)this.callMethod(entityClass, "selectByExample", new Class[]{example.getClass()}, new Object[]{example});
    }

    public <T> List<T> selectByExample(Class<T> entityClass, Object example, RowBounds rowBounds) throws Exception {
        return (List)this.callMethod(entityClass, "selectByExample", new Class[]{example.getClass(), RowBounds.class}, new Object[]{example, rowBounds});
    }

    public <T> Page<T> selectByExample(Class<T> entityClass, Object example, PageBounds pageBounds) throws Exception {
        PageHelper.startPage(pageBounds.getPage(), pageBounds.getLimit());

        Page var5;
        try {
            var5 = (Page)this.callMethod(entityClass, "selectByExample", new Class[]{example.getClass(), pageBounds.getClass()}, new Object[]{example, pageBounds});
        } finally {
            PageHelper.clearPage();
        }

        return var5;
    }

    public <T> T selectByPrimaryKey(Class<T> entityClass, Object key) throws Exception {
        return (T) this.callMethod(entityClass, "selectByPrimaryKey", new Class[]{key.getClass()}, new Object[]{key});
    }

    public Integer updateByExampleSelective(Class<?> entityClass, Object record, Object example) throws Exception {
        return (Integer)this.callMethod(entityClass, "updateByExampleSelective", new Class[]{record.getClass(), example.getClass()}, new Object[]{record, example});
    }

    public Integer updateByExample(Class<?> entityClass, Object record, Object example) throws Exception {
        return (Integer)this.callMethod(entityClass, "updateByExample", new Class[]{record.getClass(), example.getClass()}, new Object[]{record, example});
    }

    public Integer updateByPrimaryKeySelective(Class<?> entityClass, Object record) throws Exception {
        return (Integer)this.callMethod(entityClass, "updateByPrimaryKeySelective", new Class[]{record.getClass()}, new Object[]{record});
    }

    public Integer updateByPrimaryKey(Class<?> entityClass, Object record) throws Exception {
        return (Integer)this.callMethod(entityClass, "updateByPrimaryKey", new Class[]{record.getClass()}, new Object[]{record});
    }

    protected Object callMethod(Class<?> entityClass, String methodName, Class<?>[] paramClass, Object[] Object) throws Exception {
        String name = entityClass.getName();
        name = name.replaceFirst(".domain.", ".dao.") + "Mapper";
        Class<?> mapperClass = Class.forName(name);
        if (mapperClass == null) {
            throw new ClassNotFoundException("未找到类：" + name);
        } else {
            Object mapper;
            try {
                mapper = ApplicationContextHolder.getBean(mapperClass);
            } catch (Exception var10) {
                mapper = ApplicationContextHolder.getBean(mapperClass.getSimpleName());
            }

            Method m = mapper.getClass().getMethod(methodName, paramClass);

            try {
                Object result = m.invoke(mapper, Object);
                return result;
            } catch (Exception var11) {
                if (StrUtil.isNotEmpty(var11.getMessage())) {
                    throw var11;
                } else if (var11.getCause() != null) {
                    throw new Exception(var11.getCause().getMessage());
                } else {
                    return null;
                }
            }
        }
    }
}

