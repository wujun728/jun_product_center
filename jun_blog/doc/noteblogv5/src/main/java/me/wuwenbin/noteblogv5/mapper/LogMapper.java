package me.wuwenbin.noteblogv5.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.wuwenbin.noteblogv5.annotation.MybatisMapper;
import me.wuwenbin.noteblogv5.model.bo.EchartsBo;
import me.wuwenbin.noteblogv5.model.bo.EchartsUrlBo;
import me.wuwenbin.noteblogv5.model.entity.Log;

import java.util.List;

/**
 * @author wuwen
 */
@MybatisMapper
public interface LogMapper extends BaseMapper<Log> {

    /**
     * 统计访客ip信息
     *
     * @return
     */
    List<EchartsBo> ipSummary();

    /**
     * url统计
     *
     * @return
     */
    List<EchartsUrlBo> urlSummary();

    /**
     * 浏览器统计
     *
     * @return
     */
    List<EchartsBo> browserSummary();

    /**
     * 今日访问
     *
     * @return
     */
    long todayVisit();

    /**
     * 删除30天之前的日志
     */
    void removeLogBeyondMonth();
}
