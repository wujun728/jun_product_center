/***

easyui 扩展，表单校验，combobox，form，依赖calutil.js

*/


///扩展easyui表单的验证
$.extend($.fn.validatebox.defaults.rules, {
	// 验证汉子
	CHS : {
		validator : function(value) {
			return /^[\u0391-\uFFE5]+$/.test(value);
		},
		message : '只能输入汉字'
	},
	
	notCHS:{
		validator : function(value) {
			return !(/^[\u0391-\uFFE5]+$/.test(value));
		},
		message : '不能输入汉字'
	},
	
	normal:{
        validator : function(value) { 
            return /^[a-zA-Z]\w+$/i.test(value); 
        }, 
        message : '以字母开头,且只能输入字母、数字、下划线'
	},
	
	word:{
        validator : function(value) { 
            return /^\w+$/i.test(value); 
        }, 
        message : '只能输入字母、数字、下划线'
	},
	
	letter : {// 验证英语 
        validator : function(value) { 
            return /^[A-Za-z]+$/i.test(value); 
        }, 
        message : '只能输入字母'
    }, 
	 // 验证英文字母、数字
    letterAndNum : {
	    validator : function(value) {
	    return /^[a-zA-Z0-9]+$/.test(value);},
	    message : "只能包括英文字母、数字"
	},
    
	chinese : {// 验证中文
		validator : function(value) {
			return /^[\Α-\￥]+$/i.test(value);
		},
		message : '请输入中文'
	},
	name : {// 验证姓名，可以是中文或英文
		validator : function(value) {
			return /^[\Α-\￥]+$/i.test(value)
					| /^\w+[\w\s]+\w+$/i.test(value);
		},
		message : '请输入姓名'
	},
	
	
	minLength : {
		validator : function(value, param) {
			return value.length >= param[0];
		},
		message : '请输入至少{0}个字符.'
	},
	maxLength : {
		validator : function(value, param) {
			return param[0] >= value.length;
		},
		message : '请输入最大{0}位字符.'
	},
	length : {
		validator : function(value, param) {
			var len = $.trim(value).length;
			return len >= param[0] && len <= param[1];
		},
		message : "输入内容长度必须介于{0}和{1}之间."
	},
	
	date : {
		validator : function(value) {
			// 格式yyyy-MM-dd或yyyy-M-d
			return /^(?:(?!0000)[0-9]{4}([-]?)(?:(?:0?[1-9]|1[0-2])\1(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])\1(?:29|30)|(?:0?[13578]|1[02])\1(?:31))|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)([-]?)0?2\2(?:29))$/i
					.test(value);
		},
		message : '请输入合适的日期格式'
	},
	
	isDateTime : {
		validator : function(value) {
			return /(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/
					.test(value);
		},
		message : '请输入合适的日期格式'
	},
	
	intOrFloat : {// 验证整数或小数
		validator : function(value) {
			return /^\d+(\.\d+)?$/i.test(value);
		},
		message : '请输入数字，格式为整数或小数'
	},
	digits : {// 验证整数 可正负数
		validator : function(value) {
			// return /^[+]?[1-9]+\d*$/i.test(value);
			return /^-?[1-9]\d*$/.test(value);
		},
		message : '请输入整数'
	},
	
	positiveInteger:{//校验正整数
        validator:function(value,param){  
            return /^[+]?[1-9]\d*$/.test(value);  
        },  
        message: '请输入正整数'
	},
	positiveNumber:{
        validator:function(value,param){  
            return /^\d+(\.\d+)?$/.test(value) && Number(value)>0;  
        },  
        message: '请输入正数'
	},
	nonnegative:{
        validator:function(value,param){  
            return /^\d+(\.\d+)?$/.test(value) && Number(value)>=0;  
        },  
        message: '请输入非负数'
	},
    rateCheck:{  
        validator:function(value,param){  
            if(/^[0-9]+([.]{1}[0-9]{1,2})?$/.test(value)){  
                return parseFloat(value) > parseFloat(param[0]) && parseFloat(value) <= parseFloat(param[1]);  
            }else{  
                return false;  
            }  
        },  
        message:'请输入{0}到{1}之间的最多俩位小数的数字'  
    },
    same:{ 
        validator : function(value, param){ 
            if($("#"+param[0]).val() != "" && value != ""){ 
                return $("#"+param[0]).val() == value; 
            }else{ 
                return true; 
            } 
        }, 
        message : '两次输入的{2}不一致！'   
    },
//--------------------------------------------------------------------------
	
	// 移动手机号码验证
	mobile : {// value值为文本框中的值
		validator : function(value) {
			var reg = /^1[3|4|5|8|9]\d{9}$/;
			return reg.test(value);
		},
		message : '输入手机号码格式不准确.'
	},
	// 国内邮编验证
	zipcode : {
		validator : function(value) {
			var reg = /^[1-9]\d{5}$/;
			return reg.test(value);
		},
		message : '邮编必须是非0开始的6位数字.'
	},
	phone : {// 验证电话号码
		validator : function(value) {
			return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i
					.test(value);
		},
		message : '格式不正确,请使用下面格式:020-88888888'
	},
	mobile : {// 验证手机号码
		validator : function(value) {
			return /^(13|15|18)\d{9}$/i.test(value);
		},
		message : '手机号码格式不正确'
	},
	phoneNumber : {
		validator : function(value) {
			if (value.length == 11) {
				return /^(13|15|18)\d{9}$/i.test(value);
			} else {
				return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i
						.test(value);
			}
		},
		message : '联系方式格式错误，格式仅支持移动及座机'
	},
	ip : {// 验证IP地址
		validator : function(value) {
			
			return /^(\d+)\.(\d+)\.(\d+)\.(\d+)$/g.test(value);
		},
		message : 'IP地址格式不正确'
	},
	idcared : {
		validator : function(value, param) {
			return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value);
		},
		message : '不是有效的身份证号码'
	},
	//金钱校验 /(^[-+]?[1-9]\d*(\.\d{1,2})?$)|(^[-+]?[0]{1}(\.\d{1,2})?$)/
	money : {
		validator : function(value) {
			var b=/^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/;
			return b.test(value);
		},
		message:'必须是正数，最多两位小数'
		
	},
	// 扩展远程验证
	// param的值为[]中值,第三个参数为form的id，之后参数为form中字段名称
	remotex : {
		validator : function(value, param) {
			var _data = {};
			_data[param[1]] = value;
			if (param.length > 3) {
				var _form = $("#" + param[2])[0];
				for (var i = 3; i < param.length; i++) {
					_data[param[i]] = _form[param[i]].value;
					var temp = this.prototype;
				}
			}
			var _2b = $.ajax({
				url : param[0],
				dataType : "json",
				data : _data,
				async : false,
				cache : false,
				type : "post"
			}).responseText;
			return _2b == "true";
		},
		message : ''
	}
});

