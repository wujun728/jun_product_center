package io.github.wujun728.admin.page.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.github.wujun728.admin.common.config.SessionContext;
import io.github.wujun728.admin.common.constants.Constants;
import io.github.wujun728.admin.common.data.Obj;
import io.github.wujun728.admin.page.constants.DataType;
import io.github.wujun728.admin.page.constants.PageType;
import io.github.wujun728.admin.page.constants.RefType;
import io.github.wujun728.admin.page.constants.Whether;
import io.github.wujun728.admin.page.data.*;
import io.github.wujun728.admin.page.service.*;
import io.github.wujun728.admin.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service("pageConfigService")
public class PageConfigServiceImpl implements PageConfigService {
    @Resource
    PageService pageService;
    @Resource
    InputFieldService inputFieldService;
    @Resource
    @Lazy
    FormService formService;
    @Override
    public Map<String, Object> getSelectorConfig(String code,String field) {
        Page page = pageService.get(code);

        Map<String,Object> params = new HashMap<>();
        params.put("page",page);
        params.put("formField",field);
        params.put("queryConfigs",JSONUtil.toJsonPrettyStr(queryConfigs(page,true)));

        String jsonConfig = TemplateUtil.getValueByPath("ui-json-template/selector.json.vm",params);
        JSONObject json = JSONUtil.parseObj(jsonConfig);
        //设置分页
        setPage(page,params);
        params.put("pageName",page.getName());
        //设置隐藏条件
        setHiddenQueryFilter(page,params);
        setAffixRow(page,params);
        return json;
    }
    @Override
    public List<Map<String, Object>> queryConfigs(Page page) {
        return this.queryConfigs(page,false);
    }


    @Override
    public List<Map<String, Object>> queryConfigs(Page page,boolean selector) {

        List<Map<String,Object>> queryConfigs = new ArrayList<>();
        for(PageQueryField field:page.getQueryFields()){
            Map<String,Object> queryConfig = inputFieldService.buildInputField(field,selector);
            if(!field.getField().toLowerCase().contains("id") && !selector && !Whether.YES.equals(field.getRef())){
                queryConfig.put("name", Constants.QUERY_KEY_START+queryConfig.get("name"));
            }
            if(StringUtils.isNotBlank(field.getValue())){
                queryConfig.put("value", SessionContext.getTemplateValue(field.getValue()));
            }
            if(Arrays.asList(DataType.DIC,DataType.Selector).contains(field.getType())){
                queryConfig.put("submitOnChange",true);
            }
//            List<Map<String,Object>> options = (List<Map<String, Object>>) queryConfig.get("options");
//            if(options != null && !Whether.YES.equals(field.getMulti())){
//                List<Map<String,Object>> newOptions = new ArrayList<>();
//                Map<String,Object> allOption = new HashMap<>();
//                allOption.put("label","全部");
//                allOption.put("value","");
//                newOptions.add(allOption);
//                newOptions.addAll(options);
//                queryConfig.put("options",newOptions);
//            }
            queryConfigs.add(queryConfig);
        }
        return queryConfigs;
    }
    @Override
    public Map<String, Object> getCurdJson(String code) {
        return getCurdJson(code,false);
    }
    @Override
    public Map<String, Object> getCurdJson(String code,boolean addTab) {
        Page page = pageService.get(code);
        Map<String,Object> params = new HashMap<>();
        setPageConfig(page,params);
        String template = "ui-json-template/crud.json.vm";
        if(addTab && !page.getPageRefs().isEmpty()){
            template = "ui-json-template/crudTabs.json.vm";
        }
        String ui = TemplateUtil.getValueByPath(template, params);
        JSONObject json = JSONUtil.parseObj(ui);
        return json;
    }

