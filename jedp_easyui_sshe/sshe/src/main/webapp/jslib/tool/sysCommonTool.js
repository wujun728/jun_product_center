var systool = systool || {};


/**
 *  创建统一的功能按钮BUTTON
 * 
 * @example systool.createFuncButton('icon class','按钮','按钮提示','事件');
 * 
 * @returns  返回创建的BUTTON字符串
 */
systool.createFuncButton = function(icon,name,title,event) {
	
	var firstString = "<a onclick=\""+event+"\" title=\'"+title+"\' data-options=\"iconCls:'"+icon+"',plain:true\" class=\"easyui-linkbutton l-btn l-btn-plain\" href=\"javascript:void(0);\" group=\"\" id=\"\"><span class=\"l-btn-left\"><span class=\"l-btn-text "+icon+" l-btn-icon-left\">"+name+"</span></span></a>";
		
	return firstString;
};