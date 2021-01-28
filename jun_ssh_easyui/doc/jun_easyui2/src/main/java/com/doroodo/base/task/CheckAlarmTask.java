package com.doroodo.base.task;

import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;

import com.doroodo.util.SmarteUntil;
import com.doroodo.work.model.SySbRuninfo;
import com.doroodo.work.service.SySbRuninfoService;

public class CheckAlarmTask extends TimerTask {
	@Autowired
	private SySbRuninfoService sySbRuninfoService;
	private SySbRuninfo sySbRuninfo;
	
    public void run() {  
    	Runtime lRuntime = Runtime.getRuntime(); 
    	sySbRuninfo=new SySbRuninfo();
    	sySbRuninfo.setId(null);
    	sySbRuninfo.setDpDir(System.getProperty("catalina.base"));
    	sySbRuninfo.setFreeMemory(String.valueOf(lRuntime.freeMemory()/1024/1024));
    	sySbRuninfo.setMaxMemory(String.valueOf(lRuntime.maxMemory()/1024/1024));
    	sySbRuninfo.setTomcatDir(System.getProperty("catalina.home"));
    	sySbRuninfo.setTotalMemory(String.valueOf(lRuntime.totalMemory()/1024/1024));
    	sySbRuninfo.setInsertTime(SmarteUntil.getCurrentTime());
    	sySbRuninfoService.saveOrUpdate(sySbRuninfo);
    }  
   
}
