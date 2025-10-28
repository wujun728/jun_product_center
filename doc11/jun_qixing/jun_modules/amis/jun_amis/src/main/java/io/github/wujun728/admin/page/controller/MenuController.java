package io.github.wujun728.admin.page.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.github.wujun728.admin.common.BaseData;
import io.github.wujun728.admin.common.Result;
import io.github.wujun728.admin.common.data.Obj;
import io.github.wujun728.admin.db.service.JdbcService;
import io.github.wujun728.admin.page.constants.*;
import io.github.wujun728.admin.page.data.*;
import io.github.wujun728.admin.page.constants.*;
import io.github.wujun728.admin.page.data.*;
import io.github.wujun728.admin.page.service.FormService;
import io.github.wujun728.admin.page.service.PageService;
import io.github.wujun728.admin.rbac.data.MenuUrl;
import io.github.wujun728.admin.rbac.service.InputParam;
import io.github.wujun728.admin.util.StringUtil;
import io.github.wujun728.admin.util.UrlUtil;
import io.github.wujun728.admin.util.Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/admin/menu")
public class MenuController {
    @Resource
    private JdbcService jdbcService;

    @Resource
    private PageService pageService;

    @Resource
    private FormService formService;

    @RequestMapping("/initButtons/{menuId}")
    public Result initButtons(@PathVariable Long menuId){
        SysMenu menu = jdbcService.getById(SysMenu.class, menuId);
        String url = menu.getUrl();
        String prefix = "/crud/";
        String oneToMany = "/oneToMany/";
        String formPrefix = "/form/";
        if(StringUtils.isBlank(url) || (!url.startsWith(prefix) && !url.startsWith(oneToMany) && !url.startsWith(formPrefix))){
            return Result.error("只能初始化地址为["+prefix+","+oneToMany+","+formPrefix+"]开头的菜单");
        }


        Integer seq = jdbcService.findOneForObject("select max(seq) from sys_menu where parent_id = ? ", Integer.class, menu.getId());
        if(seq == null){
            seq = 0;
        }
        List<BaseData> btns = new ArrayList<>();
        List<SysMenu> btnMenus = new ArrayList<>();
        Set<String> btnIds = new HashSet<>();
        Obj<Integer> objSeq = new Obj<>(seq);

        //页面按钮
        Map<String,Set<MenuUrl>> menuUrls = new HashMap<>();

        if(url.startsWith(prefix)){
            String pageCode = url.substring(url.indexOf(prefix)+prefix.length());
            Page page = pageService.get(pageCode);
            if(page == null){
                return Result.error("页面["+pageCode+"]不存在");
            }
            this.pageButtons(btnIds,btns,page,btnMenus,menu,objSeq,menu.getMenuCode(),menuUrls);
        }else if(url.startsWith(oneToMany)){
            String pageCodes = url.substring(url.indexOf(oneToMany)+oneToMany.length());
            String[] arr = pageCodes.split("/");
            for(String pageCode:arr){
                Page page = pageService.get(pageCode);
                if(page == null){
                    continue;
                }
                this.pageButtons(btnIds,btns,page,btnMenus,menu,objSeq,menu.getMenuCode(),menuUrls);
            }
        }else if(url.startsWith(formPrefix)){
            String formCode = url.substring(url.indexOf(formPrefix)+formPrefix.length());
            Form form = formService.get(formCode);
            this.formButtons(btnIds,btns,form,btnMenus,menu,objSeq,menu.getMenuCode(),menuUrls);
        }
        jdbcService.transactionOption(() -> {
            jdbcService.bathSaveOrUpdate(btnMenus);
            jdbcService.bathSaveOrUpdate(btns);

            Set<Long> formIds = new HashSet<>();
            Set<Long> pageIds = new HashSet<>();

            for (BaseData btn : btns) {
                if(btn instanceof PageButton){
                    pageIds.add(((PageButton) btn).getPageId());
                }else if(btn instanceof FormButton){
                    formIds.add(((FormButton) btn).getFormId());
                }
            }
            //清理表单缓存
            for (Long formId : formIds) {
                Form form = jdbcService.getById(Form.class,formId);
                if(form != null){
                    formService.invalid(form.getCode());
                }
            }

            //清理页面缓存
            for (Long pageId : pageIds) {
                Page page = jdbcService.getById(Page.class,pageId);
                if(page != null){
                    pageService.invalid(page.getCode());
                }
            }


            menuUrls.entrySet().forEach((Map.Entry<String,Set<MenuUrl>> en)->{
                String menuCode = en.getKey();
                Long id = jdbcService.findOneForObject("select id from sys_menu where menu_code = ? ", Long.class, menuCode);
                for(MenuUrl s:en.getValue()){
                    Long uid = jdbcService.findOneForObject("select id from menu_url where menu_id = ? and url = ? ", Long.class, id, s.getUrl());
                    if(uid == null){
                        MenuUrl menuUrl = new MenuUrl();
                        menuUrl.setMenuId(id);
                        menuUrl.setUrl(s.getUrl());
                        menuUrl.setName(s.getName());
                        jdbcService.saveOrUpdate(menuUrl);
                    }
                }
            });
        });
        return Result.success();
    }