// combobox 扩展，增加字典数据支持,增加6个字段：

$.extend($.fn.combobox.defaults, {
	// codeClass: null,
	// enableNull: false,
	// nullText: '(全部)',
	// dataField:null 指定数据为的json中的某个字段
	// defaultFirst:false
	// dataFn : 数据函数
	loader : function (param, success, error) {
		function buildItems(json) {
			//deep clone
			var data=$.extend(true,[],opts.dataField?json[opts.dataField]:json);
			if (data instanceof Array && opts.enableNull && data.length) {
				var item = {};
				item[opts.valueField] = '';
				if (opts.nullText)
					item[opts.textField] = opts.nullText;
				else
					item[opts.textField] = "(全部)";
				data.splice(0, 0, item);
			}
			$(target).combobox("clear");
			if(data.length && opts.defaultFirst){
				data[0].selected=true;
			}
			success(data);
		}

		var target=this;
		var opts = $(target).combobox('options');
		if (opts.codeClass) {
			var items = $.dictManager.getCodeItems(opts.codeClass);
			buildItems(items);
		}else if(opts.data){
			buildItems(opts.data);
		}else if(opts.dataFn){
			buildItems(opts.dataFn());
		}else if (opts.url) {
			$.ajax({
				type : opts.method,
				url : opts.url,
				data : param,
				dataType : 'json',
				success : function (result) {
					buildItems(result);
				},
				error : function () {
					error.apply(this, arguments);
				}
			});
		}
	},
	onLoadSuccess: function(){
		var target=this;
		var opts = $(target).combobox('options');
		var data=$(target).combobox("getData");
		console.log("onLoadSuccess_"+opts.codeClass)
		if(data.length){
			setTimeout(function(){
				if(opts.originalValue){
					$(target).combobox("select",opts.originalValue);
				}else if(opts.defaultFirst && data.length ){
					$(target).combobox("select",data[0][opts.valueField]);
				}
			},10)
		}
	}
});

