package io.github.wujun728.admin.page.controller;

import io.github.wujun728.admin.common.Result;
import io.github.wujun728.admin.rbac.service.ApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class StaticPageController {

    public static final String PREFIX = "/page/static";

    @Resource
    private ApiService apiService;

    @RequestMapping(PREFIX +"/**")
    public String page(HttpServletRequest request, Model model){
        String uri = request.getRequestURI();
        uri = uri.substring(PREFIX.length());
        log.info(uri);
        model.addAttribute("js","/json"+uri+".js?_rt="+System.currentTimeMillis());
        return "page";
    }

    @RequestMapping("/crud/{pageCode}")
    public String crudPage(Model model,@PathVariable("pageCode") String pageCode){
        model.addAttribute("js","/admin/page/js/"+pageCode+".js?_rt="+System.currentTimeMillis());
        return "page";
    }

    @RequestMapping("/taskAudit/{taskId}")
    public String taskAudit(Model model,@PathVariable("taskId") String taskId){
        model.addAttribute("js","/admin/models/taskAudit/js/"+taskId+".js?_rt="+System.currentTimeMillis());
        return "page";
    }
    @RequestMapping("/taskView/{taskId}")
    public String taskView(Model model,@PathVariable("taskId") String taskId){
        model.addAttribute("js","/admin/models/taskView/js/"+taskId+".js?_rt="+System.currentTimeMillis());
        return "page";
    }
    @RequestMapping("/auditRecord/{modelName}/{id}")
    public String auditRecord(Model model,@PathVariable("modelName") String modelName,@PathVariable("id") String id ){
        model.addAttribute("js","/admin/models/auditRecord/js/"+modelName+"/"+id+".js?_rt="+System.currentTimeMillis());
        return "page";
    }
    @RequestMapping("/form/{pageCode}")
    public String formPage(Model model,@PathVariable("pageCode") String pageCode,@RequestParam Map<String,Object> data){
        StringBuilder params = new StringBuilder("");
        data.entrySet().forEach(entry -> {
            params.append("&").append(entry.getKey()).append("=").append(entry.getValue());
        });
        model.addAttribute("js","/admin/form/js/"+pageCode+".js?_rt="+System.currentTimeMillis()+params);
        return "page";
    }

    @Value("${index-page:index.html}")
    private String indexPage;

    @RequestMapping("/index")
    public void index(HttpServletRequest request, HttpServletResponse response){
        try {
            response.sendRedirect(indexPage+"?t="+System.currentTimeMillis());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Value("${login-page:login.html}")
    private String loginPage;
    @RequestMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response){
        try {
            response.sendRedirect(loginPage+"?t="+System.currentTimeMillis());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/page/**")
    @ResponseBody
    public String templatePage(HttpServletRequest request,@RequestParam Map<String,Object> params){
        log.info(request.getRequestURI());
        String api = request.getRequestURI().substring("/page/".length());

        Map<String,Object> context = new HashMap<>();
        context.put("params",params);
        Result result = apiService.call("get", "/_page/" + api, context);
        return (String) result.getData();
    }

}
