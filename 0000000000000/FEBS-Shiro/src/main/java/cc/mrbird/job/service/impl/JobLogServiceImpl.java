package cc.mrbird.job.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.job.domain.JobLog;
import cc.mrbird.job.service.JobLogService;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service("JobLogService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class JobLogServiceImpl extends BaseService<JobLog> implements JobLogService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public List<JobLog> findAllJobLogs(JobLog jobLog) {
		try {
			Example example = new Example(JobLog.class);
			Criteria criteria = example.createCriteria();
			if (StringUtils.isNotBlank(jobLog.getBeanName())) {
				criteria.andCondition("bean_name=", jobLog.getBeanName());
			}
			if (StringUtils.isNotBlank(jobLog.getMethodName())) {
				criteria.andCondition("method_name=", jobLog.getMethodName());
			}
			if (StringUtils.isNotBlank(jobLog.getStatus())) {
				criteria.andCondition("status=", Long.valueOf(jobLog.getStatus()));
			}
			example.setOrderByClause("log_id desc");
			return this.selectByExample(example);
		} catch (Exception e) {
			log.error("获取调度日志信息失败", e);
			return new ArrayList<>();
		}
	}

	@Override
	@Transactional
	public void saveJobLog(JobLog log) {
		this.save(log);
	}

	@Override
	@Transactional
	public void deleteBatch(String jobLogIds) {
		List<String> list = Arrays.asList(jobLogIds.split(","));
		this.batchDelete(list, "logId", JobLog.class);
	}

}
