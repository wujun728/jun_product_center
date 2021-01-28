package org.myframework.support.task;

import java.io.File;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.SystemConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.myframework.commons.util.io.VfsUtils;
import org.myframework.dao.jdbc.impl.ConfigedJdbc;
import org.myframework.quartz.scheduler.entities.JobInfo;
import org.myframework.support.csv.CsvImport;
import org.quartz.JobExecutionContext;


public class LogImpTask {

	protected final Log logger = LogFactory.getLog(getClass());

	@Resource(name="csvUtf8Import")
	CsvImport csvImport ;

	private static final String IMP_TABLE_ID = "sys.csvlog";

	private static final String PROP_LOCATION_KEY = "logImpLocation";

	PropertiesConfiguration config = new PropertiesConfiguration();

	public LogImpTask() {
		super();
		logger.info(  "catalina.home ="+ System.getProperty("catalina.home") );
		config.copy(new SystemConfiguration());
		logger.info(  "csvimp.location ="+config.getString("catalina.home")+"\\logs\\csvimp" );
		config.setFileName("application.properties");
		config.setReloadingStrategy(new FileChangedReloadingStrategy());
		try {
			config.load();
		} catch (ConfigurationException e) {
			logger.error(e.getMessage());
		}
	}

//	@Scheduled(cron = "0/5 * *  * * ? ")
	// 每5秒执行一次
	public void impActionLogToDb() {
		String dir = config.getString(PROP_LOCATION_KEY,config.getString("catalina.home")+"\\logs\\csvimp");
		File dirFile = new File(dir);
		if(!dirFile.exists())
			return;
		File[] impFiles = dirFile.listFiles( );
		for (File file : impFiles) {
			logger.info(" INSERT FILEDATA INTO DB ,file path :" + file.getAbsolutePath());
			csvImport.impToDb(file, IMP_TABLE_ID);
			VfsUtils.delete(file.getAbsolutePath());
		}
	}
	
	
	public void test() {
		System.out.println("test");
	}
	
	/**
	 * 测试QRTZ集群配置是否生效，两个相同的应用部署后，执行同一个插入语句，该语句每隔5秒执行一次，
	 * 如果该语句两次执行的时间不是间隔5秒，说明配置没有生效；
	 * 
	 * 配置内容：application.properties文件添加
	 *  org.quartz.jobStore.isClustered=true
		org.quartz.scheduler.instanceId=AUTO 
	 * -- Create table
	 *	create table TBL_CLUSTERED_JOB_TEST
	 *	(
	 *	  exc_time TIMESTAMP(6)
	 *	);
	 * 
	 * 
	 * insert into TBL_QRTZ_JOB_INFO (job_id, job_type, job_name, job_group_name, job_status, cron_expression, description, spring_bean_id, job_class_name, method_name, api_url, creator, create_time, last_modifier, last_modify_time, remark, domain_id, is_concurrent, job_data, trigger_name, trigger_state)
	 *	values ('5', '0', 'testCluster', 'DEFAULT', '1', '0/5 * * * * ?', null, 'logImpTask', null, 'testCluster', null, null, null, null, null, null, null, '1', '{}', null, null);
     * commit;
	 * 
	 */
	@Resource(name="configedJdbc")
	ConfigedJdbc  configedJdbc ;
	
	public void testCluster() {
		System.out.println("testCluster ");
		String sql =" INSERT INTO TBL_CLUSTERED_JOB_TEST (EXC_TIME ) VALUES (SYSDATE) ";
//		configedJdbc = configedJdbc ==null ?SpringUtil.getBean(beanName)
		configedJdbc.updateBySql(sql);
	}
	
	public void test1(Map param) {
		System.out.println("test1" + param);
	}
	
	public void test2(JobExecutionContext context) {
		System.out.println("test2" + context);
	}
	
	public void test3(JobInfo context) {
		System.out.println("test3" + context);
	}

}
