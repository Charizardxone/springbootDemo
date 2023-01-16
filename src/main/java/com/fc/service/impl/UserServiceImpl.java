package com.fc.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.fc.common.EntityService;
import com.fc.common.FcResult;
import com.fc.common.PageBounds;
import com.fc.dao.SysUserEntityMapper;
import com.fc.dao.customized.UserCustomizedMapper;
import com.fc.domain.SysUserEntity;
import com.fc.domain.SysUserEntityExample;
import com.fc.service.UserService;
import com.fc.utils.IdUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientProperties;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.awt.print.Book;
import java.util.List;
import java.util.Map;

/**
 * @author yangfucheng
 * @date 2022/9/6 15:11
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private SysUserEntityMapper sysUserEntityMapper;

    @Resource
    private UserCustomizedMapper userCustomizedMapper;

    @Resource
    private EntityService entityService;

    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public SysUserEntity getById(String id) {
        SysUserEntity user1 = new SysUserEntity();
        user1.setId("5");
        user1.setUsername("明明");
        sysUserEntityMapper.updateByPrimaryKey(user1);

        return sysUserEntityMapper.selectByPrimaryKey(id);
    }

    @Override
    public FcResult<SysUserEntity> saveUser(SysUserEntity user) {
        int count = 0;
        if(user.getId() == null){
            user.setId(IdUtils.getUlid());
            count = sysUserEntityMapper.insertSelective(user);

            IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(SysUserEntity.class);
            //创建索引
            boolean a = indexOperations.create();
            if (a) {
                //生成映射
                Document document = indexOperations.createMapping();
                //推送映射
                indexOperations.putMapping(document);
            }
        }else {
            count =  sysUserEntityMapper.updateByPrimaryKey(user);
        }

        return count == 1?FcResult.success("", user): FcResult.fail("保存用户失败！");
    }

    @Override
    public Object page(Map<String, Object> params, PageBounds pageBounds) throws Exception {
        SysUserEntityExample example = new SysUserEntityExample();


        PageHelper.startPage((Integer) params.get("page"), (Integer) params.get("limit"));
        Page<SysUserEntity> list = userCustomizedMapper.findList(params);
        PageInfo pageInfo2 = new PageInfo(list);

        return FcResult.success("", pageInfo2);
    }

    @Override
    public FcResult<SysUserEntity> getByUsername(String username) {
        if(StrUtil.isEmpty(username)){
            return FcResult.fail("用户名不能为空！");
        }

        SysUserEntityExample example = new SysUserEntityExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<SysUserEntity> list = sysUserEntityMapper.selectByExample(example);
        if(CollectionUtil.isEmpty(list)){
            return FcResult.fail("用户不存在！");
        }
        return FcResult.success("", list.get(0));
    }
}
