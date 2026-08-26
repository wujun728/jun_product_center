package com.ruoyi.workfile.module;

import com.ruoyi.workfile.domain.WorkflowMainText;
import lombok.Data;


/**
 * @Author wocurr.com
 */
@Data
public class MainInfoResult extends WorkflowMainText {

    private static final long serialVersionUID = 1L;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 正文类型，0-用户上传，1-书签替换
     */
    private String mainTextType;

    /**
     * 是否可以盖章（用户上传配置下，上传的是非office文件，不支持盖章）
     */
    private boolean canSeal;
}
