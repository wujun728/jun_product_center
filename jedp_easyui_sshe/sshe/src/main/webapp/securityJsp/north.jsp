<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="sy.model.base.SessionInfo"%>
<%-- <%@ page import="sy.util.base.ConfigUtil%"%> --%>

<%
	String contextPath = request.getContextPath();
	SessionInfo sessionInfo = (SessionInfo) session
			.getAttribute("sessionInfo");
 	String sshe_chat_webroot = (String)session.getAttribute("sshe_chat_webroot");
%>
<script type="text/javascript" charset="utf-8">
var userId = '<%=sessionInfo.getUserId()%>';
var sshe_chat_webroot = '<%=sshe_chat_webroot%>'; 
 
var gotoChatFun = function(id) {
	//parent.$.messager.confirm('询问', '您确定要登陆到聊天服务器吗？'  , function(r) {
		//if (r)
		{
			$.post(sy.contextPath + '/base/syuser!gotoChatIM.sy', {
				id : id
			}, function(result) {
				if (result.success && result.obj != null) {
					// 取得要提交的参数
				    // 取得要提交页面的URL
				    //var action = "http://115.29.188.212:8787/sshe_chat/adapter/login";
					var action = sshe_chat_webroot + "/adapter/login";
				    // 创建Form
				   // var form = $('<form></form>');
				    var form =  $('#showDataForm');
			        
				    // 设置属性
				    form.attr('action', action);
				    form.attr('method', 'post');
				    // form的target属性决定form在哪个页面提交
				    // _self -> 当前页面 _blank -> 新页面
				    form.attr('target', 'actionframe');
				    // 创建Input
				    var my_input_name =$(':input[name="name"]');
				    my_input_name.attr('value', result.obj.name);
				    // 附加到Form
				    //form.append(my_input_name); 
				    
				    var my_input_server =$(':input[name="server"]');
				    my_input_server.attr('value', result.obj.server);
				    // 附加到Form
				    //form.append(my_input_server); 
				    
				    var my_input_port = $(':input[name="port"]');
				    my_input_port.attr('value', result.obj.port);
				    // 附加到Form
				    //form.append(my_input_port); 
				    
				    var my_input_password = $(':input[name="password"]');
				    my_input_password.attr('value', result.obj.pass);
				    // 附加到Form
				    //form.append(my_input_password); 
				   // console.log(form)
				    // 提交表单
				   // form.submit(); 
				   //console.log(form)
				   form.submit(); 
				    
				  // window.open("http://115.29.188.212:8787/sshe_chat");
				} else {
					$.messager.alert('提示', result.msg, 'error', function() {
						 
					});
				}
			   }, 'json');
		}
	//});
};

	var lockWindowFun = function() {
		$.post(sy.contextPath
				+ '/base/syuser!doNotNeedSessionAndSecurity_logout.sy',
				function(result) {
					$('#loginDialog').dialog('open');
				}, 'json');
	};
	var logoutFun = function() {
		$.post(sy.contextPath
				+ '/base/syuser!doNotNeedSessionAndSecurity_logout.sy',
				function(result) {
					location.replace(sy.contextPath + '/index.jsp');
				}, 'json');
	};
	var showMyInfoFun = function() {
		var dialog = parent.sy.modalDialog({
			title : '我的信息',
			url : sy.contextPath + '/securityJsp/userInfo.jsp'
		});
	};
	var changeToWindow = function(count) {
		if(count == 2){
			location.replace(sy.contextPath + '/index.jsp?type=2');
		}else{
			location.replace(sy.contextPath + '/index.jsp?type=3');
		}
	};
	
	var changeToDesktop = function(){
		location.replace(sy.contextPath + '/desktop/index.html');
	}
	
	var changeToIM = function(){
		//window.open('http://115.29.188.212:8787/sshe_chat/');
		var id = '<%=sessionInfo.getUser().getId()%>';
		gotoChatFun(id);
		
		var dialog = parent.sy.modalDialog({
			title : '咨询',
			url : sshe_chat_webroot
		});
	}
	
	var readMessage = function() {
		var dialog = parent.sy.modalDialog({
			title : '阅读信息',
			url : sy.contextPath + '/securityJsp/app/ReadMessageForm.jsp',
			buttons : [ {
				text : '阅读后关闭',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, parent.$);
				}
			} ]
		});
	};
		
	var sysfeedback = function(){
		var url = sy.contextPath + '/securityJsp/base/SysFeedbackDef.jsp';
		window.open(url, "_blank");
		 
	};
	
	var addFun = function() {
		var dialog = parent.sy.modalDialog({
			title : '添加系统反馈信息',
			url : sy.contextPath + '/securityJsp/base/SysFeedbackDefForm.jsp',
			buttons : [ {
				text : '添加',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm2(dialog, parent.$);
				}
			} ]
		});
	};
	$(function(){  
	       run();             //加载页面时启动定时器  
	       var interval;  
	           function run() {
	              interval = setInterval(chat, "5000");  
	           }
	            function loadMessages(){
	            	try{
	            		$.post(sy.contextPath
		        				+ '/base/syuser!doNotNeedSecurity_getChatCount.sy?id=' + userId ,
		        				function(result) {
		        					 console.log(result);
		        					 if(result.success){
		        						 writeMsg(result.msg);	 
		        					 }else{
		        						 clearInterval(interval);
		        						 //writeMsg(result.msg);	 
		        					 }
		        				}, 'json');
	            	}catch(e){
	            		alert(e)
	            	}
	           }
	           function writeMsg(msg){
	        	   //var msg = msg;
	        	   if(msg != ''){
	        		   var htmlStr= "<input id=\"iconCls\"  class=\"ext-icon-bell\" readonly=\"readonly\"";
		        	   htmlStr+= "style=\"padding-left: 18px;margin-top: -10px; width: 13px;border: none\" />";
		        	   htmlStr+= " <a href=\"javascript:void(0)\" class=\"panel-header panel-header-noborder\" onclick=\"readMessage();\" style=\"color: white;\">"+msg+"</a>";
		        	   $("#MessageArea").html(htmlStr);      
	        	   }
	        	   else{
	        		   $("#MessageArea").html('');
	        	   }
	           }
	           function chat() {
	        	   loadMessages();
	               //$('#MessageArea').kxbdMarquee({ direction: 'up', isEqual: false });
	           }  
	     $("#closeChat").click(function(){  
	         clearTimeout(interval);  //关闭定时器  
	     });
	     
	   }); 
	
