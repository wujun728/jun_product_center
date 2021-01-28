package org.myframework.quartz.scheduler.dao;

import java.util.List;

import org.myframework.quartz.scheduler.entities.JobInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JobInfoDao extends JpaRepository<JobInfo, java.lang.String>{

	@Query (" from  JobInfo where jobStatus = ? " )
	public List<JobInfo> findByJobStatus(String jobStatus) ;
	
	@Query (" from  JobInfo where jobStatus = ? and domainId = ? " )
	public List<JobInfo> findByJobStatusAndDomainId(String jobStatus,String domainId) ;
	
	@Query (" from  JobInfo where   domainId = ?  " )
	public List<JobInfo> findByDomainId(String domainId) ;
	
	 
	@Query (" from  JobInfo where jobName = ? and jobGroupName = ?  " )
	public JobInfo findByJobNameAndJobGroupName(String jobName,String jobGroupName) ;
}
