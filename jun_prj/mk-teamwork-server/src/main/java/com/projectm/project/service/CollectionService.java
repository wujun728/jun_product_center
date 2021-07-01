package com.projectm.project.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.projectm.common.CommUtils;
import com.projectm.common.DateUtil;
import com.projectm.project.domain.Collection;
import com.projectm.project.domain.InviteLink;
import com.projectm.project.mapper.CollectionMapper;
import com.projectm.project.mapper.InviteLinkMapper;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CollectionService extends ServiceImpl<CollectionMapper, Collection> {

    public void starTask(String sourceCode,String memberCode,Integer star){
        Map collectionMap = baseMapper.selectCollection(sourceCode,memberCode);
        if(star>0 && MapUtils.isEmpty(collectionMap)){
            save(Collection.builder().create_time(DateUtil.getCurrentDateTime())
            .code(CommUtils.getUUID()).source_code(sourceCode).type("task").member_code(memberCode)
            .build());
            return ;
        }
        if(star==0){
            LambdaUpdateWrapper<Collection> collUQ = new LambdaUpdateWrapper<Collection>();
            collUQ.eq(Collection::getSource_code,sourceCode);
            collUQ.eq(Collection::getType,"task");
            collUQ.eq(Collection::getMember_code,memberCode);
            baseMapper.delete(collUQ);
        }
    }
}
