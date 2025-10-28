;!function () {
    $('[lay-filter="menu"] a').click(function () {
        var url = $(this).attr('href');
        if (url) {
            var tabId = url.replace(/\//g, '_') + "_tabid";
            tabId = tabId.substring(1);
            addTab($(this).text(), url, tabId);
        }
        return false;//阻止href跳转
    });
    $('[lay-filter="menu"] li.layui-nav-item:first').addClass('layui-nav-itemed');
    //tabs down
    $('#tabs_down').hover(function () {
        $('[lay-filter="pagetabs-nav"] .layui-nav-child').addClass('layui-show');
    }, function () {
        $('[lay-filter="pagetabs-nav"] .layui-nav-child').removeClass('layui-show');
    });
    layui.element.on('nav(pagetabs-nav)', function (elem) {
        var dd = elem.parent();
        dd.removeClass('layui-this');
        dd.parent().removeClass('layui-show');
    });
    //tab切换
    layui.element.on('tab(menu_tabs)', function (data) {
        var id = $(this).attr('lay-id');
        $('#tabs_header li').removeClass('layui-this');
        $('#tabs_header [lay-id="' + id + '"]').addClass('layui-this');
        $('#tabs_content .tabsbody-item').removeClass('layui-show');
        $('#tabs_content iframe[data-id="' + id + '"]').parent().addClass('layui-show');
    });
    //tab删除
    layui.element.on('tabDelete(menu_tabs)', function (data) {
        var id = $(this).attr('lay-id');
        $('#tabs_content iframe[data-id="' + id + '"]').parent().remove();
        rollTab();
    });
}();

/**获取tabId*/
function getTabId(url){
    var tabId = url.replace(/\//g, '_') + "_tabid";
    tabId = tabId.substring(1);
    return tabId;
}
/**添加tab*/
function addTab(title, url, id) {
    var tabs = $('#tabs_header li');
    var matchTo = false;
    tabs.each(function (index) {
        var li = $(this);
        var layid = li.attr('lay-id');
        if (layid == id) {
            matchTo = true;
            return false;
        }
    });
    if (!matchTo) {
        $('#tabs_content .tabsbody-item').removeClass('layui-show');
        var content = '<iframe data-id="' + id + '" src="' + url + ' "class="layadmin-iframe" frameborder="0"></iframe>';
        $('#tabs_content').append('<div class="tabsbody-item layui-show">' + content + '</div>');
        layui.element.tabAdd('menu_tabs', {
            title: title || '新标签页'
            , id: id
        });
    }
    $('#tabs_header li').removeClass('layui-this');
    $('#tabs_header [lay-id="' + id + '"]').addClass('layui-this');
    rollTab();
}

/**刷新当前标签页*/
function refreshCurTabs() {
    var curTabObj = $('#tabs_header .layui-this');
    var id = curTabObj.attr('lay-id');
    var iframeObj = $('#tabs_content iframe[data-id="' + id + '"]');
    iframeObj.attr('src', iframeObj.attr('src'));
}

/**关闭当前标签页*/
function closeCurTabs() {
    var curTabObj = $('#tabs_header .layui-this');
    var id = curTabObj.attr('lay-id');
    curTabObj.remove();
    $('#tabs_content iframe[data-id="' + id + '"]').parent().remove();
    $('[lay-filter="menu"] .layui-this').removeClass('layui-this');
    rollTab();
}

/**关闭其它标签页*/
function closeOtherTabs() {
    var curTabObj = $('#tabs_header .layui-this');
    var id = curTabObj.attr('lay-id');
    $('#tabs_header li').each(function (i, ele) {
        if ($(ele).attr('lay-id') != id) {
            $(ele).remove();
            $('#tabs_content iframe[data-id="' + $(ele).attr('lay-id') + '"]').parent().remove();
        }
    });
    rollTab();
}

/**关闭全部标签页*/
function closeAllTabs() {
    $('#tabs_header,#tabs_content').html('');
    $('[lay-filter="menu"] .layui-this').removeClass('layui-this');
    rollTab();
}

/**tab左右滚动*/
function rollTab(type) {
    var tabsHeaderWidth = $('#tabs_header').width();
    var childrenWidth = 0;
    $('#tabs_header li:visible').each(function (i, elem) {
        childrenWidth += $(elem).outerWidth();
    });
    if (childrenWidth > tabsHeaderWidth) {
        if (type == 'left') {
            $('#tabs_header').css({'left': '0px', 'right': (tabsHeaderWidth - childrenWidth) + 'px'});
        } else if (type == 'right') {
            $('#tabs_header').css({'left': (tabsHeaderWidth - childrenWidth) + 'px', 'right': '0px'});
        } else {
            $('#tabs_header').css({'left': (tabsHeaderWidth - childrenWidth) + 'px', 'right': '0px'});
        }
    } else {
        $('#tabs_header').css({'left': '0px', 'right': '0px'});
    }
}