</script>


<div id="sessionInfoDiv"
	style="position: absolute; right: 10px; top: 5px;color: blue;">
	<%
		if (sessionInfo != null) {
			out.print(sy.util.base.StringUtil.formateString("欢迎您，{0}",
					sessionInfo.getUser().getLoginname()));
		}
	%>
	<input id="iconCls"  class="ext-icon-color_wheel" readonly="readonly" style="padding-left: 18px; width: 13px; border: none" />
	
	<div class="panel-header panel-header-noborder"
		style="width: 800px; align: right; margin-top: 15px; margin-right: -10px">
		<div class="panel-title panel-with-icon">
系统消息：
			<marquee id="MessageArea" scrollamount="n" width="80%" height="n"
				direction="type[left,right,down,up]" scrolldelay="n"
				behavior="type[scroll,alternate,slide]" loop="n" style="margin-top: -10px"
				onmouseover="this.stop()" onmouseout="this.start()">
				<!-- <input id="iconCls"  class="ext-icon-bell" readonly="readonly"  style="padding-left: 18px; width: 13px;border: none" /> -->
				<a href="javascript:void(0)" class="panel-header panel-header-noborder"
					onclick="changeToIM();" style="color: white;"></a>
			</marquee>

		</div>
		<div class="panel-icon icon-standard-information"></div>
		<div class="panel-tool">
			<a href="javascript:void(0)" class="panel-tool-collapse"
				style="display: none;"></a>
		</div>
	</div>

</div>

