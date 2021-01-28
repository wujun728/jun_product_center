<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>form-validation</title>
<c:import url="/pages/common/head.jsp"/> 	
</head>
<body>
	<form class="data-form">
		<input name="id" style="display: none" value="0">  
			<input name="classCode" type="hidden"> 
			<div class="fitem">
				<label style="width: 200px">easyui-numberbox</label>  
                <label>数值框</label>  
                <input type="text" class="easyui-numberbox" value="100" data-options="min:0,precision:2"></input>
            </div>
			  
			<div class="fitem">
				<label style="width: 200px">normal</label>  
                <label>用户名校验</label>  
                <input name="param2" class="easyui-validatebox" validType="normal" required="true">
            </div>   
            <div class="fitem">
            	<label style="width: 200px">notCHS</label>
                <label>不能输入汉字</label>  
                <input id="dmitemid" name="code" class="easyui-validatebox" validType="notCHS">  
            </div>  
            <div class="fitem">
            	<label style="width: 200px">word</label>   
                <label>字母、数字、下划线</label>  
                <input name="name" class="easyui-validatebox" validType="word">
            </div>  
            <div class="fitem">
            	<label style="width: 200px">easyui-numberspinner</label> 
                <label>排序:</label>  
                <input name="ordernum" value='0' class="easyui-numberspinner" data-options="min:0,max:100,editable:true" />
            </div> 
            <div class="fitem"> 
            	<label style="width: 200px">intOrFloat</label>   
                <label>数值:</label>  
                <input name="saleprice"  class="easyui-validatebox" type="text" data-options="required:true,validType:'intOrFloat'" style="width:50px;" >
            </div> 
            <div class="fitem"> 
            	<label style="width: 200px">money</label>   
                <label>正数，小数点两位</label>  
                <input name="saleprice"  class="easyui-validatebox" type="text" data-options="required:true,validType:'money'" style="width:50px;" >
            </div>
            <div class="fitem">
            	<label style="width: 200px">positiveInteger</label>
                <label>正整数:</label>  
                <input name="saleprice"  class="easyui-validatebox" type="text" data-options="required:true,validType:'positiveInteger'" style="width:50px;" >
            </div>
            <div class="fitem">
             	<label style="width: 200px">rateCheck[3,5]:</label>   
                <label>大小限制:</label>  
                <input name="saleprice"  class="easyui-validatebox" type="text" data-options="required:true,validType:'rateCheck[3,5]'" style="width:100px;" >
            </div>
            <div class="fitem"> 
            	<label style="width: 200px">length[3,5]</label>
                <label>长度限制:</label>  
                <input name="saleprice"  class="easyui-validatebox" type="text" data-options="required:true,validType:'length[3,5]'" style="width:100px;" >
            </div>
            <div class="fitem"> 
            	<label style="width: 200px">same[\'same1\',\'same2\',\'密码\']'</label>
                <label>相同校验:</label>  
                <input id="same1" name="saleprice"  class="easyui-validatebox" type="text" data-options="required:true,validType:'same[\'same1\',\'same2\',\'密码\']'" style="width:100px;" >
                <input id="same2" name="saleprice"  class="easyui-validatebox" type="text" data-options="required:true,validType:'same[\'same1\',\'same2\',\'密码\']'" style="width:100px;" >
                
            </div>
            
            <div class="fitem">
	            <label style="width: 200px">datebox</label>
	            <input class="easyui-datebox" name="saledate" value="2016-09-09"/>
            </div>
            <div class="fitem">
	           <label style="width: 200px">datetimebox</label>
			   <input class="easyui-datetimebox" name="saledate" value="2016-09-09 24:12:12" required="true"/>
            </div>
	</form>
</body>
</html>