package com.ruoyi.biz.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.biz.constants.FormConstants;
import com.ruoyi.biz.domain.BizForm;
import com.ruoyi.biz.domain.CommonForm;
import com.ruoyi.biz.enums.ComponentTypeEnum;
import com.ruoyi.biz.enums.FormErrorMsgEnum;
import com.ruoyi.biz.factory.BizFormDataFactory;
import com.ruoyi.biz.service.IBizFormDataService;
import com.ruoyi.biz.service.IBizFormService;
import com.ruoyi.biz.utils.FormClassUtil;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.mq.api.ISyncPush;
import com.ruoyi.mq.enums.QueueEnum;
import com.ruoyi.template.domain.Template;
import com.ruoyi.template.domain.TemplateDynamicForm;
import com.ruoyi.template.enums.FormTypeEnum;
import com.ruoyi.template.service.ITemplateDynamicFormService;
import com.ruoyi.template.service.ITemplateService;
import com.ruoyi.todo.domain.vo.TodoVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.flowable.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> 业务表单服务接口实现类 </p>
 *
 * @Author wocurr.com
 */
@Slf4j
@Service
public class BizFormServiceImpl implements IBizFormService {

    @Autowired
    private BizFormDataFactory bizFormDataFactory;
    @Autowired
    private ITemplateService templateService;
    @Autowired
    private ITemplateDynamicFormService templateDynamicFormService;
    @Autowired
    private ISyncPush syncPush;
    @Autowired
    private RuntimeService runtimeService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String save(CommonForm commonForm) {
        IBizFormDataService bizFormDataImpl = getBizFormDataImpl(commonForm);
        try {
            Object transform = getTransform(bizFormDataImpl, commonForm);
            //保存表单数据
            String businessId = bizFormDataImpl.save(transform);
            // 处理表单变量
            setFormDataVariable(commonForm);
            // 插入我起草的
            createMyDraft(commonForm, businessId);
            //新增待办
            createOrUpdateTodoBySync(commonForm, businessId);
            return businessId;
        } catch (Exception e) {
            log.error("业务表单数据保存失败:", e);
            throw new BaseException("业务表单数据保存失败:" + e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(CommonForm commonForm) {
        IBizFormDataService bizFormDataImpl = getBizFormDataImpl(commonForm);
        try {
            Object transform = getTransform(bizFormDataImpl, commonForm);
            //保存表单数据
            bizFormDataImpl.update(transform);
            // 处理表单变量
            setFormDataVariable(commonForm);
            //更新待办
            createOrUpdateTodoBySync(commonForm, null);
        } catch (Exception e) {
            log.error("业务表单数据更新失败:", e);
            throw new BaseException("业务表单数据更新失败:" + e.getMessage());
        }
    }

    @Override
    public Object getBizForm(CommonForm commonForm) {
        IBizFormDataService bizFormDataImpl = getBizFormDataImpl(commonForm);
        try {
            Object bizForm;
            if (StringUtils.isBlank(commonForm.getBizId())) {
                bizForm = bizFormDataImpl.initBizForm(commonForm.getTemplateId());
            } else {
                bizForm = bizFormDataImpl.getBizForm(commonForm.getBizId());
            }
            if (FormTypeEnum.BIZ.getCode().equals(commonForm.getTemplate().getFormType())) {
                return getBizForm(commonForm, bizForm);
            }
            return bizForm;
        } catch (Exception e) {
            log.error("获取业务表单数据失败:", e);
            throw new BaseException("获取业务表单数据失败:" + e.getMessage());
        }
    }

    /**
     * 获取业务表单数据
     *
     * @param commonForm  参数
     * @param bizFormData 业务表单数据
     * @return 业务表单数据
     */
    private BizForm getBizForm(CommonForm commonForm, Object bizFormData) {
        TemplateDynamicForm templateDynamicForm = templateDynamicFormService.getTemplateDynamicFormById(commonForm.getTemplate().getFormId());
        if (ObjectUtils.isEmpty(templateDynamicForm)) {
            throw new BaseException("未配置业务表单");
        }
        JSONObject json = new JSONObject();
        json.put(FormConstants.FORM_KEY, JSONObject.parseObject(templateDynamicForm.getContent()));
        json.put(FormConstants.VAL_DATA_KEY, bizFormData);
        BizForm bizForm = new BizForm();
        bizForm.setFormData(json.toJSONString());
        return bizForm;
    }

    /**
     * 获取业务表单实现类
     *
     * @param commonForm 表单参数
     * @return bizFormDataImpl实现类
     */
    private IBizFormDataService getBizFormDataImpl(CommonForm commonForm) {
        if (StringUtils.isBlank(commonForm.getTemplateId())) {
            throw new BaseException("模板ID为空");
        }
        Template template = templateService.getTemplateById(commonForm.getTemplateId());
        if (template == null) {
            throw new BaseException("未找到模板");
        }
        if (StringUtils.isBlank(template.getType())) {
            throw new BaseException("模板类型为空");
        }
        if (StringUtils.isBlank(template.getFormCode())) {
            throw new BaseException("表单编码为空");
        }
        IBizFormDataService bizFormDataImpl = bizFormDataFactory.getBizFormDataImplByType(template.getFormCode());
        if (bizFormDataImpl == null) {
            throw new BaseException("未找到业务表单实现类");
        }
        commonForm.setTemplate(template);
        return bizFormDataImpl;
    }

    /**
     * 获取转换后表单数据
     *
     * @param bizFormDataImpl 业务表单数据实现
     * @param commonForm      业务表单
     * @return Object 转换后表单数据
     */
    private Object getTransform(IBizFormDataService bizFormDataImpl, CommonForm commonForm) {
        Object formData = commonForm.getFormData();
        //获取表单类，字段赋值
        Class<?> formClass = bizFormDataImpl.getFormClass();
        if (formClass == null) {
            throw new BaseException("业务表单类为空");
        }
        try {
            Object transform = null;
            HashMap formDataMap = (HashMap) formData;
            JSONObject jsonObject = JSONObject.parseObject(String.valueOf(formDataMap.get(FormConstants.FORM_KEY)));
            FormTypeEnum formType = FormTypeEnum.getByCode(commonForm.getTemplate().getFormType());
            if (!StringUtils.equals(FormTypeEnum.DEFINED.getCode(), formType.getCode())) {
                JSONObject valDataJson = jsonObject.getJSONObject(FormConstants.VAL_DATA_KEY);
                JSONObject sourceValDataJson = new JSONObject(valDataJson);
                JSONObject formConfig = jsonObject.getJSONObject(FormConstants.FORM_KEY);
                commonForm.setValData(sourceValDataJson);
                convertValueToLabel(valDataJson, formConfig);
                formDataMap.put(FormConstants.VAL_DATA_KEY, valDataJson);
            }
            switch (formType) {
                case DYNAMIC:
                    transform = FormClassUtil.transForm(formDataMap, formClass);
                    break;
                case BIZ:
                    transform = FormClassUtil.transForm(jsonObject.get(FormConstants.VAL_DATA_KEY), formClass);
                    break;
                case DEFINED:
                    transform = FormClassUtil.transForm(jsonObject, formClass);
                    break;
            }
            return transform;
        } catch (Exception e) {
            log.error("业务表单数据转换失败", e);
            throw new BaseException("业务表单数据转换失败:" + e.getMessage());
        }
    }

    /**
     * 转换字段值
     *
     * @param valData    字段值
     * @param formConfig 字段配置
     */
    private void convertValueToLabel(JSONObject valData, JSONObject formConfig) {
        JSONArray fields = formConfig.getJSONArray(FormConstants.FIELDS_KEY);
        for (Object fieldObj : fields) {
            JSONObject field = (JSONObject) fieldObj;
            JSONObject config = field.getJSONObject(FormConstants.CONFIG_FIELD);

            String vModel = field.getString(FormConstants.V_MODEL_FIELD);
            ComponentTypeEnum componentType = ComponentTypeEnum.fromTag(config.getString(FormConstants.TAG_FIELD));
            String componentSubType = field.containsKey(FormConstants.TYPE_FIELD) ?
                    field.getString(FormConstants.TYPE_FIELD) : StringUtils.EMPTY;

            if (!valData.containsKey(vModel)) continue;

            Object value = valData.get(vModel);
            try {
                switch (componentType) {
                    case EL_SELECT:
                        handleSelectLabel(valData, field, vModel, value);
                        break;
                    case EL_CASCADER:
                        handleCascaderLabel(valData, field, vModel, value);
                        break;
                    case EL_RADIO_GROUP:
                        handleRadioLabel(valData, field, vModel, value);
                        break;
                    case EL_CHECKBOX_GROUP:
                        handleCheckboxLabel(valData, field, vModel, value);
                        break;
                    case EL_DATE_PICKER:
                        if (FormConstants.DATE_RANGE_FIELD.equals(componentSubType)) {
                            handleDateRangeLabel(valData, vModel, value);
                        }
                        break;
                    case EL_TIME_PICKER:
                        if (field.containsKey(FormConstants.IS_RANGE_FIELD) &&
                                field.getBoolean(FormConstants.IS_RANGE_FIELD)) {
                            handleTimeRangeLabel(valData, vModel, value);
                        }
                        break;
                    default:
                        log.warn("未支持的组件类型: {}", componentType.getTag());
                        valData.put(vModel, value);
                }
            } catch (Exception e) {
                log.error("{}: {}", FormErrorMsgEnum.CONVERSION_ERROR.formatMessage(vModel), e.getMessage());
                valData.put(vModel + FormConstants.LABEL_SUFFIX, FormErrorMsgEnum.CONVERSION_ERROR.getMessage());
            }
        }
    }


    /**
     * 处理下拉框
     */
    private void handleSelectLabel(JSONObject valData, JSONObject field, String vModel, Object value) {
        JSONArray options = field.getJSONObject(FormConstants.SLOT_FIELD).getJSONArray(FormConstants.OPTIONS_FIELD);
        for (Object opt : options) {
            JSONObject option = (JSONObject) opt;
            if (option.get(FormConstants.VALUE_FIELD).equals(value)) {
                valData.put(vModel, option.getString(FormConstants.LABEL_FIELD));
                break;
            }
        }
    }

    /**
     * 处理级联选择
     */
    private void handleCascaderLabel(JSONObject valData, JSONObject field, String vModel, Object value) {
        JSONArray options = field.getJSONArray(FormConstants.OPTIONS_FIELD);
        List<String> labels = new ArrayList<>();
        List<Integer> values = JSON.parseArray(value.toString(), Integer.class);

        findCascaderLabels(options, values, 0, labels);
        valData.put(vModel, String.join(" / ", labels));
    }

    /**
     * 递归查找级联Label
     */
    private boolean findCascaderLabels(JSONArray options, List<Integer> values, int depth, List<String> labels) {
        for (Object opt : options) {
            JSONObject option = (JSONObject) opt;
            if (option.getInteger(FormConstants.VALUE_FIELD).equals(values.get(depth))) {
                labels.add(option.getString(FormConstants.LABEL_FIELD));
                if (depth < values.size() - 1 && option.containsKey(FormConstants.CHILDREN_FIELD)) {
                    return findCascaderLabels(option.getJSONArray(FormConstants.CHILDREN_FIELD), values, depth + 1, labels);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 处理日期范围
     *
     * @param valData 字段值
     * @param vModel  字段名
     * @param value   值
     */
    private void handleDateRangeLabel(JSONObject valData, String vModel, Object value) {
        JSONArray dates = (JSONArray) value;
        if (dates.size() == 2) {
            String start = dates.getString(0);
            String end = dates.getString(1);
            valData.put(vModel, start + " 至 " + end);
        }
    }

    /**
     * 处理时间范围
     *
     * @param valData 字段值
     * @param vModel  字段名
     * @param value   值
     */
    private void handleTimeRangeLabel(JSONObject valData, String vModel, Object value) {
        JSONArray times = (JSONArray) value;
        if (times.size() == 2) {
            String start = times.getString(0);
            String end = times.getString(1);
            valData.put(vModel, start + " 至 " + end);
        }
    }

    /**
     * 处理单选框
     *
     * @param valData 字段值
     * @param field   字段
     * @param vModel  字段名
     * @param value   值
     */
    private void handleRadioLabel(JSONObject valData, JSONObject field, String vModel, Object value) {
        JSONArray options = field.getJSONObject(FormConstants.SLOT_FIELD).getJSONArray(FormConstants.OPTIONS_FIELD);
        for (Object opt : options) {
            JSONObject option = (JSONObject) opt;
            if (option.get(FormConstants.VALUE_FIELD).equals(value)) {
                valData.put(vModel, option.getString(FormConstants.LABEL_FIELD));
                break;
            }
        }
    }

    /**
     * 处理复选框
     *
     * @param valData 字段值
     * @param field   字段
     * @param vModel  字段名
     * @param value   值
     */
    private void handleCheckboxLabel(JSONObject valData, JSONObject field, String vModel, Object value) {
        JSONArray values = (JSONArray) value;
        JSONArray options = field.getJSONObject(FormConstants.SLOT_FIELD).getJSONArray(FormConstants.OPTIONS_FIELD);
        List<String> labels = new ArrayList<>();

        for (Object val : values) {
            for (Object opt : options) {
                JSONObject option = (JSONObject) opt;
                if (option.get(FormConstants.VALUE_FIELD).equals(val)) {
                    labels.add(option.getString(FormConstants.LABEL_FIELD));
                    break;
                }
            }
        }

        valData.put(vModel, String.join(", ", labels));
    }

    /**
     * 异步生成待办任务
     *
     * @param commonForm 表单参数
     * @param businessId 业务ID
     */
    private void createOrUpdateTodoBySync(CommonForm commonForm, String businessId) {
        Map<String, Object> formDataMap = (HashMap) commonForm.getFormData();
        TodoVO todoVO = FormClassUtil.transForm(formDataMap, TodoVO.class);
        todoVO.setBusinessId(formDataMap.get("id") != null ? formDataMap.get("id").toString() : businessId);
        todoVO.setCreateId(SecurityUtils.getUserIdStr());
        syncPush.push(QueueEnum.ASYNC_TODO_QUEUE.getConsumerBeanName(), JSON.toJSONString(todoVO), QueueEnum.ASYNC_TODO_QUEUE.getQueueName());
    }

    /**
     * 异步处理我起草的流程
     *
     * @param commonForm
     * @param businessId
     */
    private void createMyDraft(CommonForm commonForm, String businessId) {
        Map<String, Object> formDataMap = (HashMap) commonForm.getFormData();
        JSONObject formDataObj = new JSONObject();
        formDataObj.put("templateId", commonForm.getTemplateId());
        String bizId = formDataMap.get("id") != null ? formDataMap.get("id").toString() : businessId;
        formDataObj.put("businessId", bizId);
        formDataObj.put("title", getTitle(commonForm, formDataMap));
        formDataObj.put("type", "0");
        formDataObj.put("status", "0");
        formDataObj.put("operatorId", SecurityUtils.getUserId());
        formDataObj.put("procInstId", formDataMap.get("procInstId"));
        formDataObj.put("taskId", formDataMap.get("taskId"));
        syncPush.push(QueueEnum.ASYNC_MY_DRAFT_QUEUE.getConsumerBeanName(), JSON.toJSONString(formDataObj), QueueEnum.ASYNC_MY_DRAFT_QUEUE.getQueueName());
    }

    /**
     * 设置表单数据变量
     *
     * @param commonForm
     */
    private void setFormDataVariable(CommonForm commonForm) {
        Map<String, Object> valData = commonForm.getValData();
        if (MapUtils.isEmpty(valData)) {
            return;
        }
        Map<String, Object> formDataMap = (Map) commonForm.getFormData();
        if (!formDataMap.containsKey("procInstId")) {
            return;
        }
        String procInstId = (String) formDataMap.get("procInstId");
        valData.replaceAll((key, value) -> {
            if (value instanceof BigDecimal) {
                return ((BigDecimal) value).doubleValue();
            }
            if (value instanceof JSONArray) {
                JSONArray array = (JSONArray) value;
                if (array.isEmpty()) {
                    return StringUtils.EMPTY;
                }
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < array.size(); i++) {
                    if (i > 0) {
                        sb.append(Constants.COMMA);
                    }
                    sb.append(array.get(i) == null ? StringUtils.EMPTY : array.get(i).toString());
                }
                return sb.toString();
            }
            return value;
        });
        runtimeService.setVariables(procInstId, valData);
    }

    /**
     * 获取表单标题
     * @param commonForm
     * @param formDataMap
     * @return
     */
    private String getTitle(CommonForm commonForm, Map<String, Object> formDataMap) {
        if (commonForm.getTemplate() == null) {
            return "";
        }
        if (StringUtils.equals(FormTypeEnum.DEFINED.getCode(), commonForm.getTemplate().getFormType())) {
            JSONObject jsonObject = JSONObject.parseObject(String.valueOf(formDataMap.get(FormConstants.FORM_KEY)));
            return jsonObject.getString("title");
        }
        return String.valueOf(commonForm.getValData().get("title"));
    }
}