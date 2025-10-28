package io.github.wujun728.admin.util;

import cn.hutool.core.util.ReflectUtil;

import java.lang.reflect.Field;
import java.util.Comparator;

public class SeqComparator implements Comparator<Object> {
    @Override
    public int compare(Object o1, Object o2) {
        Field seq = ReflectUtil.getField(o1.getClass(), "seq");
        if(seq != null){
            Integer seq1 = (Integer) ReflectUtil.getFieldValue(o1, seq);
            Integer seq2 = (Integer) ReflectUtil.getFieldValue(o2, seq);
            return seq1.compareTo(seq2);
        }
        return 0;
    }
    public static SeqComparator instance = new SeqComparator();
}
