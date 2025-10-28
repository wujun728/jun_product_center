;!function () {
    if (layui === undefined) {
        console.error('请先引用layui.js文件.');
    } else {

        var modules = {
            common: 'extendModules/common',
            eleTree: 'extendModules/eleTree',
            treeTable: 'extendModules/treeTable'
        };
        layui.config({
            base: '/js/'
        }).extend(modules);
    }
}();

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