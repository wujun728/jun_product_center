package me.wuwenbin.noteblogv5.service;


import me.wuwenbin.noteblogv5.model.bo.EchartsBo;
import me.wuwenbin.noteblogv5.model.bo.EchartsUrlBo;

import java.util.List;

/**
 * @author wuwen
 */
public interface LogService {

    /**
     * 统计ip
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
     * 今日访问量
     *
     * @return
     */
    long todayVisit();
}
