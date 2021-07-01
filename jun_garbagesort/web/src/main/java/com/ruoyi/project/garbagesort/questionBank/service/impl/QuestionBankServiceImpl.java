package com.ruoyi.project.garbagesort.questionBank.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.garbagesort.questionBank.mapper.QuestionBankMapper;
import com.ruoyi.project.garbagesort.questionBank.domain.QuestionBank;
import com.ruoyi.project.garbagesort.questionBank.service.IQuestionBankService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 题库Service业务层处理
 * 
 * @author luoxiang
 * @date 2021-06-08
 */
@Service
public class QuestionBankServiceImpl implements IQuestionBankService 
{
    @Autowired
    private QuestionBankMapper questionBankMapper;

    /**
     * 查询题库
     * 
     * @param questionId 题库ID
     * @return 题库
     */
    @Override
    public QuestionBank selectQuestionBankById(Long questionId)
    {
        return questionBankMapper.selectQuestionBankById(questionId);
    }

    /**
     * 查询题库列表
     * 
     * @param questionBank 题库
     * @return 题库
     */
    @Override
    public List<QuestionBank> selectQuestionBankList(QuestionBank questionBank)
    {
        return questionBankMapper.selectQuestionBankList(questionBank);
    }

    /**
     * 新增题库
     * 
     * @param questionBank 题库
     * @return 结果
     */
    @Override
    public int insertQuestionBank(QuestionBank questionBank)
    {
        return questionBankMapper.insertQuestionBank(questionBank);
    }

    /**
     * 修改题库
     * 
     * @param questionBank 题库
     * @return 结果
     */
    @Override
    public int updateQuestionBank(QuestionBank questionBank)
    {
        return questionBankMapper.updateQuestionBank(questionBank);
    }

    /**
     * 删除题库对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteQuestionBankByIds(String ids)
    {
        return questionBankMapper.deleteQuestionBankByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除题库信息
     * 
     * @param questionId 题库ID
     * @return 结果
     */
    @Override
    public int deleteQuestionBankById(Long questionId)
    {
        return questionBankMapper.deleteQuestionBankById(questionId);
    }
}
