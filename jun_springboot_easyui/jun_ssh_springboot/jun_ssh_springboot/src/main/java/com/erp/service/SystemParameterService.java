package com.erp.service;

import java.util.List;
import java.util.Map;

import com.erp.dto.ParameterModel;
import com.erp.model.Parameter;

public interface SystemParameterService
{

	List<ParameterModel> findParameterList(String type );

	boolean persistenceParameter(Map<String, List<Parameter>> map );

}
