<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/taglibs.jsp"%>
<%@ include file="/include/meta.jsp"%>
<script type="text/javascript">
//动态添加行
function addRow(){
	var idNum;
	var row1;	  
	row1=$('.conditionDiv div:last');//获得最后一行 -- 设置jquery对象(待克隆的div)
	//如果row1为空
	if(row1.length==0){										
		idNum=1;				
		row1=$("#warnConditions"+(idNum-1));									
	}else{
		idNum= Number(row1.attr('id').substring(row1.attr('id').length-1)) + 1; 
	}
	var newRow=row1.clone(true);	//创建最后一行的一个副本
	// newRow.insertBefore(row1);	//在最后一行前插入
	newRow.insertAfter(row1);		//在最后一行后插入	  
	var rndID="warnConditions"+idNum;	  
	newRow.attr("id",rndID);		//设置行ID 每次都不一样 
	  
	//给各个 select input 加上不同的id
	newRow.find("a").attr("id",rndID);
	newRow.find("select.colNamesSelect").attr("id","colNamesSelect"+rndID);
	newRow.find("select.relationsSelect").attr("id","relationsSelect"+rndID);
	newRow.find("input.colLimit").attr("id","colLimit"+rndID);
	//初始值为空
	$("#colLimit"+rndID).val("");
	  
	//给新增的每一行内的删除加上删除事件
	newRow.find("a").click(function(){
		delRow(this);  
	});
	// 新加行显示删除按键
	newRow.find("a").html(" <span style='color: blue;'>删除</span>");
	//显示克隆出的新行数据
	newRow.show();
}	

//动态删除行
function delRow(who) {
	$("#" + who.id).remove();
}

function setSearchColumns(columns){
	var fields = columns;
	var html='';
	for ( var i = 1; i < fields[0].length;i++) {
		var field = fields[0][i];
		if(!('hidden' in field)) continue;
		if(field.hidden) continue;
		html+='<option value="'+field.field+'">'+field.title+'</option>';
	}
	$('#colNames').html(html);
}

function getSearchs(dt){
	var obj={};
	 var colNamesLists=$('select[name="colNames[]"]');
	 var colLimitLists=$('input[name="colLimit[]"]');
	 var relationsLists=$('select[name="relations[]"]');
	for(var i=0;i<colLimitLists.length;i++){
		var $colLimit=$(colLimitLists[i]);
		var colLimitVal=$colLimit.val();
		if(colLimitVal){
			var colNamesVal=$(colNamesLists[i]).val();
			var relationsVal=$(relationsLists[i]).val();
			if(relationsVal=='like'){
				colLimitVal='%'+colLimitVal+'%';
			}
			var field=dt+"."+colNamesVal;
			if(obj[field]){
				obj[field]+=字段查询(relationsVal,colLimitVal);
			}else{
				obj[field]=字段查询(relationsVal,colLimitVal);
			}
		}
	}
	return obj;
}

</script>

</head>
<body class="easyui-layout">  
<div data-options="region:'north',title:'常用类型',split:true" style="height:65px;padding:5px;">
日期:<input type="text" class="easyui-datebox"></input> 
时间:<input class="easyui-datetimebox" data-options="showSeconds:false">   
</div>
<div data-options="region:'center',title:'查询条件'" style="padding:5px;">
<div class="conditionDiv">
	<div id="warnConditions0" name="divName" >
		<select id="colNames" name="colNames[]" class="colNamesSelect" style="width:100px;"> 
		</select>
		<select id="relations" name="relations[]" class="relationsSelect" style="width:80px;margin:0px 0px 0px 10px;">
			<option value="= "> 等于 </option>
			<option value="like"> 类似 </option>
			<option value=">="> 大于等于 </option>
			<option value="<="> 小于等于 </option>
			<option value="> "> 大于 </option>
			<option value="< "> 小于 </option>
		</select>
		<input id="colLimit" name="colLimit[]" class="colLimit"  style="width:100px;margin:0px 0px 0px 20px;"/>
		<a href="#" id="delA" onclick="return false;" title="删除"  class="delRow_Link" ></a>
	</div>
</div>

<div>
<input type="button" value="添加条件" onclick="return addRow();">
</div>
</div>
</body>
</html>