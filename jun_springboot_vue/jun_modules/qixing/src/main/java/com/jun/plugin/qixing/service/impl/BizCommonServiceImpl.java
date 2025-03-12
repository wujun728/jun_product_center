package com.jun.plugin.qixing.service.impl;

import javax.annotation.Resource;

import cn.hutool.core.map.MapUtil;
import com.jun.plugin.qixing.controller.PrimaryKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jun.plugin.qixing.entity.BizCommonEntity;
import com.jun.plugin.qixing.entity.PjProjectReportEntity;
import com.jun.plugin.qixing.entity.PjProjectReportnumberEntity;
import com.jun.plugin.qixing.mapper.BizCommonMapper;
import com.jun.plugin.qixing.service.BizCommonService;
import com.jun.plugin.qixing.service.PjProjectReportService;
import com.jun.plugin.qixing.service.PjProjectReportnumberService;


import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;


@Service("bizCommonService")
@Slf4j
public class BizCommonServiceImpl extends ServiceImpl<BizCommonMapper, BizCommonEntity> implements BizCommonService {
	
	@Autowired
	BizCommonMapper bizcommonmapper;
	

	
	@Autowired
	private PrimaryKeyService primaryKeyService;
	
    @Autowired
    private PjProjectReportService pjProjectReportService;
    
    @Autowired
    PjProjectReportnumberService pjProjectReportnumberService;

//	@Autowired
//	private SysUserMapper sysuer;

	@SuppressWarnings("static-access")
	@Override
	public String doAfterFlowFinish(String processName,String orderId,String businessId,String taskname) {
		String errMsg = "";
		try {
			log.info("doAfterFlowFinish processName="+processName,"orderId="+orderId);
			switch (processName) {
			case "recheck":
				String ftlStr = bizcommonmapper.queryForProjectCodeStr(orderId);
				if(!StringUtils.isEmpty(ftlStr)) {
					PjProjectReportEntity obj = pjProjectReportService.getById(businessId);
			    	//***********************************更新报告表*******************************************22
			    	
					if(!StringUtils.isEmpty(obj.getReportnumCode())) {
						errMsg = "";
						break;
					}
					String reportNumberStr = primaryKeyService.genFtlContentByFtlData(ftlStr, primaryKeyService.getReportNumberDataC("%"));
					String dbStr = bizcommonmapper.queryProjectReportCode(reportNumberStr);
					if(StringUtils.isEmpty(dbStr)) {
						reportNumberStr = primaryKeyService.genFtlContentByFtlData(ftlStr, primaryKeyService.getReportNumberData(1));
					}else {
						reportNumberStr = primaryKeyService.genFtlContentByFtlData(ftlStr, primaryKeyService.getReportNumberDataC2(dbStr));
					}
					LambdaQueryWrapper<PjProjectReportEntity> queryWrapper = Wrappers.lambdaQuery();
			    	queryWrapper.eq(PjProjectReportEntity::getReportnumCode, reportNumberStr);
//			    	PjProjectReportEntity obj = pjProjectReportService.getById(businessId);
			    	obj.setReportnumCode(reportNumberStr);
			    	pjProjectReportService.saveOrUpdate(obj);

					//***********************************更新报告记录表*******************************************11
					//@TODO 6666
					LambdaQueryWrapper<PjProjectReportnumberEntity> queryWrapper22 = Wrappers.lambdaQuery();
					queryWrapper22.eq(PjProjectReportnumberEntity::getId, obj.getId());
					PjProjectReportnumberEntity  obj22 = pjProjectReportnumberService.getOne(queryWrapper22);
					if(obj22==null) {
						obj22 = new PjProjectReportnumberEntity();
					}
					obj22.setId(obj.getId());
					obj22.setRefReportnumberTitle(obj.getReportName());
					obj22.setRefReportnumberCode(obj.getReportnumCode());
					obj22.setReportnumberCode(obj.getReportnumCode());
					obj22.setDictRpStatus("2");
					obj22.setReportnumberCode(reportNumberStr);

					List<Map> datas = bizcommonmapper.getWfHisTaskActors(orderId);
					if(datas.size()>0){
						for(Map map : datas){
							taskname = MapUtil.getStr(map,"task_Name");
							String operator = MapUtil.getStr(map,"operator");
							/*if(taskname.equalsIgnoreCase("apply")) {
								SysUser sysUser = sysuer.getUserByName(operator);
								if (sysUser!=null) {
									String name =  sysUser.getRealName();
									obj22.setCreator(name);
								}else{
									obj22.setCreator(operator);
								}
							}
							if(taskname.equalsIgnoreCase("approveDept")) {
								SysUser sysUser = sysuer.getUserByName(operator);
								if (sysUser!=null) {
									String name =  sysUser.getRealName();
									obj22.setRefReportnumberMan(name);
								}else{
									obj22.setRefReportnumberMan(operator);
								}
							}
							if(taskname.equalsIgnoreCase("approveBoss")) {
								SysUser sysUser = sysuer.getUserByName(operator);
								if (sysUser!=null) {
									String name =  sysUser.getRealName();
									obj22.setRefReportnumberCheckMan(name);
								}else{
									obj22.setRefReportnumberCheckMan(operator);
								}
							}
							if(taskname.equalsIgnoreCase("task4")) {
								SysUser sysUser = sysuer.getUserByName(operator);
								if (sysUser!=null) {
									String name =  sysUser.getRealName();
									obj22.setRefSignatureAccountant(name);
								}else{
									obj22.setRefSignatureAccountant(operator);
								}
							}*/
						}
					}

					pjProjectReportnumberService.saveOrUpdate(obj22);
			    	
				}else {
					errMsg = "项目类型及项目明细类型未维护！";
				}
				break;

			default:
				break;
			}
			log.info("processName="+processName,"orderId="+orderId);
		} catch (Exception e) {
			log.info("doAfterFlowFinish 发生异常  processName="+processName,"orderId="+orderId);
			e.printStackTrace();
		}
		return errMsg;
	}
	
//    @Autowired
//    private BizCommonMapper bizCommonMapper;
//	
//	public Page getPageDatas(Page page) {
//		List list = this.bizCommonMapper.getDataList(page);
//		long total = this.bizCommonMapper.getDataListTotal(page);
//		page.setRecords(list);
//		page.setTotal(total);
//		return page;
//	}


}