package com.doroodo.work.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.doroodo.base.model.DataGrid;
import com.doroodo.base.service.impl.BaseServiceImpl;

import org.springframework.stereotype.Service;

import com.doroodo.base.service.impl.BaseServiceImpl;
import com.doroodo.code.provider.db.table.TableFactory;
import com.doroodo.code.provider.db.table.model.Column;
import com.doroodo.code.provider.db.table.model.Table;
import com.doroodo.code.util.StringHelper;
import com.doroodo.sys.model.*;
import com.doroodo.sys.service.*;
import com.doroodo.work.model.*;
import com.doroodo.work.service.*;
@Service("vcxSbTakeStateService")
public class VcxSbTakeStateServiceImpl extends BaseServiceImpl<VcxSbTakeState>  implements VcxSbTakeStateService {
	public DataGrid dataGridk(int page, int rows, String searchName,
			String searchKey) {
		searchName = searchName.trim();
		searchKey = searchKey.trim();
		DataGrid dg = new DataGrid();
		String hql = "from VcxSbTakeState t ";
		Map<String, Object> params = new HashMap<String, Object>();
		hql += "where t." + searchName + "=:key";
		params.put("key",  Integer.parseInt(searchKey));
		String totalHql = "select count(*) " + hql;
		// hql+=" order by t.logDate desc";
		List<VcxSbTakeState> l = null;
		try {
			if (rows == 0 && page == 0) {
				l = dao.find(hql, params);
			} else {
				l = dao.find(hql, params, page, rows);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			l = convert(l);
		} catch (Exception e) {
			e.printStackTrace();
		}

		dg.setTotal(dao.count(totalHql, params));
		dg.setRows(l);
		dg.setModelName(StringHelper.uncapitalize("VcxSbTakeState"));
		return dg;
	}
}