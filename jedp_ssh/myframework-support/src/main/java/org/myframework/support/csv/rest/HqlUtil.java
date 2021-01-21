package org.myframework.support.csv.rest;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.Assert;

/**
 * 
 * 功能说明:
 * @author Wujun
 */
public class HqlUtil {
	public final static String LOGIC_AND="and";	//逻辑
	public final static String LOGIC_OR="or";	//逻辑
	public final static String LOGIC_NOT="not";	//逻辑

	
	public final static String TYPE_NUMBER="Number";		//
	public final static String TYPE_IN="IN";
	public final static String TYPE_NOT_IN="NOT IN";		//
	public final static String TYPE_DATE="Date";			//日期类型
	public final static String TYPE_COLLECTION="Collection";//集合类型
	public final static String TYPE_STRING="String";		//字符串类
	public final static String TYPE_STRING_LIKE="Like";		//模糊查询的字符串类型
	public static final String TYPE_OBJECT = "Object";		//预编译对象类
	
	
	public final static String COMPARECHAR_LESS="<";			//小于
	public final static String COMPARECHAR_GREAT=">";			//大于
	public final static String COMPARECHAR_EQ="=";				//等于
	public final static String COMPARECHAR_LESS_EQ="<=";		//小于等于
	public final static String COMPARECHAR_GREAT_EQ=">=";		//大于等于	
	public final static String COMPARECHAR_NOT_EQ="<>";			//不
	

	
	/**
	 * 在原有的hql的基础之上，添加新的条件
	 * @param hql
	 * @param conditionMap
	 * @return
	 */
	public static String addCondition(String hql,Map<String,Object> conditionMap){
		return hql;
	}
	

	

	
	public static String addCondition(String hql,String fieldName,Object value,String logic,String type,String sCompareChar){
		StringBuffer sb=new StringBuffer();
		Pattern pWhere = Pattern.compile("where\\s*", Pattern.CASE_INSENSITIVE);
		Pattern pOrder = Pattern.compile("order\\s*by\\s*", Pattern.CASE_INSENSITIVE);
		Matcher mWhere = pWhere.matcher(hql);
		Matcher mOrder = pOrder.matcher(hql);
		
		boolean bOrder=mOrder.find();
		boolean bWhere=mWhere.find();
		String basePart=null;
		String orgWhereParts=null;
		String orderPart=bOrder?hql.substring(mOrder.end()):"";
		if(bWhere){
			basePart=hql.substring(0,mWhere.start()-1);
			orgWhereParts=bOrder?hql.substring(mWhere.end(),mOrder.start()-1):hql.substring(mWhere.end());
		}else{
			orgWhereParts="";
			basePart=bOrder?hql.substring(0,mOrder.start()-1):hql;
		}
		
		sb.append(basePart);
		sb.append(" where ");
		
		String newWhere=toStringInHsql(fieldName, value, type, sCompareChar);
		sb.append(newWhere);
		
		if(bWhere){
			sb.append(" "+logic);
			sb.append(" ("+orgWhereParts+")");	
		}
		if(bOrder){
			sb.append(" order by "+orderPart);
		}
		return sb.toString();
	}
	
	private static String toStringInHsql(String fieldName,Object value,String type,String compareChar){
		String sResult="";
		
		if(value!=null){
			if(type.equals(TYPE_NUMBER)){
				//1.日期型或者日期型		
				sResult="obj."+fieldName+compareChar+value;
			}
			if(type.equals(TYPE_DATE) || type.equals(TYPE_STRING)){
				sResult="obj."+fieldName+compareChar+"'"+value+"'";
			}
			if(type.equals(TYPE_STRING_LIKE)){
				//1.模糊查询		
				sResult="obj."+fieldName+" like '%"+value+"%'";
			}
			if(type.equals(TYPE_IN)){
				sResult="obj."+fieldName+" IN ("+value+")";
			}
			if(type.equals(TYPE_NOT_IN)){
				sResult="obj."+fieldName+" NOT IN ("+value+")";
			}
			
			if(type.equals(TYPE_OBJECT)){
				sResult="obj."+fieldName+""+compareChar+"?";
			}
		}else{
			sResult="obj."+fieldName+" is null";
		}
		return sResult;
	}
	
