package com.ruoyi.workfile.module;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 正文盖章参数
 * @Author wocurr.com
 */
@Data
public class MainStampParam implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 业务id
     */
    private String businessId;

    /**
     * 标题（每个表单应该都需要携带此字段）
     */
    private String title;

    /**
     * 是否添加水印
     */
    private String waterFlag;

    /**
     * 水印内容
     */
    private String waterContent;

    /**
     * 是否添加骑缝章
     */
    private String seamFlag;

    /**
     * 印章信息
     */
    private List<SealInfo> sealInfos;

    @Data
    public static class SealInfo implements Serializable {
        private static final long serialVersionUID = 2L;

        /**
         * 印章文件ID
         */
        private String sealFileId;

        /**
         * 印章位置信息
         */
        private float positionX;
        private float positionY;

        /**
         * 印章所在页码
         */
        private int sealPage;
    }

}
