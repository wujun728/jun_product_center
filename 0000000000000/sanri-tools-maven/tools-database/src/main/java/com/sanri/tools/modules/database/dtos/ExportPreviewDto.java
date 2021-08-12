package com.sanri.tools.modules.database.dtos;

import lombok.Data;

/**
 * 数据预览查询结果
 */
@Data
public class ExportPreviewDto {
    private DynamicQueryDto body;
    private long total;

    public ExportPreviewDto() {
    }

    public ExportPreviewDto(DynamicQueryDto body, long total) {
        this.body = body;
        this.total = total;
    }
}
