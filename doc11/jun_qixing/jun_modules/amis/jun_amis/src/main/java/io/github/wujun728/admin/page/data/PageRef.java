package io.github.wujun728.admin.page.data;

import io.github.wujun728.admin.common.BaseData;
import io.github.wujun728.admin.common.annotations.OrderBy;
import lombok.Data;

import java.util.Objects;

/***
 * @date 2022-02-25 10:22:41
 * @remark 页面关联
 */
@Data
@OrderBy
public class PageRef extends BaseData {
    //关联类型
    private String refType;
    //关联字段
    private String refField;
    //关联页面编号
    private String refPageCode;
    //序号
    private Integer seq;
    //页面id
    private Long pageId;
    //名称
    private String refName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PageRef pageRef = (PageRef) o;
        return Objects.equals(refType, pageRef.refType) && Objects.equals(refField, pageRef.refField) && Objects.equals(refPageCode, pageRef.refPageCode) && Objects.equals(seq, pageRef.seq) && Objects.equals(pageId, pageRef.pageId) && Objects.equals(refName, pageRef.refName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), refType, refField, refPageCode, seq, pageId, refName);
    }
}
