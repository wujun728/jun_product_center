package com.ruoyi.seal.utils.pdf.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class KeyWordBean implements Comparable<KeyWordBean> {

    private float x;
    private float y;
    private int page;
    private String text;

    @Override
    public int compareTo(KeyWordBean o) {
        int i = (int) (o.getY() - this.getY());//先按照Y轴排序
        if (i == 0) {
            return (int) (this.x - o.getX());//如果Y轴相等了再按X轴进行排序
        }
        return i;
    }

}
