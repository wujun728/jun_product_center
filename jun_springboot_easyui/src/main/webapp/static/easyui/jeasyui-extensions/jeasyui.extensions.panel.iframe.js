/**
* jQuery EasyUI 1.4.3
* Copyright (c) 2009-2015 www.jeasyui.com. All rights reserved.
*
* Licensed under the GPL license: http://www.gnu.org/licenses/gpl.txt
* To use it on other terms please contact us at info@jeasyui.com
* http://www.jeasyui.com/license_commercial.php
*
* jQuery EasyUI panel 扩展
* jeasyui.extensions.panel.iframe.js
* 开发 流云
* 由 落阳 整理
* 最近更新：2015-11-09
*
* 依赖项：
*   1、jquery.jdirk.js
*   2、jeasyui.extensions.panel.css
*
* Copyright (c) 2015 ChenJianwei personal All rights reserved.
*/
(function ($) {

    $.util.namespace("$.fn.panel.extensions");

    function onBeforeDestroy() {
        $("iframe,frame", this).each(function () {
            try {
                if (this.contentWindow && this.contentWindow.document && this.contentWindow.close) {
                    this.contentWindow.document.write("");
                    this.contentWindow.close();
                }
                if (window.CollectGarbage) {
                    window.CollectGarbage();
                }
            } catch (ex) { }
        }).remove();
    }

    $.fn.panel.defaults.onBeforeDestroy = onBeforeDestroy;
    $.fn.window.defaults.onBeforeDestroy = onBeforeDestroy;
    $.fn.dialog.defaults.onBeforeDestroy = onBeforeDestroy;

    function getIframe(target) {
        var t = $(target),
            body = t.panel("body");
        return body.children("iframe.panel-iframe");
    }

    function setExtensionsOptions(opts) {
        opts._ext_panel_originals = { href: opts.href, content: opts.content };
        if (opts.iniframe) {
            opts.href = null;
            opts.content = null;
        }
    }

    function initializeExtensions(target) {
        var t = $(target),
            state = $.data(target, "panel"),
            opts = state.options,
            originals = opts._ext_panel_originals ? opts._ext_panel_originals : opts._ext_panel_originals = { href: opts.href, content: opts.content };

        opts.href = originals.href; opts.content = originals.content;
        if (opts.iniframe) {
            refresh(target);
        }
        if (opts.inline) {
            var panel = t.panel("panel"),
                parent = panel.parent();
            if (!parent.is("body") && !$.array.contains(["fixed", "absolute", "relative"], parent.css("position"))) {
                parent.addClass("panel-container");
            }
        }
    }

    function loadPanel(target) {
        var state = $.data(target, "panel");
        if (!state.isLoaded || !opts.cache) {
            var t = $(target),
                opts = state.options,
                param = $.extend({}, opts.queryParams);
            if (opts.onBeforeLoad.call(target, param) == false) {
                return;
            }
            state.isLoaded = false;
            t.panel("clear");
            if (opts.loadingMessage) {
                t.html($("<div class=\"panel-loading\"></div>").html(opts.loadingMessage));
            }
            opts.loader.call(target, param, success, error);
            function success(data) {
                var html = opts.extractor.call(target, data);
                $(target).html(html);
                $.parser.parse($(target));
                opts.onLoad.apply(target, arguments);
                state.isLoaded = true;
            }
            function error() {
                opts.onLoadError.apply(target, arguments);
            }
        }
    }

    function loadPanelIframe(target) {
        var t = $(target),
            state = $.data(target, "panel"),
            opts = state.options,
            param = $.extend({}, opts.queryParams);
        if (opts.onBeforeLoad.call(target, param) == false) {
            return;
        }
        var content = "<iframe class='panel-iframe' frameborder='0' width='100%' height='100%' marginwidth='0px' marginheight='0px' scrolling='auto'></iframe>";
        t.panel("clear");
        t.addClass("panel-body-withframe").html(content);

        t.panel("iframe").bind({
            load: function () {
                if ($.isFunction(opts.onLoad)) {
                    opts.onLoad.apply(target, arguments);
                }
            },
            error: function () {
                if ($.isFunction(opts.onLoadError)) {
                    opts.onLoadError.apply(target, arguments);
                }
            }
        }).attr("src", opts.href || "");
        state.isLoaded = true;
    }

    function refresh(target, href) {
        var t = $(target),
            state = $.data(target, "panel"),
            opts = state.options;
        state.isLoaded = false;
        if (href) {
            if (typeof href == "string") {
                opts.href = href;
            } else {
                opts.queryParams = href;
            }
        }

        if (!opts.href)
            return;

        t.panel("body").removeClass("panel-body-withframe");
        if (opts.iniframe) {
            loadPanelIframe(target);
        } else {
            loadPanel(target);
        }
    }



    var _panel = $.fn.panel;
    $.fn.panel = function (options, param) {
        if (typeof options == "string") {
            return _panel.apply(this, arguments);
        }
        options = options || {};
        return this.each(function () {
            var jq = $(this),
                isInited = $.data(this, "panel") ? true : false,
                opts = isInited ? options : $.extend({},
                        $.fn.panel.parseOptions(this),
                        $.parser.parseOptions(this, [
                            { iniframe: "boolean" }
                        ]), options);
            if (!isInited) {
                setExtensionsOptions(opts);
            }
            _panel.call(jq, opts, param);
            if (!isInited) {
                initializeExtensions(this);
            }
        });
    };
    $.union($.fn.panel, _panel);

    var defaults = {

        //  扩展 easyui-panel 控件的自定义属性，该属性表示 href 加载的远程页面是否装载在一个 iframe 中。
        iniframe: false,

        //  重写 easyui-panel/window/dialog 控件的原生事件 onBeforeDestroy，以在 easyui-panel/window/dialog 控件执行 destroy 时能够释放浏览器资源。
        onBeforeDestroy: onBeforeDestroy
    };

    var methods = {

        //  扩展 easyui-panel 控件的自定义方法；该方法用于获取在 iniframe: true 时当前 panel 控件中的 iframe 容器对象；
        //  备注：如果 inirame: false，则该方法返回一个空的 jQuery 对象。
        iframe: function (jq) { return getIframe(jq[0]); },

        //  重写 easyui-panel 控件的 refresh 方法，用于支持 iniframe 属性。
        refresh: function (jq, href) { return jq.each(function () { refresh(this, href); }); }
    };

    var compent = $.fn.panel;
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