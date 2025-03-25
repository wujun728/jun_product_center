
/** 获取当前项目的根路径，通过获取layui.js全路径截取assets之前的地址 */
function getProjectUrl() {
    var layuiDir = layui.cache.dir;
    if (!layuiDir) {
        var js = document.scripts, last = js.length - 1, src;
        for (var i = last; i > 0; i--) {
            if (js[i].readyState === 'interactive') {
                src = js[i].src;
                break;
            }
        }
        var jsPath = src || js[last].src;
        layuiDir = jsPath.substring(0, jsPath.lastIndexOf('/') + 1);
    }
    var projectUrl = layuiDir.substring(0, layuiDir.indexOf('assets'));
	//projectUrl = projectUrl +'snowy/'
    console.log("projectUrl="+projectUrl);
    return projectUrl;
}

function supportPreview(suffix) {
    var result = [];
    result.push('pdf');
    result.push('doc');
    result.push('docx');
    result.push('xls');
    result.push('xlsx');
    result.push('ppt');
    result.push('pptx');
    result.push('jpg');
    result.push('png');
    result.push('jpeg');
    result.push('tif');
    result.push('bmp');
    result.push('gif');
    result.push('txt');
    return result.indexOf(suffix) !== -1;
}

function isDoc(suffix) {
    var result = [];
    result.push('doc');
    result.push('docx');
    result.push('xls');
    result.push('xlsx');
    result.push('ppt');
    result.push('pptx');
    return result.indexOf(suffix) !== -1;
}

// 网络错误处理
function handleNetworkError(res) {
    if(res.code !== 0) {
        if(res.success !== null && res.success !== undefined) {
            if(!res.success) {
                // 登录已过期，请重新登录
                if(res.code === 1011008 || res.code === 1011004) {
                    window.location.href = "/";
                } else {
                    if(res.message) {
                        layui.notice.msg(res.message, {icon: 2});
                    } else {
                        layui.notice.msg("服务器出现异常，请联系管理员", {icon: 2});
                    }
                    return false;
                }
            }
        } else {
            if(res.code === 500) {
                if(res.msg === "error") {
                    layui.notice.msg("服务器出现异常，请联系管理员", {icon: 2});
                    return false;
                }
            }

            if(res.code === 404) {
                if(res.msg === "error") {
                    layui.notice.msg("资源路径不存在，请检查请求地址", {icon: 2});
                    return false;
                }
            }
        }
    } else {
        //网络错误
        if(res.msg === "timeout") {
            layui.notice.msg("请求超时，请检查网络状态", {icon: 2});
            return false;
        }
        if(res.msg === "error") {
            layui.notice.msg("网络错误，请检查网络连接", {icon: 2});
            return false;
        }
    }
    return true;
}




///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////

/*菜单选中*/
function selectMenu(menuUrl) {
    var menuObj = $('div.layui-side-menu a[href="' + menuUrl + '"]');
    menuObj.parents('li.layui-nav-item').addClass('layui-nav-itemed');
    menuObj.parents('dl.layui-nav-child').parent().addClass('layui-nav-itemed');
    menuObj.parent().addClass('layui-this');
}

/**
 * @Description 获取 datagrid pageList
 * @returns {Array}
 */
function getPageList() {
    pageList = [10, 20, 50, 100, 150, 200];
    return pageList;
}

/**
 * @Description 获取datagrid pageSize
 * @returns
 */
function getPageSize() {
    var pageList = getPageList();
    return pageList[0];
}

/**
 * @Description 表格加载异常提示
 * @returns
 */
function getLoadErrorMsg() {
    var text = {none: '没有找到匹配的记录'};
    return text;
}

/**
 * 获取排序字段
 * */
function getSort(tableId) {
    var sortParam = null;
    var obj = $('[lay-id="' + tableId + '"] .layui-table-sort.layui-inline[lay-sort="desc"]');
    if (obj.length > 0) {
        sortParam = {sortField: obj.parents('th').attr('data-field'), sortMethod: 'desc'};
        return sortParam;
    }
    obj = $('[lay-id="' + tableId + '"] .layui-table-sort.layui-inline[lay-sort="asc"]');
    if (obj.length > 0) {
        sortParam = {sortField: obj.parents('th').attr('data-field'), sortMethod: 'asc'};
        return sortParam;
    }
    return sortParam;
}

/**
 * 把一个表单序列化成一个json对象
 *@Description 把一个表单序列化成一个json对象
 *@param formObj 表单对象
 */
function serializeJson(formObj) {
    //把一个表单序列化成一个json对象
    var serializeObj = {};
    $.each(formObj.serializeArray(), function (i, item) {
        serializeObj[item.name] = $.trim(item.value);
    });
    return serializeObj;
}


/**获取tabId*/
function getTabId(url){
    var tabId = url.replace(/\//g, '_') + "_tabid";
    tabId = tabId.substring(1);
    return tabId;
}