//扩展combotree支持enableNull
//dataField:null 指定数据为的json中的某个字段
//enableNull: false,
//nullText:'(全部)'
//nullItem: null,
$.extend($.fn.combotree.defaults,{
	loadFilter:function(json){
		var opts = $(this).tree('options');
		var data=$.extend(true,[],opts.dataField?json[opts.dataField]:json);
		if(!opts.enableNull&&!opts.nullItem){
			return data;
		}
		if(opts.nullItem){
			data.splice(0, 0, opts.nullItem);
			return data;
		}					
		var nullText=opts.nullText?opts.nullText:"(全部)";
		var nullItem={
				id:null,
				text:nullText,
				iconCls:"icon-reload"
		}
		data.splice(0, 0, nullItem);
		return data;
	}
});


//---------------- reset begin---------------

//表单组件重置的时候，如果有value属性值，那么，将不会被重置，表单填写的默认值

/**
 * override  reset method 
 * 	if combobox has originalValue then set the value,
 * 	else set the value of item with selected=true
 */

/**
			<input name="ispayed" class="easyui-combobox"  style="width:60px;"
					data-options="valueField:'code',textField:'name',defaultFirst:true,editable:false,panelHeight:'auto',							
					required:true,enableNull:false,codeClass:'64'">
 *
 *
 */
 
$.extend($.fn.combobox.methods,{
	reset: function(jq){
		return jq.each(function(){
			var opts = $(this).combobox('options');
			if (opts.multiple){
				$(this).combobox('setValues', opts.originalValue);
			} else {
				if(opts.originalValue){
					$(this).combobox('setValue', opts.originalValue);	
				}else{
					var data=$(this).combobox('getData');
					for(var i=0;i<data.length;i++){
						if(data[i].selected){
							$(this).combobox('setValue',data[i][opts.valueField]);
						}
					}
				}
			}
		});
	}
});
//<input value="${nowDateFmt }" name="stockindate" style="width:100px" type="text" class="easyui-datebox" required="true">
$.extend($.fn.datebox.methods,{
		reset: function(jq){
			return jq.each(function(){
				var opts = $(this).datebox('options');
				$(this).datebox('setValue', opts.originalValue);
			});
		}
});
//<input value="${nowDateFmt }" name="stockindate" style="width:100px" type="text" class="easyui-datebox" required="true">
$.extend($.fn.form.methods,{
	reset: function(jq){
		function reset(target){
			target.reset();
			var t = $(target);
			
			var plugins = ['textbox','combo','combobox','combotree','combogrid','datebox','datetimebox','spinner','timespinner','numberbox','numberspinner','slider'];
			for(var i=0; i<plugins.length; i++){
				var plugin = plugins[i];
				var r = t.find('.'+plugin+'-f');
				if (r.length && r[plugin]){
					r[plugin]('reset');
				}
			}
			validate(target);
		}
		function validate(target){
			if ($.fn.validatebox){
				var t = $(target);
				t.find('.validatebox-text:not(:disabled)').validatebox('validate');
				var invalidbox = t.find('.validatebox-invalid');
				invalidbox.filter(':not(:disabled):first').focus();
				return invalidbox.length == 0;
			}
			return true;
		}
		return jq.each(function(){
			reset(this);
		});
	}
})

//---------------- reset end-----------------

/**
 * linkbutton方法扩展
 * 
 * @param {Object}
 *            jq
 */
