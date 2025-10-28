package com.jun.plugin.sys.dict;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.jun.plugin.common.utils.CollectionTools;
import com.jun.plugin.sys.dict.dao.CodeClassDAO;
import com.jun.plugin.sys.dict.dao.CodeItemDAO;
import com.jun.plugin.sys.dict.entity.CodeClass;
import com.jun.plugin.sys.dict.entity.CodeItem;

/**
 * 字典读取器
 * 
 * @author klguang[klguang@foxmail.com]
 * @date 2018年9月28日
 */
@Component
public class DictReader {
	private static final Logger logger = LoggerFactory.getLogger(DictReader.class);
	private static CodeItemDAO itemDao;
	private static CodeClassDAO classDao;
	//mapping classcode => itemcode => codeItem
	static Map<String, ImmutableMap<String, CodeItem>> classItemsMap = new HashMap<>();
	
	//mapping classcode => codeClass
	static Map<String,CodeClass> classMap = new HashMap<>();
	
	
	/**
	 * 静态变量注入
	 * @param itemDao
	 * @param classDao
	 */
	@Autowired	
	public DictReader(CodeItemDAO itemDao, CodeClassDAO classDao) {
		DictReader.itemDao=itemDao;
		DictReader.classDao=classDao;
	}

	public void rebuild(){

		classMap.clear();
		classMap = buildClassMap();
		
		
		classItemsMap.clear();
		
		Map<String, List> classfiedItems = classifyAllItems();
		
		// 将codeItem的code字段映射为map的key
		for (Map.Entry<String, List> entry : classfiedItems.entrySet()) {
			@SuppressWarnings("unchecked")
			ImmutableMap<String, CodeItem> temp=buildChildItemMap(entry.getValue());	
			classItemsMap.put(entry.getKey(), temp);
		}
		logger.info("dict reader rebuild success");
	}
	
	/**
	 * ImmutableMap,保留构建顺序<br>
	 * 不可变的常量集合Immutable有如下优点<br>
	 * 1.对不可靠的客户代码库来说，它使用安全，可以在未受信任的类库中安全的使用这些对象<br>
	 * 2.线程安全的：immutable对象在多线程下安全，没有竞态条件<br>
	 * 3.不需要支持可变性, 可以尽量节省空间和时间的开销. 所有的不可变集合实现都比可变集合更加有效的利用内存 (analysis)<br>
	 * 4.可以被使用为一个常量，并且期望在未来也是保持不变的<br>
	 * 
	 */
	private static ImmutableMap<String, CodeItem> buildChildItemMap(List<CodeItem> codeItemList) {
		return Maps.uniqueIndex(codeItemList, new Function<CodeItem, String>() {
			@Override
			public String apply(CodeItem arg0) {
				return arg0.getCode();
			}
		});
	}
	
	
	public static Map<String,CodeClass> buildClassMap(){
		List<CodeClass> codeClassList=classDao.findAll();
		Map<String,CodeClass> map = new HashMap<>();
		for(CodeClass codeClass:codeClassList){
			map.put(codeClass.getCode(), codeClass);
		}
		return map;
	}
	
	@SuppressWarnings("rawtypes")
	public static Map<String, List> classifyAllItems(){
		Sort sort=new Sort(new Order(Direction.ASC,"classCode"),
	   			new Order(Direction.DESC,"orderNum"));
		List<CodeItem> codeItemList = itemDao.findAll(sort);
		
		
		Map<String, List> tempMap = null;
		try {
			// 根据classCode分类
			tempMap = CollectionTools
					.classify(false, codeItemList, "classCode");
		} catch (IllegalAccessException | InvocationTargetException
				| NoSuchMethodException e) {
			e.printStackTrace();
		}
		return tempMap;
	}
	


	/**
	 * 不可变map
	 * 
	 * @param classCode
	 * @return
	 */
	public static ImmutableMap<String, CodeItem> getChildItemsMap(String classCode) {
		return classItemsMap.get(classCode);
	}

	public static CodeClass getCodeClass(String classCode) {
		return classMap.get(classCode);
	}

	public static String getCodeClassName(String classCode) {
		CodeClass codeClass = getCodeClass(classCode);
		if(classCode != null)
			return codeClass.getName();
		else
			return null;
	}

	public static CodeItem getCodeItem(String code, String classCode) {
		return getChildItemsMap(classCode).get(code);
	}

	public static String getCodeItemName(String code, String classCode) {
		CodeItem codeItem = getCodeItem(code, classCode);
		if (codeItem == null)
			return null;
		return codeItem.getName();
	}
}

