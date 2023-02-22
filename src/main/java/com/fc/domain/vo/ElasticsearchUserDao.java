package com.fc.domain.vo;

import com.fc.domain.SysUserEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;


/**
 * @author yfc
 * @date 2023/1/17 17:00
 */
@Repository
public interface ElasticsearchUserDao extends ElasticsearchRepository<SysUserEntity, String> {
}
