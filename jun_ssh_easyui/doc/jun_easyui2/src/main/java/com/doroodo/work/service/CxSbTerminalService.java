package com.doroodo.work.service;

import java.util.List;

import com.doroodo.base.service.BaseService;
import com.doroodo.sys.model.*;
import com.doroodo.work.model.*;
public interface CxSbTerminalService extends BaseService<CxSbTerminal> {
	public int cmdControlTerminal(String id,String handle);
	public List getTerminalInfoByPl(String pl);
	public List<CxSbTerminal> listByPl(String pl);
}
