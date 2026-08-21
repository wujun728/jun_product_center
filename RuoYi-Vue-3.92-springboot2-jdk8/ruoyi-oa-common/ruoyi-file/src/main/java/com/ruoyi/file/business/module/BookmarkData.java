package com.ruoyi.file.business.module;

import lombok.Data;

import java.util.Map;

/**
 * 书签替换对象
 * @Author wocurr.com
 */
@Data
public class BookmarkData {
    /**
     * 书签键值对（key:书签名称，value:要替换的内容）
      */
    private Map<String, Object> bookmarks;

    /**
     * 特殊格式设置（key:书签名称，value:字体配置）
     */
    private Map<String, FontStyle> customFormats;

}
