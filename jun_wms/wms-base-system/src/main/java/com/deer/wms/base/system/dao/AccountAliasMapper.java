package com.deer.wms.base.system.dao;

import com.deer.wms.base.system.model.AccountAlias;
import com.deer.wms.base.system.model.AccountAliasCriteria;
import com.deer.wms.common.core.commonMapper.Mapper;

import java.util.List;

public interface AccountAliasMapper extends Mapper<AccountAlias> {
    List<AccountAlias> findList(AccountAliasCriteria criteria);

    AccountAlias findByDispositionId(Integer dispositionId);
}