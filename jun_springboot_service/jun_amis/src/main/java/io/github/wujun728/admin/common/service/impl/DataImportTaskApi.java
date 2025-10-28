package io.github.wujun728.admin.common.service.impl;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import io.github.wujun728.admin.common.constants.DataImportTaskStatus;
import io.github.wujun728.admin.common.constants.ImportDataType;
import io.github.wujun728.admin.common.constants.RepeatStrategy;
import io.github.wujun728.admin.common.data.DataImportTask;
import io.github.wujun728.admin.common.data.DataImportTemplate;
import io.github.wujun728.admin.common.data.DataImportTemplateField;
import io.github.wujun728.admin.common.data.SysFile;
import io.github.wujun728.admin.common.service.SysFileService;
import io.github.wujun728.admin.db.data.ColumnInfo;
import io.github.wujun728.admin.db.data.TableInfo;
import io.github.wujun728.admin.db.service.JdbcService;
import io.github.wujun728.admin.db.service.TableService;
import io.github.wujun728.admin.page.constants.Whether;
import io.github.wujun728.admin.page.service.DicService;
import io.github.wujun728.admin.rbac.data.DynamicTask;
import io.github.wujun728.admin.rbac.service.DynamicTaskApi;
import io.github.wujun728.admin.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service("dataImportTaskApi")
@Slf4j
public class DataImportTaskApi implements DynamicTaskApi {
    @Resource
    SysFileService sysFileService;
    @Resource
    JdbcService jdbcService;
    @Resource
    DicService dicService;
    @Resource
    @Lazy
    TableService tableService;
    @Override
    public String execute(DynamicTask task) {
        DataImportTask dataImportTask = jdbcService.getById(DataImportTask.class, task.getRefId());
        DataImportTemplate template = jdbcService.findOne(DataImportTemplate.class, "code", dataImportTask.getTemplateCode());
        List<DataImportTemplateField> fields =
                jdbcService.find("select * " +
                                "from data_import_template_field " +
                                "where template_id = ? " +
                                "order by seq asc ",
                        DataImportTemplateField.class, template.getId());
        dataImportTask.setDataImportTaskStatus(DataImportTaskStatus.VALIDATION);
        dataImportTask.setStartTime(new Date());
        jdbcService.forceSaveOrUpdate(task);
        try{
            InputStream in = sysFileService.download(dataImportTask.getSrcFile());
            ExcelReader reader = ExcelUtil.getReader(in);
            List<List<Object>> rows = reader.read();
            if(rows.size()<=1){
                throw new RuntimeException("导入的excel无数据");
            }
            //excel标题
            List<Object> titles = rows.get(0);
            if(titles.size() != fields.size()){
                throw new RuntimeException("导入的excel字段数量["+titles.size()+"]和模板字段数量["+fields.size()+"]不一致");
            }
            //字段map
            Map<String,DataImportTemplateField> fieldMap = new HashMap<>();
            //字段索引
            Map<String,Integer> fieldIndex = new HashMap<>();
            //字典缓存数据
            Map<String, Map<String, String>> dicMap = new HashMap<>();
            //日期格式化
            Map<String, SimpleDateFormat> dateFormatMap = new HashMap<>();
            //关联表缓存数据
            //字段名:<字段值:关联对象>
            Map<String, Map<String, Map<String, Object>>> relateCacheMap = new HashMap<String, Map<String,Map<String,Object>>>();
            Map<String,List<String>> tableCache = new HashMap<>();
            for(int i=0;i<fields.size();i++){
                DataImportTemplateField field = fields.get(i);
                fieldMap.put(field.getColumnName(),field);
                fieldIndex.put(field.getColumnName(),i);
                String columnType = field.getColumnType();

                if(ImportDataType.DIC.equals(columnType)){
                    if(StringUtils.isBlank(field.getColumnFormat())){
                        throw new RuntimeException("数据字典类型字段没有配置字典编号["+field.getColumnName()+"]");
                    }
                    dicMap.put(field.getColumnFormat(), dicService.labelValueMap(field.getColumnFormat()));
                }else if(ImportDataType.DATE.equals(columnType)){
                    if(StringUtils.isBlank(field.getColumnFormat())){
                        throw new RuntimeException("日期类型字段没有日期格式["+field.getColumnName()+"]");
                    }
                    if(!dateFormatMap.containsKey(field.getColumnFormat())){
                        dateFormatMap.put(field.getColumnFormat(), new SimpleDateFormat(field.getColumnFormat()));
                    }
                }else if(ImportDataType.RELATE_TABLE.equals(columnType)){
                    relateCacheMap.put(field.getColumnName(), new HashMap<String,Map<String, Object>>());

                    String[] splitStr = StringUtil.splitStr(field.getColumnFormat(), ",");
                    //表
                    String table = StringUtil.toSqlColumn(splitStr[0]);
                    TableInfo tableInfo = tableService.get(table).getData();
                    if(tableInfo == null){
                        throw new RuntimeException("关联表不存在["+table+"]");
                    }
                    List<String> cols = tableInfo.getColumnInfos().stream().map(ColumnInfo::getColumnName).collect(Collectors.toList());
                    tableCache.put(table,cols);
                }
            }

            List<String> cols = tableService.get(template.getTableName()).getData().getColumnInfos().stream().map(ColumnInfo::getColumnName).collect(Collectors.toList());
            tableCache.put(template.getTableName(),cols);

            MultiValueMap<String,Integer> keyRowsMap = new LinkedMultiValueMap<>();
            //重复报错
            boolean repeatError = false;
            //重复更新
            boolean repeatUpdate = false;
            List<Integer> keyIndex = new ArrayList<>();
            List<String> repeatFieldComments = new ArrayList<>();
            //判断是否有判重字段列表
            //未配置则全部新增
            //配置字段列表,分为重复 更新/报错
            if(StringUtils.isNotBlank(template.getRepeatFields())){
                String[] keys = template.getRepeatFields().split(",");
                for(String key:keys){
                    Integer idx = fieldIndex.get(key);
                    if(idx == null){
                        throw new RuntimeException("判重字段"+key+"不存在");
                    }
                    keyIndex.add(idx);
                    repeatFieldComments.add(fields.get(idx).getColumnComment());
                }
                if(RepeatStrategy.ERROR.equals(template.getRepeatStrategy())){
                    repeatError = true;
                }else{
                    repeatUpdate = true;
                }
                if(repeatError){
                    for(int rowIndex = 1;rowIndex<rows.size();rowIndex++){
                        List<Object> row = rows.get(rowIndex);
                        List<Object> values = new ArrayList<>();
                        for(Integer idx:keyIndex){
                            if(idx >= row.size()){
                                values.add(null);
                            }else{
                                values.add(row.get(idx));
                            }
                        }
                        String key = values.toString();
                        keyRowsMap.add(key,rowIndex+1);
                    }
                }
            }
            List<Map<String,Object>> realDatas = new ArrayList<>();
            List<List<Object>> errors = new ArrayList<>();
            titles.add("错误信息");
            errors.add(titles);
            for(int rowIndex = 1;rowIndex<rows.size();rowIndex++) {
                List<Object> row = rows.get(rowIndex);
                boolean flag = true;
                StringBuilder err = new StringBuilder();
                err.append("第"+(rowIndex+1)+"行错误,");
                if(row.size() > fields.size()){
                    //数据多了,自动截取
                    row = row.subList(0,fields.size());
                }
                Map<String, Object> realData = new HashMap<>();
                realDatas.add(realData);
                for(int col=0;col<fields.size();col++){
                    DataImportTemplateField field = fields.get(col);
                    if(col>=row.size()){
                        //行数据长度不够,自动补全
                        row.add(null);
                    }
                    Object realValue = null;
                    Object value = row.get(col);
                    if((value == null || StringUtils.isBlank(String.valueOf(value)))){
                        if(Whether.YES.equals(field.getMust())){
                            err.append(field.getColumnComment()+"不能为空");
                            flag = false;
                        }
                        continue;
                    }

                    String columnType = field.getColumnType();
                    String fieldName = StringUtil.toFieldColumn(field.getColumnName());
                    if(ImportDataType.STRING.equals(columnType)){
                        //字符串
                        realValue = value;
                        realData.put(fieldName, realValue);
                    }else if(ImportDataType.DIC.equals(columnType)){
                        //字典
                        Map<String, String> dic = dicMap.get(field.getColumnFormat());
                        if(!dic.containsKey(value)){
                            flag = false;
                            err.append(field.getColumnComment()+"错误,可选值为,"+dic.keySet().toString());
                        }
                        realValue = dic.get(value);
                        realData.put(fieldName, realValue);
                    }else if(ImportDataType.DATE.equals(columnType)){
                        //日期
                        try {
                            realValue = dateFormatMap.get(field.getColumnFormat()).parse(value.toString());
                        } catch (Exception e) {
                            flag = false;
                            err.append(field.getColumnComment()+"日期格式错误,应该为:"+field.getColumnFormat()+",");
                        }
                        realData.put(fieldName, realValue);
                    }else if(ImportDataType.NUMBER.equals(columnType)){
                        //整数
                        try {
                            realValue = Long.parseLong(value.toString());
                        } catch (Exception e) {
                            flag = false;
                            err.append(field.getColumnComment()+"不是数字");
                        }
                        realData.put(fieldName, realValue);
                    }else if(ImportDataType.DOUBLE.equals(columnType)){
                        //小数
                        try {
                            realValue = Double.parseDouble(value.toString());
                        } catch (Exception e) {
                            flag = false;
                            err.append(field.getColumnComment()+"不是小数类型");
                        }
                        realData.put(fieldName, realValue);
                    }else if(ImportDataType.RELATE_TABLE.equals(columnType)){
                        //关联表
                        Map<String, Map<String, Object>> relateMap = relateCacheMap.get(field.getColumnName());
                        String[] splitStr = StringUtil.splitStr(field.getColumnFormat(), ",");
                        if(splitStr.length !=2){
                            throw new RuntimeException("关联表配置错误["+field.getColumnComment()+"],应该为[表名称,字段名]");
                        }
                        //表
                        String table = StringUtil.toSqlColumn(splitStr[0]);
                        //字段
                        String column = splitStr[1];
                        if(!relateMap.containsKey(value)){
                            List<String> _cols = tableCache.get(table);
                            String sql = "select * from {} where {} = ?";
                            List<Object> values = new ArrayList<>();
                            values.add(value.toString());

                            if(_cols.contains("enterprise_id")){
                                sql += " and enterprise_id = ? ";
                                values.add(dataImportTask.getEnterpriseId());
                            }
                            Map<String, Object> rObj = jdbcService.findOne(
                                    StrFormatter.format(sql, table,column)
                                    ,values.toArray());
                            relateMap.put(value.toString(), rObj);
                        }
                        Map<String, Object> rObj = relateMap.get(value);
                        if(rObj == null){
                            flag = false;
                            err.append(field.getColumnComment()+"错误,找不到关联数据,");
                        }else{
                            //关联表格式  userId=pid&userName=userName
                            String[] fieldsArr = field.getFieldsMapping().split("&");
                            int i=0;
                            for(String f:fieldsArr){
                                String[] arr = f.split("=");
                                realData.put(StringUtil.toFieldColumn(arr[0]), rObj.get(StringUtil.toFieldColumn(arr[1])));
                                if(i==0){
                                    //关联表id字段值
                                    realData.put(field.getColumnName()+"_r_id", rObj.get(arr[1]));
                                    //关联表实际的id字段名
                                    realData.put(field.getColumnName()+"_r_name", arr[0]);
                                }
                                i ++;
                            }
                        }
                    }
                }

                if(flag && repeatError){
                    List<Object> excelKeyValues = new ArrayList<>();
                    List<Object> dbKeyValues = new ArrayList<>();
                    String repeateSql = "select id from "+template.getTableName()+" where 1=1 ";
                    if(tableCache.get(template.getTableName()).contains("enterprise_id")){
                        repeateSql += " and enterprise_id = ? ";
                        dbKeyValues.add(dataImportTask.getEnterpriseId());
                    }

                    //最后一个是多字段分隔符
                    for(int i=0;i<keyIndex.size();i++){
                        DataImportTemplateField field = fields.get(i);
                        Object value = row.get(i);
                        excelKeyValues.add(value);
                        String name = field.getColumnName();
                        if(ImportDataType.RELATE_TABLE.equals(field.getColumnType())){
                            repeateSql += " and "+realData.get(name+"_r_name")+" = ? ";
                            dbKeyValues.add(realData.get(name+"_r_id"));
                        }else{
                            repeateSql += " and "+name+" = ? ";
                            dbKeyValues.add(value);
                        }
                    }
                    String excelKey = excelKeyValues.toString();
                    List<Integer> repeateRows = keyRowsMap.get(excelKey);
                    if(repeateRows.size()>1){
                        flag = false;
                        err.append("Excel内联合唯一字段["+StringUtil.concatStr(repeatFieldComments, "-")+"]第"+repeateRows+"重复,");
                    }else{
                        Map<String, Object> r = jdbcService.findOne(repeateSql, dbKeyValues.toArray());
                        if(r != null){
                            flag = false;
                            err.append("数据库内联合唯一字段["+StringUtil.concatStr(repeatFieldComments, "-")+"]重复,");
                        }
                    }
                }

                if(!flag){
                    row.add(err.toString());
                    errors.add(row);
                }
            }

            if(errors.size()>1){
                ExcelWriter writer = ExcelUtil.getWriter(true);
                for(int i=0;i<titles.size();i++){
                    if(i == titles.size()-1){
                        //错误列
                        writer.setColumnWidth(i,100);
                    }else{
                        writer.setColumnWidth(i,fields.get(i).getWidth());
                    }
                }
                for(List<Object> row:errors){
                    writer.writeRow(row);
                }
                ByteArrayOutputStream out= new ByteArrayOutputStream();
                writer.flush(out,false);
                ByteArrayInputStream byteInput = new ByteArrayInputStream(out.toByteArray());
                SysFile errFile = sysFileService.upload(byteInput, "错误信息.xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                byteInput.close();
                dataImportTask.setDataImportTaskStatus(DataImportTaskStatus.IN_VALID);
                dataImportTask.setErrorFile(StrUtil.format("/admin/download/{}/错误信息.xlsx",errFile.getId()));
                dataImportTask.setEndTime(new Date());
                jdbcService.forceSaveOrUpdate(dataImportTask);
            }else{
                boolean hasEnterpriseId = tableCache.get(template.getTableName()).contains("enterprise_id");
                for (Map<String,Object> obj:realDatas){
                    Map<String, Object> oldObj = null;

                    if(repeatUpdate){
                        //重复更新
                        String repeateSql = "select * from "+template.getTableName()+" where 1 = 1 ";
                        List<Object> dbKeyValues = new ArrayList<>();
                        if(hasEnterpriseId){
                            repeateSql += " and enterprise_id = ? ";
                            dbKeyValues.add(dataImportTask.getEnterpriseId());
                        }
                        //最后一个是多字段分隔符
                        for(int i=0;i<keyIndex.size()-1;i++){
                            int idx = keyIndex.get(i);
                            DataImportTemplateField field = fields.get(idx);
                            String name = field.getColumnName();
                            Object value = obj.get(StringUtil.toFieldColumn(name));
                            if(ImportDataType.RELATE_TABLE.equals(field.getColumnType())){
                                repeateSql += " and "+obj.get(name+"_r_name")+" = ? ";
                                dbKeyValues.add(obj.get(name+"_r_id"));
                            }else{
                                repeateSql += " and "+name+" = ? ";
                                dbKeyValues.add(value);
                            }
                        }
                        oldObj = jdbcService.findOne(repeateSql, dbKeyValues.toArray());
                    }
                    if(oldObj != null){
                        oldObj.putAll(obj);
                        oldObj.put("updateTime", new Date());
                        obj = oldObj;
                    }else{
                        obj.put("createTime", new Date());
                        obj.put("updateTime", new Date());
                        obj.put("createUserId", dataImportTask.getUserId());
                        obj.put("enterpriseId", dataImportTask.getEnterpriseId());
                    }
                    jdbcService.saveOrUpdate(obj,template.getTableName());
                }
                dataImportTask.setDataImportTaskStatus(DataImportTaskStatus.SUCCESS);
                dataImportTask.setEndTime(new Date());
                jdbcService.forceSaveOrUpdate(dataImportTask);
            }
        }catch (Exception e){
            log.error("数据导入失败",e);
            dataImportTask.setDataImportTaskStatus(DataImportTaskStatus.EXCEPTION_CLOSE);
            dataImportTask.setErrorMsg(ExceptionUtil.getRootCauseMessage(e));
            dataImportTask.setEndTime(new Date());
            jdbcService.forceSaveOrUpdate(dataImportTask);
            throw new RuntimeException(e);
        }
        return null;
    }
}
