package com.jun.plugin.qixing.qixing;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.utils.SecurityUtils;
import io.github.wujun728.db.record.Db;
import io.github.wujun728.db.record.Record;
import io.github.wujun728.db.utils.RecordUtil;
import io.github.wujun728.db.utils.TreeBuildUtil;
import org.apache.commons.compress.utils.Lists;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wujun
 */
@Controller
@RequestMapping("/qixing")
@DataSource(DataSourceType.MASTER)
public class JSCodeController extends BaseController {

    @PostConstruct
    public void init() {
        Db.init("master", SpringUtil.getBean("masterDataSource"));
    }

    @RequestMapping(value = "/js/{code}.js",produces = "text/javascript; charset=utf-8")
    @ResponseBody
    public String js(@PathVariable String code, @RequestParam Map<String,Object> param){
        if(code.equalsIgnoreCase("menuList")){
            List<Record> apps = Db.use("master").find(" SELECT `menu_id` as id, `menu_name` as name, `parent_id`, `order_num`, \" +\n" +
                "            \"`path`, `component` as url, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`,\" +\n" +
                "            \" `create_by`, `create_time`, `update_by`, `update_time`, `remark` from sys_menu where parent_id = 0 ");
            List<Map<String, Object>> apps1 = RecordUtil.recordToMaps(apps, true);
            List<Record> menus = Db.use("master").find(" SELECT `menu_id` as id, `menu_name` as name,`remark` as info, `parent_id`, `order_num`, " +
                "`path`, `component` as url, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`," +
                " `create_by`, `create_time`, `update_by`, `update_time`, `remark` from sys_menu where menu_type != 'F' ORDER BY parent_id,order_num ");
            List<Map<String, Object>> menus1 = RecordUtil.recordToMaps(menus, true);
            menus1 = (List) menus1.stream().map(map -> rebuildMenu(map)).collect(Collectors.toList());
            List menus2 = TreeBuildUtil.listToTree(menus1, "0", "id", "parent_id","childList");
            Map data = new HashMap<>();
            data.put("apps", apps1);
            data.put("menus", menus2);

            String jsonStr = JSONUtil.toJsonPrettyStr(menus2);
            String jsObjectStr = jsonStr.replaceAll("\"([a-zA-Z0-9_]+)\":", "$1:");

            return StrUtil.format("var {} = {};", code, jsObjectStr);
        }
        return "code";
    }


    private static Map rebuildMenu(Map<String, Object> map) {
        String menuType = MapUtil.getStr(map, "menu_type");
        String component = MapUtil.getStr(map, "url");
        String path = MapUtil.getStr(map, "path");
        String id = MapUtil.getStr(map, "id");
        map.put("id",id);
        String parent_id = MapUtil.getStr(map, "parent_id");
        map.put("parent_id",parent_id);
        if (ObjectUtil.isNotEmpty(component) && menuType.endsWith("C")) {
            if ((!component.endsWith(".html")) && !component.contains("?")) {
                if (component.startsWith("system") || component.startsWith("monitor") || component.startsWith("tool")) {
                    map.put("url", map.get("url") + ".html");
                }
            }
            /*if((!StrUtil.toString(map.get("path")).endsWith(".html"))  && !StrUtil.toString(map.get("path")).contains("?") ){
                map.put("path",map.get("path")+".html");
            }*/
        }
        return map;
    }

}
