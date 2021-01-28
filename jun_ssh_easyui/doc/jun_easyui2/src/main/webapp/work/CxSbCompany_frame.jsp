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
		UploadDg = $('#i_sy_cxSbCompany_datagrid_upload_dialog');
		UploadFm =$('#i_sy_cxSbCompany_datagrid_upload_dialog_form');
		UploadFm.attr('action',"${ctx}"+actionUrl('/sys/','cxSbCompany','Upload'));
		//加载树
		sy_tree=$("#sy_tree").tree({
			animate:true,
			url:'${ctx}'+actionUrl('/sys/','cxSbCompany','Get_Tree'),
			onClick : function(n) {
				var sy_ep_currnode = sy_tree.tree('getSelected');
				var subtitle = sy_ep_currnode.text;
				var id = sy_ep_currnode.id;
				var sy_ep_tabs_url = "${ctx}/sys/CxSbCompany.jsp?id="+id+"&topthemeName="+top.themeName;
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
				src : '${ctx}/sys/CxSbCompany.jsp?pview=add'+"&topthemeName="+top.themeName
			}
		});
		sy_tabs.tabs('addEventParam');
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
</script>
	</head>
	<body class="easyui-layout">
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
		<div id="i_sy_cxSbCompany_datagrid_upload_dialog" class="easyui-dialog" title="上传" style="width:400px;height:100px;"  
        data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true"> 
          <form id="i_sy_cxSbCompany_datagrid_upload_dialog_form" action="" enctype="multipart/form-data" method="post" >
    <input type="text" name="fileid"  style="display:none;"/>上传文件：<input type="file" name="fileGroup"><a href="${ctx}/excelmodel/组织表.xls" target="_blank">导入模版下载</a></br><span style="color:red">注：由于服务器的空间有限，上传文件大小不能超过1G</span></br>
        	<input type="button" value="上传" onClick="submitUploadForm();" />
        	</form>
</div>  
	</body>
</html>