	public static String addCondition(String hql,String fieldName,Object value,String logic,String type){
		return addCondition(hql,fieldName,value,logic,type,HqlUtil.COMPARECHAR_EQ);
	}
	
	public static String addCondition(String hql,String fieldName,Object value,String logic){
		return addCondition(hql,fieldName,value,HqlUtil.LOGIC_AND,HqlUtil.TYPE_STRING);
	}
	
	
	public static String addCondition(String hql,String fieldName,Object value){
		return addCondition(hql,fieldName,value,HqlUtil.LOGIC_AND);
	}
	
	/**
	 * 添加排序方式
	 * @param hql
	 * @param orderField
	 * @param dir
	 * @return
	 */
	public static String addOrder(String hql,String orderField,String dir){
		StringBuffer sb=new StringBuffer(hql);
		Pattern p = Pattern.compile("order\\s*by", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		
		if(m.find()){
			sb.append(",");
		}else{
			sb.append(" order by ");
		}
		sb.append(orderField+" "+dir);
		return sb.toString();
	}
	
	/**
	 * 去除hql的orderby 子句，用于pagedQuery.
	 *
	 * @see #pagedQuery(String,int,int,Object[])
	 */
	public static String removeOrders(String hql) {
		Assert.hasText(hql);
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}
//	public static void main(String[] args) {
//	String h0="from P2G as p2g where a='a' order by aa desc";
//	String h2="from P2G as p2g where a='a'";
//	String h1="from P2G as p2g order by aa desc";
//	
//	String h3="from P2G as p2g";
////	hql=addOrder(hql,"aaaa","asc");hql=addOrder(hql,"aaaa","asc");
//	String h=addCondition(h1, "bbbbb", "aaaa");
//	String sss=addCondition(h, "ccc","bbbb");
//	String bbbb=addCondition(sss, "userName", "tanchang", LOGIC_OR, TYPE_STRING_LIKE,null);
//	String ccc=addCondition(bbbb, "birthday", "2007-1-1", LOGIC_AND, TYPE_DATE, COMPARECHAR_GREAT);
//	System.out.println(ccc);
//
//}
	
	/**
	 * 添加条件
	 */
	public static void addCondition(StringBuffer hql,String fieldName,Object value,String logic,String type,String sCompareChar){
		String condition=toStringInHsql(fieldName, value, type, sCompareChar);
		Pattern pWhere = Pattern.compile("where\\s*", Pattern.CASE_INSENSITIVE);
		Pattern pOrder = Pattern.compile("order\\s*by\\s*", Pattern.CASE_INSENSITIVE);
		Matcher mWhere = pWhere.matcher(hql);
		Matcher mOrder = pOrder.matcher(hql);
		boolean bOrder=mOrder.find();
		boolean bWhere=mWhere.find();

		if(bWhere){
			hql.insert(bOrder?mOrder.start()-1:hql.length(), ")");
			hql.insert(mWhere.end(),""+condition+" "+logic+" (");
		}else{
			if(bOrder){
				hql.insert(mOrder.start()-1, " where "+condition);
			}else{
				hql.append(" where "+condition);
			}
		}
	}
	/**
	 * 添加条件
	 * @param hql
	 * @param fieldName
	 * @param value
	 * @param logic
	 * @param type
	 */
	public static void addCondition(StringBuffer hql,String fieldName,Object value,String logic,String type){
		addCondition(hql, fieldName,value,logic,type,COMPARECHAR_EQ);
	}
	
	/**
	 * 添加条件
	 * @param hql
	 * @param fieldName
	 * @param value
	 * @param logic
	 */
	public static void addCondition(StringBuffer hql,String fieldName,Object value,String logic){
		addCondition(hql, fieldName,value,logic,TYPE_STRING,COMPARECHAR_EQ);
	}
	/**
	 * 添加条件
	 * @param hql
	 * @param fieldName
	 * @param value
	 */
	public static void addCondition(StringBuffer hql,String fieldName,Object value){
		addCondition(hql, fieldName,value,LOGIC_AND,TYPE_STRING,COMPARECHAR_EQ);
	}
	/**
	 * 添加条件 完整的where条件
	 * @param hql
	 * @param fieldName
	 * @param value
	 */
	public static void addWholeCondition(StringBuffer hql,String whereHql,String logic){
		String condition=whereHql;
		Pattern pWhere = Pattern.compile("where\\s*", Pattern.CASE_INSENSITIVE);
		Pattern pOrder = Pattern.compile("order\\s*by\\s*", Pattern.CASE_INSENSITIVE);
		Matcher mWhere = pWhere.matcher(hql);
		Matcher mOrder = pOrder.matcher(hql);
		boolean bOrder=mOrder.find();
		boolean bWhere=mWhere.find();

		if(bWhere){
			hql.insert(bOrder?mOrder.start()-1:hql.length(), ")");
			hql.insert(mWhere.end(),""+condition+" "+logic+" (");
		}else{
			if(bOrder){
				hql.insert(mOrder.start()-1, " where "+condition);
			}else{
				hql.append(" where "+condition);
			}
		}
	}
	/**
	 * 添加条件 完整的where条件
	 * @param hql
	 * @param fieldName
	 * @param value
	 */
	public static void addWholeCondition(StringBuffer hql,String whereHql){
		addWholeCondition(hql,whereHql,LOGIC_AND);
	}
	/**
	 * 添加排序
	 * @param hql
	 * @param orderField
	 * @param dir
	 */
	public static void addOrder(StringBuffer hql,String orderField,String dir,boolean isAppend){
		Pattern p = Pattern.compile("order\\s*by", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		boolean bOrder=m.find();
		if(isAppend){
			if(bOrder){
				hql.append(","+orderField+" "+dir);
			}else{
				hql.append(" order by "+orderField+" "+dir);
			}
		}else{
			if(bOrder){
				hql.insert(m.end(), " "+orderField+" "+dir+",");
			}else{
				hql.append(" order by "+orderField+" "+dir);
			}
		}
	}
	/**
	 * 添加排序
	 * @param hql
	 * @param orderField
	 * @param dir
	 */
	public static void addOrder(StringBuffer hql,String orderField,String dir){
		addOrder(hql, orderField, dir,true);
	}
//	public static void main(String[] args) {
//		StringBuffer sb=new StringBuffer();
//		sb.append("from ");
//		sb.append("P2G");
//		sb.append(" as obj");
//		sb.append(" where ");
//		sb.append("obj.1='x' and boj.x='y'");
//		sb.append(" order by ");
//		sb.append("obj.x desc");
//		addCondition(sb, "name","tanchang18");
//		
//		System.out.println(sb);
//	}
	public static void main(String[] args) {
		StringBuffer sb=new StringBuffer();
		System.out.println(addCondition("SELECT ORG_ID,AREA_CODE,CREATE_TIME,CREATOR,DOMAIN_ID,IS_ORG,IS_SYSTEM,LAST_MODIFIER,LAST_MODIFY_TIME,LEVEL_NO,ORG_NAME,ORG_TYPE,PARENT_ID,REMARK,ROOT_ID,SORT_NO,ENABLED,(SELECT decode(COUNT(1),0,1,0) from tbl_sys_org s where s.enabled=1 and s.parent_id=t.ORG_ID) as IS_LEAF FROM TBL_SYS_ORG t where 1=1", "bb",1,LOGIC_AND,TYPE_DATE));
	}
	
	
}
