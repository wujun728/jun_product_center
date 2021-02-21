package com.deer.wms.base.system.service.impl;

import com.deer.wms.base.system.dao.AccountAliasMapper;
import com.deer.wms.base.system.model.AccountAlias;
import com.deer.wms.base.system.model.AccountAliasCriteria;
import com.deer.wms.base.system.service.AccountAliasService;


import com.deer.wms.common.core.service.AbstractService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by  on 2019/10/29.
 */
@Service
public class AccountAliasServiceImpl extends AbstractService<AccountAlias, Integer> implements AccountAliasService {

    @Autowired
    private AccountAliasMapper accountAliasMapper;

    @Override
    public List<AccountAlias> findList(AccountAliasCriteria criteria){
        return accountAliasMapper.findList(criteria);
    }

    @Override
    public AccountAlias findByDispositionId(@Param("dispositionId") Integer dispositionId){
        return accountAliasMapper.findByDispositionId(dispositionId);
    }
}
