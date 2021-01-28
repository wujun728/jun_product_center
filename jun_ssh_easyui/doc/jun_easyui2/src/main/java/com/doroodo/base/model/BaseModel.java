package com.doroodo.base.model;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.annotations.Table;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.springframework.beans.factory.annotation.Autowired;

import com.doroodo.base.dao.BaseDao;
import com.doroodo.base.service.GenericsUtils;
import com.doroodo.code.provider.db.table.TableFactory;

public class BaseModel {

	
	protected final String DATE_FORMAT = "yyyy-MM-dd";
    protected final String TIME_FORMAT = "HH:mm:ss";
    protected final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    protected final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.S";
	
	private List children=new ArrayList();
	private String state="open";
	private boolean checked=false;

	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public List getChildren() {
		return children;
	}
	public void setChildren(List children) {
		this.children = children;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	

}