$.extend($.fn.linkbutton.methods, {
    /**
	 * 激活选项（覆盖重写）
	 * 
	 * @param {Object}
	 *            jq
	 */
    enable: function(jq){
        return jq.each(function(){
            var state = $.data(this, 'linkbutton');
            if ($(this).hasClass('l-btn-disabled')) {
                var itemData = state._eventsStore;
                // 恢复超链接
                if (itemData.href) {
                    $(this).attr("href", itemData.href);
                }
                // 回复点击事件
                if (itemData.onclicks) {
                    for (var j = 0; j < itemData.onclicks.length; j++) {
                        $(this).bind('click', itemData.onclicks[j]);
                    }
                }
                // 设置target为null，清空存储的事件处理程序
                itemData.target = null;
                itemData.onclicks = [];
                $(this).removeClass('l-btn-disabled');
            }
        });
    },
    /**
	 * 禁用选项（覆盖重写）
	 * 
	 * @param {Object}
	 *            jq
	 */
    disable: function(jq){
        return jq.each(function(){
            var state = $.data(this, 'linkbutton');
            if (!state._eventsStore)
                state._eventsStore = {};
            if (!$(this).hasClass('l-btn-disabled')) {
                var eventsStore = {};
                eventsStore.target = this;
                eventsStore.onclicks = [];
                // 处理超链接
                var strHref = $(this).attr("href");
                if (strHref) {
                    eventsStore.href = strHref;
                    $(this).attr("href", "javascript:void(0)");
                }
                // 处理直接耦合绑定到onclick属性上的事件
                var onclickStr = $(this).attr("onclick");
                if (onclickStr && onclickStr != "") {
                    eventsStore.onclicks[eventsStore.onclicks.length] = new Function(onclickStr);
                    $(this).attr("onclick", "");
                }
                // 处理使用jquery绑定的事件
                var eventDatas = $(this).data("events") || $._data(this, 'events');
                if (eventDatas["click"]) {
                    var eventData = eventDatas["click"];
                    for (var i = 0; i < eventData.length; i++) {
                        if (eventData[i].namespace != "menu") {
                            eventsStore.onclicks[eventsStore.onclicks.length] = eventData[i]["handler"];
                            $(this).unbind('click', eventData[i]["handler"]);
                            i--;
                        }
                    }
                }
                state._eventsStore = eventsStore;
                $(this).addClass('l-btn-disabled');
            }
        });
    }
});

/**
 * --------------只显示月份扩展-----------------------------
 */
$.extend($.fn.datebox.methods, {
	yearmonth:function(jq,callback){
		var p = jq.datebox('panel'), // 日期选择对象
			tds = p.find('div.calendar-menu-month-inner td'),// 日期选择对象中月份
			span = p.find('.calendar-title').find("span"); // 显示月份层的触发控件
			jq.datebox({
	            onShowPanel: function () {// 显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
	                span.click(); // 触发click事件弹出月份层
	                setTimeout(function () {// 延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
	                    tds = p.find('div.calendar-menu-month-inner td');
	                    // 失效tds.unbind("click",_customer_select_year_month).bind("click",_customer_select_year_month);
	                    tds.unbind("click").bind("click",_customer_select_year_month);// 有效
	                }, 0);
	                var _customer_select_year_month=function (e) {
                        e.stopPropagation(); // 禁止冒泡执行easyui给月份绑定的事件
                        var year = /\d{4}/.exec(span.html())[0]// 得到年份
                        , month = parseInt($(this).attr('abbr'), 10); // 月份，这里不需要+1
                        var temp=new Date();temp.setFullYear(year);temp.setMonth(month-1);
                        jq.datebox('hidePanel')// 隐藏日期对象
                        .datebox('setValue', temp.format("yyyy-MM")); // 设置日期的值
                        if(callback)
                        	callback(year,month);
                    }
	            },
	            parser: function (s) {
	                if (!s) return new Date();
	                var arr = s.split('-');
	                return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);
	            },
	            formatter: function (d) { d.fromat("yyyy-MM"); }
	   });
	}
});	

// MaskUtil实现类似datagrid 加载效果，在easyui环境下，无需引入其他js
// MaskUtil.mask();启用遮罩
// MaskUtil.unmask(); 关闭遮罩

var MaskUtil = (function(){  
    
    var $mask,$maskMsg;  
      
    var defMsg = '正在处理，请稍待。。。';  
      
    function init(){  
        if(!$mask){  
            $mask = $("<div class=\"datagrid-mask mymask\"></div>").appendTo("body");  
        }  
        if(!$maskMsg){  
            $maskMsg = $("<div class=\"datagrid-mask-msg mymask\">"+defMsg+"</div>")  
                .appendTo("body").css({'font-size':'12px'});
        }  
          
        $mask.css({width:"100%",height:$(document).height()});  
          
        $maskMsg.css({  
            left:($(document.body).outerWidth(true) - 190) / 2,top:$(document).scrollTop()+($(window).height() - 45) / 2,
        });   
                  
    }        
    return {  
        mask:function(msg){  
            init();  
            $mask.show();  
            $maskMsg.html(msg||defMsg).show();  
        }  
        ,unmask:function(){  
            $mask.hide();  
            $maskMsg.hide();  
        }
        ,MaskIt:function(selector){
        	//遮罩层 
        	var $shade=$("<div class='shade'></div>").css({
        		position:'absolute', 
        		top:$(selector).position().top, 
        		left:$(selector).position().left,
        		width:$(selector).width(),
        		heigth:$(selector).height(),
        		backgroundColor:"#808080", 
        		opacity:0.1, 
        		zIndex:9999
        	}).height($(selector).height()).width($(selector).width());
        	$(selector).before($shade);
        	$(selector).data("mask",true);
        }
        ,UnMaskIt:function(selector){
            if($(selector).data("mask")==true){
                $(selector).parent().find(".shade").remove();
                $(selector).data("mask",false);
            }
            $(selector).data("mask",false);
        }
    }  
      
}());  

