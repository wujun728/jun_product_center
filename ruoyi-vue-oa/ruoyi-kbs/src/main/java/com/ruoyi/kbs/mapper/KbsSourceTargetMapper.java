package com.ruoyi.kbs.mapper;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.kbs.domain.*;
import com.ruoyi.kbs.domain.vo.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 转换实体
 *
 * @Author : wocurr.com
 */
@Mapper(componentModel = "spring")
public interface KbsSourceTargetMapper {
    KbsSourceTargetMapper INSTANCE = Mappers.getMapper(KbsSourceTargetMapper.class);

    KbsTopicInfo convertTopicInfoVo2TopicInfo(KbsTopicInfoVo kbsTopicInfoVo);

    KbsTopicInfoVo convertTopicInfo2TopicInfoVo(KbsTopicInfo kbsTopicInfo);

    List<KbsDocumentModel> convertDocumentBase2DocumentModel(List<KbsDocumentBase> documentBases);

    KbsDocumentBase convertDocumentInfoVo2DocumentBase(KbsDocumentInfoVo kbsDocumentInfoVo);

    KbsDocumentInfo convertDocumentInfoVo2DocumentInfo(KbsDocumentInfoVo kbsDocumentInfoVo);

    KbsTopicAllInfoVo convertTopicInfo2TopicAllInfoVo(KbsTopicInfo kbsTopicInfo);

    SysUserVo convertSysUser2SysUserVo(SysUser k);

    KbsDocumentCommentVo convertDocumentComment2DocumentCommentVo(KbsDocumentComment comment);
}
