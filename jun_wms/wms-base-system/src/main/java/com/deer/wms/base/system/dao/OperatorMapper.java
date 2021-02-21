package com.deer.wms.base.system.dao;

import com.deer.wms.base.system.model.Operator;
import com.deer.wms.base.system.model.OperatorCriteria;
import com.deer.wms.common.core.commonMapper.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OperatorMapper extends Mapper<Operator> {
    Operator findByCard(@Param("card") String card);

    List<Operator> findList(OperatorCriteria operator);

    public int checkOperatorCard(String OperatorCard);

    Operator findByOperatorCard(@Param("card") String card);
}