package com.sanri.tools.modules.database.dtos;

import com.sanri.tools.modules.database.dtos.meta.ActualTableName;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Data
public class ExcelImportParam {
    @NotNull
    private String connName;
    @Valid
    private ActualTableName actualTableName;
    @PositiveOrZero
    private int startRow;
    @Valid
    private List<Mapping> mapping;

    @Data
    public static class Mapping{
        private int index = -1;
        @NotNull
        private String columnName;
        private String random;
        private String constant;
    }
}
