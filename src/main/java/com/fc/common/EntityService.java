package com.fc.common;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @author yfc
 * @date 2022/9/9 10:41
 */
public interface EntityService {

    Integer countByExample(Class<?> entityClass, Object example) throws Exception;

    Integer deleteByExample(Class<?> entityClass, Object example) throws Exception;

    Integer deleteByPrimaryKey(Class<?> entityClass, Object key) throws Exception;

    <T> Integer insert(Class<?> entityClass, T record) throws Exception;

    <T> Integer insertBatch(Class<?> entityClass, List<T> records) throws Exception;

    <T> Integer insertSelective(Class<?> entityClass, T record) throws Exception;

    <T> List<T> selectByExample(Class<T> entityClass, Object example) throws Exception;

    <T> List<T> selectByExample(Class<T> entityClass, Object example, RowBounds rowBounds) throws Exception;

    <T> Page<T> selectByExample(Class<T> entityClass, Object example, PageBounds pageBounds) throws Exception;

    <T> T selectByPrimaryKey(Class<T> entityClass, Object key) throws Exception;

    Integer updateByExampleSelective(Class<?> entityClass, Object record, Object example) throws Exception;

    Integer updateByExample(Class<?> entityClass, Object record, Object example) throws Exception;

    Integer updateByPrimaryKeySelective(Class<?> entityClass, Object record) throws Exception;

    Integer updateByPrimaryKey(Class<?> entityClass, Object record) throws Exception;
}
