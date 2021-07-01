package com.projectm.project.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.projectm.project.domain.ProjectInfo;
import com.projectm.project.mapper.ProjectInfoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service

public class ProjectInfoService  extends ServiceImpl<ProjectInfoMapper, ProjectInfo> {

    public List<Map> getProjectInfoByProjectCode(String projectCode){
        return baseMapper.selectProjectInfoByProjectCode(projectCode);
    }
}
