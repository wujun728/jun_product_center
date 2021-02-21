package com.deer.wms.base.system.dao.ware;

import com.deer.wms.common.core.commonMapper.Mapper;
import com.deer.wms.base.system.model.ware.Supplier;

import java.util.List;

/**
 * 供应商 数据层
 * 
 * @author guo
 * @date 2019-06-16
 */
public interface SupplierMapper  extends Mapper<Supplier>
{
	/**
     * 查询供应商信息
     * 
     * @param supplierId 供应商ID
     * @return 供应商信息
     */
	public Supplier selectSupplierById(Long supplierId);
	
	/**
     * 查询供应商列表
     * 
     * @param supplier 供应商信息
     * @return 供应商集合
     */
	public List<Supplier> selectSupplierList(Supplier supplier);
	
	/**
     * 新增供应商
     * 
     * @param supplier 供应商信息
     * @return 结果
     */
	public int insertSupplier(Supplier supplier);
	
	/**
     * 修改供应商
     * 
     * @param supplier 供应商信息
     * @return 结果
     */
	public int updateSupplier(Supplier supplier);
	
	/**
     * 删除供应商
     * 
     * @param supplierId 供应商ID
     * @return 结果
     */
	public int deleteSupplierById(Long supplierId);
	
	/**
     * 批量删除供应商
     * 
     * @param supplierIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteSupplierByIds(String[] supplierIds);

	Supplier findBySupplierCode(String supplierCode);
	
}