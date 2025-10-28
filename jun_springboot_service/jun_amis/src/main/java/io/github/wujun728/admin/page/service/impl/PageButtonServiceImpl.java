package io.github.wujun728.admin.page.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.github.wujun728.admin.common.config.SessionContext;
import io.github.wujun728.admin.page.constants.ActionType;
import io.github.wujun728.admin.page.constants.Whether;
import io.github.wujun728.admin.page.data.*;
import io.github.wujun728.admin.page.service.FormService;
import io.github.wujun728.admin.page.service.PageButtonDao;
import io.github.wujun728.admin.page.service.PageButtonService;
import io.github.wujun728.admin.page.service.PageService;
import io.github.wujun728.admin.util.SpringUtil;
import io.github.wujun728.admin.util.StringUtil;
import io.github.wujun728.admin.util.Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("pageButtonService")
public class PageButtonServiceImpl implements PageButtonService {

    @Resource
    @Lazy
    private FormService formService;

    @Resource
    @Lazy
    private PageService pageService;

    @Resource
    PageButtonDao pageButtonDao;

    @Override
    public Map<String,Object> getButton(BaseButton baseButton){
        Map<String,Object> btn = new HashMap<>();
        btn.put("type","button");
        btn.put("label",baseButton.getLabel());
        btn.put("level",baseButton.getLevel());
        if(SpringUtil.isTest()){
            if(ActionType.PopForm.equals(baseButton.getOptionType())){
                btn.put("tooltip", Util.getFormTitle(formService.get(baseButton.getOptionValue())));
            }else if(ActionType.PopPage.equals(baseButton.getOptionType())){
                String pageCode = null;
                String code = baseButton.getOptionValue();
                Map<String,Object> data = new HashMap<>();
                if(code.contains(",")){
                    String[] arr = StringUtil.splitStr(code,",");
                    pageCode = arr[0];
                }else{
                    pageCode = code.substring(0,code.indexOf("?"));
                }
                btn.put("tooltip", Util.getPageTitle(pageService.get(pageCode)));
            }else{
                btn.put("tooltip",baseButton.getOptionValue());
            }
        }
        if(StringUtils.isNotBlank(baseButton.getIcon())){
            btn.put("icon",baseButton.getIcon());
        }
//        btn.put("reload","mainTable");
        if(StrUtil.isNotBlank(baseButton.getJsRule())){
            btn.put("disabledOn",baseButton.getJsRule());
        }
        if(ActionType.PopForm.equals(baseButton.getOptionType())){
            btn.put("actionType","dialog");

            Map<String, Object> dialog = formService.getFormJson(baseButton.getOptionValue(),baseButton);
            btn.put("dialog",dialog);
        }else if(ActionType.PopPage.equals(baseButton.getOptionType())){
            btn.put("actionType","dialog");

            Map<String, Object> dialog = formService.getPageJson(baseButton.getOptionValue(),baseButton);
            btn.put("dialog",dialog);
        }else if(ActionType.PopIframe.equals(baseButton.getOptionType())){
            btn.put("actionType","dialog");

            Map<String,Object> dialog = new HashMap<>();
            dialog.put("title",baseButton.getLabel());
            dialog.put("size","full");
            List<Map<String,Object>> dialogButtons = new ArrayList<>();

            dialog.put("actions",dialogButtons);

            Map<String, Object> iframe = new HashMap<>();
            iframe.put("type","iframe");
            iframe.put("src",baseButton.getOptionValue());
            iframe.put("height","calc( 100% - 10px )");
            dialog.put("body",iframe);

            btn.put("dialog",dialog);
        }else if(ActionType.Ajax.equals(baseButton.getOptionType())){
            btn.put("actionType","ajax");
            btn.put("api",baseButton.getOptionValue());

            String confirmText = StrUtil.isBlank(baseButton.getConfirmText()) ? "确定" + baseButton.getLabel()+"操作吗?" : baseButton.getConfirmText();
            if(!Whether.NO.equals(baseButton.getWhetherConfirm())){
                btn.put("confirmText",confirmText);
            }
        }else if(ActionType.OpenNew.equals(baseButton.getOptionType())){
            btn.put("actionType","url");
            btn.put("url",baseButton.getOptionValue());
        }else if(ActionType.IframeAjax.equals(baseButton.getOptionType())){
            btn.put("actionType","ajax");
            String method = "post";
            String url = baseButton.getOptionValue();
            if(baseButton.getOptionValue().contains(":")){
                String[] arr = baseButton.getOptionValue().split(":");
                method = arr[0];
                url = arr[1];
            }
            btn.put("api", new JSONObject()
                    .set("method",method)
                    .set("url",url)
                    .set("adaptor","if(payload.status == 0){parent.amisScoped.getComponentById('mainTable').search(); parent.jQuery('.cxd-Modal-close')[0].click();}return payload")
            );
            String confirmText = StrUtil.isBlank(baseButton.getConfirmText()) ? "确定" + baseButton.getLabel()+"操作吗?" : baseButton.getConfirmText();
            btn.put("confirmText",confirmText);
        }

        if(StringUtils.isNotBlank(baseButton.getBeforePopApi())){
            //dialog弹出前校验,校验接口成功的message不要有提示,否则会显示,错误需要有提示,错误了就不会弹出了
            Object actionType = btn.get("actionType");
            if("dialog".equals(actionType)){
                btn.put("actionType","ajax");
                btn.put("api",baseButton.getBeforePopApi());
                btn.put("feedback",btn.remove("dialog"));
            }
        }
        if(StringUtils.isNotBlank(baseButton.getExtraJson())){
            try{
                JSONObject json = JSONUtil.parseObj(baseButton.getExtraJson());
                btn.putAll(json);
            }catch (Exception e){
                throw new RuntimeException(StrUtil.format("按钮["+baseButton.getLabel()+"]扩展json配置错误"));
            }
        }
        return btn;
    }


