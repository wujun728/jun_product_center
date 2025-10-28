package com.jun.plugin.qixing.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jun.plugin.qixing.entity.BizCommonV2Entity;

/**
 * 公共信息
 *
 * @author wujun
 * @email wujun728@hotmail.com
 * @date 2021-10-27 11:17:30
 */
public interface BizCommonService extends IService<BizCommonV2Entity> {

	public String doAfterFlowFinish(String processName,String orderId,String businessId,String taskname);

}

