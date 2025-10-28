package io.github.wujun728.admin.page.data;

import io.github.wujun728.admin.common.BaseData;
import io.github.wujun728.admin.common.annotations.OrderBy;
import io.github.wujun728.admin.page.constants.Whether;
import io.github.wujun728.admin.rbac.service.InputParam;
import lombok.Data;

import java.beans.Transient;
import java.util.Objects;

/***
 * 页面结果字段
 */
@Data
@OrderBy
public class PageResultField extends BaseData implements InputParam {
    //关联页面id
    private Long pageId;
    //字段
    private String field;
    //字段中文
    private String label;
    //宽度
    private Integer width;
    //字段类型
    private String type;
    //格式化
    private String format;
    //是否隐藏
    private String hidden = "NO";
    //序号
    private int seq;
    //扩展json
    private String extraJson ;
    //统计sql
    private String statisticsSql;
    //统计标签
    private String statisticsLabel;
    //冻结
    private String fixed;
    //默认展示
    private String toggled;
    @Override
    @Transient
    public String getMust() {
        return Whether.NO;
    }

    @Override
    @Transient
    public String getComponentType() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PageResultField that = (PageResultField) o;
        return seq == that.seq && Objects.equals(pageId, that.pageId) && Objects.equals(field, that.field) && Objects.equals(label, that.label) && Objects.equals(width, that.width) && Objects.equals(type, that.type) && Objects.equals(format, that.format) && Objects.equals(hidden, that.hidden) && Objects.equals(extraJson, that.extraJson) && Objects.equals(statisticsSql, that.statisticsSql) && Objects.equals(statisticsLabel, that.statisticsLabel) && Objects.equals(fixed, that.fixed) && Objects.equals(toggled, that.toggled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pageId, field, label, width, type, format, hidden, seq, extraJson, statisticsSql, statisticsLabel, fixed, toggled);
    }
}
