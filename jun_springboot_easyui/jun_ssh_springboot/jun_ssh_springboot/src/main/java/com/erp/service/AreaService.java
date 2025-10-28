package com.erp.service;

import java.util.List;

import com.erp.dto.TreeModel;
import com.erp.model.City;
import com.erp.model.Province;

public interface AreaService
{

	List<TreeModel> findCities();

	List<Province> findProvinces();

	boolean addCities(City city );

}
