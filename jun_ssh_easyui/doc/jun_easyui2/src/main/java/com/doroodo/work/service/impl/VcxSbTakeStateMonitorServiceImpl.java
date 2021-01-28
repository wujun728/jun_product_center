package com.doroodo.work.service.impl;

import java.util.List;

import com.doroodo.base.dao.BaseDao;
import com.doroodo.base.service.impl.BaseServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doroodo.base.service.impl.BaseServiceImpl;
import com.doroodo.sys.model.*;
import com.doroodo.sys.service.*;
import com.doroodo.work.model.*;
import com.doroodo.work.service.*;
@Service("vcxSbTakeStateMonitorService")
public class VcxSbTakeStateMonitorServiceImpl extends BaseServiceImpl<VcxSbTakeStateMonitor>  implements VcxSbTakeStateMonitorService {
	@Autowired
	private BaseDao<VcxSbTakeStateMonitor> vcxSbTakeStateMonitorDao;
	public List countGroupByState(){		
		return vcxSbTakeStateMonitorDao.sql("SELECT pro_state_desc,COUNT(*) ct FROM v_cx_sb_take_state_monitor  GROUP BY pro_state_desc");
		//return vcxSbTakeStateMonitorDao.sql("SELECT cause_desc,COUNT(*) ct  FROM v_cx_sb_alarm_state_monitor GROUP BY cause_desc");
	}
}