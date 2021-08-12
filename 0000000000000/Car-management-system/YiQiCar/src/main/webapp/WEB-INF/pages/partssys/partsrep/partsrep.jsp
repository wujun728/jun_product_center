<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>物资采购与产品整合管理系统</title>
<link href="${pageContext.request.contextPath }/css/main.css" rel="stylesheet" type="text/css" media="all" />
<script src="${pageContext.request.contextPath }/js/jquery-1.4.2.min.js"
	type="text/javascript"></script>
<script>
$(function(){
	
	$("#select").blur(function(){
		$("#select1").empty();
		var inout = document.getElementById("select").value;
		$.getJSON("getJsons?inout="+inout,{},function(data){
			for(var i = 0; i < data.length; i++){
				$("#select1").append($("<option></option>").text(data[i].name).val(data[i].code))
			}
		}); 
	})	
	
	$.getJSON("getJsons1",{},function(data){
		
		for(var i = 0; i < data.length; i++){
			$("#select2").append($("<option name='partsid'></option>").text(data[i].partsname).val(data[i].partsid))
		}
	});
	
	$.getJSON("getJsons2",{},function(data){
		for(var i = 0; i < data.length; i++){
			$("#select3").append($("<option name='id'></option>").text(data[i].name).val(data[i].id))
		}
	});
	
});
</script>

</head>

<body class="content-pages-body">
<script color="255,0,0" pointColor="255,0,0" opacity='0.7' zIndex="-2" count="200" src="${pageContext.request.contextPath }/js/canvas-nest.min.js" type="text/javascript"></script>

<div class="content-pages-wrap" style="opacity:0.7">
    <div class="commonTitle">
        <h2>&gt;&gt; 配件出入库</h2>
  </div>
        <form id="coursesCreat" name="coursesCreat" action="addBill" method="post">
		  <table border="0" cellspacing="1" cellpadding="0" height="70" class="commonTable">
			  <tr>
				<td width="10%" align="right" class="title"><span class="required">*</span>类型：</td>
				<td width="15%" align="left">
					<select style="width:150px;" id="select" name="inout">
						<option value="">请选择</option>
						<option value="out" name="inout">出库</option>
						<option value="in" name="inout">入库</option>
					</select>
				</td>
				<td width="10%" align="right" class="title"><span class="required">*</span>出/入库类型：</td>
				<td width="15%" align="left">
					<select style="width:150px;" id="select1" name="billtype">
						<option>请选择</option>
					</select>
				</td>
				<td width="10%" align="right" class="title"><span class="required">*</span>出/入库日期：</td>
				<td width="15%" align="left"><input type="hidden" name="billtime"></td>
				<td width="10%" align="right" class="title"><span class="required">*</span>操作员：</td>
				<td width="15%" align="left">
					<select style="width:150px;" id="select3" name="billuser">
						<option>请选择</option>
					</select>
				</td>
			  </tr>
			  <tr>
				<td width="10%" align="right" class="title"><span class="required">*</span>配件：</td>
				<td width="15%" align="left">
					<select style="width:150px;" id="select2" name="partsid">
						
					</select>
				</td>
				<td width="10%" align="right" class="title"><span class="required">*</span>出/入库数量：</td>
				<td width="15%" align="left">
					<input type="text" name="billcount" style="width:150px;height:20px">
				</td>
				<td width="10%" align="right" class="title"><span class="required">*</span>说明：</td>
				<td width="15%" align="left" colspan="3"><input type="text" name="descript" style="width:470px;height:20px"></td>
			  </tr>
		 </table>
		 
		
		</form>
	 </div>
    <!--//commonTable-->
	<div id="formPageButton">
    	<ul>
			<li><a href="#" title="保存" class="btnShort" onclick="document:coursesCreat.submit()">保存</a></li>
        	<li><a href="javascript:window.history.go(-1)" title="返回" class="btnShort">返回</a></li>
        </ul>
    </div>
    <!--//commonToolBar-->
</div>
<!--//content pages wrap-->
</body>
</html>
