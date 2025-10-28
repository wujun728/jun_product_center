layui.config({
	base: 'style/js/'
}).use(['element', 'layer', 'navbar', 'tab'], function() {
	var element = layui.element,
		$ = layui.jquery,
		layer = layui.layer,
		navbar = layui.navbar(),
		tab = layui.tab({
			elem: '.admin-nav-card' //设置选项卡容器
		});
	//iframe自适应
	$(window).on('resize', function() {
		var $content = $('.admin-nav-card .layui-tab-content');
		$content.height($(this).height() - 147);
		$content.find('iframe').each(function() {
			$(this).height($content.height());
		});
	}).resize();
	
	//设置navbar
	navbar.set({
		spreadOne: false,
		elem: '#admin-navbar-side',
		cached: true,
		data: navs
		//cached:true,
		//url: 'js/nav.json'
	});
	//渲染navbar
	navbar.render(tab);
	
	//监听点击事件
	navbar.on('click(side)', function(data) {
		tab.tabAdd(data.field);
	});
	
	$('.admin-side-toggle').on('click', function() {
		var sideWidth = $('#admin-side').width();
		if(sideWidth === 200) {
			$('#admin-body').animate({
				left: '0'
			}); //admin-footer
			$('#admin-footer').animate({
				left: '0'
			});
			$('#admin-side').animate({
				width: '0'
			});
		} else {
			$('#admin-body').animate({
				left: '200px'
			});
			$('#admin-footer').animate({
				left: '200px'
			});
			$('#admin-side').animate({
				width: '200px'
			});
		}
	});

	//锁屏
	$(document).on('keydown', function() {
		var e = window.event;
		if(e.keyCode === 76 && e.altKey) {
			//alert("你按下了alt+l");
			lock($, layer);
		}
	});
	$('#lock').on('click', function() {
		lock($, layer);
	});
	if(lockFlag && lockFlag==1){
		$('#lock').click();
	}
	//手机设备的简单适配
	var treeMobile = $('.site-tree-mobile'),
		shadeMobile = $('.site-mobile-shade');
	treeMobile.on('click', function() {
		$('body').addClass('site-mobile');
	});
	shadeMobile.on('click', function() {
		$('body').removeClass('site-mobile');
	});
});

function lock($, layer) {
	localStorage.setItem(lockStorageName,1);
	//自定页
	layer.open({
		title: false,
		type: 1,
		closeBtn: 0,
		anim: 6,
		content: $('#lock-temp').html(),
		shade: [0.9, '#393D49'],
		success: function(layero, lockIndex) {

			//给显示用户名赋值
			var userName = $("#userName").html();
			layero.find('div#lockUserName').text(userName);
			layero.find('input[name=lockPwd]').on('focus', function() {
					var $this = $(this);
					if($this.val() === '输入密码解锁..') {
						$this.val('').attr('type', 'password');
					}
				})
				.on('blur', function() {
					var $this = $(this);
					if($this.val() === '' || $this.length === 0) {
						$this.attr('type', 'text').val('输入密码解锁..');
					}
				});
				
			//绑定解锁按钮的点击事件
			layero.find('button#unlock').on('click', function() {
				var $lockBox = $('div#lock-box');

				var userName = $lockBox.find('div#lockUserName').text();
				var pwd = $lockBox.find('input[name=lockPwd]').val();
				if(pwd === '输入密码解锁..' || pwd.length === 0) {
					layer.msg('请输入密码..', {
						icon: 2,
						time: 1000
					});
					return;
				}
				unlock(userName, pwd);
			});
			/**
			 * 解锁操作方法
			 * @param {String} 用户名
			 * @param {String} 密码
			 */
			var unlock = function(un, pwd) {
				var login_remember = localStorage.getItem("login_remember");
				if (login_remember && login_remember != "undefined") {
					var item = JSON.parse(login_remember);
					var remember_me = item.remember_me;
					if (remember_me == true) {
						var userCode = item.login_name;
						var pass = item.login_pass;
						var ps = md5(pwd+pass_salt).toUpperCase();
						if(ps != pass){
							layer.msg('密码输入错误..',{icon:2,time:1000});
							return;
						}
					}
				}
				layer.close(lockIndex);
				localStorage.removeItem(lockStorageName)
			};
		}
	});
};