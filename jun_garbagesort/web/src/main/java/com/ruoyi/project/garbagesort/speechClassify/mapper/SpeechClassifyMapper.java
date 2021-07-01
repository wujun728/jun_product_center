package com.ruoyi.project.garbagesort.speechClassify.mapper;

import java.util.List;
import com.ruoyi.project.garbagesort.speechClassify.domain.SpeechClassify;

/**
 * 语音识别记录Mapper接口
 * 
 * @author luoxiang
 * @date 2021-06-08
 */
public interface SpeechClassifyMapper 
{
    /**
     * 查询语音识别记录
     * 
     * @param id 语音识别记录ID
     * @return 语音识别记录
     */
    public SpeechClassify selectSpeechClassifyById(Long id);

    /**
     * 查询语音识别记录列表
     * 
     * @param speechClassify 语音识别记录
     * @return 语音识别记录集合
     */
    public List<SpeechClassify> selectSpeechClassifyList(SpeechClassify speechClassify);

    /**
     * 新增语音识别记录
     * 
     * @param speechClassify 语音识别记录
     * @return 结果
     */
    public int insertSpeechClassify(SpeechClassify speechClassify);

    /**
     * 修改语音识别记录
     * 
     * @param speechClassify 语音识别记录
     * @return 结果
     */
    public int updateSpeechClassify(SpeechClassify speechClassify);

    /**
     * 删除语音识别记录
     * 
     * @param id 语音识别记录ID
     * @return 结果
     */
    public int deleteSpeechClassifyById(Long id);

    /**
     * 批量删除语音识别记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSpeechClassifyByIds(String[] ids);
}
