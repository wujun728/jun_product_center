package com.doroodo.util;

import java.text.SimpleDateFormat;
import java.util.Collection;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.proxy.dwr.Util;


public class Pusher {

		private Pusher() {}  
	 
    private static Pusher single=null;  
    public static Pusher getInstance() {  
         if (single == null) {    
             single = new Pusher();  
         }    
        return single;  
    } 
	
	public void pushx() throws InterruptedException {
    	WebContext wctx = WebContextFactory.get(); //这里是获取WebContext上下文
    	String currentPage = wctx.getCurrentPage(); //从上下文中获取当前页面,这些是DWR    	Reverse Ajax 要求的必须方式
    	@SuppressWarnings({ "rawtypes", "deprecation" })
		Collection sessions = wctx.getScriptSessionsByPage(currentPage); //再一个page中    	可能存在多个ScriptSessions,
    	Util utilAll = new Util(sessions); //Util 是DWR 在Server端模拟Brower端 dwr.util.js    	的类, Engine也是

    	//if(closeMarket) break;
    	utilAll.setValue("msg","xxxx"); 
    	}
	
	public void push(String data){
		this.pushScript("log",data);
	}
	
	public void put(String eleId,String eleValue){
		this.pushSet(eleId, eleValue);
	}

	
	public void send(final String content){
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
	
	public void pushScript(String fun,String arg){
		try{
		WebContext wctx = WebContextFactory.get(); //这里是获取WebContext上下文
    	String currentPage = wctx.getCurrentPage(); //从上下文中获取当前页面,这些是DWR    	Reverse Ajax 要求的必须方式
		Collection<ScriptSession> sessions = wctx.getScriptSessionsByPage(currentPage); //再一个page中    	可能存在多个ScriptSessions,
    	Util util = new Util(sessions); //Util 是DWR 在Server端模拟Brower端 dwr.util.js    	的类, Engine也是
		ScriptBuffer sb = new ScriptBuffer();
		sb.appendScript(fun+"(");
		sb.appendData(arg);
		sb.appendScript(")");		
		// 推送
		util.addScript(sb);}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public void pushSet(String eleId,String eleValue){
    	WebContext wctx = WebContextFactory.get(); //这里是获取WebContext上下文
    	String currentPage = wctx.getCurrentPage(); //从上下文中获取当前页面,这些是DWR    	Reverse Ajax 要求的必须方式
    	@SuppressWarnings({ "rawtypes", "deprecation" })
		Collection sessions = wctx.getScriptSessionsByPage(currentPage); //再一个page中    	可能存在多个ScriptSessions,
    	Util utilAll = new Util(sessions); //Util 是DWR 在Server端模拟Brower端 dwr.util.js    	的类, Engine也是

    	//if(closeMarket) break;
    	utilAll.setValue(eleId,eleValue); 
	}
	
	public void pollInfo(){
		WebContext wctx = WebContextFactory.get(); //这里是获取WebContext上下文
    	String currentPage = wctx.getCurrentPage(); //从上下文中获取当前页面,这些是DWR    	Reverse Ajax 要求的必须方式
		Collection<ScriptSession> sessions = wctx.getScriptSessionsByPage(currentPage); //再一个page中    	可能存在多个ScriptSessions,
    	Util util = new Util(sessions); //Util 是DWR 在Server端模拟Brower端 dwr.util.js    	的类, Engine也是
		ScriptBuffer sb = new ScriptBuffer();
		sb.appendScript("loadData(");
/*		sb.appendData(SynInfo.getInstance().isRunFlag());
		sb.appendData(SynData.getInstance().isRunFlag());
		sb.appendData(SynDataYc.getInstance().isRunFlag());*/
		sb.appendScript(")");		
		// 推送
		util.addScript(sb);
	}
	
	public void pushState(){
		Runnable run = new Runnable(){  
            private ScriptBuffer script = new ScriptBuffer();  
            public void run() {  
                  //设置要调用的 js及参数  
                  script.appendCall("loadData" );  
                  //得到所有ScriptSession  
                 Collection<ScriptSession> sessions = Browser.getTargetSessions();  
                  //遍历每一个ScriptSession  
                  for (ScriptSession scriptSession : sessions){  
                        scriptSession.addScript( script);  
                 }  
           }  
    };  
     //执行推送  
    Browser. withAllSessions(run);  
	}
}
