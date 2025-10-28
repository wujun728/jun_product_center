/**
* jQuery EasyUI 1.4.3
* Copyright (c) 2009-2015 www.jeasyui.com. All rights reserved.
*
* Licensed under the GPL license: http://www.gnu.org/licenses/gpl.txt
* To use it on other terms please contact us at info@jeasyui.com
* http://www.jeasyui.com/license_commercial.php
*
* jQuery EasyUI tabs 扩展
* jeasyui.extensions.tabs.contextMenu.js
* 开发 流云
* 由 落阳 整理
* 最近更新：2016-02-04
*
* 依赖项：
*   1、jquery.jdirk.js
*   2、jeasyui.extensions.menu.js
*   3、jeasyui.extensions.tabs.getTabs.js
*   4、jeasyui.extensions.tabs.closeTabs.js
*
* Copyright (c) 2015 ChenJianwei personal All rights reserved.
*/
(function ($) {

    $.util.namespace("$.fn.tabs.extensions");



    function getTabOption(target, which) {
        var t = $(target), tab = t.tabs("getTab", which), tabOpts = tab.panel("options");
        return tabOpts;
    };




    function getContextMenuItems(t, opts, e, title, index) {
        var menuItems = [],
            defaultMenuItems = [],
            popts = getTabOption(t[0], index),
            args = [t[0], title, index];

        $.array.merge(defaultMenuItems, defaultMenuItems.length ? "-" : [], defaultMenus.closeTabsMenus);
        $.array.merge(defaultMenuItems, defaultMenuItems.length ? "-" : [], defaultMenus.closeOtherTabsMenus);

        if ($.array.likeArrayNotString(opts.contextMenu)) {
            $.array.merge(menuItems, menuItems.length ? "-" : [], opts.contextMenu);
        }
        if (defaultMenuItems.length) {
            $.array.merge(menuItems, menuItems.length ? "-" : [], defaultMenuItems);
        }
        return $.easyui.parseMenuItems(menuItems, args);
    }

    var defaultMenus = {};
    //关闭标签页菜单
    defaultMenus.closeTabsMenus = [
        {
            text: "关闭选项卡", iconCls: "icon-standard-application-form-delete",
            disabled: function (e, menuItem, menu, target, title, index) {
                return getTabOption(target, index).closable ? false : true;
            },
            handler: function (e, menuItem, menu, target, title, index) {
                $(target).tabs("closeClosable", index);
            }
        },
        {
            text: "关闭其他选项卡", iconCls: "icon-standard-cancel",
            disabled: function (e, menuItem, menu, target, title, index) {
                return $(target).tabs("otherClosableTabs", index).length ? false : true;
            },
            handler: function (e, menuItem, menu, target, title, index) {
                $(target).tabs("closeOtherClosable", index);
            }
        }
    ];
    defaultMenus.closeOtherTabsMenus = [
        {
            text: "关闭左侧选项卡", iconCls: "icon-standard-tab-close-left",
            disabled: function (e, menuItem, menu, target, title, index) {
                return $(target).tabs("leftClosableTabs", index).length ? false : true;
            },
            handler: function (e, menuItem, menu, target, title, index) {
                $(target).tabs("closeLeftClosable", index);
            }
        },
        {
            text: "关闭右侧选项卡", iconCls: "icon-standard-tab-close-right",
            disabled: function (e, menuItem, menu, target, title, index) {
                return $(target).tabs("rightClosableTabs", index).length ? false : true;
            },
            handler: function (e, menuItem, menu, target, title, index) {
                $(target).tabs("closeRightClosable", index);
            }
        },
        {
            text: "关闭所有选项卡", iconCls: "icon-standard-cross",
            disabled: function (e, menuItem, menu, target, title, index) {
                return $(target).tabs("closableTabs").length ? false : true;
            },
            handler: function (e, menuItem, menu, target, title, index) {
                $(target).tabs("closeAllClosable", index);
            }
        }
    ];



    function initContextMenu(t, opts) {
        t.children("div.tabs-header").unbind(".tabs-contextmenu").bind({
            "contextmenu.tabs-contextmenu": function (e) {
                if (!opts.enableConextMenu) { return; }
                var li = $(e.target).closest('li');
                if (li.length && !li.is(".tabs-disabled")) {
                    e.preventDefault();
                    var title = li.find("span.tabs-title").html(),
                        index = li.index(),
                        menuItems = getContextMenuItems(t, opts, e, title, index);
                    $.easyui.showMenu({ items: menuItems, left: e.pageX, top: e.pageY, event: e });
                }
            },
            "click.tabs-contextmenu": function (e) {
                $.easyui.hideAllMenu();
            }
        });
    }

    function initializeExtensions(target) {
        var t = $(target),
            state = $.data(target, "tabs"),
            opts = state.options;
        initContextMenu(t, opts);
    }

    var _tabs = $.fn.tabs;
    $.fn.tabs = function (options, param) {
        if (typeof options == "string") {
            return _tabs.apply(this, arguments);
        }
        options = options || {};
        return this.each(function () {
            var jq = $(this),
                isInited = $.data(this, "tabs") ? true : false,
                opts = isInited ? options : $.extend({},
                        $.fn.tabs.parseOptions(this),
                        $.parser.parseOptions(this, [
                            {
                                enableConextMenu: "boolean"
                            }
                        ]), options);
            _tabs.call(jq, opts, param);
            if (!isInited) {
                initializeExtensions(this);
            }
        });
    };
    $.union($.fn.tabs, _tabs);



    var defaults = {

        //  扩展 easyui-tabs 的自定义属性；是否启用点击选项卡头的右键菜单。
        //  Boolean 类型值，默认为 true 。
        enableConextMenu: true,

        //  扩展 easyui-tabs 的自定义属性；定义当 enableContextMenu 为 true 时，右键点击选项卡标题时候弹出的自定义右键菜单项内容；
        //  这是一个数组格式对象，数组中的每一项都是一个 menu-item 元素；该 menu-item 元素格式定义如下：
        //      id:         表示菜单项的 id；
        //      text:       表示菜单项的显示文本；
        //      iconCls:    表示菜单项的左侧显示图标；
        //      disabled:   表示菜单项是否被禁用(禁用的菜单项点击无效)；
        //      hideOnClick:    表示该菜单项点击后整个右键菜单是否立即自动隐藏；
        //      bold:           Boolean 类型值，默认为 false；表示该菜单项是否字体加粗；
        //      style:          JSON-Object 类型值，默认为 null；表示要附加到该菜单项的样式；
        //      handler:    表示菜单项的点击事件，该事件函数格式为 function(e, menuItem, menu, target, title, index)，其中 this 指向菜单项本身
        contextMenu: null
    };

    var methods = {


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