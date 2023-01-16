package com.fc.dao;

import com.fc.common.PageBounds;
import com.fc.domain.YuanRecordDetailEntity;
import com.fc.domain.YuanRecordDetailEntityExample;
import com.github.pagehelper.Page;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YuanRecordDetailEntityMapper {
    long countByExample(YuanRecordDetailEntityExample example);

    int deleteByExample(YuanRecordDetailEntityExample example);

    int deleteByPrimaryKey(String id);

    int insert(YuanRecordDetailEntity row);

    int insertSelective(YuanRecordDetailEntity row);

    List<YuanRecordDetailEntity> selectByExample(YuanRecordDetailEntityExample example);

    YuanRecordDetailEntity selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("row") YuanRecordDetailEntity row, @Param("example") YuanRecordDetailEntityExample example);

    int updateByExample(@Param("row") YuanRecordDetailEntity row, @Param("example") YuanRecordDetailEntityExample example);

    int updateByPrimaryKeySelective(YuanRecordDetailEntity row);

    int updateByPrimaryKey(YuanRecordDetailEntity row);

    Page<YuanRecordDetailEntity> selectByExample(YuanRecordDetailEntityExample example, PageBounds pageBounds);
}