$.extend($.fn.form.methods, {
	dataFormatFn:function(date){
		return date;
	},
	myLoad : function (jq, param) {
		return jq.each(function () {
			load(this, param);
		});

		function load(target, param) {
			if (!$.data(target, "form")) {
				$.data(target, "form", {
					options : $.extend({}, $.fn.form.defaults)
				});
			}
			var options = $.data(target, "form").options;
			if (typeof param == "string") {
				var params = {};
				if (options.onBeforeLoad.call(target, params) == false) {
					return;
				}
				$.ajax({
					url : param,
					data : params,
					dataType : "json",
					success : function (rsp) {
						loadData(rsp);
					},
					error : function () {
						options.onLoadError.apply(target, arguments);
					}
				});
			} else {
				loadData(param);
			}
			function loadData(dd) {
				var form = $(target);
				var formFields = form.find("input[name],select[name],textarea[name]");
				formFields.each(function(){
					var name = this.name;
					var value = jQuery.proxy(function(){try{return eval('this.'+name);}catch(e){return "";}},dd)();
					var rr = setNormalVal(name,value);
					if (!rr.length) {
						var fn = form.find("input[numberboxName=\"" + name + "\"]");
						var ft = form.find("input[textboxName=\"" + name + "\"]");
						if (fn.length) {
							fn.numberbox("setValue", value);
						}if(ft.length){
							ft.textbox("setValue", value);
						}else {
							$("input[name=\"" + name + "\"]", form).val(value);
							$("textarea[name=\"" + name + "\"]", form).val(value);
							$("select[name=\"" + name + "\"]", form).val(value);
						}
					}
					setPlugsVal(name,value);
				});
				options.onLoadSuccess.call(target, dd);
				$(target).form("validate");
			};
			function setNormalVal(key, val) {
				var rr = $(target).find("input[name=\"" + key + "\"][type=radio], input[name=\"" + key + "\"][type=checkbox]");
				rr._propAttr("checked", false);
				rr.each(function () {
					var f = $(this);
					if (f.val() == String(val) || $.inArray(f.val(), val) >= 0) {
						f._propAttr("checked", true);
					}
				});
				return rr;
			};
						
			function setPlugsVal(key, val) {
				var form = $(target);
				var cc = ["combobox", "combotree", "combogrid", "datetimebox", "datebox", "combo"];
				var c = form.find("[comboName=\"" + key + "\"]");
				if (c.length) {
					for (var i = 0; i < cc.length; i++) {
						var combo = cc[i];
						if (c.hasClass(combo + "-f")) {
							//时间解析
							if(combo=="datebox"||combo=="datetimebox"){
								var date_formatFn=eval(c.attr("date-formatFn"));
								if(date_formatFn){
									val=date_formatFn(val);
								}else{
									val=$.fn.form.methods.dataFormatFn(val,combo);
								}
							}													
							if (c[combo]("options").multiple) {
								c[combo]("setValues", val);
							} else {
								c[combo]("setValue", val);
							}
							return;
						}
					}
				}
			};
		};
	}
});

$.extend($.fn.form.methods, {
	dataFormatFn:function(date,comboName){
		if(!isNaN(date)){
			if((date+"").length==13){
				if(comboName=="datebox")
					return calUtil.formatDate(date);
				else if(comboName=="datetimebox")
					return calUtil.formatDateTime(date);
			}else{
				return calUtil.formatLong(date);
			}
		}
		return date;
	}
});

//------------------------ 默认日期、时间格式校验 begin--------------------------
$.extend($.fn.validatebox.defaults.rules, {
	_default_date_fmt: {
		validator : function(value) {
			if(value.split("-").length!=3){
				return false;
			}
			var date=new Date(value);
			return date=='Invalid Date'?false:true;
		},
		message : '请输入正确的时间格式！形如：1970-01-01'
	},
	_default_datetime_fmt:{
		validator : function(value) {
			var s=value.split(" ");
			var date=new Date(s[0]+"T"+s[1]);
			return date=='Invalid Date'?false:true;
		},
		message : '请输入正确的时间格式！形如：1970-01-01 00:00:00'
	}
});

