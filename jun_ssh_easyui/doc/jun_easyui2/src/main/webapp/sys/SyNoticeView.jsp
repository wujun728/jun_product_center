<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
#thetext{
    background: none repeat scroll 0% 0% #FFF;
    border: 1px solid #CCC;
    padding: 15px 50px 10px;
    box-shadow: 0px 1px 3px rgba(198, 198, 198, 0.5);
}
.stamper{padding-top:10px;height:100px;}
.stamper span{float:right;display:inline-block;height:100%;width:200px;}
</style>
<%@ include file="/include/taglibs.jsp"%>
<%@ include file="/include/meta.jsp"%>
<script type="text/javascript" src="${ctx}/js/jquery/jquery.stamper.js"></script>
<script>
var g_fileid="";
var g_viewModal=true;
var titleid="";
function setContent(thetext,fileid,titleid_){
	$('#thetext').html('<div class="stamper"><span></span></div>'+ttoh(thetext));
	g_fileid=fileid;
	$('#file').panel({
		closed:false,
		width:$(self).width()*0.956,
		height:200
	}).panel('refresh','../component/empty.jsp');
	$('#file').panel({
		closed:false,
		width:$(self).width()*0.956,
		height:200
	}).panel('refresh','../component/SyFile_.jsp');
	titleid=titleid_;
	reshReadusernames();
}

function reshReadusernames(){
	if(titleid=="")return;
	var setData={'id':titleid};
	data(getUrl('syNotice','Get_Readusernames'),setData,'json',updateReadusernames);
}

function updateReadusernames(d){
	$('#readusernames').html(d.readusernames);
}

function stamp() {
	$(".stamper span").stamper({
		image : "../images/notice/yy.png",
		scale : 3,
		speed : 300,
		complete : function() {
		}
	});
}

$(function(){
	$('body').layout('collapse','south');  
});
</script>
</head>
<body class="easyui-layout"  data-options="fit:true">
	<div id="thetext" data-options="region:'center'"></div>
	<div data-options="region:'south',title:'附属信息'" style="height:210px">
		<div  class="easyui-layout"  data-options="fit:true">
		<div id="file" data-options="region:'center',title:'附件'"></div>
		<div id="readusernames" data-options="region:'south',title:'已阅'" style="height:50px"></div>
		</div>
	</div>
</body>
</html>