    @Override
    public void setPageConfig(Page page, Map<String, Object> params) {
        params.put("pageTitle",page.getName());
        params.put("pageCode",page.getCode());

        StringBuffer downloadParam = new StringBuffer("?1=1");
        page.getQueryFields().forEach(f->{
            String fieldName = StringUtil.toFieldColumn(f.getField());
            if (!fieldName.toLowerCase().contains("id") && !Whether.YES.equals(f.getRef())){
                fieldName = Constants.QUERY_KEY_START+fieldName;
            }
            downloadParam.append("&")
                    .append(fieldName)
                    .append("=${")
                    .append(fieldName)
                    .append("}");
        });

        List<Map<String,Object>> queryConfigs = this.queryConfigs(page);
        params.put("pageName",page.getName());
        if(SpringUtil.isTest()){
            params.put("pageName",
                    Util.getPageTitle(page));
        }

        params.put("queryConfigs", JSONUtil.toJsonPrettyStr(queryConfigs));
        params.put("downloadParam",downloadParam);
        setHiddenQueryFilter(page,params);

        PageButtonService pageButtonService = cn.hutool.extra.spring.SpringUtil.getBean(PageButtonService.class);
        PageButtonData pageButtonData = pageButtonService.dealPageButton(page.getPageButtons(), false);
        params.put("topButtons",JSONUtil.toJsonPrettyStr(pageButtonData.getTopButtons()));
        params.put("bulkButtons",JSONUtil.toJsonPrettyStr(pageButtonData.getBulkButtons()));
        params.put("extraJson","");
        if(StringUtils.isNotBlank(page.getExtraJson())){
            try{
                JSONObject extraJson = JSONUtil.parseObj(page.getExtraJson());
                String str = extraJson.toString();
                params.put("extraJson",str.substring(1,str.length()-1)+",");
            }catch (Exception e){
                throw new RuntimeException(page.getName()+"扩展json配置错误");
            }
        }
        //设置分页
        setPage(page,params);
        if(StringUtils.isNotBlank(page.getIntroduce())){
            params.put("introduce",page.getIntroduce());
        }
        //设置统计
        setAffixRow(page,params);
        //设置关联页面
        setPageRef(page,params);
    }

    private void setAffixRow(Page page,Map<String,Object> params){
        List<PageResultField> resultFields = page.getResultFields();
        boolean hasStatistics = false;
        List<Map<String,Object>> affixRow = new ArrayList<>();
        if(Whether.YES.equals(page.getOpenRowNum())){
            Map<String,Object> data = new HashMap<>();
            data.put("type","tpl");
            data.put("tpl","");
            affixRow.add(data);
        }
        for(PageResultField resultField:resultFields){
            if(Whether.YES.equals(resultField.getHidden())){
                continue;
            }
            Map<String,Object> data = new HashMap<>();
            data.put("type","tpl");
            data.put("tpl","");
            if(StringUtils.isNotBlank(resultField.getStatisticsLabel()) && StringUtils.isNotBlank(resultField.getStatisticsSql())){
                hasStatistics = true;
                data.put("tpl",resultField.getStatisticsLabel());
            }
            affixRow.add(data);
        }
        if(hasStatistics){
            params.put("affixRow", JSONUtil.toJsonPrettyStr(affixRow));
        }
    }
    private void setHiddenQueryFilter(Page page,Map<String,Object> params){
        boolean hiddenQueryFilter = true;
        for(PageQueryField queryField :page.getQueryFields()){
            if(!Whether.YES.equals(queryField.getHidden())){
                hiddenQueryFilter = false;
                break;
            }
        }
        params.put("hiddenQueryFilter",hiddenQueryFilter);
    }
    //设置分页
    private void setPage(Page page,Map<String,Object> params){
        int perPage = 10;
        if(page.getPerPage()!= null){
            perPage = page.getPerPage();
        }
        List<Integer> perPageAvailable = new ArrayList<>();
        perPageAvailable.add(5);
        perPageAvailable.add(10);
        perPageAvailable.add(20);
        perPageAvailable.add(50);
        perPageAvailable.add(100);
        perPageAvailable.add(300);
        perPageAvailable.add(500);
        boolean openPage = true;
        if(PageType.tree.equals(page.getPageType()) ||Whether.NO.equals(page.getOpenPage())){
            perPage = Integer.MAX_VALUE;
            perPageAvailable.clear();
            //perPageAvailable.add(10000);
            openPage = false;
        }
        params.put("perPage",perPage);
        params.put("perPageAvailable",JSONUtil.toJsonPrettyStr(perPageAvailable));
        params.put("openPage",openPage);
    }
    //设置关联页面
    private void setPageRef(Page page,Map<String,Object> params){

        if (!page.getPageRefs().isEmpty()) {
            int width = page.getWidth() != null ? page.getWidth() : 6;
            int tabWidth = 12 - width;
            params.put("width", width);
            params.put("tabWidth", tabWidth);
            if(page.getPageRefs().size() == 1){
                params.put("rightType","grid");
                params.put("rightTypeKey","columns");
            }else{
                params.put("rightType","tabs");
                params.put("rightTypeKey","tabs");
            }

            List<String> targets = new ArrayList<>();
            List<String> childTargets = new ArrayList<>();
            List<List<String>> treeChildTargets = new ArrayList<>();
            treeChildTargets.add(childTargets);
            List<Map<String,Object>> columns = new ArrayList<>();
            for(PageRef pageRef:page.getPageRefs()){
                targets.add(getTarget(pageRef));
                setLinkage(pageRef,columns,page.getPageRefs().size() !=1,treeChildTargets);
            }
            targets.addAll(childTargets);
            params.put("target", StringUtil.concatStr(targets, ","));
            params.put("columns", JSONUtil.toJsonPrettyStr(columns));
        }
    }

