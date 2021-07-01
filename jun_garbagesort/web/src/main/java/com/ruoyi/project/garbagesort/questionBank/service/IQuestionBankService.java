package com.ruoyi.project.garbagesort.questionBank.service;

import java.util.List;
import com.ruoyi.project.garbagesort.questionBank.domain.QuestionBank;

/**
 * 题库Service接口
 * 
 * @author luoxiang
 * @date 2021-06-08
 */
public interface IQuestionBankService 
{
    /**
     * 查询题库
     * 
     * @param questionId 题库ID
     * @return 题库
     */
    public QuestionBank selectQuestionBankById(Long questionId);

    /**
     * 查询题库列表
     * 
     * @param questionBank 题库
     * @return 题库集合
     */
    public List<QuestionBank> selectQuestionBankList(QuestionBank questionBank);

    /**
     * 新增题库
     * 
     * @param questionBank 题库
     * @return 结果
     */
    public int insertQuestionBank(QuestionBank questionBank);

    /**
     * 修改题库
     * 
     * @param questionBank 题库
     * @return 结果
     */
    public int updateQuestionBank(QuestionBank questionBank);

    /**
     * 批量删除题库
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteQuestionBankByIds(String ids);

    /**
     * 删除题库信息
     * 
     * @param questionId 题库ID
     * @return 结果
     */
    public int deleteQuestionBankById(Long questionId);
}
