package cc.mrbird.febs.server.job.mapper;


import cc.mrbird.febs.server.job.entity.Job;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author MrBird
 */
public interface JobMapper extends BaseMapper<Job> {

    /**
     * 获取定时任务列表
     *
     * @return 定时任务列表
     */
    List<Job> queryList();
}