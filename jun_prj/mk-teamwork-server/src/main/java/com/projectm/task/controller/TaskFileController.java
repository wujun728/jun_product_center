package com.projectm.task.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.framework.common.AjaxResult;
import com.framework.common.utils.ServletUtils;
import com.framework.utils.FileUtils;
import com.projectm.common.ExcelUtils;
import com.projectm.config.MProjectConfig;
import com.projectm.task.domain.Task;
import com.projectm.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.projectm.web.BaseController;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @version V1.0
 * @program: teamwork
 * @package: com.projectm.task.controller
 * @description: 任务文件上传、下载和导出
 * @author: lzd
 * @create: 2020-06-28 19:06
 **/
@RestController
@RequestMapping("project")
public class TaskFileController extends BaseController{

    @Autowired
    private TaskService taskService;

    /**
     * 任务模板下载
     *
     */
    @GetMapping("task/_downloadTemplate")
    public void downTemplate() throws IOException {
        String filePath = MProjectConfig.getProfile() +"/template/importTask.xlsx";

        ServletUtils.getResponse().setCharacterEncoding("utf-8");
        ServletUtils.getResponse().setContentType("multipart/form-data");
        ServletUtils.getResponse().setHeader("Content-Disposition",
                "attachment;fileName=" + FileUtils.setFileDownloadHeader(ServletUtils.getRequest(), "批量导入任务模板.xlsx"));
        FileUtils.writeBytes(filePath, ServletUtils.getResponse().getOutputStream());
    }

    /**
     * 文件上传
     *
     * @param projectCode 项目code
     * @param file        Excel文件
     */
    @PostMapping("task/uploadFile")
    public AjaxResult downTemplate(@RequestParam("projectCode") String projectCode, @RequestParam("file") MultipartFile file) {
        List<List<Object>> listInfo = ExcelUtils.getListInfo(file);
        List<Task> taskList = new ArrayList<>();
        Map loginMember = getLoginMember();
        String memberCode = (String) loginMember.get("memberCode");
        if (CollUtil.isNotEmpty(listInfo)) {
            for (List<Object> obj : listInfo) {
                Task task = Task.builder().project_code(projectCode).code(IdUtil.fastSimpleUUID())
                		.name(String.valueOf(obj.get(0))).pName(String.valueOf(obj.get(1)))
                        .stage_code((String) obj.get(2))
                        .assign_to((String) obj.get(3))
                        .begin_time((String) obj.get(4)).end_time((String) obj.get(5))
                        .description((String) obj.get(6))
                        .priText((String) obj.get(7))
                        .task_tag((String) obj.get(8)).build();
                taskList.add(task);
            }
            taskService.saveTaskList(memberCode, taskList, projectCode);
            return AjaxResult.success();
        } else {
            return AjaxResult.warn("文件格式错误或内容为空！");
        }
    }

    public static void downFile(HttpServletResponse response, String headInfo, String[] head, String fileName) {
        ExcelWriter writer = null;
        OutputStream outputStream = null;
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1) + ".xls");
            List<List<String>> rows = CollUtil.newArrayList();
            List<String> rowInfo = CollUtil.newArrayList();
            List<String> rowHead = CollUtil.newArrayList();
            rowInfo.add(headInfo);
            rows.add(rowInfo);
            rowHead.addAll(Arrays.asList(head));
            rows.add(rowHead);
            //通过构造方法创建writer
            writer = new ExcelWriter();
            //一次性写出内容，强制输出标题
            writer.write(rows);
            outputStream = response.getOutputStream();
            writer.flush(outputStream);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
