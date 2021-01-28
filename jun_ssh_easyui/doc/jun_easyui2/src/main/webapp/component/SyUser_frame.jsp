<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/taglibs.jsp"%>
<%@ include file="/include/meta.jsp"%>
		<script type="text/javascript">
		var sy_user_tabs;
		var sy_og_tree,UploadDg,UploadFm;
	$(function() {
		sy_user_tabs = $("#sy_user_tabs");
		/*
		UploadDg = $('#i_sy_syUser_datagrid_upload_dialog');
		UploadFm =$('#i_sy_syUser_datagrid_upload_dialog_form');
		UploadFm.attr('action',"${ctx}"+actionUrl('/sys/','syUser','Upload'));
		*/
		//加载树
		sy_og_tree=$("#sy_og_tree").tree({
			animate:true,
			url:'${ctx}'+actionUrl('/sys/','syOrgan','Get_Tree'),
			onClick : function(n) {
				var sy_ep_currnode = sy_og_tree.tree('getSelected');
				var subtitle = sy_ep_currnode.text;
				var routeid=sy_ep_currnode.attributes.routeid.replace(/(^\s*)|(\s*$)/g,'');
				var id = sy_ep_currnode.id;
				var sy_ep_tabs_url = "${ctx}/component/SyUser.jsp?id="+id+"&routeid="+routeid+"&topthemeName="+top.themeName;
				//tabs
				if (!sy_user_tabs.tabs('exists', subtitle)) {
					sy_user_tabs.tabs('addIframeTab', {
						tab : {
							title : subtitle,
							closable : true,
							tools : [ {
								iconCls : 'icon-mini-refresh',
								handler : function() {
									sy_user_tabs.tabs('updateIframeTab', {
										'which' : subtitle
									});
									sy_user_tabs.tabs('select', subtitle);
								}
							} ]
						},
						iframe : {
							src : sy_ep_tabs_url
						}
					});
					sy_user_tabs.tabs('addEventParam');
					return false;
				} else {
					sy_user_tabs.tabs('updateIframeTab', {
						'which' : subtitle
					});
					sy_user_tabs.tabs('select', subtitle);
					sy_user_tabs.tabs('addEventParam');
					return false;
				}
				//tabs
			}//onclick
		});
		sy_user_tabs.tabs('addEventParam');
	});
	
	function getSelUserInfos(){
		var tabs =sy_user_tabs.tabs('tabs');
		var userInfos="";
		for(var i=0;i<tabs.length;i++){
			var tab=tabs[i];
		    var $iframe = tab.panel('body').find('iframe');
		    if($iframe.length==1){
		    	userInfos+= $iframe[0].contentWindow.getSelUserInfo();
		    }
		}
		return userInfos;
	}
	
</script>
	</head>
	<body class="easyui-layout">
		<!-- 树 -->
		<div data-options="region:'west',split:true" title="组织分类"
			style="width: 250px; ">
			<ul class="easyui-tree" id="sy_og_tree" style="padding: 10px 5px;">
			</ul>
		</div>
		<!-- tabs -->
		<div data-options="region:'center'" style="overflow: hidden;">
			<div class="easyui-tabs" fit="true" border="false" id="sy_user_tabs">
			</div>
		</div>
	</body>
</html>