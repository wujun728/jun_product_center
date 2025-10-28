package io.github.wujun728.admin.page.data;

import io.github.wujun728.admin.common.annotations.OrderBy;
import lombok.Data;

import java.util.Objects;

@Data
@OrderBy
public class PageButton extends BaseButton {
    //页面id
    private Long pageId;
    //按钮位置  页面按钮(新增,导出),行按钮(编辑,删除)
    private String buttonLocation;
    //添加到更多
    private String addToMore;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PageButton that = (PageButton) o;
        return Objects.equals(pageId, that.pageId) && Objects.equals(buttonLocation, that.buttonLocation) && Objects.equals(addToMore, that.addToMore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pageId, buttonLocation, addToMore);
    }
}
