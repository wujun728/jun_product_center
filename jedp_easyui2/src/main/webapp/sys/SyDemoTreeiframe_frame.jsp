<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/taglibs.jsp"%>
<%@ include file="/include/meta.jsp"%>
<script>
		var sy_tabs;
		var sy_tree,UploadDg,UploadFm;
	$(function() {
		sy_tabs = $("#sy_tabs");
		UploadDg = $('#i_sy_syDemoTreeiframe_datagrid_upload_dialog');
		UploadFm =$('#i_sy_syDemoTreeiframe_datagrid_upload_dialog_form');
		UploadFm.attr('action',"${ctx}"+actionUrl('/sys/','syDemoTreeiframe','Upload'));
		//加载树
		sy_tree=$("#sy_tree").tree({
			animate:true,
			url:'${ctx}'+actionUrl('/sys/','syDemoTreeiframe','Get_Tree'),
			onClick : function(n) {
				var sy_ep_currnode = sy_tree.tree('getSelected');
				var subtitle = sy_ep_currnode.text;
				var id = sy_ep_currnode.id;
				var sy_ep_tabs_url = "${ctx}/sys/SyDemoTreeiframe.jsp?id="+id+"&topthemeName="+top.themeName;
				//tabs
				if (!sy_tabs.tabs('exists', subtitle)) {
					sy_tabs.tabs('addIframeTab', {
						tab : {
							title : subtitle,
							closable : true,
							tools : [ {
								iconCls : 'icon-mini-refresh',
								handler : function() {
									sy_tabs.tabs('updateIframeTab', {
										'which' : subtitle
									});
									sy_tabs.tabs('select', subtitle);
								}
							} ]
						},
						iframe : {
							src : sy_ep_tabs_url
						}
					});
					sy_tabs.tabs('addEventParam');
					return false;
				} else {
					sy_tabs.tabs('updateIframeTab', {
						'which' : subtitle
					});
					sy_tabs.tabs('select', subtitle);
					sy_tabs.tabs('addEventParam');
					return false;
				}
				//tabs
			}
		});
		sy_tabs.tabs('addIframeTab', {
			tab : {
				title : '新建',
				closable : false
			},
			iframe : {
				src : '${ctx}/sys/SyDemoTreeiframe.jsp?pview=add'+"&topthemeName="+top.themeName
			}
		});
		sy_tabs.tabs('addEventParam');
		$("#distance").tabs({
			onSelect:function(title,index){
				if(title=="说明"){
					getThext();
				}
			}
		});
	});
	
	function upLoadFun(){
		UploadDg.dialog('open');
	}

	function submitUploadForm(){
		UploadFm.form('submit',{
			success:function(d){
				sy_tree.tree('reload');
				UploadDg.dialog('close');
				d=$.parseJSON(d);
				log(d.info);
				}
		});
	}
	
	function reloadTree(){
		sy_tree.tree('reload',sy_tree.target);
	}
	
	function getThext(){
		 var setData = {};
		 setData.name = "左树右表格联动模版";//需要修改
		data(getUrl('syWebmodal','Get_ByName'),setData,'json',updateFun);
	} 

	function updateFun(d){
		$('#cc').html(d.info);
		 //datetreedue('文档说明','/work/datetree_explain.jsp',d.content);
	}
</script>
	</head>
	<body>
	<div id="distance" class="easyui-tabs" data-options="tabPosition:'left'" headerWidth="50" fit="true" > 
	<div title="演示" >
	<!-- 演示开始 -->
	<div class="easyui-layout" fit="true">
		<div id="module_toolbar">
	  <a href="#" class="icon-page_white_excel" onclick="javascript:upLoadFun()"></a>  
	</div>
		<!-- 树 -->
		<div data-options="region:'west',split:true,tools:'#module_toolbar'" title="树"
			style="width: 250px;">
			<ul class="easyui-tree" id="sy_tree" style="padding: 10px 5px;">
			</ul>
		</div>
		<!-- tabs -->
		<div data-options="region:'center'" style="overflow: hidden;">
			<div class="easyui-tabs" fit="true" border="false" id="sy_tabs">
			</div>
		</div>
		<div id="i_sy_syDemoTreeiframe_datagrid_upload_dialog" class="easyui-dialog" title="上传" style="width:400px;height:100px;"  
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true"> 
          <form id="i_sy_syDemoTreeiframe_datagrid_upload_dialog_form" action="" enctype="multipart/form-data" method="post" >
    <input type="text" name="fileid"  style="display:none;"/>上传文件：<input type="file" name="fileGroup"><a href="${ctx}/excelmodel/组织表.xls" target="_blank">导入模版下载</a></br><span style="color:red">注：由于服务器的空间有限，上传文件大小不能超过1G</span></br>
        	<input type="button" value="上传" onClick="submitUploadForm();" />
        	</form>
</div>  
</div>
<!-- 演示结束 -->
</div>
<div id="cc" title="说明"" style="overflow:auto;padding:20px;">  
</div>
</div>
	</body>
</html>