    @RequestMapping("/clearButtons/{menuId}")
    public Result clearButtons(@PathVariable Long menuId){
        jdbcService.transactionOption(()->{

            //页面编号
            List<String> pageCodes = jdbcService.findForObject("select distinct code from page where id in (" +
                    "select distinct page_id from page_button where code in (" +
                    "select menu_code from sys_menu " +
                    "where parent_id = ? " +
                    "and whether_button = ? " +
                    ")" +
                    ")",String.class,menuId, Whether.YES);

            //清空页面按钮编号
            jdbcService.update("update page_button set code = null where code in (" +
                    "select menu_code from sys_menu " +
                    "where parent_id = ? " +
                    "and whether_button = ? " +
                    ")",menuId,Whether.YES);

            //表单编号
            List<String> formCodes = jdbcService.findForObject("select distinct code from form where id in (" +
                    "select distinct form_id from form_button where code in (" +
                    "select menu_code from sys_menu " +
                    "where parent_id = ? " +
                    "and whether_button = ? " +
                    ")" +
                    ")",String.class,menuId,Whether.YES);

            //清空表单按钮编号
            jdbcService.update("update form_button set code = null where code in (" +
                    "select menu_code " +
                    "from sys_menu " +
                    "where parent_id = ? " +
                    "and whether_button = ? " +
                    ")",menuId,Whether.YES);

            jdbcService.update("delete from menu_url where menu_id in (" +
                    "select id " +
                    "from sys_menu " +
                    "where id = ? " +
                    "or (parent_id = ? and whether_button = ?)" +
                    ")",menuId,menuId,Whether.YES);

            jdbcService.update("delete from sys_menu where parent_id = ? and whether_button = ? ",
                    menuId,Whether.YES);

            //清理页面缓存
            for (String pageCode : pageCodes) {
                pageService.invalid(pageCode);
            }

            //清理表单缓存
            for (String formCode : formCodes) {
                formService.invalid(formCode);
            }
        });
        return Result.success();
    }

    private void add(String menuCode,String name,String url,Map<String,Set<MenuUrl>> menuUrls){
        if(StringUtils.isBlank(url)){
            return;
        }
        if(!menuUrls.containsKey(menuCode)){
            menuUrls.put(menuCode,new HashSet<>());
        }
        MenuUrl menuUrl = new MenuUrl();
        menuUrl.setUrl(UrlUtil.getUrl(url));
        menuUrl.setName(name);
        menuUrls.get(menuCode).add(menuUrl);
    }

