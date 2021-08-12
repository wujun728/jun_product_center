package com.sanri.tools.modules.core.dtos;

import lombok.Data;

@Data
public class PageResponseDto<T> {
    //返回对象，可装list以及其他参数
    private Object obj;
    private Long total;

    public PageResponseDto() {
    }

    public PageResponseDto(Object obj, Long total) {
        this.obj = obj;
        this.total = total;
    }
}
