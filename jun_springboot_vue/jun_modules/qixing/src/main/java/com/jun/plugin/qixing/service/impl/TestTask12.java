package com.jun.plugin.qixing.service.impl;

import cn.hutool.core.map.MapUtil;
import com.jun.plugin.qixing.mapper.BizCommonMapper;
import com.jun.plugin.qixing.service.BizCommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Srping Task 测试定时任务(演示Demo，可删除)
 * testTask为spring bean的名称， 方法名称必须是run
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
@Component("testTask12")
public class TestTask12 {
    private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	BizCommonService bizCommonService;

	@Autowired
	BizCommonMapper bizcommonmapper;

    @Scheduled(cron = "0 * * * * ?")
	public void run(){
		List<Map> datas = bizcommonmapper.getProjectFinishReportList();
		if(datas.size()>0){
			for(Map map : datas){
				//@TODO,新增定时任务，更新项目管理功能的当前处理人跟当前处理流程节点；
				String orderId = MapUtil.getStr(map,"order_id");
				String businessId = MapUtil.getStr(map,"id");
				bizCommonService.doAfterFlowFinish("recheck", orderId, businessId, "all");
				logger.info("TestTask12 定时任务正在执行，每30s执行一次，参数为：{}");
			}
		}
		logger.info("TestTask12 定时任务正在执行，参数为：{}",datas.size());
	}
}
