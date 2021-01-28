package org.myframework.support.security;

import java.util.Set;

public interface IUser {
	public String getUserCode();

	public void setUserCode(String userCode);

	public String getUserName();

	public void setUserName(String userName);

	public String getUserNo();

	public void setUserNo(String userNo);

	public String getUserType();

	public void setUserType(String userType);

	public String getDeptId();

	public void setDeptId(String deptId) ;

	public String getPassword() ;

	public void setPassword(String password);

	public String getPwdValidDay();

	public void setPwdValidDay(String pwdValidDay);

	public String getBirthday();

	public void setBirthday(String birthday);

	public String getSex();

	public void setSex(String sex);
	
	public String getEducation();

	public void setEducation(String education);

	public String getTelephone();

	public void setTelephone(String telephone);

	public String getOfficephone();

	public void setOfficephone(String officephone);

	public String getMobile();

	public void setMobile(String mobile);

	public String getEmail();

	public void setEmail(String email);

	public String getAddress();

	public void setAddress(String address);
	
	public String getPostCode();

	public void setPostCode(String postCode);

	public String getContactName();

	public void setContactName(String contactName);

	public String getContactPhone();

	public void setContactPhone(String contactPhone);
	
	public String getPhoto();

	public void setPhoto(String photo);

	public String getCervificateType();

	public void setCervificateType(String cervificateType);

	public String getCervificateNo();

	public void setCervificateNo(String cervificateNo);
	
	public String getHireDate();

	public void setHireDate(String hireDate);

	public Boolean getIsEdit();

	public void setIsEdit(Boolean isEdit);

	public String getExtendField1();

	public void setExtendField1(String extendField1);


	public Boolean getEnabled();

	public void setEnabled(Boolean enabled);

	/**
	 * 得到当前用户登陆账号全名（userCode@OrgId）
	 * @return
	 */
	public String getLoginAccount();

	public String getId();

	public void setId(String id);

	/**
	 * 通过用户的角色状态得到过滤后的角色集合。
	 * @param clazz 结果集封装类型[SysRole.class 或 String.Class]
	 * @param enabled 角色状态值
	 * @return
	 */
	public Set getRolesByEnabled(Class clazz,Boolean enabled);

	public String getOrgId();

	public void setOrgId(String orgId);

	public String getVaildcode();

	public void setVaildcode(String vaildcode);

	public Object getRolesLabel();

	public void setRolesLabel(Object rolesLabel);

	public String getDeptName();

	public void setDeptName(String deptName);

	public String getOrgName();

	public void setOrgName(String orgName);

	public Boolean getIsLock();

	public void setIsLock(Boolean isLock);
	
	public Boolean getIsLogin();

	public void setIsLogin(Boolean isLogin);

	public Boolean getIsSign();

	public void setIsSign(Boolean isSign);
	
	public Object getGroupsLabel();

	public void setGroupsLabel(Object groupsLabel);
	
	public Object getSkillName();

	public void setSkillName(Object skillName);
}
