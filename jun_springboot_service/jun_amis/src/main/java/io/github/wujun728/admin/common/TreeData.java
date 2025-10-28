package io.github.wujun728.admin.common;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
//树结构
public class TreeData<T extends TreeData<T>> extends BaseData implements Comparable<T>{

    //父id
    private Long parentId;

    //子节点
    private List<T> children = new ArrayList<>();

    //序号
    private Integer seq;

    @Override
    public int compareTo(T o) {
        return Integer.compare(seq,o.getSeq());
    }
}
