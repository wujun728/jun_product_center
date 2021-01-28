<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 数据字典select -->
						<input name="warehouseid" class="easyui-combobox"  style="width:150px;"
						data-options="required:true,valueField:'code',textField:'name',editable:false,panelHeight:'auto',
							enableNull:false,codeClass:'57'">
<!-- datebox -->
					<td><label>护理日期</label></td>
					<td>
						<input name="nursingdate" class="easyui-datebox" data-options="editable:false">
					</td>






<!-- ------------------------------------------------------------------------------------------ -->
<!-- 选择老人 -->


<!-- 选择老人 数据表单 -->

<!-- ------------------------------------------------ -->

			<td><lable>老人姓名</lable></td>
				<td>
					<input id="peopleid" name="peopleName.id" style="display:none">
 					<input id="peoplename" name="peoplename" readonly="ture" style="width:125px;">
					<input type="button" value="选择" style="width:35px;margin-left:-35px" onClick="getPeople('add')">
				</td>
<!-- 选择老人  search-->
<!-- ------------------------------------------------ -->
			<label>老人姓名</label>
			<input id="peopleid-search" name="peopleName.id" style="display:none">
 			<input id="peoplename-search" name="peoplename" readonly="ture" style="width:125px;">
			<input type="button" value="选择" style="width:35px;margin-left:-35px" onClick="getPeople('query')">
			<span class="inline-clear"></span>
					
<!-- ------------------------------------------------------------------------------------------ -->
<c:import url="/pages/common/_select-people.jsp"></c:import>





<!-- ------------------------------------------------------------------------------------------ -->
<!-- 选择员工  -->


<!-- 选择员工 数据表单 -->
		<input id="employeeid" name="employeeid" style="display:none">
		<input id="employeename" name="employeename" style="width:150px;">
		<input type="button" value="选择" style="width:35px;margin-left:-35px" onClick="getEmployee('add')">
		
<!-- 选择员工 search -->
		<input id="employeeid-search" name="employeeid" style="display:none">
		<input id="employeename-search" name="employeename" style="width:150px;">
		<input type="button" value="选择" style="width:35px;margin-left:-35px" onClick="getEmployee('query')">

<!-- ------------------------------------------------------------------------------------------ -->
<c:import url="/pages/common/_select-employee.jsp"></c:import>




