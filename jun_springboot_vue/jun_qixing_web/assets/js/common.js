window.rootPath = (function (src) {
	src = document.currentScript
		? document.currentScript.src
		: document.scripts[document.scripts.length - 1].src;
		//console.log("src="+src);
	return src.substring(0, src.lastIndexOf("/") + 1);
})();
window.rootPath = /* getProjectUrl() + */ '/assets/';
//console.log("rootPath="+rootPath);
if (typeof $ == "undefined") {
	window.jQuery = layui.jquery;
	window.$ = layui.jquery;
}
if (typeof moduleInit == "undefined") {
	window.moduleInit = [];
	//window.moduleInit = ['tool'];
} 
//console.log("moduleInit="+moduleInit);
var module = {
	steps: 'steps/steps',
    notice: 'notice/notice',
    cascader: 'cascader/cascader',
    //dropdown: 'dropdown/dropdown',
    fileChoose: 'fileChoose/fileChoose',
    Split: 'Split/Split',
    Cropper: 'Cropper/Cropper',
    tagsInput: 'tagsInput/tagsInput',
    citypicker: 'city-picker/city-picker',
    introJs: 'introJs/introJs',
    zTree: 'zTree/zTree',
    iconPicker: 'iconPicker/iconPicker', 
    editormd: 'editormd/editormd.min', 
    tinymce: 'tinymce/tinymce.min', 
	tool: 'tool',
	admin: 'admin',
	index: 'index',
	tableSelect: 'tableSelect', 
	oaSchedule: 'oaSchedule',
	//treeTable: 'treeTable', 
	//treetable: 'treetable-lay/treetable', 
	employeepicker: 'employeepicker', 
	oaTool: 'oaTool', 
    xnUtil: 'xnUtil/xnUtil'
};
if (moduleInit.length > 0) {
	for (var i = 0; i < moduleInit.length; i++) {
		module[moduleInit[i]] = moduleInit[i];
	}
}
moduleInit.push('admin','xnUtil','tool','index');
//console.log("moduleInit="+moduleInit);

/** EasyWeb iframe v3.1.8 date:2020-05-04 License By http://xiaonuo.vip */
layui.config({  // common.js是配置layui扩展模块的目录，每个页面都需要引入
    version: '318',   // 更新组件缓存，设为true不缓存，也可以设一个固定值
    base: /* getProjectUrl() + */ '/assets/module/',
	//base: rootPath + "module/",
    pageTabs: true,  // 默认关闭多标签
    // 请求完成后预处理
    ajaxSuccessBefore: function (res, url, obj) {
        //关闭加载层
        layui.layer.closeAll('loading');
        if(obj.param.dataType === "html") {
            return true;
        } else {
            return handleNetworkError(res);
        }
    }
}).extend(module).use(moduleInit, function () {
/*
}).extend({
    steps: 'steps/steps',
    notice: 'notice/notice',
    cascader: 'cascader/cascader',
    //dropdown: 'dropdown/dropdown',
    fileChoose: 'fileChoose/fileChoose',
    Split: 'Split/Split',
    Cropper: 'Cropper/Cropper',
    tagsInput: 'tagsInput/tagsInput',
    citypicker: 'city-picker/city-picker',
    introJs: 'introJs/introJs',
    zTree: 'zTree/zTree',
    iconPicker: 'iconPicker/iconPicker', 
	tool: 'tool',
	tableSelect: 'tableSelect', 
	oaSchedule: 'oaSchedule',
	treetable: 'treetable-lay/treetable', 
	employeepicker: 'employeepicker', 
	oaTool: 'oaTool', 
    xnUtil: 'xnUtil/xnUtil'
}).use(['layer', 'admin', 'table', 'xnUtil', 'notice','tool','oaSchedule','oaTool','upload'], function () { */
    var $ = layui.jquery;
    var admin = layui.admin;
    var xnUtil = layui.xnUtil;
    var table = layui.table;
	var tool = layui.tool; 
	var upload = layui.upload; 
	
    //没有默认主题时，设置默认主题
    var defaultTheme = admin.getTempData('defaultTheme', true);
    if(defaultTheme === undefined) {
        admin.changeTheme('theme-snowy');
    }
    //表格重载时ajaxSuccessBefore无法捕获ajax结果，使用此处判断
    $.ajaxSetup({
        timeout : 10000, //超时时间设置，单位毫秒，默认10秒
        complete: function (XMLHttpRequest, textStatus) {
            //关闭加载层
            layui.layer.closeAll('loading');
            if(XMLHttpRequest.responseJSON !== null && XMLHttpRequest.responseJSON !== undefined) {
                if(!XMLHttpRequest.responseJSON.success) {
                    // 登录已过期，请重新登录
                    if(XMLHttpRequest.responseJSON.code === 1011008) {
                        window.location.href = "/";
                    }
                }
            }
        }
    }); 
    // 页面载入就检查按钮权限
    xnUtil.renderPerm();
	if (typeof gouguInit === 'function') {
		gouguInit();
	}
});
