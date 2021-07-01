package com.projectm.project.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.framework.common.utils.StringUtils;
import com.projectm.common.CommUtils;
import com.projectm.common.DateUtil;
import com.projectm.common.DateUtils;
import com.projectm.member.domain.Member;
import com.projectm.member.service.MemberService;
import com.projectm.project.domain.Project;
import com.projectm.project.domain.ProjectLog;
import com.projectm.project.domain.ProjectReport;
import com.projectm.project.mapper.ProjectLogMapper;
import com.projectm.project.mapper.ProjectReportMapper;
import com.projectm.system.domain.Notify;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProjectReportService extends ServiceImpl<ProjectReportMapper, ProjectReport> {

    /**
     *
     * 计算最近n天的数据
     * @param String $projectCode 项目code
     * @param Integer day 近n天
     * @param projectCode 项目code
     * @param day         近n天
     */
	 public Map getReportByDay(String projectCode, Integer day) {
         Map<String, Object> result = new HashMap<>();
         LocalDate now = LocalDate.now();
         List<String> date = new ArrayList<>();
         List<Integer> task = new ArrayList<>();
         List<Integer> undoneTask = new ArrayList<>();
         List<Integer> baseLineList = new ArrayList<>();
         List<LocalDate> dateList = Stream.iterate(now, o -> o.plusDays(-1)).limit(day).collect(Collectors.toList());
         List<ProjectReport> projectReports = lambdaQuery().in(ProjectReport::getDate, dateList).eq(ProjectReport::getProject_code, projectCode)
                 .orderByAsc(ProjectReport::getDate).list();
         if (projectReports != null) {
             projectReports.forEach(o -> {
                 date.add(o.getDate().substring(5));
                 Map<String, Object> map = JSONObject.parseObject(o.getContent());
                 task.add((int) map.get("task"));
                 undoneTask.add((int) map.get("undoneTask"));
                 baseLineList.add((int) map.get("baseLineList"));
             });
         }
         result.put("date", date);
         result.put("task", task);
         result.put("undoneTask", undoneTask);
         result.put("baseLineList", baseLineList);
         return result;
    }


}
