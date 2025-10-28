package com.ruoyi.flowable.service.definition.impl;

import cn.hutool.core.lang.Assert;
import com.ruoyi.common.mybatis.pojo.PageResult;
import com.ruoyi.common.utils.JsonUtils;
import com.ruoyi.common.utils.ValidationUtils;
import com.ruoyi.flowable.convert.definition.BpmFormConvert;
import com.ruoyi.flowable.core.enums.ErrorCodeConstants;
import com.ruoyi.flowable.core.enums.definition.BpmModelFormTypeEnum;
import com.ruoyi.flowable.domain.dto.definition.BpmFormFieldRespDTO;
import com.ruoyi.flowable.domain.dto.definition.BpmModelMetaInfoRespDTO;
import com.ruoyi.flowable.domain.entity.definition.BpmFormDO;
import com.ruoyi.flowable.domain.vo.form.BpmFormCreateReqVO;
import com.ruoyi.flowable.domain.vo.form.BpmFormPageReqVO;
import com.ruoyi.flowable.domain.vo.form.BpmFormUpdateReqVO;
import com.ruoyi.flowable.mapper.definition.BpmFormMapper;
import com.ruoyi.flowable.service.definition.BpmFormService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.*;

import static com.ruoyi.common.exception.util.ServiceExceptionUtil.exception;
import static com.ruoyi.flowable.core.enums.ErrorCodeConstants.*;


/**
 * 动态表单 Service 实现类
 *
 * @author 风里雾里
 */
@Service
@Validated
public class BpmFormServiceImpl implements BpmFormService {

    @Resource
    private BpmFormMapper formMapper;

    @Override
    public Long createForm(BpmFormCreateReqVO createReqVO) {
        // 插入
        BpmFormDO form = BpmFormConvert.INSTANCE.convert(createReqVO);
        formMapper.insert(form);
        // 返回
        return form.getId();
    }

    @Override
    public void updateForm(BpmFormUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateFormExists(updateReqVO.getId());
        // 更新
        BpmFormDO updateObj = BpmFormConvert.INSTANCE.convert(updateReqVO);
        formMapper.updateById(updateObj);
    }

    @Override
    public void deleteForm(Long id) {
        // 校验存在
        this.validateFormExists(id);
        // 删除
        formMapper.deleteById(id);
    }

    private void validateFormExists(Long id) {
        if (formMapper.selectById(id) == null) {
            throw exception(ErrorCodeConstants.FORM_NOT_EXISTS);
        }
    }

    @Override
    public BpmFormDO getForm(Long id) {
        return formMapper.selectById(id);
    }

    @Override
    public List<BpmFormDO> getFormList() {
        return formMapper.selectList();
    }

    @Override
    public List<BpmFormDO> getFormList(Collection<Long> ids) {
        return formMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<BpmFormDO> getFormPage(BpmFormPageReqVO pageReqVO) {
        return formMapper.selectPage(pageReqVO);
    }


    @Override
    public BpmFormDO checkFormConfig(String configStr) {
        BpmModelMetaInfoRespDTO metaInfo = JsonUtils.parseObject(configStr, BpmModelMetaInfoRespDTO.class);
        if (metaInfo == null || metaInfo.getFormType() == null) {
            throw exception(MODEL_DEPLOY_FAIL_FORM_NOT_CONFIG);
        }
        // 校验表单存在
        if (Objects.equals(metaInfo.getFormType(), BpmModelFormTypeEnum.NORMAL.getType())) {
            BpmFormDO form = getForm(metaInfo.getFormId());
            if (form == null) {
                throw exception(FORM_NOT_EXISTS);
            }
            return form;
        }
        return null;
    }

    private void checkKeyNCName(String key) {
        if (!ValidationUtils.isXmlNCName(key)) {
            throw exception(MODEL_KEY_VALID);
        }
    }

    /**
     * 校验 Field，避免 field 重复
     *
     * @param fields field 数组
     */
    private void checkFields(List<String> fields) {
        // key 是 vModel，value 是 label
        Map<String, String> fieldMap = new HashMap<>();
        for (String field : fields) {
            BpmFormFieldRespDTO fieldDTO = JsonUtils.parseObject(field, BpmFormFieldRespDTO.class);
            Assert.notNull(fieldDTO);
            String oldLabel = fieldMap.put(fieldDTO.getVModel(), fieldDTO.getLabel());
            // 如果不存在，则直接返回
            if (oldLabel == null) {
                continue;
            }
            // 如果存在，则报错
            throw exception(ErrorCodeConstants.FORM_FIELD_REPEAT, oldLabel, fieldDTO.getLabel(), fieldDTO.getVModel());
        }
    }

}
