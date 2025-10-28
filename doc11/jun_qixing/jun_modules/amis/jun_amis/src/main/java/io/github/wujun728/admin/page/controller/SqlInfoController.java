package io.github.wujun728.admin.page.controller;

import io.github.wujun728.admin.common.Result;
import io.github.wujun728.admin.db.service.SqlInfoService;
import io.github.wujun728.admin.page.data.SqlInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/sqlInfo")
@Slf4j
public class SqlInfoController {
    @Resource
    private SqlInfoService sqlInfoService;

    @PostMapping("/resultFields")
    public Result<SqlInfo> resultFields(@RequestBody SqlInfo sqlInfo){
        sqlInfoService.resultFields(sqlInfo);
        return Result.success(sqlInfo);
    }
}
