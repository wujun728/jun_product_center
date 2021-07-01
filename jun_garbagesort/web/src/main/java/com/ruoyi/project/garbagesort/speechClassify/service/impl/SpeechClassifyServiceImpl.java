package com.ruoyi.project.garbagesort.speechClassify.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.garbagesort.speechClassify.mapper.SpeechClassifyMapper;
import com.ruoyi.project.garbagesort.speechClassify.domain.SpeechClassify;
import com.ruoyi.project.garbagesort.speechClassify.service.ISpeechClassifyService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 语音识别记录Service业务层处理
 * 
 * @author luoxiang
 * @date 2021-06-08
 */
@Service
public class SpeechClassifyServiceImpl implements ISpeechClassifyService 
{
    @Autowired
    private SpeechClassifyMapper speechClassifyMapper;

    /**
     * 查询语音识别记录
     * 
     * @param id 语音识别记录ID
     * @return 语音识别记录
     */
    @Override
    public SpeechClassify selectSpeechClassifyById(Long id)
    {
        return speechClassifyMapper.selectSpeechClassifyById(id);
    }

    /**
     * 查询语音识别记录列表
     * 
     * @param speechClassify 语音识别记录
     * @return 语音识别记录
     */
    @Override
    public List<SpeechClassify> selectSpeechClassifyList(SpeechClassify speechClassify)
    {
        return speechClassifyMapper.selectSpeechClassifyList(speechClassify);
    }

    /**
     * 新增语音识别记录
     * 
     * @param speechClassify 语音识别记录
     * @return 结果
     */
    @Override
    public int insertSpeechClassify(SpeechClassify speechClassify)
    {
        return speechClassifyMapper.insertSpeechClassify(speechClassify);
    }

    /**
     * 修改语音识别记录
     * 
     * @param speechClassify 语音识别记录
     * @return 结果
     */
    @Override
    public int updateSpeechClassify(SpeechClassify speechClassify)
    {
        return speechClassifyMapper.updateSpeechClassify(speechClassify);
    }

    /**
     * 删除语音识别记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSpeechClassifyByIds(String ids)
    {
        return speechClassifyMapper.deleteSpeechClassifyByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除语音识别记录信息
     * 
     * @param id 语音识别记录ID
     * @return 结果
     */
    @Override
    public int deleteSpeechClassifyById(Long id)
    {
        return speechClassifyMapper.deleteSpeechClassifyById(id);
    }
}
