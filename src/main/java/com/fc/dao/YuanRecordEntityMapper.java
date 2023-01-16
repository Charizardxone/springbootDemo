package com.fc.dao;

import com.fc.common.PageBounds;
import com.fc.domain.YuanRecordEntity;
import com.fc.domain.YuanRecordEntityExample;
import com.github.pagehelper.Page;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface YuanRecordEntityMapper {
    long countByExample(YuanRecordEntityExample example);

    int deleteByExample(YuanRecordEntityExample example);

    int deleteByPrimaryKey(String id);

    int insert(YuanRecordEntity row);

    int insertSelective(YuanRecordEntity row);

    List<YuanRecordEntity> selectByExample(YuanRecordEntityExample example);

    YuanRecordEntity selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("row") YuanRecordEntity row, @Param("example") YuanRecordEntityExample example);

    int updateByExample(@Param("row") YuanRecordEntity row, @Param("example") YuanRecordEntityExample example);

    int updateByPrimaryKeySelective(YuanRecordEntity row);

    int updateByPrimaryKey(YuanRecordEntity row);

    Page<YuanRecordEntity> selectByExample(YuanRecordEntityExample example, PageBounds pageBounds);
}