    //设置联动页面
    //设置联动页面
    private void setLinkage(PageRef pageRef,List<Map<String,Object>> pages,boolean isTab,List<List<String>> treeChildTargets){

        Map<String,Object> refJson = null;
        if(RefType.Page.equals(pageRef.getRefType())){
            Page page = pageService.get(pageRef.getRefPageCode());
            refJson = getCurdJson(page.getCode());
            refJson.put("title", page.getName());
            if(SpringUtil.isTest() && !isTab){
                refJson.put("title", Util.getPageTitle(page));
            }
            if(StringUtils.isNotBlank(pageRef.getRefName())){
                refJson.put("title",pageRef.getRefName());
                if(SpringUtil.isTest() && !isTab){
                    refJson.put("title", Util.getRefTitle(pageRef));
                }
            }
            addItem(refJson,isTab,pages,page);
            //pages.add(refJson);
            if (!page.getPageRefs().isEmpty()) {
                if(page.getPageRefs().size() == 1){
                    PageRef ref = page.getPageRefs().get(0);
                    Map<String,Object> itemAction = new HashMap<>();
                    itemAction.put("type","action");
                    itemAction.put("actionType","reload");

                    List<String> targets = new ArrayList<>();
                    targets.add(getTarget(ref));
                    List<String> childTargets = new ArrayList<>();
                    treeChildTargets.add(childTargets);

                    addTreeTarget(treeChildTargets,getChildTarget(getTarget(ref)),childTargets);

                    setLinkage(ref,pages,false,treeChildTargets);

                    targets.addAll(childTargets);
                    itemAction.put("target",StringUtil.concatStr(targets,","));
                    refJson.put("itemAction",itemAction);

                    treeChildTargets.remove(treeChildTargets);


                }else{
                    Map<String,Object> tab = new HashMap<>();
                    tab.put("type","tabs");

                    //全部加载,不在点击tab的时候才渲染,  如果点击tab才渲染会导致 前面点击传不到这个组件
                    tab.put("mountOnEnter",false);

                    List<Map<String,Object>> tabs = new ArrayList<>();
                    tab.put("tabs",tabs);
                    //pages.add(tab);
                    addItem(tab,isTab,pages,null);
                    List<String> targets = new ArrayList<>();

                    List<String> childTargets = new ArrayList<>();
                    treeChildTargets.add(childTargets);
                    for(PageRef childRef:page.getPageRefs()){
                        targets.add(getTarget(childRef));
                        //不能添加当前子节点,需要添加 孙子节点及以下节点
                        addTreeTarget(treeChildTargets,getChildTarget(getTarget(childRef)),childTargets);

                        setLinkage(childRef,tabs,true,treeChildTargets);
                    }
                    Map<String,Object> itemAction = new HashMap<>();
                    itemAction.put("type","action");
                    itemAction.put("actionType","reload");

                    targets.addAll(childTargets);

                    itemAction.put("target",StringUtil.concatStr(targets,","));
                    refJson.put("itemAction",itemAction);

                    treeChildTargets.remove(treeChildTargets);
                }
            }
        }else if(RefType.Form.equals(pageRef.getRefType())){
            Form form = formService.get(pageRef.getRefPageCode());
            Map<String, Object> formJson = formService.getFormJson(pageRef.getRefPageCode(), new BaseButton());
            Map<String, Object> formBody = (Map<String, Object>) formJson.get("body");
            List<Map<String, Obj>> actions = (List<Map<String, Obj>>) formJson.get("actions");
            formBody.put("name", form.getCode() + "Form");
            if (actions != null && !actions.isEmpty()) {
                formBody.put("actions", actions);
            }
            refJson = formBody;
            refJson.put("title", form.getName());
            if(StringUtils.isNotBlank(pageRef.getRefName())){
                refJson.put("title",pageRef.getRefName());
            }
            addItem(refJson,isTab,pages,null);
            //pages.add(refJson);
        }else if(RefType.Iframe.equals(pageRef.getRefType())){
            refJson = new HashMap<>();
            if(StringUtils.isNotBlank(pageRef.getRefName())){
                refJson.put("title",pageRef.getRefName());
            }
            refJson.put("type", "iframe");
            refJson.put("src", pageRef.getRefPageCode());
            refJson.put("name", pageRef.getId() + "tabFrame");
            refJson.put("id", pageRef.getId() + "tabFrame");
            refJson.put("height", "600px");
            refJson.put("title", "关联页面");
            addItem(refJson,isTab,pages,null);
            //pages.add(refJson);

        }

        String[] dataArr = StringUtil.splitStr(pageRef.getRefField(), "&");
        Map<String, Object> data = new HashMap<>();
        data.put("id", "");
        for (String p : dataArr) {
            String[] kv = StringUtil.splitStr(p, "=");
//            data.put(kv[0], kv[1]);
            data.put(UrlUtil.getPathArgNames(kv[1]).get(0), "");
        }

        refJson.put("xs","12");
        refJson.put("data",data);

    }

