package com.doroodo.work.service.impl;

import java.util.HashMap;
import java.util.List;

import com.doroodo.base.dao.BaseDao;
import com.doroodo.base.service.impl.BaseServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doroodo.base.service.impl.BaseServiceImpl;
import com.doroodo.config.SysVal;
import com.doroodo.sys.model.*;
import com.doroodo.sys.service.*;
import com.doroodo.util.SmarteUntil;
import com.doroodo.work.model.*;
import com.doroodo.work.service.*;
@Service("vcxSbCurrentMonitorService")
public class VcxSbCurrentMonitorServiceImpl extends BaseServiceImpl<VcxSbCurrentMonitor>  implements VcxSbCurrentMonitorService {
	@Autowired
	private BaseDao<CxSbTerminal> cxSbTerminalDao;
	@Autowired
	private BaseDao<VcxSbCurrentMonitor> vcxSbCurrentMonitorDao;
	public HashMap<String,String>cmdCollection(String id){
		HashMap<String,String> hp=new HashMap<String,String>();
		hp.put("code", "0");
		hp.put("msg", "采集成功");
		if(id.isEmpty())
		{
			hp.put("code", "2");
			hp.put("msg", "参数缺省");
			return hp;
		}
		String cmd="";
		int _result=0;
		CxSbTerminal cxSbTerminal=cxSbTerminalDao.get(CxSbTerminal.class,Integer.parseInt(id));
		try{
			
			if(cxSbTerminal==null)
			{
				//return 1;//未找到
				hp.put("code", "1");
				hp.put("msg", "未找到");
				return hp;
			}
			String ip=SysVal.DAS_SERVER_IP;
			int port=SysVal.DAS_SERVER_PORT;
			String cmdModel=SysVal.DAS_CMD_DAS;
			cmd=cmdModel.replace("addr", cxSbTerminal.getTerminalNumber().replace(",", ";"));
			//_result=socketCmd(ip,port,cmd);
			HashMap<String,String>_res=new HashMap<String,String>();
			_res=SmarteUntil.socketCmd(ip, port, cmd);
			if(_res.get("code").equals("0")){
				String _re=_res.get("msg");
				if(_re.split(",").length==3&&_re.split(",")[1].equals("00")){
					_result=0;
					hp.put("code", "0");
					hp.put("msg", "操作成功");
				}
				else{
					_result=6;
					hp.put("code", "6");
					hp.put("msg", "操作失败");
				}
			}else{
				hp=_res;				
			}
			return hp;
			}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return hp;
	}
	
	public List getConnectionData(String id){
		String sql="";
		String msql="SELECT a.id, a.alarm_state, a.brake_state, a.cause, a.terminal_id, a.terminal_number, a.alarm_desc, a.brake_desc, a.cause_desc, a.msg_desc, a.line, a.factory, a.protocol, a.position, a.protocol_name, a.sim_card, b.leakage_current, (CASE WHEN (a.save_date > b.save_date) THEN a.save_date ELSE b.save_date END) AS save_date FROM v_cx_sb_alarm_state_monitor a,v_cx_sb_leakage_current_monitor b WHERE a.terminal_id = b.terminal_id AND a.terminal_id="+id;
		String nsql="SELECT a.id, a.A_crrent, a.B_crrent, a.C_crrent, a.terminal_id, a.terminal_number, a.line, a.factory, a.protocol, a.position, a.protocol_name, a.sim_card, b.A_voltage, b.B_voltage, b.C_voltage, (CASE WHEN (a.save_date > b.save_date) THEN a.save_date ELSE b.save_date END) AS save_date FROM v_cx_sb_current_monitor a, v_cx_sb_voltage_monitor b WHERE a.terminal_id = b.terminal_id and a.terminal_id="+id;
		sql="SELECT n.id, n.A_crrent, n.B_crrent, n.C_crrent, n.terminal_id, n.terminal_number, n.line, n.factory, n.protocol, n.position, n.protocol_name, n.sim_card, n.A_voltage, n.B_voltage, n.C_voltage, m.alarm_state, m.brake_state, m.cause, m.alarm_desc, m.brake_desc, m.cause_desc, m.msg_desc, m.leakage_current,  (CASE WHEN (m.save_date > n.save_date) THEN m.save_date ELSE n.save_date END) AS save_date FROM ("+msql+") m ,("+nsql+")n WHERE n.terminal_id=m.terminal_id AND n.terminal_id="+id;
		return vcxSbCurrentMonitorDao.sqlObj(sql);
	}
}