<div style="position: absolute; right: 0px; bottom: 0px; z-index: 20">

	<a href="javascript:void(0);" class="easyui-menubutton"
		data-options="menu:'#layout_north_langMenu',iconCls:'ext-icon-rainbow'">设置语言</a>
	<a href="javascript:void(0);" class="easyui-menubutton"
		data-options="menu:'#layout_north_pfMenu',iconCls:'ext-icon-rainbow'">更换皮肤</a>
	<a href="javascript:void(0);" class="easyui-menubutton"
		data-options="menu:'#layout_north_kzmbMenu',iconCls:'ext-icon-cog'">控制面板</a>
	 <a href="javascript:void(0);" class="easyui-menubutton"
		data-options="menu:'#layout_north_zxMenu',iconCls:'ext-icon-disconnect'">注销</a>
	<a href="javascript:void(0);"   class="easyui-menubutton"
		data-options="menu:'#layout_north_bugMenu',iconCls:'ext-icon-disconnect'">bug提交</a> 
</div>
<div id="layout_north_bugMenu" style="width: 120px; display: none;">
<div onclick="sysfeedback()" title="default" data-options="iconCls:'ext-icon-user_edit'">系统反馈</div>
<div onclick="addFun()" title="default" data-options="iconCls:'ext-icon-user_edit'">bug提交</div>

</div>
<div id="layout_north_pfMenu" style="width: 120px; display: none;">
	<div onclick="sy.changeTheme('default');" title="default" data-options="iconCls:'ext-icon-book_add'">默认</div>
	<div onclick="sy.changeTheme('gray');" title="gray" data-options="iconCls:'ext-icon-book_add'">灰色</div>
	<div onclick="sy.changeTheme('metro');" title="metro" data-options="iconCls:'ext-icon-book_add'">简洁</div>
	<div onclick="sy.changeTheme('bootstrap');" title="bootstrap" data-options="iconCls:'ext-icon-book_add'">扁平</div>
	<div onclick="sy.changeTheme('black');" title="black" data-options="iconCls:'ext-icon-book_add'">黑色</div>
	<div class="menu-sep"></div>
	<div onclick="sy.changeTheme('metro-blue');" title="metro-blue" data-options="iconCls:'ext-icon-book_add'">简洁-蓝色</div>
	<div onclick="sy.changeTheme('metro-gray');" title="metro-gray" data-options="iconCls:'ext-icon-book_add'">简洁-灰色</div>
	<div onclick="sy.changeTheme('metro-green');" title="metro-green" data-options="iconCls:'ext-icon-book_add'">简洁-绿色</div>
	<div onclick="sy.changeTheme('metro-orange');" title="metro-orange" data-options="iconCls:'ext-icon-book_add'">简洁-橘黄色</div>
	<div onclick="sy.changeTheme('metro-red');" title="metro-red" data-options="iconCls:'ext-icon-book_add'">简洁-红色</div>
</div>
<div id="layout_north_langMenu" style="width: 120px; display: none;">
	<div onclick="sy.changeLang('默认');" title="default" data-options="iconCls:'ext-icon-book_add'">默认</div>
	<div onclick="sy.changeLang('中文');" title="gray" data-options="iconCls:'ext-icon-book_add'">中文</div>
	<div onclick="sy.changeLang('英语');" title="metro" data-options="iconCls:'ext-icon-book_add'">英语</div>
</div>

<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
	<div data-options="iconCls:'ext-icon-user_edit'"
		onclick="$('#passwordDialog').dialog('open');">修改密码</div>
	<div class="menu-sep"></div>
	<div data-options="iconCls:'ext-icon-user'" onclick="showMyInfoFun();">我的信息</div>
<!-- 	<div data-options="iconCls:'ext-icon-user'" onclick="showMyInfoFun();">显示头像</div>
	<div data-options="iconCls:'ext-icon-user'" onclick="showMyInfoFun();">显示系统时间</div>
	<div class="menu-sep"></div>
	<div data-options="iconCls:'ext-icon-user'" onclick="showMyInfoFun();">查看系统版本</div>
	<div data-options="iconCls:'ext-icon-user'" onclick="showMyInfoFun();">查看开发作者</div>
	<div data-options="iconCls:'ext-icon-user'" onclick="showMyInfoFun();">版权申明</div> -->
