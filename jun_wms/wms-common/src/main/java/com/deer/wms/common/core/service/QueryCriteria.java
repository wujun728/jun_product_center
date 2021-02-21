package com.deer.wms.common.core.service;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 查询条件
 *
 * Created by Floki on 2017/9/30.
 */
public abstract class QueryCriteria {

    private Integer wareId;

    private Integer itemMasterId;

    private String startTime;

    private String endTime;

    private String keyWords;

    private List<String> keyWordList;

    /**
     * 页码
     */
    private Integer pageNum = 1;

    /**
     * 每页显示的条数
     */
    private Integer pageSize = 1000;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getKeyWords() {
        return keyWords;
    }

    // 字符串分割加入 keyWordList 参数
    public void setKeyWords(String keyWords) {
        keyWords = StringUtils.trim(keyWords);
        this.keyWords = keyWords;

        if(keyWords!="" && keyWords!=null){
            this.keyWordList = Arrays.asList(keyWords.split("\\s+"));
        }

    }



    public List<String> getKeyWordList() {
        return keyWordList;
    }

    public void setKeyWordList(List<String> keyWordList) {
        this.keyWordList = keyWordList;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getWareId() {
        return wareId;
    }

    public void setWareId(Integer wareId) {
        this.wareId = wareId;
    }

    public Integer getItemMasterId() {
        return itemMasterId;
    }

    public void setItemMasterId(Integer itemMasterId) {
        this.itemMasterId = itemMasterId;
    }


}
