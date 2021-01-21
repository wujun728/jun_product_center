/**
 * Created by ____′↘夏悸，Universe，風亦飛
 * User: ____′↘夏悸
 * Date: 2012-8-8
 * 这个是一个jQuery Easyui的的桌面扩展
 * 讨论学习群：
 *    142872541（一）
 *    47729185（二）
 *    70168958（500人超级群）
 *    腾讯微博群号：83952631
 *    社区： http://bbs.btboys.com
 * version: 0.4
 */
(function ($) {
	var loaded = false;
	var prevOpenedApp,
	currentOpenedApp,allWindow = {};
	/**
	 * layout初始化
	 * @param target
	 */
	function initLayout(target) {
		var jqTarget = $(target);
		var opts = $.data(target, 'app').options;
		
		var center = $('<div/>').attr({
				'border' : false,
				'region' : 'center'
			}).addClass('app-wall').appendTo(jqTarget);
		
		//为桌面绑定右键菜
		center.bind('contextmenu', function (e) {
			var appContainer = $.data(target, 'app')['appContainer'];
			if (appContainer && e.target != appContainer[0])
				return;
			opts.onWallContextMenu.call(target, e);
			e.preventDefault();
		});
		
		//墙纸设置
		if (opts.wallpaper) {
			center.css('background-image', 'url("' + opts.wallpaper + '")');
		}
		
		if (jqTarget.context.nodeName !== 'BODY') //非body对象，添加fit属性
			jqTarget.attr('fit', true);
		
		var region = {
			south : true,
			north : true,
			west : true,
			east : true
		};
		if (!region[opts.taskBlankPos]) //有效值验证
			opts.taskBlankPos = 'south';
		
		var taskBlank = $('<div/>').attr({
				'border' : false,
				'region' : opts.taskBlankPos
			}).css({
				overflow : 'hidden'
			}).appendTo(jqTarget);
		if (opts.taskBlankPos == 'north' || opts.taskBlankPos == 'south') {
			taskBlank.css("height", 35);
		} else {
			taskBlank.css("width", 35);
		}
		
		//执行layout实例
		jqTarget.layout();
		
		var calendarDiv = $('<div/>').appendTo(center).calendar({
				current : new Date()
			}).hide().css({
				"position" : "absolute",
				"z-index" : 100
			}); //插入calendar占位
		
		$.data(target, 'app')['calendarDiv'] = calendarDiv;
		
		center.panel({
			onResize : function (width, height) {
				appReset(target);
				setTaskListWidth(target);
			}
		});
	}
	
	/**
	 * 初始化任务栏
	 * @param target
	 */
	function initTaskBlank(target) {
		var jqTarget = $(target);
		var opts = $.data(target, 'app').options;
		var taskBlank = jqTarget.layout('panel', opts.taskBlankPos); //获取任务栏Layout面板容器
		var taskBar = $('<div/>').addClass('app-taskBar'); //创建任务栏对象
		
		var start = $('<span/>'); //开始菜单按钮
		var taskList = $('<div/>'); //创建任务区域
		var calendar = $('<div/>'); //创建时间区域
		
		if ($.browser.msie) { //兼容ie的hover
			start.hover(function () {
				$(this).addClass('app-startMenu-hover');
			}, function () {
				$(this).removeClass('app-startMenu-hover');
			});
		}
		
		if (opts.taskBlankPos == 'south' || opts.taskBlankPos == 'north') {
			taskBar.addClass('app-taskBar-bg1');
			start.addClass('app-startMenu-x');
			taskList.addClass('app-taskList-x');
			calendar.addClass('app-taskBar-calendar-x');
			
			var scrollLeft = $('<div/>').addClass('app-scroll-left').appendTo(taskList);
			var scrollRight = $('<div/>').addClass('app-scroll-right').appendTo(taskList);
			var listWrap = $('<div/>').addClass('app-list-wrap').appendTo(taskList);
			var list = $('<ul/>').addClass('app-list-list').appendTo(listWrap);
			
		} else {
			taskBar.addClass('app-taskBar-bg2');
			start.addClass('app-startMenu-y');
			taskList.addClass('app-taskList-y');
			calendar.addClass('app-taskBar-calendar-y');
			
			var scrollTop = $('<div/>').addClass('app-scroll-top').appendTo(taskList);
			var scrollBottom = $('<div/>').addClass('app-scroll-bottom').appendTo(taskList);
		}
		
		//依次添加到任务栏对象里面
		start.appendTo(taskBar);
		taskList.appendTo(taskBar);
		calendar.appendTo(taskBar);
		taskBar.appendTo(taskBlank);
		
		$.data(target, 'app')['taskBar'] = taskBar;
		$.data(target, 'app')['start'] = start;
		$.data(target, 'app')['taskList'] = taskList;
		$.data(target, 'app')['calendar'] = calendar;
		
		//为taskList绑定右键菜单
		taskList.bind('contextmenu', function (e) {
			if (e.target.nodeName == "LI") {
				opts.onTaskBlankContextMenu.call(target, e, $(e.target).attr('l_id'));
			} else {
				opts.onTaskBlankContextMenu.call(target, e, false);
			}
		});
		
		setTaskListWidth(target);
	}
	
	/**
	 * 设置任务栏宽度
	 * @param target
	 */
	function setTaskListWidth(target) {
		var jqTarget = $(target);
		var opts = $.data(target, 'app').options;
		var taskBlank = jqTarget.layout('panel', opts.taskBlankPos); //获取任务栏Layou面板容器
		var taskList = $.data(target, 'app')['taskList'];
		
		if (opts.taskBlankPos == 'south' || opts.taskBlankPos == 'north') {
			taskList.width(taskBlank.width() - 130);
		} else {
			taskList.height(taskBlank.height() - 75);
		}
	}
	
	/**
	 *初始化桌面
	 * @param target
	 */
	function initDeskTop(target) {
		var jqTarget = $(target);
		var opts = $.data(target, 'app').options;
		var wall = jqTarget.layout('panel', 'center'); //桌面对象
		var appContainer = $('<ul/>').addClass('app-container'); //app容器
		appContainer.appendTo(wall);
		$.data(target, 'app')['appContainer'] = appContainer;
		
		if (opts.loadUrl.app && !loaded) {
			$.ajax({
				url : opts.loadUrl.app,
				dataType : "JSON",
				async : false,
				cache : false,
				success : function (resp) {
					initApp(target, resp);
				},
				error : function (XMLHttpRequest, textStatus, errorThrown) {
					$.messager.alert("", textStatus || errorThrown, "error");
				}
			});
		}
	}
	
	/**
	 * 初始app
	 * @param apps
	 */
	function initApp(target, apps) {
		var jqTarget = $(target);
		var opts = $.data(target, 'app').options;
		var wall = jqTarget.layout('panel', 'center'); //桌面对象
		var appContainer = jqTarget.data().app.appContainer;
		
		var lines = Math.floor((wall.height() - 20) / (opts.iconSize + 45)); //可显示行数
		var columns = Math.floor((wall.width() - 20) / (opts.iconSize + 45)); //可显示列数
		var wallMax = lines * columns; //每页显示app最大值
		var lineAppBlank = ((wall.height() - 20) - (opts.iconSize + 45) * lines) / lines; //行间隔高度
		var columnAppBlank = ((wall.width() - 20) - (opts.iconSize + 45) * columns) / columns; //列间隔宽度
		//初始值
		var line = 1,
		col = 1,
		top = 20,
		left = 10;
		
		var relSize = opts.iconSize + 45;
		for (var i = 0;i<apps.length;i++) {
			if (line > lines) {
				line = 1;
				top = 20;
				left += relSize + columnAppBlank;
				col++;
			}
			
			var app = apps[i];
			
			var appItem = $('<li/>').css({
					height : relSize,
					width : relSize
				});
			
			appItem.data('app', app); //绑定每个app的详细信息到app元素上
			appItem.attr("app_id", UUID()); //指定app的唯一标识
			if (app.id) {
				appItem.attr("id", app.id);
			}
			
			appItem.css({
				left : left,
				top : top
			});
			
			var icon = $('<img/>').height(opts.iconSize).width(opts.iconSize).attr('src', app.icon).appendTo(appItem);
			var text = $('<span/>').text(app.text).appendTo(appItem);
			var em = $('<em/>').css({
					height : relSize,
					width : relSize
				}).appendTo(appItem);
			
			appItem.appendTo(appContainer);
			
			top += relSize + lineAppBlank; //下一行的top值
			line++;
			
			if ($.browser.msie) { //兼容ie的hover
				appItem.hover(function () {
					$(this).addClass('hover');
				}, function () {
					$(this).removeClass('hover');
				});
			}
			
			initAppDrag(target, appItem); //初始化app的拖拽事件
			
			if (opts.dbClick) { //绑定App的点击事件（dbClick是否双击）
				appItem.on('dblclick', function () {
					openApp.call(this, target);
				});
			} else {
				appItem.on('click', function () {
					openApp.call(this, target);
				});
			}
		}
		
		var appItems = appContainer.children('li');
		appItems.mousedown(
			function () {
			appItems.removeClass("select");
			$(this).addClass("select");
		}).bind('contextmenu', function (e) {
			opts.onAppContextMenu.call(target, e, $(this).attr('app_id'));
			e.preventDefault();
		});
	}
	
	/**
	 * 初始化图标拖拽
	 * @param target
	 * @param appItem
	 */
	function initAppDrag(target, appItem) {
		appItem.draggable({
			revert : true,
			cursor : "default"
		}).droppable({
			onDrop : function (e, source) {
				if ($(source).prev().attr('app_id') == $(this).attr('app_id')) {
					$(source).insertBefore(this);
				} else {
					$(source).insertAfter(this);
				}
				setTimeout(function () {
					appReset(target);
				}, 0);
			},
			accept : '.app-container li'
		})
	}
	/**
	 * 刷新APP
	 * @param target
	 * @param appContainer
	 */
	function refresh(target, href) {
		var jqTarget = $(target);
		var opts = $.data(target, 'app').options;
		href = href || opts.loadUrl.app;
		if (href) {
			$.ajax({
				url : href,
				dataType : "JSON",
				async : false,
				cache : false,
				success : function (resp) {
					jqTarget.data().app.appContainer.empty();
					initApp(target, resp);
				},
				error : function (XMLHttpRequest, textStatus, errorThrown) {
					$.messager.alert("", textStatus || errorThrown, "error");
				}
			});
		}
	}
	
	/**
	 * 初始app
	 * @param target
	 * @param appContainer
	 */
	function appReset(target) {
		var jqTarget = $(target);
		var opts = $.data(target, 'app').options;
		var wall = jqTarget.layout('panel', 'center');
		var appContainer = $.data(target, 'app').appContainer;
		
		var lines = Math.floor((wall.height() - 20) / (opts.iconSize + 45)); //可显示行数
		var columns = Math.floor((wall.width() - 20) / (opts.iconSize + 45)); //可显示列数
		var wallMax = lines * columns; //每页显示app最大值
		var lineAppBlank = ((wall.height() - 20) - (opts.iconSize + 45) * lines) / lines; //行间隔高度
		var columnAppBlank = ((wall.width() - 20) - (opts.iconSize + 45) * columns) / columns; //列间隔宽度
		
		//初始值
		var line = 1,
		col = 1,
		top = 20,
		left = 10;
		
		var relSize = opts.iconSize + 45;
		appContainer.children().each(function () {
			if (line > lines) {
				line = 1;
				top = 20;
				left += relSize + columnAppBlank;
				col++;
			}
			$(this).css({
				left : left,
				top : top
			});
			
			top += relSize + lineAppBlank;
			line++;
		});
	}
	
	/**
	 *初始化开始菜单
	 * @param target
	 */
	function initStartMenu(target) {
		var jqTarget = $(target);
		var opts = $.data(target, 'app').options;
		
		if (opts.loadUrl.startMenu && !loaded) {
			$.ajax({
				url : opts.loadUrl.startMenu,
				dataType : "JSON",
				async : false,
				cache : false,
				success : function (resp) {
					initMenu(target, resp);
				},
				error : function (XMLHttpRequest, textStatus, errorThrown) {
					$.messager.alert("", textStatus || errorThrown, "error");
				}
			});
		}
	}
	
	/**
	 * 初始化菜单
	 * @param menus
	 */
	function initMenu(target, menus) {
		var jqTarget = $(target);
		var opts = $.data(target, 'app').options;
		var wall = jqTarget.layout('panel', 'center');
		var startMenuDiv;
		
		startMenuDiv = createMenu(target, menus,{
			onClick : function (item) {
				opts.onStartMenuClick.call(target, item);
			}
		});
		
		var start = $.data(target, 'app')['start'];
		//确定菜单显示位置
		var left = 0,
		top = 0;
		
		start.click(function (e) {
			if (opts.taskBlankPos == 'south') {
				top = wall.height();
			} else if (opts.taskBlankPos == 'north') {
				top = start.height();
			} else if (opts.taskBlankPos == 'west') {
				top = start.height() + 7;
			} else if (opts.taskBlankPos == 'east') {
				left = wall.width();
				top = start.height() + 7;
			}
			
			startMenuDiv.menu('show', {
				left : left,
				top : top
			});
		});
		
		start.data('menu', startMenuDiv);
	}
	
	/**
	 * 创建菜单dom
	 * @param menus
	 */
	function createMenu(target, menus,opt) {
		var opts = $.data(target, 'app').options;
		var menuDiv = $('<div style="width:200px;"></div>').appendTo('body');
		for (var i = 0; i < menus.length; i++) {
			var menu = menus[i];
			if (menu == '-') {
				var sep = $('<div class="menu-sep"></div>');
				menuDiv.append(sep);
				continue;
			}
			if (menu.children) {
				menuDiv.append(appendChild(menu));
			} else {
				var item = $('<div></div>').html(menu.text).data("data",menu);
				
				if (menu.href)
					item.attr("url", menu.href);
				
				if (menu.iconCls) {
					item.attr('iconCls', menu.iconCls);
				}
				menuDiv.append(item);
			}
		}
		
		return menuDiv.menu(opt);
		
		/**
		 * 递归添加子菜单
		 * @param menu
		 */
		function appendChild(menu) {
			var itemText = menu.text,
			children = menu.children;
			var item = $('<div/>').append($('<span></span>').html(itemText)).data("data",menu);
			
			if (menu.href)
				item.attr("url", menu.href); //未添加点击事件
			
			if (menu.iconCls) {
				item.attr('iconCls', menu.iconCls);
			}
			
			var ci = $('<div style="width:200px;"></div>');
			for (var i = 0; i < children.length; i++) {
				var cMenu = children[i];
				if (cMenu == '-') {
					var sep = $('<div class="menu-sep"></div>');
					menu.append(sep);
					continue;
				}
				if (cMenu.children) {
					item.append(ci.append(appendChild(cMenu)));
				} else {
					var cItem = $('<div/>').html(cMenu.text).data("data",cMenu);
					
					if (cMenu.href)
						cItem.attr("url", cMenu.href);
					
					if (cMenu.iconCls) {
						cItem.attr('iconCls', cMenu.iconCls);
					}
					item.append(ci.append(cItem));
					
				}
			}
			
			return item;
		}
	}
	
	/**
	 * 设置时间位置
	 * @param target
	 */
	function setCalendarTopAndLeft(target) {
		var calendarDiv = $.data(target, 'app')['calendarDiv'];
		var opts = $.data(target, 'app').options;
		var css = {};
		if (opts.taskBlankPos == 'south' || opts.taskBlankPos == 'east') {
			css['bottom'] = 0;
			css['right'] = 0;
		} else if (opts.taskBlankPos == 'west') {
			css['bottom'] = 0;
			css['left'] = 0;
		} else {
			css['top'] = 0;
			css['right'] = 0;
		}
		calendarDiv.css(css);
	}
	
	/**
	 * 初始化时间
	 * @param target
	 */
	function initCalendar(target) {
		var jqTarget = $(target);
		var opts = $.data(target, 'app').options;
		var calendar = $.data(target, 'app')['calendar'];
		
		function init() {
			var nowDate = new Date();
			var date = format.call(nowDate, opts.dateFmt);
			calendar.attr('title', format.call(nowDate, 'yyyy-MM-dd'));
			
			if (opts.taskBlankPos == 'south' || opts.taskBlankPos == 'north') {
				calendar.html(date);
			} else {
				var t = nowDate.getHours() + ':';
				if (nowDate.getMinutes() < 10) {
					t += '0';
				}
				t += nowDate.getMinutes();
				calendar.html(t);
			}
		}
		
		init();
		window.setInterval(function () {
			init();
		}, 1000);
		
		var calendarDiv = $.data(target, 'app')['calendarDiv'];
		setCalendarTopAndLeft(target);
		
		calendar.click(function () {
			calendarDiv.slideToggle();
		});
		jqTarget.click(function (e) {
			var c = $(e.target).attr('class');
			if (c != 'app-taskBar-calendar-x' && c != 'app-taskBar-calendar-y' && !$.contains(calendarDiv[0], e.target)) {
				calendarDiv.hide();
			}
		});
		
		function format(format) {
			/*
			 * eg:format="yyyy-MM-dd hh:mm:ss";
			 */
			if (!format) {
				format = "yyyy-MM-dd hh:mm:ss";
			}
			var o = {
				"M+" : this.getMonth() + 1, // month
				"d+" : this.getDate(), // day
				"h+" : this.getHours(), // hour
				"m+" : this.getMinutes(), // minute
				"s+" : this.getSeconds(), // second
				"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
				"S" : this.getMilliseconds() // millisecond
			};
			if (/(y+)/.test(format)) {
				format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
			}
			for (var k in o) {
				if (new RegExp("(" + k + ")").test(format)) {
					format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
				}
			}
			return format;
		}
	}
	
	/**
	 * 初始化widget
	 * @param target
	 */
	function initWidget(target) {
		var jqTarget = $(target);
		var opts = $(target).data('app').options;
		var wall = jqTarget.layout('panel', 'center'); //桌面对象
		
		var css = {},
		axis = "v";
		if (opts.taskBlankPos == 'south' || opts.taskBlankPos == 'west') {
			css['top'] = 0;
			css['right'] = 0;
		} else if (opts.taskBlankPos == 'east') {
			css['top'] = 0;
			css['left'] = 0;
		} else {
			css['bottom'] = 0;
			css['right'] = 0;
			axis = "h";
		}
		
		if (opts.loadUrl.widget && !loaded) {
			$.ajax({
				url : opts.loadUrl.widget,
				dataType : "JSON",
				async : false,
				cache : false,
				success : function (resp) {
					init(resp);
				},
				error : function (XMLHttpRequest, textStatus, errorThrown) {
					$.messager.alert("", textStatus || errorThrown, "error");
				}
			});
		}
		
		function init(resp) {
			$.each(resp, function () {
				var w = $("<div/>").addClass("app-widget").css(css);
				
				if (this.style) {
					w.attr("style", this.style);
				}
				if (this.cls) {
					w.addClass(this.cls);
				}
				
				if (this.href) {
					w.load(this.href);
				} else if (this.content) {
					w.html(this.content);
				}
				
				w.appendTo(wall);
				w.draggable({
					cursor : "default",
					axis : axis
				});
				$.parser.parse(w);  
			});
		}
	}
	
	/**
	 * 打开默认实现
	 * @param target
	 * @param options
	 */
	function openApp(target, options) {
		var jqTarget = $(target),
		opt = $(target).data('app').options,
		wall = jqTarget.layout('panel', 'center'),
		uuid,
		appOpt;
		if (options) {
			if(!options['uuid']){
				uuid = UUID();
				options['uuid'] = uuid;
			}else{
				uuid = options['uuid'];
			}
			appOpt = options;
		} else {
			uuid = $(this).attr('app_id');
			appOpt = $(this).data("app");
		}
		
		if (opt.iframeOpen) {
			appOpt['iframe'] = true;
		}
		
		var thisAppWindow = $('div[w_id="' + uuid + '"]', wall);
		
		if (thisAppWindow.length) {
			thisAppWindow.window('open');
			return;
		}
		
		var appWindow = $('<div/>').attr('w_id', uuid).appendTo(wall);
		
		allWindow[uuid] = appWindow;
		
		var customOption = opt.onBeforeOpenApp.call(target, appOpt) || {};
		
		var defaultConfig = {
			height : 400,
			width : 700,
			resizable : true,
			maximizable : true,
			minimizable : true,
			shadow : false
		};
		
		var opened = $('div[w_id]', wall).length;
		
		if (opened > 1) {
			var T = opened * 25 + 10;
			var L = opened * 25 + 300;
			defaultConfig = $.extend(defaultConfig, {
					top : T,
					left : L
				});
		}
		
		var defaultRequiredConfig = {
			title : appOpt.text,
			inline : true,
			cache : true,
			onOpen : function () {
				appendToList($(this).attr('w_id'), $.data(this, 'panel').options.title);
				if (customOption.onOpen) {
					customOption.onOpen.call(this);
				} else {
					if (opt.onOpenApp)
						opt.onOpenApp.call(this);
				}
				
				prevOpenedApp = currentOpenedApp;
				currentOpenedApp = $(this).attr('w_id');
				$('li[l_id="' + $(this).attr('w_id') + '"]').attr('status', 'opened');
			},
			onClose : function () {
				var frame = $('iframe', this);
				if (frame.length > 0) { //释放iframe
					if (!/^http/i.test(frame[0].src)) {
						frame[0].contentWindow.document.write('');
					}
					frame[0].contentWindow.close();
					frame.remove();
					if ($.browser.msie) {
						CollectGarbage();
					}
				}
				
				removeListItem($(this).attr('w_id'));
				
				if (customOption.onClose) {
					customOption.onClose.call(this);
				} else {
					if (opt.onClosedApp)
						opt.onClosedApp.call(this);
					
				}
				
				delete allWindow[$(this).attr('w_id')];
				
				$(this).window("destroy");
				$('li[l_id="' + prevOpenedApp + '"]').addClass('selected');
			},
			onMinimize : function () {
				if ($(this).prev('.window-header').find('.panel-tool-restore').length == 1) {
					var opts = $.data(this, 'panel').options;
					opts.maximized = true;
				}
				if (customOption.onMinimize) {
					customOption.onMinimize.call(this);
				}
				$('li[l_id="' + $(this).attr('w_id') + '"]').attr('status', 'closed');
			},
			onMove : function (left, top) {
				var opts = $.data(this, 'panel').options;
				if (top < 0) {
					$(this).window("move", {
						"left" : left,
						"top" : 0
					});
					$(this).window("maximize");
				} else if (opts.maximized) {
					$(this).window("restore");
					$(this).window("move", {
						"left" : left + 100,
						"top" : top
					});
				}
				if (top > wall.height()) {
					$(this).window("move", {
						"left" : left,
						"top" : (wall.height() - 25)
					});
				}
				if (customOption.onMove) {
					customOption.onMove.call(this);
				}
			},
			onMaximize : function () {
				$(this).closest('.window').css("z-index", $.fn.window.defaults.zIndex++);
				if (customOption.onMaximize) {
					customOption.onMaximize.call(this);
				}
			}
		};
		
		var config = $.extend({}, defaultConfig, appOpt.cnf, customOption, defaultRequiredConfig);
		
		if (appOpt.href && !/^http/i.test(appOpt.href) && 　!appOpt.iframe) {
			config.href = appOpt.href;
		}
		appWindow.window(config);
		
		if(appOpt.id){
			appWindow.attr("id","app_window_"+appOpt.id);
		}
		
		if (appOpt.href && /^http/i.test(appOpt.href) || appOpt.iframe) {
			var iframe = $('<iframe/>').attr({
					width : '100%',
					height : '99%',
					frameborder : 0,
					src : appOpt.href
				});
			appWindow.append(iframe);
		}
		
		appWindow.prev('div.window-header').click(function (e) {
			var taskList = jqTarget.data('app').taskList;
			var list = taskList.find('ul.app-list-list');
			list.children().removeClass('selected');
			$('li[l_id="' + uuid + '"]', list).addClass('selected');
		});
		
		/**
		 * 添加任务栏站位
		 * @param uuid
		 * @param text
		 * @param status
		 */
		function appendToList(uuid, text) {
			var taskList = jqTarget.data('app').taskList;
			var list = taskList.find('ul.app-list-list');
			var wrap = list.parent();
			list.children().removeClass('selected');
			
			if ($('li[l_id="' + uuid + '"]', list).length) {
				$('li[l_id="' + uuid + '"]', list).addClass('selected');
			} else {
				var item = $('<li/>').attr("l_id", uuid).addClass('selected').text(text).attr('status', 'opened');
				list.append(item);
				item.click(function () {
					if ($(this).attr('status') == 'opened') {
						var currentWin = $('div[w_id="' + uuid + '"]', wall);
						var currentWinZindex = parseInt(currentWin.parent().css("z-index")) + 1;
						if (currentWinZindex != $.fn.window.defaults.zIndex) {
							currentWin.parent().css("z-index", $.fn.window.defaults.zIndex++);
						} else {
							$(this).attr('status', 'closed');
							currentWin.animate({
								opacity : 'show'
							}, 'slow', function () {
								$(this).window('minimize');
							});
						}
					} else {
						$(this).attr('status', 'opened');
						$('div[w_id="' + uuid + '"]', wall).window();
						list.children().removeClass('selected');
						$(this).addClass('selected');
					}
				});
				
				if (wrap.width() > taskList.width()) {
					wrap.width(taskList.width());
					$('div[class^="app-scroll-"]', taskList).show();
				}
				
				if (list.children().length != 1) {
					wrap.width(item.outerWidth() + 10 + wrap.width());
				} else {
					wrap.width(item.outerWidth() + 10);
				}
			}
		}
		
		/**
		 * 移除任务栏站位
		 * @param uuid
		 */
		function removeListItem(uuid) {
			var item = $('li[l_id="' + uuid + '"]');
			var wrap = item.parent().parent();
			
			wrap.width(wrap.width() - (item.outerWidth() + 4));
			
			item.remove();
		}
		return appWindow;
	}
	
	/**
	 * 创建窗口
	 * @param target
	 * @param options
	 */
	function createWindow(target, options) {
		return openApp(target, options);
	}
	
	/**
	 * 生成UUID
	 */
	function UUID() {
		function S4() {
			return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
		}
		
		return "UUID-" + (S4() + S4() + "-" + S4() + "-" + S4() + "-" + S4() + "-" + S4() + S4() + S4());
	}
	
	/**
	 * 墙纸设置
	 * @param target
	 * @param url
	 */
	function setWallpaper(target, url) {
		var wall = $(target).layout('panel', 'center');
		wall.css('background-image', 'url("' + url + '")');
		$.data(target, 'app').options.wallpaper = url;
	}
	
	/**
	 * 第一步是初始化layout
	 * 第二部初始化任务栏
	 * 第三部初始化桌面
	 * 第四步初始化开始菜单
	 * 第五步初始化时间
	 * 第六步初始化widget
	 */
	var initMethods = [initLayout, initTaskBlank, initDeskTop, initStartMenu, initCalendar, initWidget];
	
	/**
	 * 初始化
	 * @param target
	 * @param options
	 */
	function init(target, options) {
		if (loaded)
			return;
		
		var progress = $.messager.progress({ //实例化进度条
				title : options.lang.progress.title,
				msg : options.lang.progress.msg,
				interval : null
			});
		
		var progressBar = $.messager.progress('bar'); //获取进度条实例
		$.ajaxSetup({
			async : false
		});
		for (var i = 0;i<initMethods.length;i++) {
			var step = initMethods[i];
			progressBar.progressbar({
				text : options.lang[step.name]
			}).progressbar('setValue', ((parseInt(i) + 1) / initMethods.length) * 100);
			step.call(this, target);
		}
		$.messager.progress('close');
		$.ajaxSetup({
			async : true
		});
		loaded = true;
		
		options.onLoaded.call(target);
		
		setTimeout(function () {
			$('body').attr({ //禁用全局事件
				oncontextmenu : 'return false',
				onselectstart : 'return false',
				ondragstart : 'return false',
				onbeforecopy : 'return false'
			});
		}, 500);
	}
	
	$.fn.app = function (options, params) {
		if (typeof options === 'string') {
			return $.fn.app.methods[options.toLowerCase()].call(this, params);
		}
		options = options || {};
		return this.each(function () {
			var state = $.data(this, 'app');
			if (state) {
				options = $.extend(state.options, options);
				state.options = options;
			} else {
				options = $.extend({}, $.fn.app.defaults, $.fn.app.parseOptions, options);
				$.data(this, 'app', {
					options : options
				});
			}
			
			init(this, options);
		});
	};
	
	$.fn.app.methods = {
		options : function () {
			return this.data().app.options;
		},
		appcontainer : function () {
			return this.data().app.appContainer;
		},
		calendar : function () {
			return this.data().app.calendar;
		},
		start : function () {
			return this.data().app.start;
		},
		taskbar : function () {
			return this.data().app.taskBar;
		},
		tasklist : function () {
			return this.data().app.taskList;
		},
		startmenu : function () {
			return this.data().app.start.data().menu;
		},
		layout : function () {
			return this.data().layout;
		},
		setwallpaper : function (wallpaperUrl) {
			return this.each(function () {
				setWallpaper(this, wallpaperUrl);
			});
		},
		appreset : function () {
			return this.each(function () {
				appReset(this);
			});
		},
		seticonsize : function (size) {
			return this.each(function () {
				$.data(this, 'app').options.iconSize = size;
				var appContainer = $.data(this, 'app').appContainer;
				appContainer.find("img").height(size).width(size);
				appContainer.find("em,li").height(size + 45).width(size + 45);
				appReset(this);
			});
		},
		closeapp : function (appId) {
			$("#app_window_" + appId).window("close");
		},
		openapp : function (appId) {
			$("#app_window_" + appId).window("open");
		},
		createmenu : function (opt) {
			return createMenu(this[0], opt.data,opt.opt||{});
		},
		createwindow : function (options) {
			return createWindow(this[0], options);
		},
		refreshapp : function (href) {
			refresh(this[0], href);
		},
		closeall:function(){
			$.each(allWindow,function(){
				this.window("close");
			});
			allWindow = {};
		}
	};
	
	$.fn.app.parseOptions = function () {};
	
	$.fn.app.defaults = {
		taskBlankPos : 'south', //任务栏的位置（north|south|west|east）
		iconSize : 32, //app图标大小
		dbClick : true, //app打开是否双击
		dateFmt : 'yyyy年MM月dd日 <br/> hh:mm:ss', //时间格式化形式
		wallpaper : null, //壁纸,url路径
		onTaskBlankContextMenu : function (event, appid) {}, //任务栏右键事件
		onWallContextMenu : function (event) {}, //桌面右键事件
		onAppContextMenu : function (event, appid) {}, //app右键事件
		onBeforeOpenApp : function (appOpt) {}, //打开app之前的事件,可以返回自定义的窗口options
		onLoaded : function () {}, //app实例化完成事件
		onOpenApp : function () {}, //app打开事件
		onClosedApp : function () {}, //app关闭事件
		onStartMenuClick : function (item) {}, //开始菜单点击事件
		iframeOpen : false,
		loadUrl : { //远程数据加载路径
			app : 'apps.json', //app数据
			startMenu : 'startMenu.json', //开始菜单数据
			widget : 'widget.json'
		},
		lang : { //国际化
			initLayout : "init layout",
			initTaskBlank : "init task blank",
			initDeskTop : "init desktop",
			initStartMenu : "init start menu",
			initCalendar : "init calendar",
			initWidget : "init widget",
			progress : {
				title : 'Please waiting',
				msg : 'Loading data...'
			}
		}
	};
	
	$.parser.plugins.push('app');
})(jQuery);
