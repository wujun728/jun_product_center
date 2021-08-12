package com.sanri.tools.modules.mongodb.service;

import com.alibaba.fastjson.JSONObject;
import com.sanri.tools.modules.core.dtos.param.PageParam;
import lombok.Data;
import org.bson.conversions.Bson;

import javax.validation.constraints.NotNull;

@Data
public class MongoQueryParam {
    @NotNull
    private String connName;
    @NotNull
    private String databaseName;
    @NotNull
    private String collectionName;
    private String filter;
    private String sort;
}
