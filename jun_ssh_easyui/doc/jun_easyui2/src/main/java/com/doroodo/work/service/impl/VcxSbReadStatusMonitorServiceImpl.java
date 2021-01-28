package com.doroodo.work.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;

import com.doroodo.base.dao.BaseDao;
import com.doroodo.base.service.impl.BaseServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doroodo.base.service.impl.BaseServiceImpl;
import com.doroodo.code.GeneratorProperties;
import com.doroodo.config.SysVal;
import com.doroodo.sys.model.*;
import com.doroodo.sys.service.*;
import com.doroodo.util.SmarteUntil;
import com.doroodo.work.model.*;
import com.doroodo.work.service.*;
@Service("vcxSbReadStatusMonitorService")
public class VcxSbReadStatusMonitorServiceImpl extends BaseServiceImpl<VcxSbReadStatusMonitor>  implements VcxSbReadStatusMonitorService {
	@Autowired
	private BaseDao<CxSbTerminal> cxSbTerminalDao;
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
	
}