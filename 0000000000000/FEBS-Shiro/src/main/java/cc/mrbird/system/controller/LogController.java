package cc.mrbird.system.controller;

import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.common.util.FileUtil;
import cc.mrbird.system.domain.SysLog;
import cc.mrbird.system.service.LogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class LogController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LogService logService;

    @RequestMapping("log")
    @RequiresPermissions("log:list")
    public String index() {
        return "system/log/log";
    }

    @RequestMapping("log/list")
    @ResponseBody
    public Map<String, Object> logList(QueryRequest request, SysLog log) {
        return super.selectByPageNumSize(request, () -> this.logService.findAllLogs(log));
    }

    @RequestMapping("log/excel")
    @ResponseBody
    public ResponseBo logExcel(SysLog log) {
        try {
            List<SysLog> list = this.logService.findAllLogs(log);
            return FileUtil.createExcelByPOIKit("系统日志表", list, SysLog.class);
        } catch (Exception e) {
            logger.error("导出系统日志Excel失败", e);
            return ResponseBo.error("导出Excel失败，请联系网站管理员！");
        }
    }

    @RequestMapping("log/csv")
    @ResponseBody
    public ResponseBo logCsv(SysLog log) {
        try {
            List<SysLog> list = this.logService.findAllLogs(log);
            return FileUtil.createCsv("系统日志表", list, SysLog.class);
        } catch (Exception e) {
            logger.error("导出系统日志Csv失败", e);
            return ResponseBo.error("导出Csv失败，请联系网站管理员！");
        }
    }

    @RequiresPermissions("log:delete")
    @RequestMapping("log/delete")
    @ResponseBody
    public ResponseBo deleteLogss(String ids) {
        try {
            this.logService.deleteLogs(ids);
            return ResponseBo.ok("删除日志成功！");
        } catch (Exception e) {
            logger.error("删除日志失败", e);
            return ResponseBo.error("删除日志失败，请联系网站管理员！");
        }
    }
}
