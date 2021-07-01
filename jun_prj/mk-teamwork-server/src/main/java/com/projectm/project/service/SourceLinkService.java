package com.projectm.project.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.projectm.common.BeanMapUtils;
import com.projectm.project.domain.SourceLink;
import com.projectm.project.mapper.SourceLinkMapper;
import com.projectm.task.domain.File;
import com.projectm.task.service.FileService;
import com.projectm.task.service.TaskService;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SourceLinkService  extends ServiceImpl<SourceLinkMapper, SourceLink> {

    public List<Map> getSourceLinkByLinkCodeAndType(String linkCode, String linkType){
        return baseMapper.selectSourceLinkByLinkCodeAndType(linkCode,linkType);
    }
    public SourceLink getSourceLinkByCode(String code){
        LambdaQueryWrapper<SourceLink> pvQw = new LambdaQueryWrapper<>();
        pvQw.eq(SourceLink::getCode,code);
        return baseMapper.selectOne(pvQw);
    }
    public int delSourceLinkByCode(String code){
        LambdaQueryWrapper<SourceLink> pvQw = new LambdaQueryWrapper<>();
        pvQw.eq(SourceLink::getCode,code);
        return baseMapper.delete(pvQw);
    }

    @Autowired
    TaskService taskService;
    @Transactional
    public int deleteSource(String code,String memberCode) {
        SourceLink sourceLinkDetail = getSourceDetail(code);
        int i = delSourceLinkByCode(code);
        if("task".equals(sourceLinkDetail.getLink_type())){
            taskService.taskHook(memberCode,sourceLinkDetail.getLink_code(),"unlinkFile","",0,
                    "","","",new HashMap(){{
                        put("title",sourceLinkDetail.getTitle());
                        put("url",MapUtils.getString(sourceLinkDetail.getSourceDetail(),"file_url"));
                    }},null);
        }
        return i;
    }
    @Autowired
    private FileService fileService;
    @Autowired
    private ProjectService projectService;
    public SourceLink getSourceDetail(String code) {
        SourceLink sourceLink = getSourceLinkByCode(code);
        String source_type = sourceLink.getSource_type();
        Map sourceDetail = new HashMap();
        if("file".equals(source_type)){
            sourceLink.setTitle("");
            sourceDetail = fileService.getFileByCode(sourceLink.getSource_code());
            if(MapUtils.isNotEmpty(sourceDetail)){
                sourceLink.setTitle(MapUtils.getString(sourceDetail,"title"));
                Map projectMap=projectService.getProjectByCode(MapUtils.getString(sourceDetail,"project_code"));
                sourceDetail.put("projectName",MapUtils.getString(projectMap,"name"));
            }
        }
        sourceLink.setSourceDetail(sourceDetail);
        return sourceLink;
    }
}