    private void add(String menuCode, InputParam field, Map<String,Set<MenuUrl>> menuUrls){
        if(DataType.Selector.equals(field.getType())){
            String name = jdbcService.findOneForObject("select name from page where code = ? ", String.class, field.getFormat());
            add(menuCode,StrUtil.format("{}选择器",name),StrUtil.format("/admin/page/selector/{}",field.getFormat()),menuUrls);
        }
        if(field.getComponentType() != null && field.getComponentType().contains("tree")){
            String name = jdbcService.findOneForObject("select name from page where code = ? ", String.class, field.getFormat());
            add(menuCode,StrUtil.format("{}选择树",name),StrUtil.format("/admin/page/options/{}",field.getFormat()),menuUrls);
        }else if(ComponentType.InputTable.equals(field.getComponentType())){
            String format = field.getFormat();
            if(StringUtils.isNotBlank(format)){
                String[] arr = format.split(",");
                Form form = formService.get(arr[0]);
                add(menuCode,form.getFormFields(),menuUrls);
            }
        }

        if(StringUtils.isNotBlank(field.getExtraJson())){
            try{
                JSONObject extraJson = JSONUtil.parseObj(field.getExtraJson());
                String source = extraJson.getStr("source");
                if(StringUtils.isNotBlank(source)){
                    add(menuCode,StrUtil.format("{}数据来源",field.getLabel()),source,menuUrls);
                }
                String schemaApi = extraJson.getStr("schemaApi");
                if(StringUtils.isNotBlank(schemaApi)){
                    add(menuCode,StrUtil.format("{}动态组件",field.getLabel()),schemaApi,menuUrls);
                }
            }catch (Exception e){

            }
        }
    }
    private void add(String menuCode,List<? extends InputParam> fields,Map<String,Set<MenuUrl>> menuUrls){
        for(InputParam field:fields){
            add(menuCode,field,menuUrls);
        }
    }

    private void pageButtons(Set<String> btnIds, List<BaseData> btns, Page page, List<SysMenu> btnMenus, SysMenu menu, Obj<Integer> seq,String menuCode,Map<String,Set<MenuUrl>> menuUrls){
        if(page == null){
            return;
        }
        SysMenu sysMenu = null;
        String code = menu.getMenuCode();
        List<PageButton> pageButtons = page.getPageButtons();

        add(menuCode,StrUtil.format("{}页面",page.getName()),StrUtil.format("/crud/{}",page.getCode()),menuUrls);
        add(menuCode,StrUtil.format("{}查询",page.getName()),StrUtil.format("/admin/page/crudQuery/{}",page.getCode()),menuUrls);
        add(menuCode,StrUtil.format("{}页面JS",page.getName()), StrUtil.format("/admin/page/js/{}.js",page.getCode()),menuUrls);
        add(menuCode,StrUtil.format("{}导出",page.getName()), StrUtil.format("/admin/page/crudExport/{}",page.getCode()),menuUrls);

        Long viewBtnId = jdbcService.findOneForObject("select id from sys_menu where menu_name = ? ",Long.class,page.getName()+"-"+"查看");

        if(viewBtnId == null && !btnIds.contains(page.getName()+"-"+"查看")){
            sysMenu = new SysMenu();
            sysMenu.setMenuName(page.getName()+"-"+"查看");
            seq.setValue(seq.getValue()+1);
            sysMenu.setSeq(seq.getValue());
            sysMenu.setMenuCode(code+StringUtil.getAddCode(sysMenu.getSeq()+"","0",2));
            sysMenu.setWhetherButton(Whether.YES);
            sysMenu.setParentId(menu.getId());
            sysMenu.setMenuType(menu.getMenuType());
            btnMenus.add(sysMenu);
            btnIds.add(page.getName()+"-"+"查看");
        }

        for(PageButton btn:pageButtons){
            if(StringUtils.isNotBlank(btn.getCode()) || btnIds.contains("p"+btn.getId())){

            }else{
                sysMenu = new SysMenu();
                sysMenu.setMenuName(page.getName()+"-"+btn.getLabel());
                seq.setValue(seq.getValue()+1);
                sysMenu.setSeq(seq.getValue());
                sysMenu.setMenuCode(code+StringUtil.getAddCode(sysMenu.getSeq()+"","0",2));
                sysMenu.setWhetherButton(Whether.YES);
                sysMenu.setParentId(menu.getId());
                sysMenu.setMenuType(menu.getMenuType());
                btnMenus.add(sysMenu);
                btn.setCode(sysMenu.getMenuCode());
                btns.add(btn);
                btnIds.add("p"+btn.getId());
            }

            this.buttonButtons(page.getName(),btnIds,btns,btn,btnMenus,menu,seq,btn.getCode(),menuUrls);
        }
        this.add(menuCode,page.getQueryFields(),menuUrls);
        this.add(menuCode,page.getResultFields(),menuUrls);
        for(PageRef pageRef:page.getPageRefs()){
            String refType = pageRef.getRefType();
            if(RefType.Page.equals(refType)){
                Page refPage = pageService.get(pageRef.getRefPageCode());
                this.pageButtons(btnIds,btns,refPage,btnMenus,menu,seq,menuCode,menuUrls);
            }else if(RefType.Form.equals(refType)){
                Form form = formService.get(pageRef.getRefPageCode());
                this.formButtons(btnIds,btns,form,btnMenus,menu,seq,menuCode,menuUrls);
            }else if(RefType.Iframe.equals(refType)){
                String url = pageRef.getRefPageCode();
                String logPrefix = "/admin/operationLog/page/";
                if(url != null && url.contains(logPrefix)){
                    String end = url.substring(url.indexOf(logPrefix) + logPrefix.length());
                    add(menuCode,page.getName()+"-日志页面",url,menuUrls);
                    add(menuCode,page.getName()+"-日志JS","/admin/operationLog/js/"+end+".js",menuUrls);
                    add(menuCode,page.getName()+"-日志数据","/admin/operationLog/data/"+end,menuUrls);
                }else{
                    add(menuCode,page.getName()+"-"+pageRef.getRefName(),url,menuUrls);
                }
            }
        }
    }


