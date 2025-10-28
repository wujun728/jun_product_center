package io.github.wujun728.admin.common.controller;

import io.github.wujun728.admin.common.Result;
import io.github.wujun728.admin.common.data.SysFile;
import io.github.wujun728.admin.common.service.SysFileService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/admin")
@RestController
public class SysFileController {

    @Resource
    private SysFileService sysFileService;

    @RequestMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file){
        if (file.isEmpty()) {
            return Result.error("上传失败,清选择文件");
        }
        SysFile sysFile = sysFileService.upload(file);
        Map<String,Object> data = new HashMap<>();
        data.put("value","/admin/download/"+sysFile.getId()+"/"+sysFile.getFileName());
        return Result.success(data);
    }

    @RequestMapping("/download/{id}/{fileName}")
    public void download(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response){
        sysFileService.download(id,request,response);
    }
}
