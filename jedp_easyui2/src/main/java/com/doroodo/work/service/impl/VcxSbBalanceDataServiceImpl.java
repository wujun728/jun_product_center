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

import com.doroodo.base.service.impl.BaseServiceImpl;

import org.springframework.stereotype.Service;

import com.doroodo.base.service.impl.BaseServiceImpl;
import com.doroodo.code.GeneratorProperties;
import com.doroodo.config.SysVal;
import com.doroodo.sys.model.*;
import com.doroodo.sys.service.*;
import com.doroodo.util.SmarteUntil;
import com.doroodo.work.model.*;
import com.doroodo.work.service.*;
@Service("vcxSbBalanceDataService")
public class VcxSbBalanceDataServiceImpl extends BaseServiceImpl<VcxSbBalanceData>  implements VcxSbBalanceDataService {
	public HashMap<String,String> autoBalance(String addr,String val){
		HashMap<String,String>hp=new HashMap<String,String>();
		if(addr.isEmpty()||val.isEmpty())
		{
			hp.put("code", "2");
			hp.put("msg", "控制参数错误");
		}
		String cmd="";
		
		cmd="68,"+addr+",90,"+val+",16";
		
		String ip=SysVal.DAS_SERVER_IP;
		int port=SysVal.DAS_SERVER_PORT;
		String cmdModel=SysVal.DAS_CMD_BALANCE;
		cmd=cmdModel.replace("addr", addr);
		cmd=cmdModel.replace("addr", val);
		
		HashMap<String,String>_res=new HashMap<String,String>();
		_res=SmarteUntil.socketCmd(ip, port, cmd);
		if(_res.get("code").equals("0")){
			String _re=_res.get("msg");
			if(_re.split(",").length==3&&_re.split(",")[1].equals("00")){
				hp.put("code", "0");
				hp.put("msg", "操作成功");
			}
			else{
				hp.put("code", "6");
				hp.put("msg", "操作失败");
			}
		}else{
			hp=_res;				
		}

		return hp;
	}
}