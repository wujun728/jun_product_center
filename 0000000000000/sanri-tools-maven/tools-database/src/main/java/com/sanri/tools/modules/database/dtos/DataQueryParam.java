package com.sanri.tools.modules.database.dtos;

import com.sanri.tools.modules.core.exception.ToolException;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class DataQueryParam {
    @NotNull
    private String connName;
    private List<String> sqls;
    private String traceId;

    public String getFirstSql(){
        if (CollectionUtils.isNotEmpty(sqls)){
            return sqls.get(0);
        }
        throw new ToolException("必须要有一句 sql 做为查询条件");
    }
}
