package cc.mrbird.quartz.dao;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.quartz.domain.Job;

import java.util.List;

public interface JobMapper extends MyMapper<Job> {

	List<Job> queryList();
}