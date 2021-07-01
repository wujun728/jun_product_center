package com.projectm.task.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.framework.common.exception.CustomException;
import com.framework.common.utils.StringUtils;
import com.projectm.common.CommUtils;
import com.projectm.project.domain.Project;
import com.projectm.project.domain.ProjectLog;
import com.projectm.project.domain.SourceLink;
import com.projectm.project.mapper.SourceLinkMapper;
import com.projectm.project.service.ProjectLogService;
import com.projectm.project.service.SourceLinkService;
import com.projectm.task.domain.File;
import com.projectm.task.mapper.FileMapper;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FileService  extends ServiceImpl<FileMapper, File> {

    @Autowired
    SourceLinkMapper sourceLinkMapper;
    @Autowired
    ProjectLogService projectLogService;

    public Map getFileByCode(String fileCode){
        return baseMapper.selectFileByCode(fileCode);
    }

    public IPage<Map> gettFileByProjectCodeAndDelete(IPage<Map> page, Map params){
        return baseMapper.selectFileByProjectCodeAndDelete(page,params);
    }

    public  void recovery(String fileCode){
        File file = lambdaQuery().eq(File::getCode,fileCode).one();
        if(ObjectUtils.isEmpty(file)){
            throw new CustomException("文件不存在");
        }
        if(file.getDeleted()==0){
            throw new CustomException("文件已恢复");
        }
        lambdaUpdate().eq(File::getCode,fileCode).set(File::getDeleted,0).update();
    }
    public  void deleteFile(String fileCode){
        File file = lambdaQuery().eq(File::getCode,fileCode).one();
        if(ObjectUtils.isEmpty(file)){
            throw new CustomException("文件不存在");
        }
        lambdaUpdate().eq(File::getCode,fileCode).remove();
    }
    @Autowired
    TaskService taskService;
    @Transactional
    public Project uploadFiles(File file,String memberCode,String projectCode){
        file.setProject_code(projectCode);
        file.setCreate_by(memberCode);
        if(StringUtils.isNotEmpty(file.getTask_code())){

            SourceLink sourceLink = SourceLink.builder().source_type("file").code(CommUtils.getUUID()).
                    create_by(memberCode).organization_code(file.getOrganization_code()).link_code(file.getTask_code())
                    .link_type("task").source_code(file.getCode()).source_type("file").sort(0).build();
            sourceLinkMapper.insert(sourceLink);
        }
        baseMapper.insert(file);
        ProjectLog projectLog=ProjectLog.builder().project_code(file.getProject_code()).member_code(memberCode)
                .type("uploadFile").to_member_code("").is_comment(0).remark("").content("").build();
        /**
         * is_comment
         * to_member_code
         * content
         * type
         * source_code
         * member_code
         *
         */
        Project project = projectLogService.run(new HashMap(){{
            put("is_comment",0);
            put("to_member_code","");
            put("content","");
            put("type","uploadFile");
            put("source_code",file.getTask_code());
            put("member_code",memberCode);
            put("action_type","task");
            put("url",file.getFile_url());
            put("title",file.getTitle());
            put("project_code",projectCode);
        }});
        return project;
    }
}
