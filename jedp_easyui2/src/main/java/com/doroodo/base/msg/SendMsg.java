package com.doroodo.base.msg;

import java.text.SimpleDateFormat;
import java.util.Collection;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.proxy.dwr.Util;

public class SendMsg {
	
	@SuppressWarnings("deprecation")
	public static void sendMsg(String msg){
		//得到上下文
		WebContext contex = WebContextFactory.get();
		
		//得到要推送到 的页面  dwr3为项目名称 ， 一定要加上。
		Collection<ScriptSession> sessions = contex.getScriptSessionsByPage(contex.getContextPath()+"/index.jsp");
		
		//不知道该怎么解释这个 ， 
		Util util = new Util(sessions);
		
		//下面是创建一个javascript脚本 ， 相当于在页面脚本中添加了一句  show(msg); 
		ScriptBuffer sb = new ScriptBuffer();
		sb.appendScript("addChart(");
		sb.appendData(msg);
		sb.appendScript(")");
		System.out.println("dwr_+++++++++++");
		//推送
		util.addScript(sb);
	}
	
	public static void send(final String content){
		try{
			WebContext wctx = WebContextFactory.get();
			System.out.println(wctx.getCurrentPage());
		 Collection<ScriptSession> se = Browser.getTargetSessions();  
         System.out.println(se.size());
          //遍历每一个ScriptSession  
          for (ScriptSession scriptSession : se){  
        	  System.out.println(scriptSession.getPage());
               
         }}
		catch(Exception ex){
			//ex.printStackTrace();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟  
			System.out.println(sdf.format(new java.util.Date()));
		}
		
		
		  try{
        Runnable run = new Runnable(){  
                private ScriptBuffer script = new ScriptBuffer();  
                public void run() {  
                      //设置要调用的 js及参数  
                      script.appendCall("log" , content);  
                      //得到所有ScriptSession  
                     
                     Collection<ScriptSession> sessions = Browser.getTargetSessions();  
                     System.out.println(sessions.size());
                      //遍历每一个ScriptSession  
                      for (ScriptSession scriptSession : sessions){  
                    	  System.out.println(scriptSession.getPage());
                            scriptSession.addScript( script);  
                     }  
               }  
        };  
         //执行推送  
        Browser. withAllSessions(run);  
		  }
		  catch(Exception ex){
			  
		  }
 }  
	
	
}
