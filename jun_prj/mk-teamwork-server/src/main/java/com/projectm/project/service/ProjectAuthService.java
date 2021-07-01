package com.projectm.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.projectm.common.CommUtils;
import com.projectm.project.domain.ProjectAuth;
import com.projectm.project.domain.ProjectAuthNode;
import com.projectm.project.mapper.ProjectAuthMapper;
import com.projectm.project.mapper.ProjectAuthNodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProjectAuthService extends ServiceImpl<ProjectAuthMapper, ProjectAuth> {

    @Autowired
    private ProjectAuthNodeMapper projectAuthNodeMapper;

    public List<Map> getProjectAuthByStatusAndOrgCode(String status, String orgCode){
        return baseMapper.selectProjectAuthByStatusAndOrgCode(status,orgCode);
    }

    public IPage<Map> gettProjectAuthByOrgCode(IPage<Map> page,String orgCode){
        return baseMapper.selectProjectAuthByOrgCode(page,orgCode);
    }

    @Transactional
    public Integer setProjectAuthDefault(Integer isDefault,String orgCode,ProjectAuth setDefaultPa){

        Integer i2 = baseMapper.updateProjectAuthIsDefaultByOrgCode(isDefault,orgCode);
        Integer i1 = baseMapper.updateById(setDefaultPa);
        return i1+i2;
    }

    @Transactional
    public Integer  authDelete(Integer authId){
        Integer i1=baseMapper.deleteById(authId);
        Map params = new HashMap();
        params.put("proAuthCodeList",new ArrayList(){{add(authId);}});
        Integer i2 = projectAuthNodeMapper.delProjectAuthNodeByAuthCodes(params);
        return i1+i2;
    }

    @Transactional
    public Integer authApply(Integer authId,List<ProjectAuthNode> pans ){
        Integer i = projectAuthNodeMapper.deleteProjectAuthNodeByAuth(authId);
        for(ProjectAuthNode pan:pans){
            i += projectAuthNodeMapper.insert(pan);
        }
        return i;
    }

    public List<String> getProjectAuthNodeNodeByAuth(Integer authId){
        List<Map> listPan = projectAuthNodeMapper.selectProjectAuthNodeByAuth(authId);
        return CommUtils.getListStringForListMapField(listPan,"node");
    }

}