    private void addItem(Map<String,Object> item,boolean isTab,List<Map<String,Object>> items,Page page){
        if(isTab){
            Map<String,Object> column = new HashMap<>();
            column.put("title",item.remove("title"));
            column.put("tab",item);
//            if(page != null && Whether.YES.equals(page.getCloseQuery())){
//                Map<String,Object> filter = (Map<String, Object>) item.get("filter");
//                if(filter != null){
//                    filter.put("title","");
//                }
//            }
            items.add(column);
        }else{
            if(page != null){
                item.remove("title");
            }
            items.add(item);
        }
    }

    private void addTreeTarget(List<List<String>> treeChildTargets,String target,List<String> notAddList){
        for (int i = 0; i < treeChildTargets.size(); i++) {
            if(i!=treeChildTargets.size()-1 || treeChildTargets.get(i) != notAddList){
                treeChildTargets.get(i).add(target);
            }
        }
    }

    private String getTarget(PageRef pageRef){
        if(RefType.Page.equals(pageRef.getRefType())) {
            return StrUtil.format("{}Table?{}", pageRef.getRefPageCode(), pageRef.getRefField
                    ());
        }else if(RefType.Form.equals(pageRef.getRefType())){
            return StrUtil.format("{}Form?{}", pageRef.getRefPageCode(), pageRef.getRefField());
        }else if(RefType.Iframe.equals(pageRef.getRefType())){
            return StrUtil.format("{}tabFrame?{}", pageRef.getId(), pageRef.getRefField());
        }
        return null;
    }
    private String getChildTarget(String target){
        if(StringUtils.isNotBlank(target)){
            List<String> pathArgNames = UrlUtil.getPathArgNames(target);
            Map<String,Object> values = new HashMap<>();
            for(String name:pathArgNames){
                values.put(name,"-1");
            }
            return TemplateUtil.getValue(target,values);
        }
        return null;
    }

}
