package io.github.wujun728.admin.page.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import io.github.wujun728.admin.common.*;
import io.github.wujun728.admin.common.*;
import io.github.wujun728.admin.common.constants.Constants;
import io.github.wujun728.admin.db.service.JdbcService;
import io.github.wujun728.admin.page.constants.PageKey;
import io.github.wujun728.admin.page.constants.Whether;
import io.github.wujun728.admin.page.data.*;
import io.github.wujun728.admin.page.data.Page;
import io.github.wujun728.admin.page.data.PageResultField;
import io.github.wujun728.admin.page.service.FormService;
import io.github.wujun728.admin.page.service.PageButtonService;
import io.github.wujun728.admin.page.service.PageConfigService;
import io.github.wujun728.admin.page.service.PageService;
import io.github.wujun728.admin.rbac.service.ApiService;
import io.github.wujun728.admin.util.StringUtil;
import io.github.wujun728.admin.util.TemplateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping({"/admin/page","/lowcode/admin/page"})
@Slf4j
public class PageController {
    @Resource
    private JdbcService jdbcService;

    @Resource
    private PageService pageService;

    @Resource
    private FormService formService;
    @Resource
    private PageConfigService pageConfigService;

    @Resource
    private PageButtonService pageButtonService;

    @Resource
    private ApiService apiService;

    @RequestMapping("/query")
    public Result<PageData<Page>> query(@RequestBody PageParam pageParam){
        String sql = "select * from page where 1=1 ";
        List<Object> values = new ArrayList<>();
        if(StringUtils.isNotBlank(pageParam.getStr("code"))){
            sql += " and code like ? ";
            values.add("%"+pageParam.getStr("code")+"%");
        }
        if(StringUtils.isNotBlank(pageParam.getStr("name"))){
            sql += " and name like ? ";
            values.add("%"+pageParam.getStr("name")+"%");
        }
        return jdbcService.query(pageParam,Page.class,sql,values.toArray());
    }
    @RequestMapping("/copyPage")
    public Result<Page> copyPage(Long id){
        if(id == null){
            return Result.success();
        }
        Page copy = pageService.get(id);
        copy.setId(null);
        copy.setCode(copy.getCode()+"_copy");
        return Result.success(copy);
    }
    /*@RequestMapping("/get")
    public Result<Page> get(Long id){
        if(id == null){
            return Result.success(new Page());
        }
        return Result.success(pageService.get(id));
    }*/
    @RequestMapping("/get")
    public Result<Page> getNew(String id){
        if(NumberUtil.isNumber(id)){
            if(id == null){
                return Result.success(new Page());
            }
            return Result.success(pageService.get(Long.valueOf(id)));
        }else{
            if(id == null){
                return Result.success(new Page());
            }
            return Result.success(pageService.load(id));
        }
    }
    @RequestMapping("/getJson")
    public Result getJson(Long id){
        Page page;
        if(id == null){
            page = new Page();
        }else{
            page = pageService.get(id);
        }
        return Result.success(MapUtil.builder().put("json",JSONUtil.toJsonPrettyStr(page)).build());
    }
    @RequestMapping("/saveJson")
    public Result saveJson(@RequestBody Map<String,Object> map){
        String json = (String) map.get("json");
        Page page = JSONUtil.toBean(json, Page.class);
        Page oldPage = pageService.get(page.getCode());
        if(oldPage != null){
            page.setId(oldPage.getId());
        }else{
            page.setId(null);
        }
        pageService.save(page);
        return Result.success();
    }

    @RequestMapping("/save")
    public Result<String> save(@RequestBody Page page){
        if(jdbcService.isRepeat("select id from page where code = '$code' and id <> $id ",BeanUtil.beanToMap(page))){
            return Result.error("页面编号重复");
        }
        pageService.save(page);
        return Result.success();
    }
    @RequestMapping("/resultFields")
    public Result resultFields(@RequestBody Page page){
        pageService.reload(page);
        return Result.success(page,"已刷新");
    }

