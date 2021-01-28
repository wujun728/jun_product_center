/**
* jQuery EasyUI 1.4.3
* Copyright (c) 2009-2015 www.jeasyui.com. All rights reserved.
*
* Licensed under the GPL license: http://www.gnu.org/licenses/gpl.txt
* To use it on other terms please contact us at info@jeasyui.com
* http://www.jeasyui.com/license_commercial.php
*
* jQuery EasyUI tabs 扩展
* jeasyui.extensions.tabs.closeTabs.js
* 开发 流云
* 由 落阳 整理
* 最近更新：2016-01-18
*
* 依赖项：
*   1、jquery.jdirk.js
*   2、jeasyui.extensions.tabs.getTabs.js
*
* Copyright (c) 2015 ChenJianwei personal All rights reserved.
*/
(function ($) {

    $.util.namespace("$.fn.tabs.extensions");


    function closeLeftTabs(target, which) {
        var tabs = $(target), panels = tabs.tabs("leftTabs", which);
        $.each($.array.clone(panels), function () { tabs.tabs("close", tabs.tabs("getTabIndex", this)); });
    };

    function closeRightTabs(target, which) {
        var tabs = $(target), panels = tabs.tabs("rightTabs", which);
        $.each($.array.clone(panels), function () { tabs.tabs("close", tabs.tabs("getTabIndex", this)); });
    };

    function closeOtherTabs(target, which) {
        var tabs = $(target), panels = tabs.tabs("otherTabs", which);
        $.each($.array.clone(panels), function () { tabs.tabs("close", tabs.tabs("getTabIndex", this)); });
    };

    function closeAllTabs(target) {
        var tabs = $(target), panels = tabs.tabs("tabs");
        $.each($.array.clone(panels), function () { tabs.tabs("close", tabs.tabs("getTabIndex", this)); });
    };

    function closeClosableTab(target, which) {
        var tabs = $(target), panel = tabs.tabs("getTab", which);
        if (panel && panel.panel("options").closable) {
            var index = $.isNumeric(which) ? which : tabs.tabs("getTabIndex", panel);
            tabs.tabs("close", index);
        }
    };

    function closeLeftClosableTabs(target, which) {
        var tabs = $(target), panels = tabs.tabs("leftClosableTabs", which);
        $.each($.array.clone(panels), function () { tabs.tabs("close", tabs.tabs("getTabIndex", this)); });
    };

    function closeRightClosableTabs(target, which) {
        var tabs = $(target), panels = tabs.tabs("rightClosableTabs", which);
        $.each($.array.clone(panels), function () { tabs.tabs("close", tabs.tabs("getTabIndex", this)); });
    };

    function closeOtherClosableTabs(target, which) {
        var tabs = $(target), panels = tabs.tabs("otherClosableTabs", which);
        $.each($.array.clone(panels), function () { tabs.tabs("close", tabs.tabs("getTabIndex", this)); });
    };

    function closeAllClosableTabs(target, which) {
        var tabs = $(target), panels = tabs.tabs("closableTabs", which);
        $.each($.array.clone(panels), function () { tabs.tabs("close", tabs.tabs("getTabIndex", this)); });
    };


    var defaults = {

    };

    var methods = {

        //  扩展 easyui-tabs 的自定义方法；关闭指定选项卡左侧的所有选项卡；该方法定义如下参数：
        //      which:  指定的选项卡的 索引号 或者 标题。
        //  返回值：返回当前选项卡控件 easyui-tabs 的 jQuery 对象。
        closeLeft: function (jq, which) { return jq.each(function () { closeLeftTabs(this, which); }); },

        //  扩展 easyui-tabs 的自定义方法；关闭指定选项卡右侧的所有选项卡；该方法定义如下参数：
        //      which:  指定的选项卡的 索引号 或者 标题。
        //  返回值：返回当前选项卡控件 easyui-tabs 的 jQuery 对象。
        closeRight: function (jq, which) { return jq.each(function () { closeRightTabs(this, which); }); },

        //  扩展 easyui-tabs 的自定义方法；关闭除指定选项卡外的其他所有选项卡；该方法定义如下参数：
        //      which:  指定的选项卡的 索引号 或者 标题。
        //  返回值：返回当前选项卡控件 easyui-tabs 的 jQuery 对象。
        closeOther: function (jq, which) { return jq.each(function () { closeOtherTabs(this, which); }); },

        //  扩展 easyui-tabs 的自定义方法；关闭所有选项卡；
        //  返回值：返回当前选项卡控件 easyui-tabs 的 jQuery 对象。
        closeAll: function (jq) { return jq.each(function () { closeAllTabs(this); }); },

        //  扩展 easyui-tabs 的自定义方法；关闭指定的选项卡，但是如果该选项卡不可被关闭(closable:false)，则不执行任何动作；该方法定义如下参数：
        //      which:  指定的选项卡的 索引号 或者 标题。
        //  返回值：返回当前选项卡控件 easyui-tabs 的 jQuery 对象。
        closeClosable: function (jq, which) { return jq.each(function () { closeClosableTab(this, which); }); },

        //  扩展 easyui-tabs 的自定义方法；关闭指定的选项卡左侧的所有可关闭的选项卡；该方法定义如下参数：
        //      which:  指定的选项卡的 索引号 或者 标题。
        //  返回值：返回当前选项卡控件 easyui-tabs 的 jQuery 对象。
        closeLeftClosable: function (jq, which) { return jq.each(function () { closeLeftClosableTabs(this, which); }); },

        //  扩展 easyui-tabs 的自定义方法；关闭指定的选项卡右侧的所有可关闭的选项卡；该方法定义如下参数：
        //      which:  指定的选项卡的 索引号 或者 标题。
        //  返回值：返回当前选项卡控件 easyui-tabs 的 jQuery 对象。
        closeRightClosable: function (jq, which) { return jq.each(function () { closeRightClosableTabs(this, which); }); },

        //  扩展 easyui-tabs 的自定义方法；关闭除指定选项卡外的所有可关闭的选项卡；该方法定义如下参数：
        //      which:  指定的选项卡的 索引号 或者 标题。
        //  返回值：返回当前选项卡控件 easyui-tabs 的 jQuery 对象。
        closeOtherClosable: function (jq, which) { return jq.each(function () { closeOtherClosableTabs(this, which); }); },

        //  扩展 easyui-tabs 的自定义方法；关闭所有可关闭的选项卡；
        //  返回值：返回当前选项卡控件 easyui-tabs 的 jQuery 对象。
        closeAllClosable: function (jq) { return jq.each(function () { closeAllClosableTabs(this); }); }
    };

    var compent = $.fn.tabs;
    if (compent.extensions.defaults) {
        $.extend(compent.extensions.defaults, defaults);
    } else {
        compent.extensions.defaults = defaults;
    }

    if (compent.extensions.methods) {
        $.extend(compent.extensions.methods, methods);
    } else {
        compent.extensions.methods = methods;
    }

    $.extend(compent.defaults, defaults);
    $.extend(compent.methods, methods);

})(jQuery);