/* ============================================================
 * bootsrtapui v1.0 by taylor@ichartjs.com
 * required bootstrap
 * http://www.ichartjs.com/
 * ============================================================
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 * ============================================================ */
!function ($, I) {
    var B = {
        },
        unsave = false,
        loading = false,
        chart = undefined,
        $template,
        $form_tbody,
        $form_tr_temlate,
        $validate,
        customizedUI = [],
        storage = window.localStorage,
        ichartjsKey = 'ichartjs_localStorage',
        uiMap = {},
        handler = [],
        M = {
            render: 'ichart-render'
        },
        isDefined = function (e) {
            return typeof e !== "undefined"
        },
        isString = function (e) {
            return typeof e === "string"
        },
        isArray = function (e) {
            return !!e && ts.apply(e) === "[object Array]"
        },
        /**
         * simple extend
         */
            extend = function (M, U) {
            U = U || UI;
            M = M || {};
            var J = function () {
                U.apply(this, arguments);
            }
            var E = function () {
            }, H, D = U.prototype;
            E.prototype = D;
            H = J.prototype = new E();
            H.constructor = J;
            J.superclass = D;
            for (var a in M) {
                H[a] = M[a]
            }
            return J;
        },
        ts = Object.prototype.toString,
        isObject = function (e) {
            return !!e && ts.apply(e) === "[object Object]"
        },
        UI = function (key, options) {
            this.options = $.extend({
                type: 'number',
                owner: '',
                value: ''
            }, isString(options) ? {text: options} : options);
            this.key = key;
            this.timeout = null;

            this.owner = this.options.owner;

            /**
             * 用于dealay组件的判定
             */
            this.loading = false;
            this.delaytime = 300;
            this.inputer = true;
            this.target = $(this.markup().join(""));

            if (this.owner != '') {
                this.hide();
                customizedUI.push(this);
            }

            (B.box || this.options.box).append(this.target);
            this.after(this);
            if (this.inputer) {
                uiMap[key] = this;
                this.update();
            }
        }
    UI.prototype = {
        input: function () {
            return this.target.find(":input");
        },
        hide: function () {
            this.target.hide();
        },
        show: function () {
            this.target.show();
        },
        doLayout: function () {
            if (this.owner == M.type) {
                this.show();
            } else {
                this.hide();
            }
        },
        disabled: function (o) {
            this.input().attr("disabled", o ? "disabled" : false);
        },
        getM: function () {
            return{
                key: this.key,
                value: this.val()
            }
        },
        delay: function (_) {
            if (chart && !this.doInit) {
                this.loading = loading;
                if (_.timeout) {
                    clearTimeout(_.timeout);
                }
                _.timeout = setTimeout(function () {
                    _.handler();
                    _.timeout = null;
                }, _.delaytime);
            }
            this.doInit = false;
        },
        update: function () {
            var A = this.key.split("."), L = A.length - 1, V = M;
            for (var i = 0; i < L; i++) {
                if (!V[A[i]])
                    V[A[i]] = {};
                V = V[A[i]];
            }
            V[A[L]] = this.val();
        },
        handler: function () {
            if (chart) {
                for (var i = 0; i < handler.length; i++) {
                    handler[i].apply(this, [this.key, this.val()]);
                }

                this.update();
                if (!loading && !this.loading) {
                    saved(false);
                    this.loading = false;
                }
            }
        },
        val: function (v) {
            if (!isDefined(v)) {
                v = this.input().val();
                if (this.options.type == 'number' || this.options.type == 'range') {
                    v = parseFloat(v);
                    if (isNaN(v)) {
                        v = 0;
                    }
                }
                v = this.zoom ? v / this.zoom : v;
                if (this.unit) {
                    v = v + this.unit;
                }
                if (this.formatter) {
                    v = this.formatter(v);
                }
            } else {
                v = this.zoom ? v * this.zoom : v;
                if (this.options.type == 'number' || this.options.type == 'range') {
                    v = parseFloat(v);
                    if (isNaN(v)) {
                        v = 0;
                    }
                }
                if (this.formatter) {
                    v = this.formatter(v, true);
                }
                this.input().val(v);
                this.update();
            }
            return v;
        },
        after: function (_) {
        }
    }

    var getItem = function (k) {
        return storage.getItem(k);
    }

    var setItem = function (k, v) {
        return storage.setItem(k, v);
    }

    var removeItem = function (k) {
        if (M.id && M.id == k) {
            clear();
        } else {
            if(M.id)
            I.remove(M.id);
        }
        var arr = JSON.parse(getItem(ichartjsKey));
        var arr0 = [];
        arr.forEach(function (a) {
            if (a != k) {
                arr0.push(a);
            }
        });
        setItem(ichartjsKey, JSON.stringify(arr0));
        return storage.removeItem(k);
    }

    var saved = function (s) {
        if (s) {
            $("#ide-save").attr("disabled", "disabled");
        } else {
            $("#ide-save").removeAttr("disabled");
        }
        unsave = !s;
    }
    /**
     * save M to localStorage
     */
    var saveM = function () {
        saved(true);
        if (!M.id) {
            M.id = new Date().getTime().toString().substring(4);
        }
        setItem(M.id, JSON.stringify(M));
        var str = getItem(ichartjsKey);
        var arr = JSON.parse(getItem(ichartjsKey));
        if (str.indexOf(M.id) == -1) {
            arr.push(M.id);
            setItem(ichartjsKey, JSON.stringify(arr));
            loadLocal(M.id);
        } else {
            var arr0 = [];
            arr.forEach(function (a) {
                if (a != M.id) {
                    arr0.push(a);
                }
            });
            arr0.push(M.id);
            setItem(ichartjsKey, JSON.stringify(arr0));
            $("#ichartjs_" + M.id).html(M.title.text);
        }
    }

    var loadLocal = function (m) {
        m = JSON.parse(getItem(m));
        var $item = $("<li><a href=\"javascript:BootStrap.load('" + m.id + "')\"><strong>&middot;</strong>&nbsp;<span id='ichartjs_" + m.id + "'>" + m.title.text + "</span>&nbsp;&nbsp;<button class='btn btn-mini btn-success' type='button'>移除</button></a></li>");
        $item.find("button").data("value", m.id).click(function (e) {
            removeItem($(this).data("value"));
            $(this).parent().parent().slideDown(600, function () {
                $(this).remove();
            });
            e.stopPropagation();
            e.preventDefault();
        });
        $("#ide-local").prepend($item);
    }

    $(function () {
        var k = getItem(ichartjsKey);
        if (!k) {
            setItem(ichartjsKey, JSON.stringify([]));
        } else {
            JSON.parse(k).forEach(function (m) {
                loadLocal(m);
            });
        }

        $('.myminicolors').each(function () {
            $(this).minicolors({
                control: $(this).attr('data-control') || 'hue',
                defaultValue: $(this).attr('data-default-value') || '',
                inline: $(this).hasClass('inline'),
                letterCase: 'lowercase',
                opacity: $(this).hasClass('opacity'),
                position: 'top left',
                styles: '',
                swatchPosition: 'right',
                textfield: !$(this).hasClass('no-textfield'),
                theme: 'bootstrap'
            });
        });
        $template = $("#form_tbody").children("tr").clone(true);
        $form_tbody = $("#form_tbody");
        $validate = $("#validateTips");
        /**
         * 移除行数据事件
         */
        $form_tbody.find("button").click(function () {
            if (!$(this).hasClass("disabled")) {
                $(this).parents("tr").remove();
            }
        });

        $form_tr_temlate = $form_tbody.children("tr").first().clone(true);
        var $inputs = $form_tr_temlate.find("input");
        $inputs.eq(0).val("新增");
        $inputs.eq(1).val(10);
        $inputs.eq(2).minicolors('value', '#4572a7');
        $form_tr_temlate.find("button").removeClass("disabled");

        /**
         * 增加行数据事件
         */
        $("#ide-add-row").click(function () {
            $form_tbody.append($form_tr_temlate.clone(true));
           // $modal_body.scrollTop(1000);
        });

        var validate = function (input) {
            if (input.value == "") {
                $(input).focus();
                $validate.html("提示:数据项不能为空!");
                return true;
            }
            return false;
        }

        $('#save_btn').bind('click', function () {
            var text = $("#form_title").val();
            if (text == "") {
                $validate.html("提示:标题项不能为空!");
                $("#form_title").focus();
                return;
            }
            M.title.text = text;

            M.subtitle.text = $("#form_subtitle").val();
            M.footnote.text = $("#form_footnote").val();

            var $inputs = $form_tbody.children("tr").find("input"), data = [], color;

            for (var i = 0; i < $inputs.length; i += 4) {
                if (validate($inputs[i]) || validate($inputs[i + 1]) || validate($inputs[i + 2])) {
                    return;
                }
                color = $inputs[i + 2].value;
                var opacity = $($inputs[i + 2]).attr('data-opacity');

                if (isDefined(opacity) && opacity != 1) {
                    color = I.toRgba(color, opacity);
                }

                data.push({
                    name: $inputs[i].value,
                    value: parseFloat($inputs[i + 1].value) || 0,
                    color: color,
                    url:$inputs[i + 3].value
                });
            }

            M.data = data;
            $('#myModal').data("create", true).modal('hide');
        });

        $('#myModal').on('hidden', function () {
            if ($(this).data("create")) {
                create();
                saved();
            }
            if (!$(this).data("edit")) {
                M.id = null;
            }
            $(this).data("create", false).data("edit", false);
        });
        
        $('#myModal').on('show',function() {
            $(this).css({
                'margin-top': -0,
                'margin-left': -($(window).width() / 2)
            });
    	});

        $("#ide-data").attr("disabled", "disabled").on("click", function () {
            $("#dialog-form").html("编辑数据");
            $('#myModal').data("edit", true).modal('show');
        });

        var tab = "      ";
        var prefix = function (arr, i) {
            while (i > 0) {
                arr.push(tab);
                i--;
            }
        }
        /**
         * format code
         * @param obj object
         * @param index indent measure
         * @param d data
         * @return {String} code
         */
        var format = function (obj, index,d) {
            if (isObject(obj)) {
                var fragment = [], s;
                fragment.push("{");
                for (var a in obj) {
                    if(a=='id'){
                        continue;
                    }
                    if(d){
                        if(a!="name"&&a!="value"&&a!="color"&&a!="url"){
                            continue;
                        }
                    }
                    fragment.push("<br>");
                    fragment.push(prefix(fragment, index));
                    fragment.push(a);
                    fragment.push(":");
                    if (isObject(obj[a])) {
                        fragment.push(format(obj[a], index + 1));
                    } else if (isArray(obj[a])) {
                        var object = true;
                        obj[a].forEach(function (o) {
                            object = isObject(o)&&object;
                        });

                        fragment.push("[");
                        if(object){
                            fragment.push("<br>");
                            fragment.push(prefix(fragment, index + 1));
                        }

                        obj[a].forEach(function (o) {
                            fragment.push(format(o, index + 1,a=="data"));
                            fragment.push(",");
                        });

                        if (fragment[fragment.length - 1] == ",") {
                            fragment[fragment.length - 1] = "";
                        }
                        if(object){
                            fragment.push("<br>");
                            fragment.push(prefix(fragment, index));
                        }
                        fragment.push("]");

                    } else {
                        s = isString(obj[a]);
                        if (s) {
                            fragment.push("\"");
                        }
                        fragment.push(obj[a]);
                        if (s) {
                            fragment.push("\"");
                        }
                    }
                    fragment.push(",");
                }

                if (fragment[fragment.length - 1] == ",") {
                    fragment[fragment.length - 1] = "";
                }
                fragment.push("<br>");
                fragment.push(prefix(fragment, index - 1));
                fragment.push("}");
                return fragment.join("");
            } else {
                if(obj==''){
                    return "\"\"";
                }
                return obj;
            }
        }



        var code = function (h) {
            var html = [];

            html.push("&lt;!DOCTYPE html&gt;<br>");
            html.push("&lt;html lang='en'&gt;<br>");
            html.push("&lt;head&gt;<br>");
            html.push("&lt;meta charset='UTF-8'&gt;<br>");
            html.push("&lt;title>"+h.title.text+"&lt;/title&gt;<br>");
            html.push("&lt;script src='../plug/ichartjs-designer/plugin/jquery-1.8.0.min.js'&gt;&lt;/script&gt;<br>");
            html.push("&lt;script src='../plug/ichartjs-designer/js/ichart.1.2.1.src.beta.20140328.js'&gt;&lt;/script&gt;<br>");
            html.push("&lt;script src='../plug/ichartjs-designer/js/jquery.timers.js'&gt;&lt;/script&gt;<br>");
            html.push("&lt;script src='../plug/ichartjs-designer/js/ichart.data.js'&gt;&lt;/script&gt;<br>");
            html.push("&lt;/head&gt;<br>");
            html.push("&lt;body style='margin:0; padding:10px;'&gt;<br>");
            html.push("&lt;div id='ichart-render'&gt;&lt;/div&gt;<br>");
            html.push("&lt;script type='text/javascript'&gt;<br>");
            html.push("var chart_data=<br>");
            html.push("//数据开始<br>");
            html.push(format(h, 2));
            html.push("<br>//数据结束<br>");
            html.push("$(function(){<br>");
            html.push(tab);
            html.push("var chart = iChart.create(");
            html.push("chart_data");
            html.push(");<br>");
            html.push(tab);
            html.push("chart.draw();<br>");
            html.push("var h=$(this).height()-70;<br>");
            html.push("var w=$(this).width()-25;<br>");
            html.push("chart.resize(w,h);<br>");
            html.push("start(chart);<br>");
            html.push("});<br>");
            html.push("&lt;/script&gt;<br>");
            html.push("&lt;/body&gt;<br>");
            html.push("&lt;/html&gt;");
            return html.join("");
        }
        $("#code").keydown(function (e) {
             console.log(e);
        });

        $("#ide-code").attr("disabled", "disabled").on("click", function () {
            $("#code").html(code(M));
            hljs.fixMarkup("xml", null, true);
            hljs.highlightBlock(document.getElementById("code"));
            $('#myCodeModal').modal('show');
            $("#code").focus();

        });
        $("#ide-sys-code").on("click", function () {
            $("#code").html(code(M));
        });

        $("#ide-save").attr("disabled", "disabled").on("click", function () {
            if (B.editable() && unsave) {
                saveM();
            }
        });

        $("#ide-clear").on("click", function () {
            M.type = '';
            layout();
            clear();
        });

        $("#clipboard").on("click", function () {
            //var html = document.getElementById("code");
            //TODO
        });

        $("#save_create_btn").on("click", function () {
            $("#ide-save").click();
            $("#myTipModal").modal('hide').data("create", true);
        });


        $("#discard_create_btn").on("click", function () {
            $("#myTipModal").modal('hide').data("create", true);
        });

        $('#myTipModal').on('hidden', function () {
            if ($(this).data("create")) {
                clear();
                guide();
            }
            $(this).data("create", false);
        });
    });

    /**
     * 解析配置项
     */
    var stringify = function (e, p) {
        for (var a in e) {
            if (!isObject(e[a])) {
                if (uiMap[(p || "") + a]) {
                    uiMap[(p || "") + a].val(e[a], true);
                }
            } else {
                stringify(e[a], (p || "") + a + ".");
            }
        }
    }

    var clear = function () {
        if(M.id)
        I.remove(M.id);
        chart = null;
        saved(true);
        $("#ide-data").attr("disabled", "disabled");
        $("#ide-code").attr("disabled", "disabled");
        $("#ichart-render").fadeOut(300, function () {
            $(this).html("");
        });
        stringify(B.DefaultOption);
        $("#form_tbody").html($template.clone(true));
        $("#form_title").val("DOROODO图表组件");
        $("#form_sub_title").val("");
        $("#form_footnote").val("");
    }

    var guide = function () {
        $('#myModal').modal('show');
    }

    var layout = function () {
        for (var i = 0; i < customizedUI.length; i++) {
            customizedUI[i].doLayout();
        }
    }

    /**
     * 渲染
     */
    var create = function () {
        $("#ichart-render").hide();
        if(M.id)
        I.remove(M.id);
        chart = I.create(M);
        chart.draw();
        start(chart);
        $("#ichart-render").fadeIn();
        layout();
        $("#ide-data").removeAttr("disabled");
        $("#ide-code").removeAttr("disabled");
    }

    /**
     * 绑定行数据项
     */
    var bindingrow = function ($row, d) {
        var $input = $row.find("input");
        $($input[0]).val(d.name);
        $($input[1]).val(d.value);
        toHex($($input[2]), d.color);
        $($input[3]).val(d.url);
        return $row;
    }

    var databinding = function (d, t, st, ft) {
        $("#form_title").val(t);
        $("#form_sub_title").val(st);
        $("#form_footnote").val(ft);

        $form_tbody.children("tr").slice(d.length).remove();
        var $tr = $form_tbody.children("tr");
        for (var i = 0; i < d.length; i++) {
            if (!$tr[i]) {
                $form_tbody.append(bindingrow($form_tr_temlate.clone(true), d[i]));
            } else {
                bindingrow($($tr[i]), d[i]);
            }
        }
    }

    var f = function (o) {
        return (o && o.text) ? o.text : "";
    }

    var i2hex = function (N) {
        return ('0' + parseInt(N).toString(16)).slice(-2);
    }

    var toHex = function ($input, v) {
        v = v.toLowerCase();
        $input.attr('data-opacity', 1);
        if (/^rgb\([0-9]{1,3},[0-9]{1,3},[0-9]{1,3}\)$/.exec(v)) {
            var result = /rgb\((\w*),(\w*),(\w*)\)/.exec(v);
            v = ('#' + i2hex(result[1]) + i2hex(result[2]) + i2hex(result[3])).toUpperCase();
        }
        if (/^rgba\([0-9]{1,3},[0-9]{1,3},[0-9]{1,3},(0(\.\d*)?|1(\.0)?)\)$/.exec(v)) {
            var result = /rgba\((\w*),(\w*),(\w*),(.*)\)/.exec(v);
            v = ('#' + i2hex(result[1]) + i2hex(result[2]) + i2hex(result[3])).toUpperCase();
            $input.attr('data-opacity', result[4]);
        }
        $input.minicolors('value', v);
        return v;
    }

    /**
     * 加载配置项
     */
    B.load = function (e) {
        loading = true;
        if (isObject(e)) {
            M.type = e.type;

            stringify(e);

            databinding(e.data, f(e.title), f(e.subtitle), f(e.footnote));

            M.id = e.id;

            M.data = e.data;

            M.title = I.clone(e.title);
            M.subtitle = I.clone(e.subtitle);
            M.footnote = I.clone(e.footnote);

            create();
            saved(true);
        } else if (isString(e)) {
            B.load(JSON.parse(getItem(e)));
        }
        loading = false;
    }

    B.create = function (t, t0) {
        M.type = t;
        $("#dialog-form").html("创建图表-" + t0);
        $validate.html("*项文本均为必填项.");
        if (unsave) {
            $("#myTipModal").modal('show');
        } else {
            if (B.editable()) {
                clear();
            }
            M.type = t;
            guide();
        }
    }

    B.handler = function (f) {
        handler.push(f);
    }

    B.editable = function () {
        return !!chart;
    }

    B.handler(function (key, value) {
        if (chart) {
            if (key == 'width' || key == 'height') {
                if (key == 'width') {
                    chart.resize(value, chart.height);
                } else {
                    chart.resize(chart.width, value);
                }
                return;
            }
            chart.push(key, value);
            chart.setUp();
            chart.draw();
            start(chart);
        }
    });

    /**
     * 输入框组件
     */
    B.Input = extend({
        markup: function () {
            var H = [];
            H.push('<div class="row-fluid">');
            H.push('<div class="span12">');
            H.push('<div class="');
            this.zoom = this.options.zoom;
            this.unit = this.options.unit;
            this.formatter = this.options.formatter;
            if (this.options.text[0]) {
                H.push('input-prepend');
            }
            if (this.options.text[1]) {
                H.push(' input-append');
            }
            H.push('">');
            if (this.options.text[0]) {
                H.push('<span class="add-on">' + this.options.text[0] + '</span>');
            }
            H.push('<input autocomplete="off" class="' + (this.options.span || 'span6') + '" type="' + this.options.type + '" value="' + this.options.value + '"');
            if (isDefined(this.options.min)) {
                H.push(' min="' + this.options.min + '" ');
            }
            if (isDefined(this.options.max)) {
                H.push(' max="' + this.options.max + '" ');
            }
            if (isDefined(this.options.step)) {
                H.push(' step="' + this.options.step + '" ');
            }
            H.push('>');
            if (this.options.text[1]) {
                H.push('<span class="add-on">' + this.options.text[1] + '</span>');
            }
            H.push('</div>');
            H.push("</div>");
            H.push("</div>");
            return H;
        },
        after: function (_) {
            this.input().change(function () {
                _.handler();
            });
        }
    });
    /**
     * 按钮组件
     */
    B.Button = extend({
        input: function () {
            return this.target.find(":button");
        },
        val: function (v) {
            if (!isDefined(v)) {
                return this.options.value[this.active ? 1 : 0];
            } else {
                this.active = (this.options.value[1] == v);
                if (this.active) {
                    this.input().addClass("active");
                } else {
                    this.input().removeClass("active");
                }
                this.update();
            }
            return v;
        },
        markup: function () {
            this.active = false;
            var H = [];
            H.push('<div class="row-fluid">');
            H.push('<div class="span12">');
            H.push('<button type="button" style="margin-bottom:6px;" class="btn" data-toggle="button"><strong>' + this.options.text + '</strong></button>');
            H.push("</div>");
            H.push("</div>");
            return H;
        },
        after: function (_) {
            this.input().click(function () {
                _.active = !$(this).hasClass("active");
                _.handler();
            });
        }
    });

    /**
     * 下拉框组件
     */
    B.Select = extend({
        markup: function () {
            var H = [];
            H.push('<div class="row-fluid">');
            H.push('<div class="span12">');
            H.push('<select class="span6">');
            H.push('<option value="微软雅黑">微软雅黑</option>');
            H.push('<option value="Arial">Arial</option>');
            H.push('<option value="Verdana">Verdana</option>');
            H.push('</select>');
            H.push("</div>");
            H.push("</div>");
            return H;
        }
    }, B.Input);

    /**
     * 标签组件
     */
    B.Label = extend({
        markup: function () {
            this.inputer = false;
            return ['<span class="label ' + (this.options.label || "label-info") + '">' + this.key + '</span>'];
        }
    });

    /**
     * 分隔符组件
     */
    B.Divider = extend({
        markup: function () {
            this.inputer = false;
            return ['<div class="ide-divider"></div>'];
        }
    });

    /**
     * 取色器组件
     */
    B.Colors = extend({
        markup: function () {
            this.options.type = "text";
            var H = [];
            H.push('<div class="row-fluid">');
            H.push('<div class="span12">');
            H.push('<input type="text" value="' + this.options.value + '">');
            H.push("</div>");
            H.push("</div>");
            return H;
        },
        val: function (v, init) {
            this.doInit = !!init;
            if (!isDefined(v)) {
                return this.value;
            } else {
                toHex(this.input(), v);
                this.value = v;
                this.update();
            }
            return v;
        },
        after: function (_) {
            _.value = this.options.value || '#ffffff';
            this.input().minicolors({
                control: 'hue',
                defaultValue: _.value,
                inline: false,
                letterCase: 'lowercase',
                opacity: this.options.opacity,
                position: this.options.position || 'default',
                swatchPosition: 'left',
                theme: 'bootstrap',
                change: function (hex, opacity) {
                    _.value = ((!isDefined(opacity) || opacity == 1) ? hex : $(this).minicolors('rgbaString'));
                    _.delay(_);
                }
            });
        }
    });

    /**
     * 开关组件
     */
    B.Switch = extend({
        bind: function (o) {
            this.binding.push(o);
            o.disabled(!this.button.bootstrapSwitch("status"));
            return this;
        },
        markup: function () {
            this.binding = [];
            this.delaytime = 500;
            var H = [];
            H.push('<div class="row-fluid">');
            H.push('<div class="span12">');
            H.push('<div class="switch switch-small" data-on="success" data-off="warning">');
            H.push('<input type="checkbox" ' + (this.options.checked ? "checked" : "") + '/>');
            H.push("</div>");
            H.push("</div>");
            H.push("</div>");
            return H;
        },
        val: function (v, init) {
            this.doInit = !!init;
            if (!isDefined(v)) {
                return this.input()[0].checked;
            } else {
                this.button.bootstrapSwitch('setState', v);
                this.update();
            }
        },
        switched: function (v) {
            var Bi = this.binding;
            for (var i = 0; i < Bi.length; i++) {
                Bi[i].disabled(v);
            }
        },
        after: function (_) {
            var toggle = this.options.toggle;
            this.button = this.target.find(".switch");
            this.button.bootstrapSwitch().on('switch-change', function (e, data) {
                _.switched(!data.value);
                _.delay(_);
            });
        }
    });

    /**
     * 对齐组件
     */
    B.Alignment = extend({
        input: function () {
            return this.target.find(".active");
        },
        val: function (v) {
            if (isDefined(v)) {
                this.value = v;
                this.target.find(".active").removeClass("active");
                this.target.find(".icon-align-" + v).parent().addClass("active");
                this.update();
            }
            return this.value;
        },
        disabled: function (o) {
            this.target.find("button").attr("disabled", o ? "disabled" : false);
        },
        markup: function () {
            var H = [],
                A = this.options.active || 'center',
                f = function (t, a) {
                    H.push('<button value="' + t + '" class="btn' + (t == a ? " active" : "") + '" type="button">');
                    H.push('<em class="icon-align-' + t + '"></em>');
                    H.push('</button>');
                };

            this.value = A;
            H.push('<div class="row-fluid">');
            H.push('<div class="span12">');
            H.push('<div class="btn-group" data-toggle="buttons-radio">');

            f('left', A);
            f('center', A);
            f('right', A);

            H.push("</div>");
            H.push("</div>");
            H.push("</div>");
            return H;
        },
        after: function (_) {
            this.target.find("button").on('click', function () {
                if (!$(this).hasClass("active")) {
                    _.value = $(this).val();
                    _.handler();
                }
            });
        }
    });

    /**
     * 垂直对齐组件
     */
    B.VAlignment = extend({
        markup: function () {
            var H = [],
                A = this.options.active || 'middle',
                f = function (v, c, a) {
                    H.push('<button value="' + v + '" class="btn' + (a == v ? " active" : "") + '" type="button">');
                    H.push('<em class="' + c + '"></em>');
                    H.push('</button>');
                };

            this.value = A;
            H.push('<div class="row-fluid">');
            H.push('<div class="span12">');
            H.push('<div class="btn-group" data-toggle="buttons-radio">');

            f('top', "icon-chevron-up", A);
            f('middle', "icon-align-center", A);
            f('bottom', "icon-chevron-down", A);

            H.push("</div>");
            H.push("</div>");
            H.push("</div>");
            return H;
        }
    }, B.Alignment);
    window.BootStrap = B;
}(window.jQuery, window.iChart);