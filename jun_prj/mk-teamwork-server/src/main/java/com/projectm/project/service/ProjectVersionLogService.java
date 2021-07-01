package com.projectm.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.projectm.project.domain.ProjectVersionLog;
import com.projectm.project.mapper.ProjectVersionLogMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProjectVersionLogService extends ServiceImpl<ProjectVersionLogMapper, ProjectVersionLog> {

    public IPage<Map> getProjectVersionBySourceCode(IPage<Map> page,String sourceCode){
        return  baseMapper.selectProjectVersionLogBySourceCode(page,sourceCode);
    }

    public List<Map> getProjectVersionLogBySourceCodeAll(String sourceCode){
        return  baseMapper.selectProjectVersionLogBySourceCodeAll(sourceCode);
    }
}
