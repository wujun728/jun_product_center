package io.github.wujun728.admin.rbac.controller;

import cn.hutool.json.JSONUtil;
import io.github.wujun728.admin.common.Result;
import io.github.wujun728.admin.db.service.JdbcService;
import io.github.wujun728.admin.rbac.data.Config;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/config")
public class ConfigController {
    @Resource
    private JdbcService jdbcService;
    @GetMapping("/get/{code}")
    public Result get(@PathVariable String code){
        Config config = jdbcService.findOne(Config.class, "code", code);
        if(config == null){
            return Result.success(new HashMap<>());
        }
        return Result.success(JSONUtil.parseObj(config.getValue()));
    }

    @PostMapping("/save/{code}")
    public Result save(@PathVariable String code,@RequestBody Map<String,Object> value){
        Config config = jdbcService.findOne(Config.class, "code", code);
        if(config == null){
            config = new Config();
            config.setCode(code);
            config.setName(code);
        }
        config.setValue(JSONUtil.toJsonStr(value));

        jdbcService.saveOrUpdate(config);
        return Result.success();
    }
}
