package cc.mrbird.febs.web.controller;

import cc.mrbird.febs.common.annotation.Log;
import cc.mrbird.febs.common.controller.BaseController;
import cc.mrbird.febs.common.domain.FebsResponse;
import cc.mrbird.febs.common.domain.QueryRequest;
import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.system.domain.City;
import cc.mrbird.febs.system.domain.Dict;
import cc.mrbird.febs.system.domain.Test;
import cc.mrbird.febs.system.service.CityService;
import cc.mrbird.febs.system.service.DictService;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.wuwenze.poi.ExcelKit;
import com.wuwenze.poi.handler.ExcelReadHandler;
import com.wuwenze.poi.pojo.ExcelErrorField;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Slf4j
@Validated
@RestController
@RequestMapping("city")
public class CityController extends BaseController {

    private String message;

    @Autowired
    private CityService cityService;

    @GetMapping
    public Map<String, Object> CityList(QueryRequest request, City city) {
        return getDataTable(this.cityService.findCitys(request, city));
    }

    @Log("新增城市")
    @PostMapping
    @RequiresPermissions("city:add")
    public FebsResponse addCity(@RequestBody @Valid  City city) throws FebsException {
        try {
            city.setCreateTime(new Date());
            this.cityService.createCity(city);
            return new FebsResponse().code("200").message("新增城市成功").status("success");
        } catch (Exception e) {
            message = "新增城市失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("删除城市")
    @DeleteMapping("/{cityIds}")
    @RequiresPermissions("city:delete")
    public FebsResponse deleteCitys(@NotBlank(message = "{required}") @PathVariable String cityIds) throws FebsException {
        try {
            String[] ids = cityIds.split(StringPool.COMMA);
            this.cityService.deleteCitys(ids);
            return new FebsResponse().code("200").message("删除城市成功").status("success");
        } catch (Exception e) {
            message = "删除城市失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @Log("修改城市")
    @PutMapping
    @RequiresPermissions("city:update")
    public FebsResponse updateDict(@RequestBody @Valid City city) throws FebsException {
        try {
            this.cityService.updateCity(city);
            return new FebsResponse().code("200").message("修改城市成功").status("success");
        } catch (Exception e) {
            message = "修改城市失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }

    @PostMapping("excel")
    @RequiresPermissions("city:export")
    public void export(QueryRequest request,String cityIds,@RequestBody City city, HttpServletResponse response) throws FebsException {
        try {
            List<City> citys;
            if(StringUtils.isNotBlank(cityIds)){
                String [] idsArrs=cityIds.split(",");
                List list=new ArrayList();
                for (String string : idsArrs) {
                    list.add(string);
                }
                citys = this.cityService.selectListByIds(list);
            }else{
                 citys = this.cityService.findCityList(request, city);
            }
            ExcelKit.$Export(City.class, response).downXlsx(citys, false);
        } catch (Exception e) {
            message = "导出Excel失败";
            log.error(message, e);
            throw new FebsException(message);
        }
    }


    /**
     * 生成 Excel导入模板
     */
    @PostMapping("template")
    public void generateImportTemplate(HttpServletResponse response) {
        // 构建数据
        List<City> list = new ArrayList<>();
//        IntStream.range(0, 20).forEach(i -> {
//            City city = new City();
//            city.setCityName("上海");
//            city.setIntroduce("沿海城市");
//            city.setLongitude("133.45");
//            city.setLatitude("87.66");
//            list.add(city);
//        });

        // 构建模板
        ExcelKit.$Export(City.class, response).downXlsx(list, true);
    }

    /**
     * 导入Excel数据，并批量插入 City表
     */
    @PostMapping("import")
    public FebsResponse importExcels(@RequestParam("file") MultipartFile file) throws FebsException {
        try {
            if (file.isEmpty()) {
                throw new FebsException("导入数据为空");
            }
            String filename = file.getOriginalFilename();
            if (!StringUtils.endsWith(filename, ".xlsx")) {
                throw new FebsException("只支持.xlsx类型文件导入");
            }
            // 开始导入操作
            long beginTimeMillis = System.currentTimeMillis();
            final List<City> data = Lists.newArrayList();
            final List<Map<String, Object>> error = Lists.newArrayList();
            ExcelKit.$Import(City.class).readXlsx(file.getInputStream(), new ExcelReadHandler<City>() {
                @Override
                public void onSuccess(int sheet, int row, City city) {
                    // 数据校验成功时，加入集合
                    city.setCreateTime(new Date());
                    data.add(city);
                }
                @Override
                public void onError(int sheet, int row, List<ExcelErrorField> errorFields) {
                    // 数据校验失败时，记录到 error集合
                    error.add(ImmutableMap.of("row", row, "errorFields", errorFields));
                }
            });
            if (!data.isEmpty()) {
                // 将合法的记录批量入库
                this.cityService.batchInsert(data);
            }
            long time = ((System.currentTimeMillis() - beginTimeMillis));
            ImmutableMap<String, Object> result = ImmutableMap.of(
                    "time", time,
                    "data", data,
                    "error", error
            );
            return new FebsResponse().data(result);
        } catch (Exception e) {
            message = "导入Excel数据失败," + e.getMessage();
            log.error(message);
            throw new FebsException(message);
        }
    }
}
