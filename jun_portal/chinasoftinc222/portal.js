(function ($){
	//检测密码强度方法
	function passwordLevel(password) {
		  var Modes = 0;
		  if(password.length < 8){
			return -1;
		  }
		  for (i = 0; i < password.length; i++) {
			Modes |= CharMode(password.charCodeAt(i));
		  }
		  return bitTotal(Modes);
		  //CharMode函数
		  function CharMode(iN) {
			if (iN >= 48 && iN <= 57) //数字
			  return 1;
			if (iN >= 65 && iN <= 90) //大写字母
			  return 2;
			if ((iN >= 97 && iN <= 122) || (iN >= 65 && iN <= 90)) //大小写
			  return 4;
			else
			  return 8; //特殊字符
		  }
		  //bitTotal函数
		  function bitTotal(num) {
			modes = 0;
			for (i = 0; i < 4; i++) {
			  if (num & 1) modes++;
			  num >>>= 1;
			}
			return modes;
		  }
	};
	Portal={
		init:function(config){
			this.config=config;
			this.initEvent();
			this.rendPageComponent();
			this.editPageComponent();
		},initEvent:function(){
			$('#'+this.config.changePasswordButtonId).click(this.showChangePasswordDialog);
		},showChangePasswordDialog:function(){
			var d=$('#changePWDDialog');
			if(d.length==0){
				var t='<div id="changePWDDialog"><table  border="0" cellspacing="2" cellpadding="5" width="300"><tr><td align="right">'
				+ '原密码'
				+ '</td><td align="left">'
				+ '<div>'
				+ '<input type="password" name="oldPassword" id="oldPassword" /></td></tr><tr>'
				+ '</div><td align="right">'
				+ '新密码'
				+ '</td><td align="left">'
				+ '<input type="password" name="newPassword" id="newPassword" />'
				+ '</td></tr><tr><td align="right">'
				+ '确认新密码'
				+ '</td><td align="left">'
				+ '<input type="password" name="confirmPassword" id="confirmPassword" />'
				+ '</td></tr>'
				+ '</table></form>';
				d=$(t).hide().appendTo('body');
			}
			$('#changePWDDialog input').val('');
			d.dialog({
				width:315,
				height:'auto',
				title:'修改密码',
				modal:true,
				resizable:false,
				buttons : {
					"修改" : function () {
						var e=Portal.check();
						if(e!=''){
							Portal.alert(e,'error');
						}else{
							$(this).dialog("close");
							$.post(Portal.config.context+'/PersonHelperServlet?flag=changePWD&type=json',{
								'oldPassword':$('input:[name=oldPassword]').attr('value'),
								'newPassword':$('input:[name=newPassword]').attr('value')
							},function(data){
								Portal.alert(data,data.indexOf('失败')!=-1?'error':'info');
							});
						}
					},
					"取消" : function () {
						$(this).dialog("close");
					}
				}
			});
		},check:function(){
			var newpwd = $('input:[name=newPassword]').val();
			var e='';
			if(newpwd==''){
				e='密码不能为空'
			}
			if($('input:[name=confirmPassword]').val()!=newpwd){
				if(e!=''){
					e=e+',';
				}
				e=e+'两次输入的新密码不一致';
			}
			if($.md5(newpwd) === oldpwd){
				if(e!=''){
					e=e+',';
				}
				e=e+'新密码不能与旧密码相同！';
			}
			if(passwordLevel(newpwd) < 3){

				if(e!=''){
					e=e+',';
				}
				e=e+'密码强度不够，请保证长度至少8位以上，数字、字母加字符组合！';
			}
			return e;
		},alert : function (msg,type) {
			if($('#alert-dialog').length==0){
				$("body").append("<div id='alert-dialog'><p><span id='alert-dialog-icon' class='ui-icon'></span><span id='alert-content'></span></p></div>");
			}
			$('#alert-content').html(msg);
			var icon=$('#alert-dialog-icon').removeClass('ui-icon-alert ui-icon-circle-check');
			if(type=='error'){
				icon.addClass('ui-icon-alert');
			}else{
				icon.addClass('ui-icon-circle-check');
			}
			$('#alert-dialog').dialog({
				title : '提示',
				modal : true,
				height :'auto',
				resizable : false,
				buttons : {
					'关闭' : function () {
						$(this).dialog('close');
					}
				}
			});
		},rendPageComponent:function(el){//通过ajax请求渲染页面组件
			$('.portlet > .R1PAGECOMPONENT',el).each(function(){
				var t=$(this);
				t.mask();
				$.ajax({url:t.attr('url')}).always(function(html){
					t.html(html.responseText?html.responseText:html);
					t.unmask();
					t.removeClass('loading-placer');
				});
			});
		},editPageComponent:function(el){//通过ajax请求渲染编辑页面组件
			$('.x-portlet-model-editmodel').click(function(){
				var pageComponentEdit= $(this);
				var R1PageComponentName = pageComponentEdit.attr('name');
				var url = Portal.config.context +"/pagecomponentManager?flag=toEdit&fromPortalPage=true&R1PageComponentName="+R1PageComponentName;
				var editDialog = $("#editPageComponentDialog");
				if(editDialog.length==0){
					var t='<div id="editPageComponentDialog" style="padding:0;overflow:hidden"><iframe id="editPageComponent-ifr" frameborder="0" style="height:100%;width:100%"></iframe></div>';
					editDialog = $(t).hide().appendTo('body');
				}
				var ifr=$("#editPageComponent-ifr");
				var width = $(window).width()*0.7;
				var height = $(window).height()*0.7;
				editDialog.dialog({
					width:width,
					title:'页面组件设置:'+R1PageComponentName,
					height:height,
					modal:true,
					resizable:false,
					buttons:{
						'关闭':function(){
							$(this).dialog("close");
							if(ifr[0].contentWindow.R1PageComponentEditer.isUpdated){
								window.location.reload();
							}else{
								ifr.attr("src","about:blank");
							}
						}
					}
				});
				ifr.attr("src",url);
			});
		}
	}
})(jQuery);