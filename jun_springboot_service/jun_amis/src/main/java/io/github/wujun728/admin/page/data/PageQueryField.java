package io.github.wujun728.admin.page.data;

import io.github.wujun728.admin.common.annotations.OrderBy;
import lombok.Data;

import java.util.Objects;

/***
 * 页面查询字段
 */
@Data
@OrderBy
public class PageQueryField extends InputField  {
    //关联页面id
    private Long pageId;
    //操作类型
    private String opt;
    //是否关联字段
    private String ref;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PageQueryField that = (PageQueryField) o;
        return Objects.equals(pageId, that.pageId) && Objects.equals(opt, that.opt) && Objects.equals(ref, that.ref);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pageId, opt, ref);
    }
}
