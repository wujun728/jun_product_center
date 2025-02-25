package com.jun.plugin.generator.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.jun.plugin.common.utils.DataResult;
import com.jun.plugin.common.Result;
import com.jun.plugin.generator.code.BeanColumn;
import com.jun.plugin.generator.code.TableInfo;
import com.jun.plugin.generator.entity.SysGenerator;
import com.jun.plugin.generator.service.ISysGeneratorService;
import com.jun.plugin.generator.utils.GenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成
 *
 * @author wujun
 * @version V1.0
 * @date 2020年3月18日
 */
@Api(tags = "系统模块-代码生成")
@Slf4j
@Controller
@RequestMapping("/sysGenerator")
public class SysGeneratorController {
    @Resource
    private ISysGeneratorService sysGeneratorService;

    private String prefix = "generator";
//    @Autowired
//    private ITGeneratorService generatorService;

    /**
     * 生成代码
     */
    @ApiOperation(value = "生成")
    @GetMapping("/gen")
//    //@RequiresPermissions("sysGenerator:add")
    @ResponseBody
    public void code(String tables, HttpServletResponse response) throws IOException {
        byte[] data = sysGeneratorService.generatorCode(tables.split(","));

        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\""+tables+".zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IOUtils.write(data, response.getOutputStream());
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("/listByPage")
    //@RequiresPermissions("sysGenerator:list")
    @ResponseBody
    public Result findListByPage(@RequestBody SysGenerator vo) {
        Page page = new Page(vo.getPage(), vo.getLimit());
        IPage<SysGenerator> iPage = sysGeneratorService.selectAllTables(page, vo);
        return Result.success(iPage);
    }


    /**
     * 生成文件Zip
     *
     * @throws IOException
     * @author fuce
     * @Date 2021年1月15日 下午2:21:55
     */
    @GetMapping("/createAutoZip")
    @ResponseBody
    public void createAutoZip(String tableName,String tableComment, HttpServletResponse response) throws IOException {
        byte[] b;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        // 根据表名查询表字段集合
        List<BeanColumn> list = sysGeneratorService.queryColumns2(tableName);
        // 初始化表信息
        TableInfo tableInfo = new TableInfo(tableName, list, tableComment);
        // 自动生成
        GenUtils.autoCodeOneModel(tableInfo, tableName,tableComment, zip);
        IOUtils.closeQuietly(zip);
        b = outputStream.toByteArray();
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\""+tableName+".zip\"");
        response.addHeader("Content-Length", "" + b.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(b, response.getOutputStream());
    }


    /**
     * 预览生成文件
     *
     * @author fuce
     * @Date 2021年1月15日 下午2:21:55
     */
    @GetMapping("/viewAuto")
    public String viewAuto(@RequestParam String tableName ,String tableComment, ModelMap model) {
        List<BeanColumn> list = sysGeneratorService.queryColumns2(tableName);
        TableInfo tableInfo = new TableInfo(tableName, list, tableComment);//获取表信息
        //查询表信息
        Map<String, String> table = sysGeneratorService.queryTable(tableName);
        //查询列信息
        List<Map<String, String>> columns = sysGeneratorService.queryColumns(tableName);
        //生成代码VIEW
        Map<String, String> map = GenUtils.generatorCode(table, columns, null,true);
        model.put("viewmap", map);
        return "generator/view";
    }


}
