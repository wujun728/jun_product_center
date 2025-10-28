package com.erp.service;

import java.util.List;

import com.erp.dto.TreeModel;
import com.erp.model.Organization;

public interface OrganizationService 
{
	List<TreeModel> findOrganizationList();

	List<Organization> findOrganizationList(Integer id );

	boolean persistenceOrganization(Organization o );

	boolean delOrganization(Integer id );
}
