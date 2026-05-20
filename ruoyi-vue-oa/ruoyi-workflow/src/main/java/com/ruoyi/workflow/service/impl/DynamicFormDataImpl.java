package com.ruoyi.workflow.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.biz.constants.FormConstants;
import com.ruoyi.biz.enums.ComponentTypeEnum;
import com.ruoyi.biz.enums.FormErrorMsgEnum;
import com.ruoyi.biz.service.abs.AbstractBizFormData;
import com.ruoyi.common.core.domain.TreeSelect;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.file.business.module.BookmarkData;
import com.ruoyi.workfile.service.IWorkflowMainTextService;
import com.ruoyi.workflow.domain.Form;
import com.ruoyi.workflow.domain.FormExtend;
import com.ruoyi.workflow.service.IFormService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p> 动态表单数据相关业务实现 </p>
 *
 * @Author wocurr.com
 */
@Slf4j
@Service
public class DynamicFormDataImpl extends AbstractBizFormData<FormExtend> {

    @Autowired
    private IFormService formService;
    @Autowired
    private IWorkflowMainTextService mainTextService;

    @Override
    public String getBizType() {
        return "dynamic";
    }

    @Override
    public Class<?> getFormClass() {
        return FormExtend.class;
    }

    @Override
    public String save(FormExtend formData) {
        String businessId = formService.saveForm(formData);
        // 处理正文
        handleMainText(businessId, formData);
        return businessId;
    }

    @Override
    public void update(FormExtend formData) {
        // 处理正文
        handleMainText(formData.getId(), formData);
        formService.updateForm(formData);
    }

    @Override
    public Object getBizForm(String bizId) {
        if (StringUtils.isBlank(bizId)) {
            return null;
        }
        return formService.getFormById(bizId);
    }

    @Override
    public Object initBizForm(String templateId) {
        JSONObject json = new JSONObject();
        json.put("formData", JSONObject.parseObject(formService.dynamicFormData(templateId)));
        Form form = new Form();
        form.setTemplateId(templateId);
        form.setFormData(json.toJSONString());
        return form;
    }

    /**
     * 生成或更新正文
     * @param businessId
     * @param formData
     */
    private void handleMainText(String businessId, FormExtend formData) {
        String templateId = formData.getTemplateId();
        Map<String, Object> valData = formData.getValData();
        // 处理转义字段，如选人、选组织，字段值是一个对象，书签替换时，只需要值
        translateField(valData, formData.getFormData());
        // 特殊样式，在此处单独处理
        BookmarkData data = new BookmarkData();
        data.setBookmarks(valData);
        mainTextService.saveMainText(templateId, businessId, data);
    }

    /**
     * 转义字段
     *  默认转义选人、选组织
     * @param valData
     * @param formDataStr
     */
    private void translateField(Map<String, Object> valData, String formDataStr) {
        if (valData == null || StringUtils.isBlank(formDataStr)) {
            return;
        }
        JSONObject jsonObject = JSONObject.parseObject(formDataStr);
        if (jsonObject == null) {
            return;
        }
        JSONObject formData = jsonObject.getJSONObject(FormConstants.FORM_KEY);
        if (formData == null) {
            return;
        }
        JSONArray fields = formData.getJSONArray(FormConstants.FIELDS_KEY);
        for (Object fieldObj : fields) {
            JSONObject field = (JSONObject) fieldObj;
            String vModel = field.getString(FormConstants.V_MODEL_FIELD);
            if (!valData.containsKey(vModel)) {
                continue;
            }
            JSONObject config = field.getJSONObject(FormConstants.CONFIG_FIELD);
            ComponentTypeEnum componentType = ComponentTypeEnum.fromTag(config.getString(FormConstants.TAG_FIELD));
            Object value = valData.get(vModel);
            try {
                switch (componentType) {
                    case ORG_SELECT:
                        translateOrg(vModel, value, valData);
                        break;
                    case USER_SELECT:
                        translateUser(vModel, value, valData);
                        break;
                    default:
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    /**
     * 转义组织字段
     * @param vModel
     * @param value
     * @param valData
     */
    private void translateOrg(String vModel, Object value, Map<String, Object> valData) {
        if (value instanceof List) {
            List<String> orgNames = new ArrayList<>();
            for (Object item : (List) value) {
                Map itemMap = (Map) item;
                orgNames.add(String.valueOf(itemMap.get("label")));
            }
            valData.put(vModel, String.join("、", orgNames));
            return;
        }
        Map itemMap = (Map) value;
        valData.put(vModel, String.valueOf(itemMap.get("label")));
    }

    /**
     * 转义用户字段
     * @param vModel
     * @param value
     * @param valData
     */
    private void translateUser(String vModel, Object value, Map<String, Object> valData) {
        if (value instanceof List) {
            List<String> userNames = new ArrayList<>();
            for (Object item : (List) value) {
                Map itemMap = (Map) item;
                userNames.add(String.valueOf(itemMap.get("nickName")));
            }
            valData.put(vModel, String.join("、", userNames));
            return;
        }
        Map itemMap = (Map) value;
        valData.put(vModel, String.valueOf(itemMap.get("nickName")));
    }
}
