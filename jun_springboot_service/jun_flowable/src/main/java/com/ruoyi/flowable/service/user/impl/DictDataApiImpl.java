package com.ruoyi.flowable.service.user.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.enums.CommonStatusEnum;
import com.ruoyi.common.utils.collection.CollectionUtils;
import com.ruoyi.flowable.service.user.DictDataApi;
import com.ruoyi.system.service.ISysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.ruoyi.common.exception.util.ServiceExceptionUtil.exception;
import static com.ruoyi.flowable.core.enums.user.SysErrorCodeConstants.DICT_DATA_NOT_ENABLE;
import static com.ruoyi.flowable.core.enums.user.SysErrorCodeConstants.DICT_DATA_NOT_EXISTS;


/**
 * 字典数据 API 实现类
 * <p>
 * hasPermi
 */
@Service
public class DictDataApiImpl implements DictDataApi {

    @Autowired
    private ISysDictDataService dictDataService;

    @Override
    public void validDictDatas(String dictType, Collection<String> values) {
        if (CollUtil.isEmpty(values)) {
            return;
        }
        List<SysDictData> list = dictDataService.list(
                Wrappers.<SysDictData>query().lambda().eq(SysDictData::getDictType, dictType).in(SysDictData::getDictValue, values));
        Map<String, SysDictData> dictDataMap = CollectionUtils.convertMap(
                list, SysDictData::getDictValue);
        // 校验
        values.forEach(value -> {
            SysDictData dictData = dictDataMap.get(value);
            if (dictData == null) {
                throw exception(DICT_DATA_NOT_EXISTS);
            }
            if (!CommonStatusEnum.ENABLE.getStatus().equals(Integer.parseInt(dictData.getStatus()))) {
                throw exception(DICT_DATA_NOT_ENABLE, dictData.getDictLabel());
            }
        });
    }
}
