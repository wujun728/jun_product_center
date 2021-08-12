package cc.mrbird.febs.system.controller;

import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.domain.FebsConstant;
import cc.mrbird.febs.common.domain.FebsResponse;
import cc.mrbird.febs.common.domain.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.system.domain.Dict;
import cc.mrbird.febs.system.service.DictService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

@Slf4j
@Validated
@RestController
@RequestMapping("dict")
public class DictController extends BaseController {

    private String message;

    @Autowired
    private DictService dictService;

    @GetMapping
    @RequiresPermissions("dict:view")
    public Map<String, Object> DictList(QueryRequest request, Dict dict) {
        return getDataTable(this.dictService.findDicts(request, dict));
    }

    @GetMapping("/trim")
    public Map<String,List<Map<String,Object>>> DictTrimList(Dict dict) {
        QueryRequest request= new QueryRequest();
        request.setPageNum(1);
        request.setPageSize(-1);
        IPage<Dict> dicts = this.dictService.findDicts(request, dict);
        Map<String,List<Map<String,Object>>> _map= new ConcurrentHashMap();
        dicts.getRecords().parallelStream().forEach(_dict->{
            HashMap<String,Object> map=new HashMap<>();
            map.put("keyy",_dict.getKeyy());
            map.put("valuee",_dict.getValuee());
            map.put("otherKeyy",_dict.getOtherKeyy());
            String key = _dict.getTableName()+ FebsConstant.UNDER_LINE+_dict.getFieldName();
            if(!_map.containsKey(key)){
                _map.put(key,new ArrayList<>());
            }
            _map.get(key).add(map);
        });
        return _map;
    }

    @Log("新增字典")
    @PostMapping
    @RequiresPermissions("dict:add")
    public FebsResponse addDict(@RequestBody @Valid Dict dict) throws FebsException {
        try {
            this.dictService.createDict(dict);
            return new FebsResponse().code("200").message("新增字典成功").status("success");
        } catch (Exception e) {
            message = "新增字典失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除字典")
    @DeleteMapping("/{dictIds}")
    @RequiresPermissions("dict:delete")
    public FebsResponse deleteDicts(@NotBlank(message = "{required}") @PathVariable String dictIds) throws FebsException {
        try {
            String[] ids = dictIds.split(StringPool.COMMA);
            this.dictService.deleteDicts(ids);
            return new FebsResponse().code("200").message("删除字典成功").status("success");
        } catch (Exception e) {
            message = "删除字典失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改字典")
    @PutMapping
    @RequiresPermissions("dict:update")
    public FebsResponse updateDict(@RequestBody @Valid Dict dict) throws FebsException {
        try {
            this.dictService.updateDict(dict);
            return new FebsResponse().code("200").message("修改字典成功").status("success");
        } catch (Exception e) {
            message = "修改字典失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PostMapping("excel")
    @RequiresPermissions("dict:export")
    public void export(QueryRequest request,@RequestBody Dict dict, HttpServletResponse response) throws FebsException {
        try {
            List<Dict> dicts = this.dictService.findDicts(request, dict).getRecords();
            ExcelKit.$Export(Dict.class, response).downXlsx(dicts, false);
        } catch (Exception e) {
            message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }
}
