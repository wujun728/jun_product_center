package com.ruoyi.workfile.service;


import com.ruoyi.file.business.module.BookmarkData;
import com.ruoyi.workfile.domain.WorkflowMainText;
import com.ruoyi.workfile.module.MainInfoResult;
import com.ruoyi.workfile.module.MainStampParam;
import com.ruoyi.workfile.module.MainTextParam;

/**
 * 正文Service接口
 *
 * @author wocurr.com
 */
public interface IWorkflowMainTextService {
    /**
     * 查询正文
     *
     * @param id 正文主键
     * @return 正文
     */
    public WorkflowMainText getWorkflowMainTextById(String id);

    /**
     * 根据业务ID查询正文
     *
     * @param businessId 业务ID
     * @return
     */
    public WorkflowMainText getByBusinessId(String businessId);

    /**
     * 获取正文信息
     *
     * @param param 查询参数
     * @return
     */
    public MainInfoResult getMainInfo(MainTextParam param);

    /**
     * 新增正文
     *
     * @param workflowMainText 正文
     * @return 结果
     */
    public int saveWorkflowMainText(WorkflowMainText workflowMainText);

    /**
     * 修改正文
     *
     * @param workflowMainText 正文
     * @return 结果
     */
    public int updateWorkflowMainText(WorkflowMainText workflowMainText);

    /**
     * 异步任务更新正文（任务执行时，无法获取当前登录人信息，需要通过参数传递）
     *
     * @param param
     * @return
     */
    public int asyncUpdateWorkflowMainText(MainTextParam param);

    /**
     * 上传正文
     *
     * @param mainTextParam 上传参数
     */
    public void uploadMainText(MainTextParam mainTextParam);

    /**
     * 删除正文
     *
     * @param businessId 业务ID
     */
    public int removeMainText(String businessId);

    /**
     * 保存正文
     *
     * @param templateId 模板ID
     * @param businessId 业务ID
     * @param data       书签数据
     */
    public void saveMainText(String templateId, String businessId, BookmarkData data);

    /**
     * 正文盖章
     *
     * @param param 盖章参数
     * @return 返回盖章后的文件id
     */
    public String stamp(MainStampParam param);

    /**
     * 还原印章
     *
     * @param businessId 业务ID
     * @return
     */
    public String restoreSeal(String businessId);
}