    private void formButtons(Set<String> btnIds, List<BaseData> btns, Form form, List<SysMenu> btnMenus, SysMenu menu, Obj<Integer> seq,String menuCode,Map<String,Set<MenuUrl>> menuUrls){
        if(form == null) {
            return;
        }
        SysMenu sysMenu = null;
        String code = menu.getMenuCode();

        String initApi = null;
        String api = null;
        if(StrUtil.isNotBlank(form.getTableName())){
            initApi = StrUtil.format("/admin/common/{}/get",form.getCode());
            api = StrUtil.format("/admin/common/{}/saveOrUpdate",form.getCode());
        }
        if(StrUtil.isNotBlank(form.getInitApi())){
            initApi = form.getInitApi();
        }
        if(StrUtil.isNotBlank(form.getApi())){
            api = form.getInitApi();
        }
        this.add(menuCode,StrUtil.format("查询{}",form.getName()),initApi,menuUrls);
        this.add(menuCode,StrUtil.format("保存{}",form.getName()),api,menuUrls);
        this.add(menuCode,form.getFormFields(),menuUrls);

        if(StrUtil.isNotBlank(form.getInitApi())){
            String copyApi = StrUtil.format("/admin/common/{}/copy",form.getCode());
            this.add(menuCode,StrUtil.format("复制{}",form.getName()),copyApi,menuUrls);
        }

        List<FormButton> formButtons = form.getFormButtons();
        //页面表单按钮
        for(FormButton formButton:formButtons){
            if(StringUtils.isNotBlank(formButton.getCode()) || btnIds.contains("f"+formButton.getId())){

            }else{
                sysMenu = new SysMenu();
                sysMenu.setMenuName(form.getName()+"-"+formButton.getLabel());
                seq.setValue(seq.getValue()+1);
                sysMenu.setSeq(seq.getValue());
                sysMenu.setMenuCode(code+StringUtil.getAddCode(sysMenu.getSeq()+"","0",2));
                sysMenu.setWhetherButton(Whether.YES);
                sysMenu.setParentId(menu.getId());
                sysMenu.setMenuType(menu.getMenuType());
                btnMenus.add(sysMenu);
                formButton.setCode(sysMenu.getMenuCode());
                btns.add(formButton);
                btnIds.add("f"+formButton.getId());
            }
            this.buttonButtons(form.getName(),btnIds,btns,formButton,btnMenus,menu,seq,formButton.getCode(),menuUrls);
        }
        //表单关联页面按钮
        List<FormRef> formRefs = form.getFormRefs();
        for(FormRef formRef:formRefs){
            Page refPage = pageService.get(formRef.getRefPageCode());
            this.pageButtons(btnIds,btns,refPage,btnMenus,menu,seq,menuCode,menuUrls);
        }
    }
    private void buttonButtons(String parentName, Set<String> btnIds, List<BaseData> btns, BaseButton btn, List<SysMenu> btnMenus, SysMenu menu, Obj<Integer> seq, String menuCode, Map<String,Set<MenuUrl>> menuUrls){
        if(ActionType.PopForm.equals(btn.getOptionType())){
            Form form = formService.get(btn.getOptionValue());
            this.formButtons(btnIds,btns,form,btnMenus,menu,seq,btn.getCode(),menuUrls);
        }else if(ActionType.PopPage.equals(btn.getOptionType())){
            if(btn.getOptionValue().contains(",")){
                String[] arr = StringUtil.splitStr(btn.getOptionValue(), ",");
                if(arr != null && arr.length>0){
                    Page page = pageService.get(arr[0]);
                    this.pageButtons(btnIds,btns,page,btnMenus,menu,seq,btn.getCode(),menuUrls);
                }
            }else if(btn.getOptionValue().contains("?")){
                String[] arr = StringUtil.splitStr(btn.getOptionValue(), "\\?");
                if(arr != null && arr.length>0){
                    Page page = pageService.get(arr[0]);
                    this.pageButtons(btnIds,btns,page,btnMenus,menu,seq,btn.getCode(),menuUrls);
                }
            }

        }else if(ActionType.PopIframe.equals(btn.getOptionType())){
            String logPrefix = "/admin/operationLog/page/";
            if(btn.getOptionValue() != null && btn.getOptionValue().contains(logPrefix)){
                String end = btn.getOptionValue().substring(btn.getOptionValue().indexOf(logPrefix) + logPrefix.length());
                add(btn.getCode(),parentName+"-日志页面",btn.getOptionValue(),menuUrls);
                add(btn.getCode(),parentName+"-日志JS","/admin/operationLog/js/"+end+".js",menuUrls);
                add(btn.getCode(),parentName+"-日志数据","/admin/operationLog/data/"+end,menuUrls);
            }else{
                add(btn.getCode(),parentName+"-"+btn.getLabel(),btn.getOptionValue(),menuUrls);
            }
        }else if(ActionType.Ajax.equals(btn.getOptionType())){
            add(btn.getCode(),parentName+"-"+btn.getLabel(),btn.getOptionValue(),menuUrls);
        }
    }


