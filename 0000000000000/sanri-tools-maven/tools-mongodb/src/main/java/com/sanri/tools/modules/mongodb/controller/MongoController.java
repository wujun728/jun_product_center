package com.sanri.tools.modules.mongodb.controller;

import com.sanri.tools.modules.core.dtos.PageResponseDto;
import com.sanri.tools.modules.core.dtos.param.PageParam;
import com.sanri.tools.modules.mongodb.dtos.CollectionDto;
import com.sanri.tools.modules.mongodb.service.MongoQueryParam;
import com.sanri.tools.modules.mongodb.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/mongo")
@Validated
public class MongoController {
    @Autowired
    private MongoService mongoService;

    /**
     * 查询所有的库
     * @param connName
     * @return
     * @throws IOException
     */
    @GetMapping("/databaseNames")
    public List<String> databaseNames(@NotNull String connName) throws IOException {
        return mongoService.databaseNames(connName);
    }

    /**
     * 查询某个库里的所有集合
     * @param connName
     * @param databaseName
     * @return
     * @throws IOException
     */
    @GetMapping("/collectionNames/{databaseName}")
    public List<CollectionDto> collectionNames(@NotNull String connName, @PathVariable("databaseName") String databaseName) throws IOException {
        return mongoService.collectionNames(connName,databaseName);
    }

    /**
     * mongo 分页数据查询
     * @param mongoQueryParam
     * @param pageParam
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @GetMapping("/queryPage")
    public PageResponseDto<List<String>> queryPage(@Valid MongoQueryParam mongoQueryParam,PageParam pageParam) throws IOException, ClassNotFoundException {
        return mongoService.queryDataPage(mongoQueryParam,pageParam);
    }
}
