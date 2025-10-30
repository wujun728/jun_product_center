package io.github.wujun728.admin.db.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONUtil;
import io.github.wujun728.admin.common.PageData;
import io.github.wujun728.admin.common.PageParam;
import io.github.wujun728.admin.common.Result;
import io.github.wujun728.admin.db.data.ColumnInfo;
import io.github.wujun728.admin.db.data.TableInfo;
import io.github.wujun728.admin.db.service.JdbcService;
import io.github.wujun728.admin.db.service.TableService;
import io.github.wujun728.admin.page.constants.ActionType;
import io.github.wujun728.admin.page.constants.ButtonLocation;
import io.github.wujun728.admin.page.constants.PageType;
import io.github.wujun728.admin.page.constants.Whether;
import io.github.wujun728.admin.page.data.Form;
import io.github.wujun728.admin.page.data.Page;
import io.github.wujun728.admin.page.data.PageButton;
import io.github.wujun728.admin.page.service.FormService;
import io.github.wujun728.admin.page.service.PageService;
import io.github.wujun728.admin.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping({"/tableInfo", "/lowcode/tableInfo"})
@Slf4j
public class TableInfoController {

    @Resource
    private TableService tableService;
    @Resource
    private PageService pageService;
    @Resource
    private FormService formService;
    @Resource
    private JdbcService jdbcService;

    @RequestMapping("/queryTable")
    public Result<PageData<TableInfo>> queryTable(@RequestBody PageParam pageParam) {
        log.info("参数,{}", pageParam);
        return tableService.queryTable(pageParam);
    }

    @RequestMapping("/tableInfo")
    public Result<TableInfo> tableInfo(String tableName) {
        return tableService.get(tableName);
    }

    @RequestMapping("/getJson")
    public Result getJson(String tableName) {
        Result<TableInfo> tableInfo = tableService.get(tableName);
        String json = JSONUtil.toJsonPrettyStr(tableInfo.getData());
        return Result.success(MapUtil.builder().put("json", json).build());
    }

    @RequestMapping("/saveJson")
    public Result saveJson(@RequestBody Map map) {
        String json = (String) map.get("json");
        TableInfo tableInfo = JSONUtil.toBean(json, TableInfo.class);
        Result<TableInfo> oldTableInfo = tableService.get(tableInfo.getTableName());
        if (oldTableInfo.getData() == null) {
            tableInfo.setOldTableName(null);
        }
        return tableService.updateTable(tableInfo);
    }

    @RequestMapping("/copyTableInfo")
    public Result<TableInfo> copyTableInfo(String tableName) {
        Result<TableInfo> copyTableInfo = tableService.tableInfo(tableName);
        if (copyTableInfo.isSuccess()) {
            TableInfo data = copyTableInfo.getData();
            data.setOldTableName(null);
            data.setTableName(data.getTableName() + "_copy");

            data.getColumnInfos().forEach(c -> {
                c.setOldColumnName(null);
            });
            data.getIndexInfos().forEach(c -> {
                c.setOldKeyName(null);
            });
        }
        return copyTableInfo;
    }

    @RequestMapping("/updateTable")
    public Result updateTableInfo(@RequestBody TableInfo tableInfo) {
        return tableService.updateTable(tableInfo);
    }

    @RequestMapping("/dropTable")
    public Result dropTable(String tableName) {
        return tableService.dropTable(tableName);
    }

    @RequestMapping("/generateJavaCode")
    public Result generateJavaCode(String tableName) {
        return Result.success(tableService.generateCode(tableName));
    }

    @RequestMapping("/oneTouchNew")
    public Result oneTouchV2(String tableName) {
        return oneTouch(tableName, true);
    }

    @RequestMapping("/oneTouch")
    public Result oneTouchV1(String tableName) {
        return oneTouch(tableName, false);
    }

    public Result oneTouch(String tableName, Boolean isDelete) {
        TableInfo tableInfo = tableService.get(tableName).getData();
        String pageCode = StringUtil.toFieldColumn(tableName);
        Page page = pageService.get(pageCode);
        if (page == null || isDelete) {
            if (isDelete) {
                pageService.del(page);
            }
            page = new Page();
            page.setName(tableInfo.getTableComment() + "列表");
            page.setCode(pageCode);
            List<String> columnNames = tableInfo.getColumnInfos().stream().map(ColumnInfo::getColumnName).collect(Collectors.toList());

            if (columnNames.contains("parent_id") || columnNames.contains("pid")) {
                page.setPageType(PageType.tree);
                page.setOpenRowNum(Whether.NO);
                page.setOpenPage(Whether.NO);
            } else {
                page.setPageType(PageType.list);
                page.setOpenRowNum(Whether.YES);
                page.setOpenPage(Whether.YES);
            }
            page.setOrderBy(" order by id desc ");
            Map<String, String> codeMap = tableService.generateCode(tableName);
            page.setQuerySql(codeMap.get("querySql"));
            pageService.reload(page);

            PageButton add = new PageButton();
            add.setLabel("新增");
            add.setButtonLocation(ButtonLocation.Top);
            add.setOptionType(ActionType.PopForm);
            add.setOptionValue(pageCode);
            add.setLevel("primary");
            add.setIcon("fa fa-plus");
            page.getPageButtons().add(add);

            PageButton edit = new PageButton();
            edit.setLabel("编辑");
            edit.setButtonLocation(ButtonLocation.Row);
            edit.setOptionType(ActionType.PopForm);
            edit.setOptionValue(pageCode);
            edit.setIcon("fa fa-pen-to-square");
            edit.setLevel("primary");

            page.getPageButtons().add(edit);

            PageButton delete = new PageButton();
            delete.setLabel("删除");
            delete.setButtonLocation(ButtonLocation.Row);
            delete.setOptionType(ActionType.Ajax);
            delete.setOptionValue("post:/admin/common/" + pageCode + "/delete/${id}");
            delete.setLevel("danger");
            delete.setIcon("fa fa-trash-can");
            delete.setConfirmText("确定删除" + tableInfo.getTableComment() + "${name}吗?");
            page.getPageButtons().add(delete);

            pageService.save(page);
        }
        Form form = formService.get(pageCode);
        if (form == null || isDelete) {
            if (isDelete) {
                formService.del(form);
            }
            form = new Form();
            form.setCode(pageCode);
            form.setName(tableInfo.getTableComment() + "表单");
            form.setFieldWidth(12);
            form.setSize("default");
            form.setDisabled(Whether.NO);
            form.setTableName(tableName);

            formService.reload(form);
            formService.save(form);
        }
        String url = "/crud/" + pageCode;
        List<Map<String, Object>> menus = jdbcService.find("select id from sys_menu where url=?", url);
        List<Map<String, Object>> maxMenus = jdbcService.find("select max(menu_code) max_code from sys_menu where parent_id is null ");
        int maxCode = 0;
        if (!maxMenus.isEmpty()) {
            maxCode = Integer.parseInt((String) maxMenus.get(0).get("maxCode"));
        }

        if (menus.isEmpty()) {
            Map<String, Object> menu = new HashMap<>();
            maxCode++;
            menu.put("menuCode", StringUtil.getAddCode(maxCode + "", "0", 2));
            menu.put("menuName", tableInfo.getTableComment());
            menu.put("menuType", "1");
            menu.put("url", url);
            menu.put("seq", maxCode * 10);
            menu.put("whetherButton", Whether.NO);
            jdbcService.saveOrUpdate(menu, "sys_menu");
        }
        return Result.success();
    }
}