    @RequestMapping("/crudQuery/{pageCode}")
    public Result<CrudData<Map<String,Object>>> crudQuery(@RequestBody PageParam pageParam, @PathVariable(name="pageCode") String pageCode){
        Set<String> keySet = new HashSet<>(pageParam.keySet());
        keySet.forEach(key->{
            if(key.startsWith(Constants.QUERY_KEY_START)){
                Object value = pageParam.remove(key);
                pageParam.put(key.substring(Constants.QUERY_KEY_START.length()),value);
            }
        });
        return pageService.query(pageCode,pageParam);
    }
    @RequestMapping("/options/{pageCode}")
    public Result pageOptions(@PathVariable(name="pageCode") String pageCode){
        PageParam pageParam = new PageParam();
        pageParam.put("page",1);
        pageParam.put("perPage",Integer.MAX_VALUE);
        Result<CrudData<Map<String, Object>>> result = pageService.query(pageCode, pageParam);
        Map<String,Object> data = new HashMap<>();
        data.put("options",result.getData().getRows());
        return Result.success(data);
    }
    @RequestMapping("/selector/{pageCode}")
    public Result<CrudData<Map<String,Object>>> selector(@RequestBody PageParam pageParam, @PathVariable(name="pageCode") String pageCode, HttpServletRequest request){
        pageParam.put("selector",true);

        Map<String, String[]> parameterMap = request.getParameterMap();

        Set<String> keySet = new HashSet<>(pageParam.keySet());
        keySet.forEach(key->{
            if(key.startsWith(Constants.QUERY_KEY_START)){
                Object value = pageParam.remove(key);
                pageParam.put(key.substring(Constants.QUERY_KEY_START.length()),value);
            }
        });
        //过滤树结构当前id,防止循环引用
        if(parameterMap.containsKey("parentId") && parameterMap.containsKey("id")){
            String[] ids = parameterMap.get("id");
            if(ids != null && ids.length == 1 && StrUtil.isNotBlank(ids[0])){
                Long treeId = Long.parseLong(ids[0]);
                pageParam.put("treeId",treeId);
            }
        }
        return pageService.query(pageCode,pageParam);
    }

    @RequestMapping("/crudExport/{pageCode}")
    public void crudExport(@RequestParam Map<String,Object> pageParam, @PathVariable(name="pageCode") String pageCode,HttpServletResponse response){
        Set<String> keySet = new HashSet<>(pageParam.keySet());
        keySet.forEach(key->{
            if(key.startsWith(Constants.QUERY_KEY_START)){
                Object value = pageParam.remove(key);
                pageParam.put(key.substring(Constants.QUERY_KEY_START.length()),value);
            }
        });
        PageParam param = new PageParam();
        param.putAll(pageParam);
        param.put("page",1);
        param.put("perPage",Integer.MAX_VALUE);
        param.put("exportExcel",true);

        Result<CrudData<Map<String, Object>>> result = pageService.query(pageCode, param);
        Page page = pageService.get(pageCode);

        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String fn = page.getName() + sdf.format(new Date()) + ".xlsx";
            // 读取字符编码
            String utf = "UTF-8";
            // 设置响应
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding(utf);
            response.setHeader("Pragma", "public");
            response.setHeader("Cache-Control", "max-age=30");
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fn, utf));

            ExcelWriter writer = ExcelUtil.getWriter(true);
            DataFormat dataFormat = writer.getWorkbook().createDataFormat();

            CellStyle cellStyle = writer.getCellStyle();
            cellStyle.setDataFormat(dataFormat.getFormat("@"));
            writer.getCellStyle().setAlignment(HorizontalAlignment.LEFT);