</div>
<div id="layout_north_zxMenu" style="width: 100px; display: none;">
	<div data-options="iconCls:'ext-icon-lock'" onclick="lockWindowFun();">锁定窗口</div>
	<div class="menu-sep"></div>
	<div data-options="iconCls:'ext-icon-door_out'" onclick="logoutFun();">退出系统</div>
</div>
<div id="layout_north_scMenu" style="width: 100px; display: none;">
	<div data-options="iconCls:'ext-icon-lock'" onclick="changeToDesktop();">进入桌面</div>
	<div class="menu-sep"></div>
	<div data-options="iconCls:'ext-icon-door_out'" onclick="changeToIM();">进入咨询</div>
</div>
<div id="layout_north_toolMenu" style="width: 100px; display: none;">
	<div data-options="iconCls:'ext-icon-lock'" onclick="lockWindowFun();">电子万年历</div>
	<div class="menu-sep"></div>
	<div data-options="iconCls:'ext-icon-door_out'" onclick="logoutFun();">天气预报</div>
</div>
<div id="layout_north_sitemeshMenu" style="width: 100px; display: none;">
	<div data-options="iconCls:'ext-icon-lock'" onclick="changeToWindow(2);">左-右</div>
	<div class="menu-sep"></div>
	<div data-options="iconCls:'ext-icon-door_out'" onclick="changeToWindow(3);">左-中-右</div>
</div>
<div class="panel-header panel-header-noborder"
	style="margin-top: 60px; width: 100%; height: 120px">
	<div class="panel-title panel-with-icon">
		<div style="right: 0px; bottom: 0px; z-index: 20">
			<!-- 	<a href="javascript:void(0);" class="easyui-menubutton"
		data-options="menu:'#layout_north_pfMenu',iconCls:'ext-icon-rainbow'">首 页</a> -->
			<a href="javascript:void(0);" class="easyui-menubutton"
				data-options="menu:'#layout_north_sitemeshMenu',iconCls:'ext-icon-application_side_list'">页面布局</a>
			<a href="javascript:void(0);" class="easyui-menubutton"
				data-options="menu:'#layout_north_scMenu',iconCls:'ext-icon-application_side_list'">我的收藏夹</a>
			<a href="javascript:void(0);" class="easyui-menubutton"
				data-options="menu:'#layout_north_toolMenu',iconCls:'ext-icon-zoom'">常用工具</a>
		</div>

		<!-- <div
			style="color: #fff; font-size: 37px; font-weight: bold; text-decoration: none; z-index: 10">
			<div style="border: 0px solid #ddd;">
				<a href="#" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon'">首 页</a> <a href="#"
					class="easyui-linkbutton" data-options="plain:true,iconCls:'icon'">客户管理</a>
				<a href="#" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon'">员工管理</a> <a href="#"
					class="easyui-linkbutton" data-options="plain:true,iconCls:'icon'">系统管理</a>
				<a href="#" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon'">库存管理</a> <a href="#"
					class="easyui-linkbutton" data-options="plain:true,iconCls:'icon'">就诊</a>
				<a href="#" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon'">体检</a> <a href="#"
					class="easyui-linkbutton" data-options="plain:true,iconCls:'icon'">知识管理</a>
				<a href="#" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon'">流行病管理</a> <a href="#"
					class="easyui-linkbutton" data-options="plain:true,iconCls:'icon'">在线咨询</a>
				<a href="#" class="easyui-linkbutton"
					data-options="plain:true,iconCls:'icon'">统计报表</a>
			</div>
		</div> -->
	</div>
	<div class="panel-icon icon-standard-information"></div>
	<div class="panel-tool">
		<a href="javascript:void(0)" class="panel-tool-collapse"
			style="display: none;"></a><a href="javascript:void(0)"
			class="layout-button-down"></a>
	</div>
	<div data-options="region:'center',fit:true,border:false"
		style="visibility: hidden">
		<iframe width="0" height="0" name="actionframe"></iframe>
		<form id="showDataForm" action="actionframe"
			method="post" target="_blank">
			<input type="text" name="name" /> <input type="text" name="server" />
			<input type="text" name="port" /> <input type="text" name="password" />
		</form>
	</div>
</div>
