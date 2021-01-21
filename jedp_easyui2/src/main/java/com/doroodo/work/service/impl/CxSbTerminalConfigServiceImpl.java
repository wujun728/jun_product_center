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

@Service("cxSbTerminalConfigService")
public class CxSbTerminalConfigServiceImpl extends
		BaseServiceImpl<CxSbTerminalConfig> implements
		CxSbTerminalConfigService {
	@Autowired
	private BaseDao<CxSbTerminal> cxSbTerminalDao;

	public HashMap<String,String> config(String id, String leakage, String over, String time) {
		HashMap<String,String> hp=new HashMap<String,String>();
		if (id.isEmpty()) {
			hp.put("code", "2");
			hp.put("msg", "命令参数不对");
			return hp;
			//return 2;// 命令参数不对
		}
		String cmd = "";
		int _result = 0;
		// /处理分合闸
		CxSbTerminal cxSbTerminal = cxSbTerminalDao.get(CxSbTerminal.class,
				Integer.parseInt(id));
		try {

			if (cxSbTerminal == null) {
				hp.put("code", "1");
				hp.put("msg", "无该设备");
				return hp;
			}
			String ip = SysVal.DAS_SERVER_IP;
			int port = SysVal.DAS_SERVER_PORT;
			String cmdModel = SysVal.DAS_CMD_CONFIG;
			cmd = cmdModel.replace("addr", cxSbTerminal.getTerminalNumber().replace(",", ";"));
			cmd = cmd.replace("leakage", leakage);
			cmd = cmd.replace("over", over);
			cmd = cmd.replace("time", time);
			// _result=socketCmd(ip,port,cmd);
			HashMap<String, String> _res = new HashMap<String, String>();
			_res = SmarteUntil.socketCmd(ip, port, cmd);
			if (_res.get("code").equals("0")) {
				String _re = _res.get("msg");
				if (_re.split(",").length == 3
						&& _re.split(",")[1].equals("00")) {
					hp.put("code", "0");
					hp.put("msg", "操作成功");
					return hp;
				} else {
					hp.put("code", "6");
					hp.put("msg", "操作失败");
					return hp;
				}
			} else {
				return _res;
			}
			// _result=socketCmd("116.55.229.221",8800,cmd);
		} catch (Exception ex) {
			hp.put("code", "2");
			hp.put("msg", "命令参数不对");
			
		}
		return hp;
	}

	public int batchUpdate(String ids, String leakage, String over, String time) {

		return 0;
	}

	public HashMap<String,String> batchConfig(String ids, String leakage, String over, String time) {
		HashMap<String,String> hp=new HashMap<String,String>();
		if (ids.isEmpty()) {
			hp.put("code", "2");
			hp.put("msg", "命令参数不对");
			return hp;
			//return 2;// 命令参数不对
		}
		String cmd = "";
		int _result = 0;
		// /处理分合闸
		try {

			String ip = SysVal.DAS_SERVER_IP;
			int port = SysVal.DAS_SERVER_PORT;
			String cmdModel = SysVal.DAS_CMD_CONFIG;
			cmd = cmdModel.replace("addr", ids);
			cmd = cmd.replace("leakage", leakage);
			cmd = cmd.replace("over", over);
			cmd = cmd.replace("time", time);
			// _result=socketCmd(ip,port,cmd);
			HashMap<String, String> _res = new HashMap<String, String>();
			_res = SmarteUntil.socketCmd(ip, port, cmd);
			if (_res.get("code").equals("0")) {
				String _re = _res.get("msg");
				if (_re.split(",").length == 3
						&& _re.split(",")[1].equals("00")) {
					hp.put("code", "0");
					hp.put("msg", "操作成功");
				} else {
					hp.put("code", "6");
					hp.put("msg", "操作失败");
				}
				return hp;
			} else {
				return _res;
			}
			// _result=socketCmd("116.55.229.221",8800,cmd);
		} catch (Exception ex) {
			hp.put("code", "2");
			hp.put("msg", "命令参数不对");
		}
		return hp;
	}

}