package com.ruoyi.template.mapper;
import com.ruoyi.template.domain.Template;
import com.ruoyi.template.domain.TemplateDynamicForm;
import com.ruoyi.template.domain.TemplateMainText;
import com.ruoyi.template.domain.TemplateMessageNotice;
import com.ruoyi.template.module.TemplateDTO;
import com.ruoyi.template.module.TemplateMainTextDTO;
import com.ruoyi.template.module.TemplateMessageNoticeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 对象转换
 * @Author : wocurr.com
 */
@Mapper(componentModel = "spring")
public interface TemplateSourceTargetMapper {
    TemplateSourceTargetMapper INSTANCE = Mappers.getMapper(TemplateSourceTargetMapper.class);

    Template convertTemplate(TemplateDTO templateDTO);

    TemplateDTO convertTemplateDTO(Template template);

    TemplateDynamicForm copyDynamicForm(TemplateDynamicForm templateDynamicForm);

    Template copyTemplate(Template template);

    TemplateMainTextDTO copyTemplateMainTextDTO(TemplateMainText templateMainText);

    TemplateMessageNoticeDTO copyTemplateMessageNoticeDTO(TemplateMessageNotice messageNotice);
}
