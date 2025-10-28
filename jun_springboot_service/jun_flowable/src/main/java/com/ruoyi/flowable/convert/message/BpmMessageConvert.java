package com.ruoyi.flowable.convert.message;

import com.ruoyi.flowable.domain.dto.send.SmsSendSingleToUserReqDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Map;

@Mapper
public interface BpmMessageConvert {

    BpmMessageConvert INSTANCE = Mappers.getMapper(BpmMessageConvert.class);

    SmsSendSingleToUserReqDTO convert(Long userId, String templateCode, Map<String, Object> templateParams);

}
