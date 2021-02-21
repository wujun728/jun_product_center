package com.deer.wms.base.system.service.ware.impl;

import java.util.List;

import com.deer.wms.common.core.service.AbstractService;
import com.deer.wms.base.system.model.ware.Supplier;
import com.deer.wms.base.system.dao.ware.SupplierMapper;
import com.deer.wms.base.system.service.ware.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.deer.wms.common.core.text.Convert;

/**
 * 供应商 服务层实现
 * 
 * @author guo
 * @date 2019-06-16
 */
@Service
public class SupplierServiceImpl extends AbstractService<Supplier, String> implements ISupplierService
{
	@Autowired
	private SupplierMapper supplierMapper;

	/**
     * 查询供应商信息
     * 
     * @param supplierId 供应商ID
     * @return 供应商信息
     */
    @Override
	public Supplier selectSupplierById(Long supplierId)
	{
	    return supplierMapper.selectSupplierById(supplierId);
	}
	
	/**
     * 查询供应商列表
     * 
     * @param supplier 供应商信息
     * @return 供应商集合
     */
	@Override
	public List<Supplier> selectSupplierList(Supplier supplier)
	{
	    return supplierMapper.selectSupplierList(supplier);
	}
	
    /**
     * 新增供应商
     * 
     * @param supplier 供应商信息
     * @return 结果
     */
	@Override
	public int insertSupplier(Supplier supplier)
	{
	    return supplierMapper.insertSupplier(supplier);
	}
	
	/**
     * 修改供应商
     * 
     * @param supplier 供应商信息
     * @return 结果
     */
	@Override
	public int updateSupplier(Supplier supplier)
	{
	    return supplierMapper.updateSupplier(supplier);
	}

	/**
     * 删除供应商对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSupplierByIds(String ids)
	{
		return supplierMapper.deleteSupplierByIds(Convert.toStrArray(ids));
	}

	public Supplier findBySupplierCode(String supplierCode){
		return supplierMapper.findBySupplierCode(supplierCode);
	}
}
