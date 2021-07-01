package com.projectm.task.service;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.projectm.common.DateUtil;
import com.projectm.project.domain.Project;
import com.projectm.project.domain.ProjectReport;
import com.projectm.project.service.ProjectReportService;
import com.projectm.project.service.ProjectService;
import com.projectm.task.domain.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @version V1.0
 * @program: teamwork
 * @package: com.projectm.task.service
 * @description: 定时任务
 * @author: lzd
 * @create: 2020-08-04 15:19
 **/
@Slf4j
@Configuration
@EnableScheduling
public class ScheduleTask {

    @Autowired
    TaskService taskService;
    @Autowired
    ProjectService projectService;
    @Autowired
    ProjectReportService projectReportService;

    /**
     * 每天执行计算任务完成情况
     */
    @Scheduled(cron = "0 1 0 * * ?")
    public void taskSettlement() {
        taskCountInfo(null);
    }

    @Transactional(rollbackFor = Exception.class)
    public void taskCountInfo(List<String> proCodeList) {
        if (proCodeList == null) {
            List<Project> projects = projectService.lambdaQuery().select(Project::getCode).list();
            proCodeList = projects == null ? null : projects.parallelStream().map(Project::getCode).distinct().collect(Collectors.toList());
        }
        if (proCodeList != null) {
            proCodeList.forEach(pro -> {
                for (int i = -9; i <= -1; i++) {
                    LocalDate now = LocalDate.now().plusDays(i);
                    LocalDate date = now.plusDays(-1);
                    List<Task> list = taskService.lambdaQuery().eq(Task::getDeleted, 0).eq(Task::getProject_code, pro).lt(Task::getCreate_time, now).list();
                    Map<String, Object> map = new HashMap<>(8);
                    int task = 0;
                    int undoneTask = 0;
                    int baseLineList = 0;
                    if (list != null) {
                        task = list.size();
                        undoneTask = (int) list.stream().filter(o -> o.getDone() == 0).count();
                        baseLineList = (int) list.stream().filter(o -> o.getDone() == 0).filter(o -> {
                            if (StrUtil.isEmpty(o.getEnd_time())) {
                                if (StrUtil.isNotEmpty(o.getCreate_time())) {
                                    LocalDate create = LocalDate.parse(o.getCreate_time(), DateTimeFormatter.ofPattern(DateUtil.PATTERN_DATETIME));
                                    return create.plusDays(5).isAfter(now);
                                }
                                return true;
                            } else {
                                LocalDate end = LocalDate.parse(o.getEnd_time(), DateTimeFormatter.ofPattern(DateUtil.PATTERN_DATETIME_MIN));
                                return end.plusDays(-1).isBefore(now);
                            }
                        }).count();
                    }
                    map.put("task", task);
                    map.put("undoneTask", undoneTask);
                    map.put("baseLineList", baseLineList);
                    String content = JSON.toJSONString(map);
                    String dateStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateUtil.PATTERN_DATETIME));
                    ProjectReport.ProjectReportBuilder projectReportBuilder = ProjectReport.builder().content(content).update_time(dateStr);
                    ProjectReport one = projectReportService.lambdaQuery().eq(ProjectReport::getProject_code, pro).eq(ProjectReport::getDate, date).one();
                    if (one != null) {
                        ProjectReport build = projectReportBuilder.id(one.getId()).build();
                        boolean update = projectReportService.updateById(build);
                        log.info("更新项目完成数量：{}", update);
                    } else {
                        ProjectReport build = projectReportBuilder.create_time(dateStr).project_code(pro)
                                .date(date.format(DateTimeFormatter.ofPattern(DateUtil.PATTERN_DATE))).build();
                        boolean save = projectReportService.save(build);
                        log.info("新增项目完成数量：{}", save);
                    }
                }

            });

        }
    }
}