    @GetMapping("/generateCode")
    public Result generateCode(){
        List<SysMenu> sysMenus = jdbcService.find(SysMenu.class);
        sysMenus = Util.buildTree(sysMenus);
        List<BaseData> updates = new ArrayList<>();
        generateCode("",sysMenus,updates);
        jdbcService.bathSaveOrUpdate(updates);

        return Result.success();
    }

    private void generateCode(String prefix,List<SysMenu> sysMenus,List<BaseData> updates){
        for(int i=0;i<sysMenus.size();i++){
            SysMenu sysMenu = sysMenus.get(i);
            int seq = i+1;
            sysMenu.setSeq(seq*10);
            String oldCode = sysMenu.getMenuCode();
            sysMenu.setMenuCode(prefix+StringUtil.getAddCode(seq+"","0",2));
            updates.add(sysMenu);
            if(Whether.YES.equals(sysMenu.getWhetherButton())){
                List<PageButton> pageButtons = jdbcService.find(PageButton.class, "code", oldCode);
                for(PageButton pageButton:pageButtons){
                    pageButton.setCode(sysMenu.getMenuCode());
                    updates.add(pageButton);
                }
                List<FormButton> formButtons = jdbcService.find(FormButton.class, "code", oldCode);
                for(FormButton formButton:formButtons){
                    formButton.setCode(sysMenu.getMenuCode());
                    updates.add(formButton);
                }
            }
            generateCode(sysMenu.getMenuCode(),sysMenu.getChildren(),updates);
        }
    }
}