//            PrintWriter writer = response.getWriter();
            List<String> fields = new ArrayList<>();
            List<String> titles = new ArrayList<>();
            List<ColumnData> columns = result.getData().getColumns();
            Map<String,ColumnData> columnsMap = new HashMap<>();
            columns.forEach(c->{
                columnsMap.put(c.getName(),c);
            });
            List<Integer> maxSize = new ArrayList<>();
            for(PageResultField resultField:page.getResultFields()){
                if(!Whether.YES.equals(resultField.getHidden())){
                    fields.add(StringUtil.toFieldColumn(resultField.getField()));
                    titles.add(resultField.getLabel());
                    maxSize.add(resultField.getLabel().length()*2+5);
                }
            }
            writer.writeRow(titles);
            List<Map<String, Object>> rows = result.getData().getRows();
            for(Map<String,Object> row:rows){
                writeTree(row,writer,fields,0,columnsMap,maxSize);
            }
            for(int i=0;i<maxSize.size();i++){
                writer.setColumnWidth(i,maxSize.get(i));
            }

            writer.flush(response.getOutputStream(),false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void writeTree(Map<String,Object> data, ExcelWriter writer, List<String> fields, int len, Map<String,ColumnData> columnsMap, List<Integer> maxSize){
        List<Object> rowDatas = new ArrayList<>();
        fields.forEach(field->{
            ColumnData columnData = columnsMap.get(field);
            Object value = data.get(field);
            if("mapping".equals(columnData.get("type"))){
                Map<String,Object> map = (Map<String, Object>) columnData.get("map");
                value = map.get(data.get(field));
            }
            if(value == null){
                value = "";
            }
            rowDatas.add(value);
        });

        String pre = "";
        for(int i=0;i<len;i++){
            if(i==len-1){
                pre += "--";
            }else{
                pre += "----";
            }
        }
        if(len>0){
            pre += "--";
        }
//        rowDatas.set(0,pre+rowDatas.get(0));
        List<Map<String,Object>> children = (List<Map<String, Object>>) data.get("children");
        for(int i=0;i<rowDatas.size();i++){
            Integer curSize = maxSize.get(i);
            Object o = rowDatas.get(i);
            if(o!=null){
                int size = o.toString().length()*2+5;
                if(size>curSize){
                    curSize = size;
                    if(curSize>100){
                        curSize = 100;
                    }
                    maxSize.set(i,curSize);
                }
            }
        }
        writer.writeRow(rowDatas);
        //writer.println("\t"+pre+StringUtil.concatStr(rowDatas,",\t"));
        if(children != null){
            children.forEach(child->{
                writeTree(child,writer,fields,len+1,columnsMap,maxSize);
            });
        }
    }


    private String checkPage(Page page){
        Map<String,Object> context = new HashMap<>();
        context.put("page",page);

        Result<String> beforeApiResult = apiService.call(page.getBeforeApi(), context);
        if(!beforeApiResult.isSuccess()){

            Map<String,Object> params = new HashMap<>();
            params.put("errMsg",beforeApiResult.getMsg());
            return TemplateUtil.getUi("error.js.vm",params);
        }
        return null;
    }
    @RequestMapping("/json/{pageCode}")
    public Result json(@PathVariable("pageCode") String pageCode,HttpServletResponse response){
        String json = this.js(pageCode, response);
        json = json.substring(PageKey.AMIS_JSON.length()+1);
        return Result.success(JSONUtil.parse(json));
    }
    @RequestMapping(value = "/js/{pageCode}.js",produces = "text/javascript; charset=utf-8")
    public String js(@PathVariable("pageCode") String pageCode,HttpServletResponse response){

        response.addHeader("Cache-Control","no-store");
        Page page = pageService.get(pageCode);

        String checkPage = this.checkPage(page);
        if(StringUtils.isNotBlank(checkPage)){
            return checkPage;
        }

        if(page == null){
            return "location.href='/admin/lyear_pages_error.html?url=crud/"+pageCode+"';";
        }
        String template = "ui-json-template/crud.js.vm";
        if(!page.getPageRefs().isEmpty()){
            template = "ui-json-template/crudTabs.js.vm";
        }

        Map<String,Object> params = new HashMap<>();
        pageConfigService.setPageConfig(page,params);
        String js = TemplateUtil.getValueByPath(template,params);
        String json = js.substring(PageKey.AMIS_JSON.length()+1);
        //System.out.println(json);
        JSONObject jsonObject = JSONUtil.parseObj(json);
        TemplateUtil.filterDefinitions(jsonObject);
        return PageKey.AMIS_JSON + "="+jsonObject.toStringPretty();
    }

    @RequestMapping("/queryConfigs/{pageCode}")
    public Result queryConfigs(@PathVariable("pageCode") String pageCode){
        Page page = pageService.get(pageCode);
        List<Map<String, Object>> queryConfigs = pageConfigService.queryConfigs(page);
        return Result.successForKey("config",queryConfigs);
    }
}
