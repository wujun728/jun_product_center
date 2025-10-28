/**
* jQuery EasyUI 1.4.3
* Copyright (c) 2009-2015 www.jeasyui.com. All rights reserved.
*
* Licensed under the GPL license: http://www.gnu.org/licenses/gpl.txt
* To use it on other terms please contact us at info@jeasyui.com
* http://www.jeasyui.com/license_commercial.php
*
* jQuery EasyUI menu 扩展
* jeasyui.extensions.menu.js
* 开发 流云
* 由 落阳 整理
* 最近更新：2015-12-28
*
* 依赖项：
*   1、jquery.jdirk.js
*
* Copyright (c) 2015 ChenJianwei personal All rights reserved.
*/
(function ($) {

    /*
        以下代码是 easyui-1.4.2 版本的 jquery.menu.js 源码，并在其基础上增加了 easyui-menu 控件中对 menu-item 的 hideOnClick 属性的支持。
    */

    // 以下8行代码为 jquery.menu.js 的源码版本
    //$(function () {
    //    $(document).unbind('.menu').bind('mousedown.menu', function (e) {
    //        var m = $(e.target).closest('div.menu,div.combo-p');
    //        if (m.length) { return }
    //        $('body>div.menu-top:visible').not('.menu-inline').menu('hide');
    //        hideMenu($('body>div.menu:visible').not('.menu-inline'));
    //    });
    //});

    /**
	 * initialize the target menu, the function can be invoked only once
	 */
    function init(target) {
        var opts = $.data(target, 'menu').options;
        $(target).addClass('menu-top');	// the top menu
        opts.inline ? $(target).addClass('menu-inline') : $(target).appendTo('body');
        $(target).bind('_resize', function (e, force) {
            if ($(this).hasClass('easyui-fluid') || force) {
                $(target).menu('resize', target);
            }
            return false;
        });

        var menus = splitMenu($(target));
        for (var i = 0; i < menus.length; i++) {
            createMenu(menus[i]);
        }

        function splitMenu(menu) {
            var menus = [];
            menu.addClass('menu');
            menus.push(menu);
            if (!menu.hasClass('menu-content')) {
                menu.children('div').each(function () {
                    var submenu = $(this).children('div');
                    if (submenu.length) {
                        //						submenu.insertAfter(target);
                        submenu.appendTo('body');
                        this.submenu = submenu;		// point to the sub menu
                        var mm = splitMenu(submenu);
                        menus = menus.concat(mm);
                    }
                });
            }
            return menus;
        }

        function createMenu(menu) {
            var wh = $.parser.parseOptions(menu[0], ['width', 'height']);
            menu[0].originalHeight = wh.height || 0;
            if (menu.hasClass('menu-content')) {
                menu[0].originalWidth = wh.width || menu._outerWidth();
            }
            else {
                menu[0].originalWidth = wh.width || 0;
                menu.children('div').each(function () {
                    var item = $(this);

                    // 以下3行代码为 jquery-menu.js 源码原版
                    var itemOpts = $.extend({}, $.parser.parseOptions(this, ['name', 'iconCls', 'href', { separator: 'boolean' }]), {
                        disabled: (item.attr('disabled') ? true : undefined)
                    });

                    // 注释掉上面 3 行代码，并添加了下面 3 行代码，以实现获取 menu-item 的属性 hideOnClick，该参数表示是否在点击菜单项后菜单自动隐藏
                    var itemOpts = $.extend({ hideOnClick: true }, $.parser.parseOptions(this, ['name', 'iconCls', 'href', { hideOnClick: 'boolean', separator: 'boolean' }]), {
                        disabled: (item.attr('disabled') ? true : undefined)
                    });
                    if (itemOpts.separator) {
                        item.addClass('menu-sep');
                    }
                    if (!item.hasClass('menu-sep')) {
                        item[0].itemName = itemOpts.name || '';
                        item[0].itemHref = itemOpts.href || '';

                        // 添加了下一行代码，以实现将 menu-item 的 hideOnClick 绑定到菜单项上
                        item[0].hideOnClick = (itemOpts.hideOnClick == undefined || itemOpts.hideOnClick == null ? true : !!itemOpts.hideOnClick);

                        var text = item.addClass('menu-item').html();
                        item.empty().append($('<div class="menu-text"></div>').html(text));
                        if (itemOpts.iconCls) {
                            $('<div class="menu-icon"></div>').addClass(itemOpts.iconCls).appendTo(item);
                        }
                        if (itemOpts.disabled) {
                            setDisabled(target, item[0], true);
                        }
                        if (item[0].submenu) {
                            $('<div class="menu-rightarrow"></div>').appendTo(item);	// has sub menu
                        }

                        bindMenuItemEvent(target, item);
                    }
                });
                $('<div class="menu-line"></div>').prependTo(menu);
            }
            setMenuSize(target, menu);
            if (!menu.hasClass('menu-inline')) {
                menu.hide();
            }

            bindMenuEvent(target, menu);
        }
    }

    function setMenuSize(target, menu) {
        var opts = $.data(target, 'menu').options;
        var style = menu.attr('style') || '';
        menu.css({
            display: 'block',
            left: -10000,
            height: 'auto',
            overflow: 'hidden'
        });
        menu.find('.menu-item').each(function () {
            $(this)._outerHeight(opts.itemHeight);
            $(this).find('.menu-text').css({
                height: (opts.itemHeight - 2) + 'px',
                lineHeight: (opts.itemHeight - 2) + 'px'
            });
        });
        menu.removeClass('menu-noline').addClass(opts.noline ? 'menu-noline' : '');

        var width = menu[0].originalWidth || 'auto';
        if (isNaN(parseInt(width))) {
            width = 0;
            menu.find('div.menu-text').each(function () {
                if (width < $(this)._outerWidth()) {
                    width = $(this)._outerWidth();
                }
            });
            width += 40;
        }

        var autoHeight = menu.outerHeight();
        var height = menu[0].originalHeight || 'auto';
        if (isNaN(parseInt(height))) {
            height = autoHeight;

            if (menu.hasClass('menu-top') && opts.alignTo) {
                var at = $(opts.alignTo);
                var h1 = at.offset().top - $(document).scrollTop();
                var h2 = $(window)._outerHeight() + $(document).scrollTop() - at.offset().top - at._outerHeight();
                height = Math.min(height, Math.max(h1, h2));
            } else if (height > $(window)._outerHeight()) {
                height = $(window).height();
            }
        }

        menu.attr('style', style);	// restore the original style
        menu._size({
            fit: (menu[0] == target ? opts.fit : false),
            width: width,
            minWidth: opts.minWidth,
            height: height
        });
        menu.css('overflow', menu.outerHeight() < autoHeight ? 'auto' : 'hidden');
        menu.children('div.menu-line')._outerHeight(autoHeight - 2);
    }

    /**
	 * bind menu event
	 */
    function bindMenuEvent(target, menu) {
        if (menu.hasClass('menu-inline')) { return }
        var state = $.data(target, 'menu');
        menu.unbind('.menu').bind('mouseenter.menu', function () {
            if (state.timer) {
                clearTimeout(state.timer);
                state.timer = null;
            }
        }).bind('mouseleave.menu', function () {
            if (state.options.hideOnUnhover) {
                state.timer = setTimeout(function () {
                    hideAll(target, $(target).hasClass('menu-inline'));
                }, state.options.duration);
            }
        });
    }

    /**
	 * bind menu item event
	 */
    function bindMenuItemEvent(target, item) {
        if (!item.hasClass('menu-item')) { return }
        item.unbind('.menu');
        item.bind('click.menu', function () {
            if ($(this).hasClass('menu-item-disabled')) {
                return;
            }
            // only the sub menu clicked can hide all menus
            if (!this.submenu) {

                // 以下1行代码为 jquery-menu.js 源码原版
                //hideAll(target, $(target).hasClass('menu-inline'));
                // 注释掉上面1行代码，并添加下面 3 行代码，以实现当 menu-item 的属性 hideOnClick 为 false 的情况下，点击菜单项不自动隐藏菜单控件。
                if (this.hideOnClick) {
                    hideAll(target, $(target).hasClass('menu-inline'));
                }

                var href = this.itemHref;
                if (href) {
                    location.href = href;
                }
            }
            $(this).trigger('mouseenter');
            var item = $(target).menu('getItem', this);
            $.data(target, 'menu').options.onClick.call(target, item);
        }).bind('mouseenter.menu', function (e) {
            // hide other menu
            item.siblings().each(function () {
                if (this.submenu) {
                    hideMenu(this.submenu);
                }
                $(this).removeClass('menu-active');
            });
            // show this menu
            item.addClass('menu-active');

            if ($(this).hasClass('menu-item-disabled')) {
                item.addClass('menu-active-disabled');
                return;
            }

            var submenu = item[0].submenu;
            if (submenu) {
                $(target).menu('show', {
                    menu: submenu,
                    parent: item
                });
            }
        }).bind('mouseleave.menu', function (e) {
            item.removeClass('menu-active menu-active-disabled');
            var submenu = item[0].submenu;
            if (submenu) {
                if (e.pageX >= parseInt(submenu.css('left'))) {
                    item.addClass('menu-active');
                } else {
                    hideMenu(submenu);
                }

            } else {
                item.removeClass('menu-active');
            }
        });
    }

    /**
	 * hide top menu and it's all sub menus
	 */
    function hideAll(target, inline) {
        var state = $.data(target, 'menu');
        if (state) {
            if ($(target).is(':visible')) {
                hideMenu($(target));
                if (inline) {
                    $(target).show();
                } else {
                    state.options.onHide.call(target);
                }
            }
        }
        return false;
    }

    /**
	 * show the menu, the 'param' object has one or more properties:
	 * left: the left position to display
	 * top: the top position to display
	 * menu: the menu to display, if not defined, the 'target menu' is used
	 * parent: the parent menu item to align to
	 * alignTo: the element object to align to
	 */
    function showMenu(target, param) {
        var left, top;
        param = param || {};
        var menu = $(param.menu || target);
        $(target).menu('resize', menu[0]);
        if (menu.hasClass('menu-top')) {
            var opts = $.data(target, 'menu').options;
            $.extend(opts, param);
            left = opts.left;
            top = opts.top;
            if (opts.alignTo) {
                var at = $(opts.alignTo);
                left = at.offset().left;
                top = at.offset().top + at._outerHeight();
                if (opts.align == 'right') {
                    left += at.outerWidth() - menu.outerWidth();
                }
            }
            if (left + menu.outerWidth() > $(window)._outerWidth() + $(document)._scrollLeft()) {
                left = $(window)._outerWidth() + $(document).scrollLeft() - menu.outerWidth() - 5;
            }
            if (left < 0) { left = 0; }
            top = _fixTop(top, opts.alignTo);
        } else {
            var parent = param.parent;	// the parent menu item
            left = parent.offset().left + parent.outerWidth() - 2;
            if (left + menu.outerWidth() + 5 > $(window)._outerWidth() + $(document).scrollLeft()) {
                left = parent.offset().left - menu.outerWidth() + 2;
            }
            top = _fixTop(parent.offset().top - 3);
        }

        function _fixTop(top, alignTo) {
            if (top + menu.outerHeight() > $(window)._outerHeight() + $(document).scrollTop()) {
                if (alignTo) {
                    top = $(alignTo).offset().top - menu._outerHeight();
                } else {
                    top = $(window)._outerHeight() + $(document).scrollTop() - menu.outerHeight();
                }
            }
            if (top < 0) { top = 0; }
            return top;
        }

        menu.css({ left: left, top: top });
        menu.show(0, function () {
            if (!menu[0].shadow) {
                menu[0].shadow = $('<div class="menu-shadow"></div>').insertAfter(menu);
            }
            menu[0].shadow.css({
                display: (menu.hasClass('menu-inline') ? 'none' : 'block'),
                zIndex: $.fn.menu.defaults.zIndex++,
                left: menu.css('left'),
                top: menu.css('top'),
                width: menu.outerWidth(),
                height: menu.outerHeight()
            });
            menu.css('z-index', $.fn.menu.defaults.zIndex++);
            if (menu.hasClass('menu-top')) {
                $.data(menu[0], 'menu').options.onShow.call(menu[0]);
            }
        });
    }

    function hideMenu(menu) {
        if (menu && menu.length) {
            hideit(menu);
            menu.find('div.menu-item').each(function () {
                if (this.submenu) {
                    hideMenu(this.submenu);
                }
                $(this).removeClass('menu-active');
            });
        }

        function hideit(m) {
            m.stop(true, true);
            if (m[0].shadow) {
                m[0].shadow.hide();
            }
            m.hide();
        }
    }

    function findItem(target, text) {
        var result = null;
        var tmp = $('<div></div>');
        function find(menu) {
            menu.children('div.menu-item').each(function () {
                var item = $(target).menu('getItem', this);
                var s = tmp.empty().html(item.text).text();
                if (text == $.trim(s)) {
                    result = item;
                } else if (this.submenu && !result) {
                    find(this.submenu);
                }
            });
        }
        find($(target));
        tmp.remove();
        return result;
    }

    function setDisabled(target, itemEl, disabled) {
        var t = $(itemEl);
        if (!t.hasClass('menu-item')) { return }

        if (disabled) {
            t.addClass('menu-item-disabled');
            if (itemEl.onclick) {
                itemEl.onclick1 = itemEl.onclick;
                itemEl.onclick = null;
            }
        } else {
            t.removeClass('menu-item-disabled');
            if (itemEl.onclick1) {
                itemEl.onclick = itemEl.onclick1;
                itemEl.onclick1 = null;
            }
        }
    }

    function appendItem(target, param) {
        var opts = $.data(target, 'menu').options;
        var menu = $(target);
        if (param.parent) {
            if (!param.parent.submenu) {
                var submenu = $('<div class="menu"><div class="menu-line"></div></div>').appendTo('body');
                submenu.hide();
                param.parent.submenu = submenu;
                $('<div class="menu-rightarrow"></div>').appendTo(param.parent);
            }
            menu = param.parent.submenu;
        }
        if (param.separator) {
            var item = $('<div class="menu-sep"></div>').appendTo(menu);
        } else {
            var item = $('<div class="menu-item"></div>').appendTo(menu);
            $('<div class="menu-text"></div>').html(param.text).appendTo(item);
        }
        if (param.iconCls) $('<div class="menu-icon"></div>').addClass(param.iconCls).appendTo(item);
        if (param.id) item.attr('id', param.id);
        if (param.name) { item[0].itemName = param.name }
        if (param.href) { item[0].itemHref = param.href }
        if (param.onclick) {
            if (typeof param.onclick == 'string') {
                item.attr('onclick', param.onclick);
            } else {
                item[0].onclick = eval(param.onclick);
            }
        }
        if (param.handler) { item[0].onclick = eval(param.handler) }
        if (param.disabled) { setDisabled(target, item[0], true) }

        bindMenuItemEvent(target, item);
        bindMenuEvent(target, menu);
        setMenuSize(target, menu);
    }

    function removeItem(target, itemEl) {
        function removeit(el) {
            if (el.submenu) {
                el.submenu.children('div.menu-item').each(function () {
                    removeit(this);
                });
                var shadow = el.submenu[0].shadow;
                if (shadow) shadow.remove();
                el.submenu.remove();
            }
            $(el).remove();
        }
        var menu = $(itemEl).parent();
        removeit(itemEl);
        setMenuSize(target, menu);
    }

    function setVisible(target, itemEl, visible) {
        var menu = $(itemEl).parent();
        if (visible) {
            $(itemEl).show();
        } else {
            $(itemEl).hide();
        }
        setMenuSize(target, menu);
    }

    function destroyMenu(target) {
        $(target).children('div.menu-item').each(function () {
            removeItem(target, this);
        });
        if (target.shadow) target.shadow.remove();
        $(target).remove();
    }




    $.fn.menu = function (options, param) {
        if (typeof options == 'string') {
            return $.fn.menu.methods[options](this, param);
        }

        options = options || {};
        return this.each(function () {
            var state = $.data(this, 'menu');
            if (state) {
                $.extend(state.options, options);
            } else {
                state = $.data(this, 'menu', {
                    options: $.extend({}, $.fn.menu.defaults, $.fn.menu.parseOptions(this), options)
                });
                init(this);
            }
            $(this).css({
                left: state.options.left,
                top: state.options.top
            });
        });
    };

    $.fn.menu.methods = {
        options: function (jq) {
            return $.data(jq[0], 'menu').options;
        },
        show: function (jq, pos) {
            return jq.each(function () {
                showMenu(this, pos);
            });
        },
        hide: function (jq) {
            return jq.each(function () {
                hideAll(this);
            });
        },
        destroy: function (jq) {
            return jq.each(function () {
                destroyMenu(this);
            });
        },
        /**
		 * set the menu item text
		 * param: {
		 * 	target: DOM object, indicate the menu item
		 * 	text: string, the new text
		 * }
		 */
        setText: function (jq, param) {
            return jq.each(function () {
                $(param.target).children('div.menu-text').html(param.text);
            });
        },
        /**
		 * set the menu icon class
		 * param: {
		 * 	target: DOM object, indicate the menu item
		 * 	iconCls: the menu item icon class
		 * }
		 */
        setIcon: function (jq, param) {
            return jq.each(function () {
                $(param.target).children('div.menu-icon').remove();
                if (param.iconCls) {
                    $('<div class="menu-icon"></div>').addClass(param.iconCls).appendTo(param.target);
                }
            });
        },
        /**
		 * get the menu item data that contains the following property:
		 * {
		 * 	target: DOM object, the menu item
		 *  id: the menu id
		 * 	text: the menu item text
		 * 	iconCls: the icon class
		 *  href: a remote address to redirect to
		 *  onclick: a function to be called when the item is clicked
		 * }
		 */
        getItem: function (jq, itemEl) {
            var t = $(itemEl);
            var item = {
                target: itemEl,
                id: t.attr('id'),
                text: $.trim(t.children('div.menu-text').html()),
                disabled: t.hasClass('menu-item-disabled'),
                //				href: t.attr('href'),
                //				name: t.attr('name'),
                name: itemEl.itemName,
                href: itemEl.itemHref,

                // 增加下面一行代码，使得通过 getItem 方法返回的 menu-item 中包含其 hideOnClick 属性
                hideOnClick: itemEl.hideOnClick,

                onclick: itemEl.onclick
            }
            var icon = t.children('div.menu-icon');
            if (icon.length) {
                var cc = [];
                var aa = icon.attr('class').split(' ');
                for (var i = 0; i < aa.length; i++) {
                    if (aa[i] != 'menu-icon') {
                        cc.push(aa[i]);
                    }
                }
                item.iconCls = cc.join(' ');
            }
            return item;
        },
        findItem: function (jq, text) {
            return findItem(jq[0], text);
        },
        /**
		 * append menu item, the param contains following properties:
		 * parent,id,text,iconCls,href,onclick
		 * when parent property is assigned, append menu item to it
		 */
        appendItem: function (jq, param) {
            return jq.each(function () {
                appendItem(this, param);
            });
        },
        removeItem: function (jq, itemEl) {
            return jq.each(function () {
                removeItem(this, itemEl);
            });
        },
        enableItem: function (jq, itemEl) {
            return jq.each(function () {
                setDisabled(this, itemEl, false);
            });
        },
        disableItem: function (jq, itemEl) {
            return jq.each(function () {
                setDisabled(this, itemEl, true);
            });
        },
        showItem: function (jq, itemEl) {
            return jq.each(function () {
                setVisible(this, itemEl, true);
            });
        },
        hideItem: function (jq, itemEl) {
            return jq.each(function () {
                setVisible(this, itemEl, false);
            });
        },
        resize: function (jq, menuEl) {
            return jq.each(function () {
                setMenuSize(this, $(menuEl));
            });
        }
    };

    $.fn.menu.parseOptions = function (target) {
        return $.extend({}, $.parser.parseOptions(target, [
		     { minWidth: 'number', itemHeight: 'number', duration: 'number', hideOnUnhover: 'boolean' },
		     { fit: 'boolean', inline: 'boolean', noline: 'boolean' }
        ]));
    };

    $.fn.menu.defaults = {
        zIndex: 110000,
        left: 0,
        top: 0,
        alignTo: null,
        align: 'left',
        minWidth: 120,
        itemHeight: 22,
        duration: 100,	// Defines duration time in milliseconds to hide when the mouse leaves the menu.
        hideOnUnhover: true,	// Automatically hides the menu when mouse exits it
        inline: false,	// true to stay inside its parent, false to go on top of all elements
        fit: false,
        noline: false,
        onShow: function () { },
        onHide: function () { },
        onClick: function (item) { }
    };

    /*
        以上代码是 easyui-1.4.2 版本的 jquery.menu.js 源码，并在其基础上增加了 easyui-menu 控件中对 menu-item 的 hideOnClick 属性的支持。
    */


    /*
        真正的扩展从此行开始
    */

    function hideAllMenu(target) {
        $.util.pageNestingExecute(function (win) {
            if (!win || !win.document || !win.jQuery) {
                return;
            }
            var jq = win.jQuery;
            if (target) {
                var m = jq(target).closest('div.menu,div.combo-p');
                if (m.length) {
                    return
                }
            }

            jq('body>div.menu-top:visible').not('.menu-inline').menu('hide');
            hide(jq('body>div.menu:visible').not('.menu-inline'));

            function hide(menu) {
                if (menu && menu.length) {
                    hideit(menu);
                    menu.find('div.menu-item').each(function () {
                        if (this.submenu) {
                            hide(this.submenu);
                        }
                        jq(this).removeClass('menu-active');
                    });
                }
                function hideit(m) {
                    m.stop(true, true);
                    if (m[0].shadow) {
                        m[0].shadow.hide();
                    }
                    m.hide();
                }
            }
        });
    }

    // 下面这段代码实现即使在跨 IFRAME 的情况下，一个 WEB-PAGE 中也只能同时显示一个 easyui-menu 控件。
    $.util.bindDocumentNestingEvent("mousedown.menu-nesting", function (doc, e) {
        hideAllMenu(e.target);
    });




    $.util.namespace("$.easyui");


    function createMenu(options) {
        var defaults = $.extend({}, $.fn.menu.defaults, {
            event: window.event || undefined,
            left: window.event ? window.event.clientX : 0,
            top: window.event ? window.event.clientY : 0,
            slideOut: false,
            autoDestroy: true,
            hideOnUnhover: false,
            hideDisabledItem: false,
            items: null,
            minWidth: 140
        });

        var root = $("<div></div>").appendTo("body"),
            opts = $.util.likeArrayNotString(options)
                ? $.extend({}, defaults, { items: options })
                : $.extend({}, defaults, options || {});

        opts.items = $.util.likeArrayNotString(opts.items) ? opts.items : [];

        if (opts.id) {
            root.attr("id", opts.id);
        }
        if (opts.name) {
            root.attr("name", opts.name);
        }
        if (!opts.items.length) {
            opts.items.push({ text: "当前无菜单项", disabled: true });
        }
        $.each(opts.items, function (i, item) {
            appendItemToMenu(opts.event, root, item, root[0], opts.hideDisabledItem);
        });
        return { menu: root, options: opts };
    }

    function appendItemToMenu(e, menu, item, root, hideDisabledItem) {
        if ($.util.isString(item) && $.array.contains(["-", "—", "|"], $.trim(item))) {
            $("<div class='menu-sep'></div>").appendTo(menu);
            return;
        }
        item = $.extend({
            id: null,
            text: null,
            iconCls: null,
            href: null,
            disabled: false,
            onclick: null,
            handler: null,
            bold: false,
            style: null,
            children: null,
            hideDisabledItem: hideDisabledItem,
            hideOnClick: true
        }, item || {});

        var onclick = item.onclick,
            handler = item.handler,
            itemEle = $("<div></div>").appendTo(menu),
            args = [e, item, root];

        item.onclick = undefined;
        item.handler = undefined;
        item = $.util.parseMapFunction(item, args, itemEle[0]);
        item.onclick = onclick;
        item.handler = handler;

        if (item.disabled && item.hideDisabledItem) {
            return;
        }
        itemEle.attr({
            iconCls: item.iconCls,
            href: item.href,
            disabled: item.disabled,
            hideOnClick: item.hideOnClick
        });

        if (item.id) {
            itemEle.attr("id", item.id);
        }
        if (item.style) {
            itemEle.css(item.style);
        }

        if ($.isFunction(item.handler)) {
            var handler = item.handler;
            item.onclick = function (e, item, root) {
                handler.call(this, e, item, root);
            };
        }
        if ($.isFunction(item.onclick)) {
            itemEle.click(function (e) {
                if (item.disabled || itemEle.hasClass("menu-item-disabled")) {
                    return;
                }
                item.onclick.call(this, e, item, root);
            });
        }
        var span = $("<span></span>").appendTo(itemEle).text(item.text === null || item.text == undefined ? "" : item.text);
        if (item.bold) {
            span.css("font-weight", "bold");
        }

        if (item.children && item.children.length) {
            var itemNode = $("<div></div>").appendTo(itemEle);
            $.each(item.children, function (i, n) {
                appendItemToMenu(e, itemNode, n, root, item.hideDisabledItem);
            });
        }
    }

    function createAndShowMenu(options) {
        var ret = createMenu(options),
            mm = ret.menu,
            opts = mm.menu(ret.options).menu("options");

        if (opts.autoDestroy) {
            var onHide = opts.onHide;
            opts.onHide = function () {
                if ($.isFunction(onHide)) {
                    onHide.apply(this, arguments);
                }
                var m = $(this);
                $.util.delay(function () { m.menu("destroy"); });
            };
        }

        mm.menu("show", {
            left: opts.left, top: opts.top
        });

        if (opts.slideOut) {
            mm.hide().slideDown(200);
            if (mm[0] && mm[0].shadow) {
                mm[0].shadow.hide().slideDown(200);
            }
        }
        return mm;
    }

    function parseMenuItems(menuItems, args) {
        if (menuItems == null || menuItems == undefined || !$.util.likeArrayNotString(menuItems)) {
            return [];
        }
        args = (args == null || args == undefined) ? [] : ($.util.likeArrayNotString(args) ? args : [args])
        return $.array.map(menuItems, function (val, index) {
            if (!val || typeof val == "string") {
                return val;
            }
            var item = $.extend({}, val);
            $.each(item, function (key, value) {
                if ($.isFunction(value)) {
                    item[key] = function () {
                        var newArgs = $.array.merge(arguments, args);
                        return value.apply(this, newArgs);
                    };
                }
            });
            var children = item.children;
            if ($.util.likeArrayNotString(children)) {
                item.children = parseMenuItems(children, args);
            }
            return item;
        });
    }


    $.extend($.easyui, {

        //  根据指定的属性创建 easyui-menu 对象；该方法定义如下参数：
        //      options: JSON 对象类型，参数属性继承 easyui-menu 控件的所有属性和事件（参考官方 API 文档），并在此基础上增加了如下参数：
        //          id      : 一个 String 对象，表示创建的菜单对象的 ID 属性。
        //          name    : 一个 String 对象，表示创建的菜单对象的 name 属性。
        //          hideOnUnhover   : 这是官方 API 中原有属性，此处调整其默认值为 false；
        //          hideDisabledItem: 一个 Boolean 值，默认为 false；该属性表示当菜单项的 disabled: true，是否自动隐藏该菜单项；
        //          event   :  表示引发生成菜单列表的动作事件对象；该参数可选；
        //          items: 一个 Array 对象，该数组对象中的每一个元素都是一个 JSON 格式对象用于表示一个 menu-item （关于 menu-item 对象属性，参考官方 API）；
        //                  该数组中每个元素的属性，除 easyui-menu 中 menu-item 官方 API 定义的属性外，还增加了如下属性：
        //              hideDisabledItem: 该属性表示在当前子菜单级别下当菜单项的 disabled: true，是否自动隐藏该菜单项；一个 Boolean 值，默认取上一级的 hideDisabledItem 值；
        //              handler: 一个回调函数，表示点击菜单项时触发的事件；
        //                  回调函数 handler 和回调函数 onclick 的签名都为 function(e, itemOpts, target)，其中：
        //                      e       : 表示点击菜单列表中单个菜单项所触发的动作事件对象；
        //                      itemOpts: 表示当前点击的菜单项的 options 选项；
        //                      target  : 表示整个菜单控件的 HTML-DOM 对象。
        //                      函数中 this 指向触发事件的单个菜单项 HTML-DOM 对象本身
        //                  另，如果同时定义了 onclick 和 handler，则只处理 handler 而不处理 onclick，所以请不要两个回调函数属性同时使用。
        //              children: 同上一级对象的 items 属性，为一个 Array 对象；
        //          slideOut:   一个 Boolean 类型值，表示菜单是否以滑动方式显示出来；默认为 false；
        //          autoDestroy : Boolean 类型值，表示菜单是否在隐藏时自动销毁，默认为 true；
        //      options 参数也可直接定义为数组类型，即为 items 格式的 Array 对象；当 options 直接定义为 items 格式数组时，生成的 easyui-item 对象的其他属性则为系统默认值。
        //  返回值：返回一个 JSON 格式对象，该返回的对象中具有如下属性：
        //      menu: 依据于传入参数 options 构建出的菜单 DOM 元素对象，这是一个 jQuery 对象，该对象未初始化为 easyui-menu 控件，而只是具有该控件的 DOM 结构；
        //      options: 传入参数 options 解析后的结果，该结果尚未用于但可用于初始化 menu 元素。
        createMenu: function (options) { return createMenu(options); },

        //  根据指定的属性创建 easyui-menu 对象并立即显示出来；该方法定义的参数和本插件文件中的插件方法 createMenu 相同：
        //  注意：本方法与 createMenu 方法不同之处在于：
        //      createMenu: 仅根据传入的 options 参数创建出符合 easyui-menu DOM 结构要求的 jQuery DOM 对象，但是该对象并未初始化为 easyui-menu 控件；
        //      showMenu: 该方法在 createMenu 方法的基础上，对创建出来的 jQuery DOM 对象立即进行 easyui-menu 结构初始化，并显示出来。
        //  返回值：返回一个 jQuery 对象，该对象表示创建并显示出的 easyui-menu 元素，该返回的元素已经被初始化为 easyui-menu 控件。
        showMenu: function (options) { return createAndShowMenu(options); },

        // 将一个表示 easyui-menu 中菜单项集合的数组，解析成 $.easyui.showMenu 方法能够支持的菜单项数组格式。该方法定义如下参数：
        //      menuItems   : Array 数组格式，数组中的每个元素都是一个表示 menu-item 菜单项的 JSON-Object，该 JSON-Object 对象包含如下属性定义：
        //          id      : String 类型值，默认为 null ；表示菜单项的 ID ；
        //          text    : String 类型值，默认为 null ；表示菜单项的本文信息；
        //          iconCls : String 类型值，默认为 null ；表示菜单项的图标样式名称；
        //          href    : String 类型值，默认为 null ；表示菜单项指向的远程地址；
        //          disabled: Boolean 类型值，默认为 false；表示菜单项是否禁用；
        //          bold    : Boolean 类型值，默认为 false；表示该菜单项是否字体加粗；
        //          style   : JSON-Object 类型值，默认为 null；表示要附加到该菜单项的样式；
        //          hideOnClick     : Boolean 类型值，默认为 true；表示点击该菜单项后整个菜单是否会自动隐藏；
        //          hideDisabledItem: Boolean 类型值，默认为 false；该属性表示当菜单项的 disabled: true，是否自动隐藏该菜单项；
        //          handler : 回调函数，表示点击菜单项时触发的事件，签名为 function(e, itemOpts, target)；
        //          onclick : 回调函数，表示点击菜单项时触发的事件，签名为 function(e, itemOpts, target)；
        //      args        : 需要附加到解析回调函数的参数数组；该参数的作用在于：
        //          menu-item 中的任意属性可为具体的值，也可以为一个返回值的回调函数；但是在属性值为回调函数的情况下，函数签名为 function (e, itemOpts, target)
        //          假设在这种情况下 args 的值为 [a, b, c]，则在调用 $.easyui.parseMenuItems 方法解析后，menu-item 属性的回调函数签名将变更为 function (e, itemOpts, target, a, b, c)；
        //          也就是说，参数 args 也将一个数组值作为 menu-item 属性回调函数的调用参数附加（而不是替换，因为原来的参数会保留）至函数的调用中；
        // 返回值：返回一个 Array 数组；该数组可用于作为 $.easyui.showMenu 方法调用的参数；
        parseMenuItems: function (menuItems, args) { return parseMenuItems(menuItems, args); },

        // 隐藏/销毁 页面上所有的 easyui-menu 菜单项。
        hideAllMenu: function () { return hideAllMenu(); }


        // 另，本扩展增加 easyui-menu 控件中 menu-item 的如下自定义扩展属性:
        //      hideOnClick:    Boolean 类型值，默认为 true；表示点击该菜单项后整个菜单是否会自动隐藏；
        //      bold:           Boolean 类型值，默认为 false；表示该菜单项是否字体加粗；
        //      style:          JSON-Object 类型值，默认为 null；表示要附加到该菜单项的样式；
        // 注意：自定义扩展属性 bold、style 仅在通过 $.easyui.createMenu 或者 $.easyui.showMenu 生成菜单时，才有效。

    });



})(jQuery);