var sy = sy || {};

/**
 * 去字符串空格
 * 
 * @author 孙宇
 */
sy.trim = function(str) {
	return str.replace(/(^\s*)|(\s*$)/g, '');
};
sy.ltrim = function(str) {
	return str.replace(/(^\s*)/g, '');
};
sy.rtrim = function(str) {
	return str.replace(/(\s*$)/g, '');
};

/**
 * 判断开始字符是否是XX
 * 
 * @author 孙宇
 */
sy.startWith = function(source, str) {
	var reg = new RegExp("^" + str);
	return reg.test(source);
};
/**
 * 判断结束字符是否是XX
 * 
 * @author 孙宇
 */
sy.endWith = function(source, str) {
	var reg = new RegExp(str + "$");
	return reg.test(source);
};

/**
 * iframe自适应高度
 * 
 * @author 孙宇
 * 
 * @param iframe
 */
sy.autoIframeHeight = function(iframe) {
	iframe.style.height = iframe.contentWindow.document.body.scrollHeight
			+ "px";
};

/**
 * 设置iframe高度
 * 
 * @author 孙宇
 * 
 * @param iframe
 */
sy.setIframeHeight = function(iframe, height) {
	iframe.height = height;
};

Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	}
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
	return format;
}

// 定义一个方法，使日期列的显示正常：
function formatDatebox(value) {
	
	if (value == null || value == '') {
		return '';
	}
	var dt;
	if (value instanceof Date) {
		dt = value;
	} else {
		dt = new Date(value);
		if (isNaN(dt)) {
			value = value.replace(/\/Date\((-?\d+)\)\//, '$1'); // 标红的这段是关键代码，将那个长字符串的日期值转换成正常的JS日期格式
			dt = new Date();
			dt.setTime(value);
			//alert(dt)
		}
	}

	return dt.format("yyyy-MM-dd"); // 这里用到一个javascript的Date类型的拓展方法，这个是自己添加的拓展方法，在后面的步骤3定义
}

$.extend($.fn.datagrid.defaults.editors, {
	datebox : {
		init : function(container, options) {
			var input = $('<input type="text">').appendTo(container);
			input.datebox(options);
			return input;
		},
		destroy : function(target) {
			$(target).datebox('destroy');
		},
		getValue : function(target) {
			return $(target).datebox('getValue');
		},
		setValue : function(target, value) {
			$(target).datebox('setValue',value);
		},
		resize : function(target, width) {
			$(target).datebox('resize', width);
		}
	}
});

Date.CreateDateTime = function(s)
{
	var myDate= new Date(Date.parse(s.replace(/-/g, "/"))); 
    return myDate.format("yyyy-MM-dd");
};

Date.CreateDateTime = function(s,format)
{
	var myDate= new Date(Date.parse(s.replace(/-/g, "/"))); 
	if(format != undefined){
		 return myDate.format(format);
	}
	return myDate.format("yyyy-MM-dd");
};