$.extend($.fn.datebox.defaults,{
	validType:'_default_date_fmt'
})
$.extend($.fn.datetimebox.defaults,{
	validType:'_default_datetime_fmt'
})
//  ------------------------ 日期校验 end--------------------------


/**
 * datagrid：拓展对id操作
 */

$.extend($.fn.datagrid.methods, {
	getRowByIndex : function ($dg, index) {
		var opts = $.data($dg[0], "datagrid").options;
		var data = $.data($dg[0], "datagrid").data;
		return data.rows[index];
	},
	getRowById : function ($dg, idValue) {
		var opts = $.data($dg[0], "datagrid").options;
		var data = $.data($dg[0], "datagrid").data;
		var rows = data.rows;
		for (var i = 0; i < rows.length; i++) {
			if (rows[i][opts.idField] == idValue) {
				return rows[i];
			}
		}
	},
	/**
	 * 隐藏字段
	 * 
	 * fields: 字段名的数组
	 $("#fooddetail-dg").datagrid("hideColumns",["remark","createDate"]);
	 */
	hideColumns:function(target,hide_fields){
		console.log(JSON.stringify(hide_fields))
		var $dg=$(target);
		for(var index=0; index < hide_fields.length; index++){
			$dg.datagrid("hideColumn",hide_fields[index]);
		}
	},
	
	addColumns:function($dg,columns){
		var _columns=$dg.datagrid("options").columns[0];
		$dg.datagrid({columns:[_columns.concat(columns)]});
	},
	fitColumnsByForce:function($dg) {
		var target = $dg[0];
		var opts = $.data(target, "datagrid").options;
		var dc = $.data(target, "datagrid").dc;
		var header = dc.view2.children("div.datagrid-header");
		var fields = $dg.datagrid("getColumnFields", false);
		var innerHeader = header.children("div.datagrid-header-inner").show();
		if (!opts.showHeader) {
			innerHeader.hide();
		}
		//所有可见列的宽度
		var visableWidth = 0;
		var cols_full_width = 0;
		var cols_old_width = 0;

		var first_col = $dg.datagrid("getColumnOption", fields[0]);
		var last_col = $dg.datagrid("getColumnOption", fields[fields.length - 1]);
		var first_col_opt = $dg.find("th:first-child").attr("data-options");
		var first_col_width = eval("({" + first_col_opt + "})").width;

		$dg.find("th").each(function () {
			var col_opt = eval("({" + $(this).attr("data-options") + "})");
			var col = $dg.datagrid("getColumnOption", col_opt.field);
			if (isVisible(col)) {
				cols_old_width += col.boxWidth;
				//等比公式
				col.width = col_opt.width / first_col_width * first_col.width;				
				col.boxWidth = col_opt.width / first_col_width * first_col.boxWidth;

				visableWidth += col.width;
				cols_full_width += col.boxWidth;
			}
		})

		//加上所有列因为调整的增量
		var fullWidth = header.width() + cols_full_width - cols_old_width;
		var increasedWidth = fullWidth - header.find("table").width() - opts.scrollbarSize;
		console.log("header.width():"+header.width()+"\tcols_full_width:"+cols_full_width+"\tcols_old_width:"+cols_old_width)
		console.log("table.width():"+header.find("table").width() +"\tincreasedWidth:"+increasedWidth)
		var rate = increasedWidth / visableWidth;
		for (var i = 0; i < fields.length; i++) {
			var col = $dg.datagrid("getColumnOption", fields[i]);
			if (isVisible(col)) {
				//求每一列各自的增量
				var increment = Math.floor(col.width * rate);
				fitColumnWidth(col, increment);
				increasedWidth -= increment;
			}
		}
		$dg.datagrid("fixColumnSize");
		$dg.datagrid("fitColumns");//调用自身的调整列方法
		function fitColumnWidth(col, increment) {
			col.boxWidth += increment;
			header.find("td[field=\"" + col.field + "\"] div.datagrid-cell").width(col.boxWidth);
		};
		function isVisible(col) {
			if (!col.hidden && !col.checkbox && !col.auto) {
				return true;
			}
		};
	}
});

//  ------------------------ datagrid end--------------------------