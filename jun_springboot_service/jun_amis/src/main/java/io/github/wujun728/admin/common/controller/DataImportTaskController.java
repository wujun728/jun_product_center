package io.github.wujun728.admin.common.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import io.github.wujun728.admin.common.Result;
import io.github.wujun728.admin.common.config.SessionContext;
import io.github.wujun728.admin.common.config.UserSession;
import io.github.wujun728.admin.common.constants.DataImportTaskStatus;
import io.github.wujun728.admin.common.constants.ImportDataType;
import io.github.wujun728.admin.common.data.DataImportTask;
import io.github.wujun728.admin.common.data.DataImportTemplate;
import io.github.wujun728.admin.common.data.DataImportTemplateField;
import io.github.wujun728.admin.db.service.JdbcService;
import io.github.wujun728.admin.page.constants.Whether;
import io.github.wujun728.admin.page.service.DicService;
import io.github.wujun728.admin.rbac.service.DynamicTaskService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/dataImportTask")
@Slf4j
public class DataImportTaskController {
    @Resource
    private JdbcService jdbcService;
    @Resource
    private DicService dicService;
    @Resource
    private DynamicTaskService dynamicTaskService;
    @RequestMapping("/save/{templateCode}")
    public Result save(@PathVariable String templateCode, @RequestBody Map<String,Object> params){
        log.info("templateCode,{},params,{}",templateCode,params);

        DataImportTask task = new DataImportTask();
        UserSession userSession = SessionContext.getSession();
        task.setUserId(userSession.getUserId());
        task.setEnterpriseId(userSession.getEnterpriseId());
        String srcFile = (String) params.get("srcFile");
        task.setSrcFile(srcFile);
        task.setTemplateCode(templateCode);
        task.setCreateTime(new Date());
        task.setDataImportTaskStatus(DataImportTaskStatus.NOT_START);
        String planTime = (String) params.get("planTime");
        Date minTime = new Date(System.currentTimeMillis()+30*1000);
        if(StringUtils.isBlank(planTime)){
            task.setPlanTime(minTime);
        }else {
            Date date = Convert.toDate(planTime);
            if(date.getTime() <minTime.getTime()){
                date = minTime;
            }
            task.setPlanTime(date);
        }
        jdbcService.transactionOption(()->{
            jdbcService.saveOrUpdate(task);

            Long dynamicTaskId = dynamicTaskService.save("数据导入", task.getId(), task.getPlanTime(), null, null,"dataImportTaskApi");
            task.setDynamicTaskId(dynamicTaskId);
            jdbcService.saveOrUpdate(task);
        });
        return Result.success();
    }
    @RequestMapping("/template/{templateCode}/{name}")
    public String template(@PathVariable String templateCode, HttpServletResponse response) throws IOException {
        log.info("下载文件{}",templateCode);
        DataImportTemplate template = jdbcService.findOne(DataImportTemplate.class, "code", templateCode);
        if(template == null){
            return StrUtil.format("模板{}不存在",templateCode);
        }
        List<DataImportTemplateField> fields = jdbcService.find("select * " +
                "from data_import_template_field " +
                "where template_id = ? order by seq asc ", DataImportTemplateField.class, template.getId());
        ExcelWriter writer = ExcelUtil.getWriter(true);
        List<String> row = new ArrayList<>();
        int i=0;
        Sheet sheet = writer.getSheet();
        Workbook wb = writer.getWorkbook();
        Font font = wb.createFont();
//        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 9);

        DataFormat dataFormat = wb.createDataFormat();

        CellStyle cellStyle = writer.getCellStyle();
        cellStyle.setDataFormat(dataFormat.getFormat("@"));
        for(DataImportTemplateField field:fields){
            String title = field.getColumnComment();
            String comment = "";
            if(Whether.YES.equals(field.getMust())){
                comment += "必填\n";
            }else{
                comment += "非必填\n";
            }
            String label = dicService.getLabel("importDataType", field.getColumnType());
            comment += label+"类型\n";
            if(ImportDataType.DATE.equals(field.getColumnType())){
                comment += "格式为:"+field.getColumnFormat()+"\n";
            }else if(ImportDataType.DIC.equals(field.getColumnType())){
                List<String> labels = dicService.getLabels(field.getColumnFormat());
                comment += "可选值为"+labels+"\n";
            }

            row.add(title);
            writer.setColumnWidth(i,field.getWidth());
//            writer.set
            Cell cell = writer.getOrCreateCell(i, 0);
            Drawing<?> draw = sheet.createDrawingPatriarch();
            Comment cellComment = draw.createCellComment(new XSSFClientAnchor(0, 0, 0, 0, (short) i, 0, (short)( i + 3), 5));

            XSSFRichTextString rtf = new XSSFRichTextString(comment);

            rtf.applyFont(font);
            cellComment.setString(rtf);
            cell.setCellComment(cellComment );
            writer.setColumnStyle(i,cellStyle);
            i++;
        }

        writer.writeRow(row);
//        writer.close();
        writer.flush(response.getOutputStream(),true);

        return null;
    }
    @RequestMapping("/cancel/{id}")
    public Result cancel(@PathVariable Long id){
        DataImportTask task = jdbcService.getById(DataImportTask.class, id);
        if(!DataImportTaskStatus.NOT_START.equals(task.getDataImportTaskStatus())){
            return Result.error("当前状态无法取消");
        }
        task.setDataImportTaskStatus(DataImportTaskStatus.EXCEPTION_CLOSE);
        task.setErrorMsg("手工取消");
        jdbcService.saveOrUpdate(task);
        dynamicTaskService.stop(task.getDynamicTaskId());
        return Result.success();
    }
}
