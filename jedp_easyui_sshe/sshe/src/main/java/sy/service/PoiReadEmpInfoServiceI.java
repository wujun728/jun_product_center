package sy.service;

import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import sy.model.app.CustDept;
import sy.model.app.CustUser;
import sy.model.app.CustomerInfo;
import sy.model.app.DrugSpecInfo;
import sy.model.app.ImpCustUserData;
import sy.model.app.MyDrugSpecInfo;
import sy.service.BaseServiceI;
import sy.util.base.HqlFilter;

/**
 * 药品信息部门业务逻辑
 * 
 */
public interface PoiReadEmpInfoServiceI {

	void saveCustomerInfo(CustomerInfo customerInfo);

	void saveCustDept(CustDept custDept);

	void saveCustUser(CustUser custUser);

	CustomerInfo createCustomerInfo(String customerName);

	CustDept createCustDept(String deptName);

	CustUser createCustUser(String userCode);

	public int handleForeachReadExcel(int customerId, String path)
			throws InvalidFormatException, IOException;
	
	public void handleForeachReadExcel(int customerId, String path,
			ImpCustUserData impCustUserData) throws InvalidFormatException,
			IOException;
}
