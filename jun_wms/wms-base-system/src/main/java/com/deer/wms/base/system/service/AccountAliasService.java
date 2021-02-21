package com.deer.wms.base.system.service;

import com.deer.wms.base.system.model.AccountAlias;
import com.deer.wms.base.system.model.AccountAliasCriteria;
import com.deer.wms.common.core.service.Service;

import java.util.List;


/**
 * Created by  on 2019/10/29.
 */
public interface AccountAliasService extends Service<AccountAlias, Integer> {
    List<AccountAlias> findList(AccountAliasCriteria criteria);
    AccountAlias findByDispositionId(Integer dispositionId);
}
