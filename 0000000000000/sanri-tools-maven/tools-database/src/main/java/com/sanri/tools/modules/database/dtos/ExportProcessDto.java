package com.sanri.tools.modules.database.dtos;

import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
public class ExportProcessDto {
    private String relativePath;
    private boolean finish;
    private String percent;

    public ExportProcessDto() {
    }

    public ExportProcessDto(String relativePath, String percent) {
        this.relativePath = relativePath;
        this.percent = percent;
    }

    public ExportProcessDto(String relativePath, float current , float total) {
        this.relativePath = relativePath;
        BigDecimal bigDecimal = new BigDecimal(current).divide(new BigDecimal(total)).setScale(2, RoundingMode.HALF_DOWN);
        this.percent = bigDecimal.toString();
        if (current == total){
            this.finish = true;
        }
    }
}
