package io.github.wujun728.admin.common.controller;

import cn.hutool.json.JSONUtil;
import io.github.wujun728.admin.common.Result;
import io.github.wujun728.admin.common.constants.ImportDataType;
import io.github.wujun728.admin.common.data.DataImportTemplate;
import io.github.wujun728.admin.common.data.DataImportTemplateField;
import io.github.wujun728.admin.db.data.ColumnInfo;
import io.github.wujun728.admin.db.data.TableInfo;
import io.github.wujun728.admin.db.service.JdbcService;
import io.github.wujun728.admin.db.service.TableService;
import io.github.wujun728.admin.page.constants.Whether;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/admin/dataImportTemplate")
public class DataImportTemplateController {
    @Resource
    private JdbcService jdbcService;
    @Resource
    private TableService tableService;
    @RequestMapping("/saveJson")
    public Result saveJson(@RequestBody Map<String,Object> map){
        String json = (String) map.get("json");
        DataImportTemplate obj = JSONUtil.toBean(json, DataImportTemplate.class);
        DataImportTemplate oldObj = jdbcService.findOne(DataImportTemplate.class,"code",obj.getCode());
        if(oldObj != null){
            obj.setId(oldObj.getId());
        }else{
            obj.setId(null);
        }
        jdbcService.transactionOption(()->{
            jdbcService.saveOrUpdate(obj);
            jdbcService.delete("delete from data_import_template_field where template_id = ? ",obj.getId());
            int seq = 1;
            for(DataImportTemplateField field:obj.getFields()){
                field.setId(null);
                field.setTemplateId(obj.getId());
                field.setSeq(seq++);
                jdbcService.saveOrUpdate(field);
            }
        });
        return Result.success();
    }

    @RequestMapping("/reload")
    public Result reload(@RequestBody DataImportTemplate template){
        TableInfo tableInfo = tableService.tableInfo(template.getTableName()).getData();
        if(tableInfo == null){
            return Result.error("表不存在");
        }
        List<String> names = new ArrayList<>();
        for(DataImportTemplateField field:template.getFields()){
            names.add(field.getColumnName().toUpperCase());
        }
        for(ColumnInfo columnInfo : tableInfo.getColumnInfos()){
            if(names.contains(columnInfo.getColumnName())){
                continue;
            }
            DataImportTemplateField field = new DataImportTemplateField();
            field.setColumnName(columnInfo.getColumnName());
            field.setColumnComment(columnInfo.getColumnComment());
            field.setWidth(columnInfo.getColumnComment().length()*2+5);
            field.setMust(Whether.YES);
            String columnType = columnInfo.getColumnType();
            if(columnType.contains("varchar")){
                field.setColumnType(ImportDataType.STRING);
            }else if(columnType.contains("timestamp") || columnType.contains("date")){
                field.setColumnType(ImportDataType.DATE);
                field.setColumnFormat("yyyy-MM-dd");
            }else if(columnType.contains("int")){
                field.setColumnType(ImportDataType.NUMBER);
            }else if(columnType.contains("float")){
                field.setColumnType(ImportDataType.DOUBLE);
            }
            if(columnInfo.getColumnName().contains("id")){
                field.setColumnType(ImportDataType.RELATE_TABLE);
                String columnName = columnInfo.getColumnName();
                String tableName = columnName.substring(0,columnName.lastIndexOf("_id"));
                field.setColumnName(tableName+"_code");
                field.setColumnComment(columnInfo.getColumnComment().replace("id", "编号"));
                field.setColumnFormat(tableName+",code");
                field.setFieldsMapping(columnInfo.getColumnName()+"=id&"+tableName+"_code=code");
            }
            template.getFields().add(field);
        }
        return Result.success(template);
    }
}
