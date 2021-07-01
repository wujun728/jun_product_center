package com.projectm.project.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.projectm.project.domain.InviteLink;
import com.projectm.project.mapper.InviteLinkMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InviteLinkService  extends ServiceImpl<InviteLinkMapper, InviteLink> {

    //查询inviteLink是否存在
    public Map getInviteLinkByInSoCr(String inviteType,String sourceCode,String createBy){
        Map params = new HashMap();
        params.put("inviteType",inviteType);params.put("sourceCode",sourceCode);
        params.put("createBy",createBy);
        return baseMapper.getInviteLinkByParams(params);
    }
}
