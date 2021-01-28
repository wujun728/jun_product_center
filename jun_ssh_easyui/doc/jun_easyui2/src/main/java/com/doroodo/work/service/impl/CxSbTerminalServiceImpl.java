package com.doroodo.work.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.doroodo.base.dao.BaseDao;
import com.doroodo.base.model.Tree;
import com.doroodo.base.service.impl.BaseServiceImpl;

import org.hibernate.Query;
import org.omg.CORBA_2_3.portable.OutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doroodo.base.service.impl.BaseServiceImpl;
import com.doroodo.code.GeneratorProperties;
import com.doroodo.config.SysVal;
import com.doroodo.sys.model.*;
import com.doroodo.sys.service.*;
import com.doroodo.util.SmarteUntil;
import com.doroodo.util.socket.SanySocketClient;
import com.doroodo.work.model.*;
import com.doroodo.work.service.*;

import java.io.IOException;  
import java.io.InputStream;  
import java.io.InputStreamReader;  
import java.io.OutputStreamWriter;  
import java.math.BigInteger;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;  
import java.net.SocketTimeoutException;
import java.net.UnknownHostException; 

@Service("cxSbTerminalService")
public class CxSbTerminalServiceImpl extends BaseServiceImpl<CxSbTerminal>  implements CxSbTerminalService {
	@Autowired
	private BaseDao<CxSbTerminal> cxSbTerminalDao;
	public int cmdControlTerminal(String id,String handle){
		if(id.isEmpty()||handle.isEmpty())
		{
			return 2;//命令参数不对
		}
		if(!handle.equals("分")&&!handle.equals("合")){
			return 2;//命令参数不对
		}
		//char[] cmd={0x68, 0x12, 0x16};
		String cmd="";
		int _result=0;
		///处理分合闸
		CxSbTerminal cxSbTerminal=cxSbTerminalDao.get(CxSbTerminal.class,Integer.parseInt(id));
		/*cxSbTerminal.setState(handle);		
		cxSbTerminalDao.saveOrUpdate(cxSbTerminal);
		int __res=0;
		if(__res==0){
			return 0;
		}*/
		try{
			
			if(cxSbTerminal==null)
			{
				return 1;//未找到
			}
		if(handle.equals("分")){
			String cmdModel=SysVal.DAS_CMD_OPEN;
			cmd=cmdModel.replace("addr", cxSbTerminal.getTerminalNumber().replace(",", ";"));
			cxSbTerminal.setState("分");
		}else{
			String cmdModel=SysVal.DAS_CMD_CLOSE;
			cmd=cmdModel.replace("addr", cxSbTerminal.getTerminalNumber().replace(",", ";"));
			cxSbTerminal.setState("合");
		}
		
		String ip=SysVal.DAS_SERVER_IP;
		int port=SysVal.DAS_SERVER_PORT;
		

		//_result=socketCmd(ip,port,cmd);
		HashMap<String,String>_res=new HashMap<String,String>();
		_res=SmarteUntil.socketCmd(ip, port, cmd);
		if(_res.get("code").equals("0")){
			String _re=_res.get("msg");
			if(_re.split(",").length==3&&_re.split(",")[1].equals("00")){
				_result=0;
			}
			else{
				_result=6;
			}
		}else{
			_result=Integer.parseInt(_res.get("code"));
		}
		
		if(_result>0)
		{
			return _result;
		}
		cxSbTerminal.setState(handle);
		cxSbTerminalDao.saveOrUpdate(cxSbTerminal);
		}
		catch(Exception ex){
			return 99;//更新数据库失败
		}
		return 0;
	}
	public List getTerminalInfoByPl(String pl){
		List _result=cxSbTerminalDao.sql("select * from cx_sb_terminal where circuit="+pl);
		return _result;
	}
	
	public List<CxSbTerminal> listByPl(String pl){
		String hql = " from  CxSbTerminal  where circuit=:circuit";
		Map <String ,Object> params = new HashMap<String,Object>();
		params.put("circuit", pl);
		return cxSbTerminalDao.find(hql, params);
		//Query query =cxSbTerminalDao.getCurrentSession().createQuery(hql);  
		//query.setParameter(0, pl);
		//return query.list();
	}

	
}