    @Override
    public List<PageButton> byPageCode(String pageCode) {
        Page page = pageService.get(pageCode);
        if (page == null) {
            return new ArrayList<>();
        }
        return pageButtonDao.byPage(page);
    }

    @Override
    public void save(PageButton pageButton) {
        pageButtonDao.save(pageButton);
    }

    @Override
    public List<PageButton> byPageId(Long id) {
        return pageButtonDao.byPageId(id);
    }

    @Override
    public List<PageButton> byPage(Page page) {
        return pageButtonDao.byPage(page);
    }

    @Override
    public List<PageButton> getByForm(Form form) {
        return pageButtonDao.getByForm(form);
    }

    @Override
    public Page getPage(PageButton pageButton){
        return pageService.get(pageButton.getPageId());
    }

    @Override
    public PageButtonData dealPageButton(List<PageButton> pageButtons, boolean isRow) {
        PageButtonData pageButtonData = new PageButtonData();
        if(!isRow){
            pageButtonData.getTopButtons().add("filter-toggler");
        }
        for(PageButton pageButton:pageButtons){
            if(!SessionContext.hasButtonPermission(pageButton.getCode())){
                continue;
            }
            if(isRow){
                if("row".equals(pageButton.getButtonLocation())){
                    if(Whether.YES.equals(pageButton.getAddToMore())){
                        Map<String, Object> button = getButton(pageButton);
                        if(StringUtils.isNotBlank(pageButton.getLevel())){
                            button.put("className","my-dropdown-button my-dropdown-button-"+pageButton.getLevel());
                        }
                        pageButtonData.getRowMoreButtons().add(button);
                    }else{
                        pageButtonData.getRowButtons().add(getButton(pageButton));
                    }
                }
            }else{
                if("top".equals(pageButton.getButtonLocation())){
                    pageButtonData.getTopButtons().add(getButton(pageButton));
                }else if("bulk".equals(pageButton.getButtonLocation())){
                    pageButtonData.getBulkButtons().add(getButton(pageButton));
                }
            }
        }
        if(isRow && !pageButtonData.getRowMoreButtons().isEmpty()){
            Map<String,Object> dropdownButton = new HashMap<>();
            dropdownButton.put("type","dropdown-button");
            dropdownButton.put("label","更多");
//                    dropdownButton.put("primary",true);
            dropdownButton.put("level","success");
            dropdownButton.put("menuClassName","my-dropdown");
            dropdownButton.put("buttons",pageButtonData.getRowMoreButtons());

            pageButtonData.getRowButtons().add(dropdownButton);
        }

        if(!pageButtonData.getBulkButtons().isEmpty()){
            pageButtonData.getTopButtons().add("bulkActions");
        }
        return pageButtonData;
    }
}
