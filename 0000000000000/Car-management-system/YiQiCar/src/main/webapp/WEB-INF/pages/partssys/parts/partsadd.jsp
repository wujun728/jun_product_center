<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<link href="${pageContext.request.contextPath }/css/main.css" rel="stylesheet" type="text/css" media="all" />
	<script src="${pageContext.request.contextPath }/js/jquery-1.4.2.min.js"
	type="text/javascript"></script>

</head>

<body class="content-pages-body">
<script color="255,0,0" pointColor="255,0,0" opacity='0.7' zIndex="-2" count="200" src="${pageContext.request.contextPath }/js/canvas-nest.min.js" type="text/javascript"></script>

<div class="content-pages-wrap" style="opacity:0.7">
    <div class="commonTitle">
        <h2>&gt;&gt; 新增配件</h2>
  </div>
        <form id="coursesCreat" name="coursesCreat" action="insertParts" method="post">     
		  <table border="0" cellspacing="1" cellpadding="0" class="commonTable">
			  <tr>
				<td width="10%" align="right" class="title"><span class="required">*</span>配件名称：</td>
				<td width="15%" align="left"><input type="text" name="partsname" style="height:20px"></td>
				<td width="10%" align="right" class="title"><span class="required">*</span>规格型号：</td>
				<td width="15%" align="left"><input type="text" name="partsmodel" style="height:20px"></td>
			  </tr>
			  <tr>
				<td width="10%" align="right" class="title"><span class="required">*</span>生产厂家：</td>
				<td width="15%" align="left"><input type="text" name="partsloc" style="height:20px"></td>
				<td width="10%" align="right" class="title"><span class="required">*</span>生产日期：</td>
				<td width="15%" align="left"><input type="text" name="partsprodate" style="height:20px"></td>
			  </tr>			  
			  <tr>	
				<td width="10%" align="right" class="title"><span class="required">*</span>备注：</td>
				<td width="15%" align="left" colspan="3"><textarea name="partsremark" style="width:720px;height:100px"></textarea></td>
			  </tr>
		 </table>
		 
		 <!-- <tr>
		 	<td><li><input type="submit" value="保存" class="btnShort" id="save">保存</a></li>
        	<li><a href="javascript:window.history.go(-1)" title="返回" class="btnShort">返回</a></li></td>
		 </tr> -->
		 
		</form>
	 </div>
    <div id="formPageButton">
    	<ul>
			<li><a href="#" title="保存" class="btnShort" id="save" onclick="document:coursesCreat.submit()">保存</a></li>
        	<li><a href="javascript:window.history.go(-1)" title="返回" class="btnShort">返回</a></li>
        </ul>
    </div>
  
</div>

</body>
</html>
