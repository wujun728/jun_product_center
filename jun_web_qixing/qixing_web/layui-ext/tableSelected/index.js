/**
 * 表格选择器,赋予普通输入框更强的选择能力
 * @author 黄俊杰
 * @version 1.0.0.20210828
 */
layui.define(["jquery", "layer", "table", "form"], function (exports) {
    var $ = layui.jquery;
    var layer = layui.layer;
    var table = layui.table;
    var form = layui.form;

    // 自增id
    var id = 0;

    // 扩展入口
    function Plugin() {
        this.name = "tableSelected";
        this.version = "1.0.0.20210828";
        this.package = this.name.replace(/([A-Z])/g, "-$1").toLowerCase();

        this.options = {
            // 搜索field
            searchField: "keyword",
            // 弹层位置, 可设置为 up(上), down(下)
            position: "down",
            // 自动渲染, 意味着只要你选择了就自动回显
            // 而不是还需要点击按钮才提交
            autoRender: false,
            // 自动重置, 当搜索输入框没内容恢复默认状态
            autoReset: true,
            // 分隔符
            delimiter: {
                // 原始input的分隔符
                native: ",",
                // 渲染input的分隔符
                render: ",",
            },
            // 选中列表, 也可通过input的value直接设定
            value: "",
            // 1=单选;!0且大于1=多选,等于限制可选数量
            limit: 0,
            // 多重显示(双倍快乐), 这是什么意思呢?
            // 就是如果比如你希望页面显示的是 echoKey <名字>
            // 而表单内值为 rowKey, 那么就需要开启本选项
            // 默认是开启的, 如果你不需要这样, 可以关闭
            // 如果你开启了, 考虑到分页的场景, 会提供一个翻译器
            // 通过翻译器返回请求后端的接口通过id返回行数据
            // 没事, 如果你理解不了这啥意思就直接看示例吧
            // 或者就换成id吧, 不丢人
            doubleData: true,
            // 每一行数据的唯一标志位,理论上对应着每个表的主键
            // 当然如果你是前端不知道主键啥意思就去问后端要行数据的唯一值
            // 大部分情况下就那么几个关键字, 自己猜都能猜到
            rowKey: "id",
            // 回显值, 即显示的输入框显示的值
            // 本身的输入框会变成隐藏域
            echoKey: "name",
            // 表格属性, 可参考 layui table 基础属性部分知识
            // 学习地址: https://www.layui.com/doc/element/table.html#default
            tableAttr: {
                // 隔行背景
                layEven: true,
                // 风格
                laySkin: "",
                // 尺寸
                laySize: "",
            },
            // 搜索表单配置
            searchForm: {
                inputValue: "",
                inputPlaceholder: "在列表内搜索",
                submitText: "搜索",
            },
            // 提交按钮配置
            submitText: {
                zero: "请选择",
                notzero: "已选择%s个",
                radio: "确认",
            },
            // 容器尺寸定制
            width: "100%",
            // 事件托管
            event: {
                // 生命周期类
                beforeCreate: function () {},
                created: function () {},
                beforeMount: function () {},
                mounted: function () {},
                beforeUpdate: function () {},
                updated: function () {},
                // 业务逻辑类
                formatRowKey: function () {},
                tableDone: function () {},
                change: function () {},
                checked: function () {},
                submit: function () {},
                search: function () {},
                checkbox: function () {},
                radio: function () {},
            },
            // 表格控制
            tableConfig: undefined,
        };
    }

    /**
     * 合并对象
     * @param {*} obj1 对象1
     * @param {*} obj2 对象2
     * @returns
     */
    Plugin.prototype.mergeObject = function (obj1, obj2) {
        var _this = this;
        if (!obj1) obj1 = {};
        if (!obj2) obj2 = {};

        var result = {};

        Object.keys(obj1).forEach(function (key) {
            if (obj2[key]) {
                if (_this.isObject(obj1[key])) {
                    result[key] = _this.mergeObject(obj1[key], obj2[key]);
                } else {
                    result[key] = obj2[key];
                }
            } else {
                result[key] = obj1[key];
            }
        });
        return result;
    };

    /**
     * 检查是否是对象
     * @param {*} data
     * @returns
     */
    Plugin.prototype.isObject = function (data) {
        return Object.prototype.toString.call(data) === "[object Object]";
    };

    /**
     * 构建路径, 通常用于自定义DOM的classList或id
     * @returns
     */
    Plugin.prototype.buildPath = function () {
        var path = [this.package].concat(Array.apply(this, arguments));
        return path.join("-");
    };

    /**
     * 隐藏特定表格容器
     * @param {*} ignoreId 忽略的id
     */
    Plugin.prototype.hideTableContainer = function (ignoreId) {
        var _this = this;
        var $elems = $("." + _this.buildPath("table-container"));
        $elems.each(function () {
            var $el = $(this);
            if ($el.attr("id") !== ignoreId) {
                $el.hide();
                $el.find('input[type="checkbox"],input[type="radio"]').prop("checked", false);
            }
        });
    };

    /**
     * 激活配置中的函数
     * @param {*} reference 参考配置
     * @param {*} funcName 函数名
     */
    Plugin.prototype.activeFunction = function (reference, funcName, args) {
        if (typeof reference !== "undefined" && this.isObject(reference)) {
            if (typeof reference[funcName] === "function") {
                reference[funcName](args);
            }
        }
    };

    /**
     * 数组对象进行合并并且去重
     * @param {*} arg1 数组1
     * @param {*} arg2 数组2
     * @param {*} rowKey 行key
     */
    Plugin.prototype.mergeArrayObject = function (arg1, arg2, rowKey) {
        if (!arg1) arg1 = [];
        if (!arg2) arg2 = [];

        arg2.forEach(function (newRow) {
            var append = true;
            arg1.forEach(function (oldRow) {
                if (oldRow[rowKey] == newRow[rowKey]) {
                    append = false;
                }
            });
            if (append) {
                arg1.push(newRow);
            }
        });

        return arg1;
    };

    /**
     * 再数组对象中进行根据id去除数据
     * @param {*} arg1 数组1
     * @param {*} arg2 数组2
     * @param {*} rowKey 行key
     */
    Plugin.prototype.removeArrayObject = function (arg1, arg2, rowKey) {
        arg2.forEach(function (newRow) {
            for (var i = 0; i < arg1.length; i++) {
                var oldRow = arg1[i];

                if (oldRow[rowKey] == newRow[rowKey]) {
                    arg1.splice(i, 1);
                    i--;
                }
            }
        });

        return arg1;
    };

    // 渲染入口
    Plugin.prototype.render = function (elem, opt) {
        if (!elem) throw new Error("丢失必要参数: elem");
        if (!opt) opt = {};

        var $elems = $(elem);

        var _this = this;

        $(document).click(function () {
            _this.hideTableContainer();
        });

        $elems.each(function () {
            id++;
            main($(this), _this.mergeObject(_this.options, opt || {}), _this, id);
        });
    };

    // 入口
    function main(elem, opt, plugin, id) {
        var _this = plugin;

        // 表格实例
        var layuiTable = null;

        // 表格及容器id
        var tableId = _this.buildPath("table", id);
        var tableContainerId = _this.buildPath("table-container", id);

        // 原始input
        var $nativeInput = elem;

        // 原始父元素
        var $nativeParnet = $nativeInput.parent();

        // 映射关系
        var event = opt.event || {};
        var tableAttr = opt.tableAttr || {};
        var searchForm = opt.searchForm || {};
        var width = opt.width;
        var tableConfig = opt.tableConfig || {};
        var limit = opt.limit || 0;
        var type = limit == 1 ? "radio" : "checkbox";
        var value = opt.value || $nativeInput.val();
        var selectedRows = [];
        var rowKey = opt.rowKey;
        var echoKey = opt.echoKey;
        var submitText = opt.submitText || {};
        var autoRender = opt.autoRender;
        var delimiter = opt.delimiter || {};
        var doubleData = opt.doubleData || true;
        var position = opt.position;
        var searchField = opt.searchField;
        var autoReset = opt.autoReset;

        // 创建Dom
        _this.activeFunction(event, "beforeCreate");

        // 虚拟DOM汇总
        var $container = $("<div>").addClass(_this.buildPath("container"));
        var $input = $("<input>").addClass(_this.buildPath("input")).addClass("layui-input").attr("placeholder", $nativeInput.attr("placeholder")).attr("readonly", true).attr("unselectable", "on");
        var $tableContainer = $("<div>").addClass(_this.buildPath("table-container")).addClass(_this.buildPath(position)).attr("id", tableContainerId);
        if (width) $tableContainer.css({ width: width });
        var $tableContainerHeader = $("<div>").addClass(_this.buildPath("table-container-header"));
        var $tableContainerForm = $("<div>").addClass(_this.buildPath("table-container-form"));
        var $tableContainerFormInput = $("<input>").addClass(_this.buildPath("table-container-form-input")).attr("placeholder", searchForm.inputPlaceholder).val(searchForm.inputValue);
        var $tableContainerFormSubmit = $("<input>").addClass(_this.buildPath("table-container-form-submit")).attr("type", "button").val(searchForm.submitText);
        var $tableContainerSubmit = $("<input>").addClass(_this.buildPath("table-container-submit")).attr("type", "button");
        var $tableContainerBody = $("<div>").addClass(_this.buildPath("table-container-body"));
        var $table = $("<table>").attr("id", tableId).attr("lay-filter", tableId);
        if (tableAttr.layEven) $table.attr("lay-event", true);
        if (tableAttr.laySkin) $table.attr("lay-skin", tableAttr.laySkin);
        if (tableAttr.laySize) $table.attr("lay-size", tableAttr.laySize);

        // 虚拟DOM创建完成事件
        _this.activeFunction(event, "created");

        // 组装DOM
        $tableContainerForm.append($tableContainerFormInput).append($tableContainerFormSubmit);
        $tableContainerHeader.append($tableContainerForm);
        if (!autoRender) $tableContainerHeader.append($tableContainerSubmit);
        $tableContainerBody.append($table);
        $tableContainer.append($tableContainerHeader).append($tableContainerBody);
        $container.append($input).append($tableContainer);

        // 虚拟DOM组装完成, 但尚未塞到页面里
        _this.activeFunction(event, "beforeMount");

        // 塞入页面
        $nativeInput.hide();
        $nativeParnet.append($container);

        // 虚拟dom
        var dom = {
            $container: $container,
            $input: $input,
            $tableContainer: $tableContainer,
            $tableContainerHeader: $tableContainerHeader,
            $tableContainerForm: $tableContainerForm,
            $tableContainerFormInput: $tableContainerFormInput,
            $tableContainerFormSubmit: $tableContainerFormSubmit,
            $tableContainerBody: $tableContainerBody,
            $table: $table,
        };

        // 虚拟DOM组装完并已经塞入页面
        _this.activeFunction(event, "mounted", dom);

        // 注册事件
        $container.click(function (e) {
            e.stopPropagation();
        });
        $tableContainerSubmit.click(function () {
            renderInput();
            _this.hideTableContainer();
            if (type == "checkbox") _this.activeFunction(event, "submit", selectedRows);
            if (type == "radio") _this.activeFunction(event, "submit", selectedRows[0]);
        });

        function tableSearch() {
            var where = {};
            var value = $tableContainerFormInput.val();
            where[searchField] = value;
            table.reload(tableId, {
                page: {
                    curr: 1,
                },
                where: where,
            });
        }

        $tableContainerFormSubmit.click(function () {
            var val = $tableContainerFormInput.val();
            if (val) tableSearch();
            _this.activeFunction(event, "search", { value: value, table: table, tableId: tableId });
        });

        if (autoReset) {
            $tableContainerFormInput.on("input", function () {
                var val = $tableContainerFormInput.val();
                if (!val) tableSearch();
            });
        }

        $input.click(function () {
            // 隐藏藏除了自己以外的其他容器
            _this.hideTableContainer();

            // 显示自己
            $tableContainer.show();

            // 渲染表格
            if (!layuiTable) {
                tableConfig.elem = "#" + tableId;
                tableConfig.id = tableId;
                tableConfig.done = function (res, curr, count) {
                    _this.activeFunction(event, "tableDone", { res: res, curr: curr, count: count });
                    activeSelected();
                };
                layuiTable = table.render(tableConfig);
            }
        });

        // 激活选中
        function activeSelected() {
            var nowRows = table.getData(tableId);

            if (type == "radio") {
                selectedRows.forEach(function (row) {
                    for (var i = 0; i < nowRows.length; i++) {
                        if (row[rowKey] == nowRows[i][rowKey]) {
                            nowRows[i].LAY_CHECKED = true;
                            var $elem = $tableContainer.find("tr[data-index=" + i + '] input[type="radio"]');
                            $elem.prop("checked", true);
                            break;
                        }
                    }
                });

                form.render();
            }

            if (type == "checkbox") {
                selectedRows.forEach(function (row) {
                    for (var i = 0; i < nowRows.length; i++) {
                        if (row[rowKey] == nowRows[i][rowKey]) {
                            nowRows[i].LAY_CHECKED = true;
                            var $elem = $tableContainer.find("tr[data-index=" + i + '] input[type="checkbox"]');
                            $elem.prop("checked", true);
                            break;
                        }
                    }
                });

                var selectedNumber = 0;
                nowRows.forEach(function (row) {
                    if (row.LAY_CHECKED) selectedNumber++;
                });
                if (selectedNumber == nowRows.length) {
                    var $elem = $tableContainer.find('.layui-table-header input[name="layTableCheckbox"]');
                    $elem.prop("checked", true);
                }

                form.render();
            }
        }

        // 内置渲染函数: 提交按钮
        function renderSubmit() {
            if (selectedRows.length == 0) {
                $tableContainerSubmit.addClass("layui-btn-disabled").attr("disabled", true).val(submitText.zero);
            } else {
                $tableContainerSubmit.removeClass("layui-btn-disabled").removeAttr("disabled");
                var str;
                if (type == "radio") str = submitText.radio;
                if (type == "checkbox") str = submitText.notzero;
                $tableContainerSubmit.val(str.replace(new RegExp("%s", "g"), selectedRows.length));
            }
        }

        // 内置渲染函数: 渲染的输入框
        function getEchoValues() {
            var values = [];
            selectedRows.forEach(function (row) {
                values.push(row[echoKey]);
            });
            return values.splice(delimiter.render);
        }

        // 内置渲染函数: 原始输入框
        function getRowValues() {
            var values = [];
            selectedRows.forEach(function (row) {
                values.push(row[rowKey]);
            });
            return values.splice(delimiter.native);
        }

        // 渲染输入框
        function renderInput() {
            var echoValues = getEchoValues();
            var rowValues = getRowValues();

            $input.val(doubleData ? echoValues : rowValues);
            $nativeInput.val(rowValues);
        }

        // 默认执行一遍
        renderSubmit();
        if (autoRender) renderInput();

        if (doubleData && value) {
            function next(result) {
                selectedRows = result;
                renderSubmit();
                renderInput();
            }

            _this.activeFunction(event, "formatRowKey", { value: value, next: next });
        }

        // 如果是多选
        switch (type) {
            case "checkbox":
                table.on("checkbox(" + tableId + ")", function (res) {
                    var type = res.type;
                    var row = res.data;
                    var checked = res.checked;
                    var allRows = table.getData(tableId);

                    if (type == "all") {
                        if (checked) {
                            var checkStatus = table.checkStatus(tableId);
                            selectedRows = _this.mergeArrayObject(selectedRows, checkStatus.data, rowKey);
                        } else {
                            var nowRows = [].concat(allRows);
                            selectedRows = _this.removeArrayObject(selectedRows, nowRows, rowKey);
                        }
                    }

                    if (type == "one") {
                        if (checked) {
                            selectedRows = _this.mergeArrayObject(selectedRows, [row], rowKey);
                        } else {
                            selectedRows = _this.removeArrayObject(selectedRows, [row], rowKey);
                        }
                    }

                    _this.activeFunction(event, "beforeUpdate", { res: res, allRows: allRows, selectedRows: selectedRows });
                    renderSubmit();
                    if (autoRender) renderInput();
                    _this.activeFunction(event, "updated", { res: res, allRows: allRows, selectedRows: selectedRows });
                    _this.activeFunction(event, "change", { res: res, allRows: allRows, selectedRows: selectedRows });
                    _this.activeFunction(event, "checked", { res: res, allRows: allRows, selectedRows: selectedRows });

                    if (type != "all") {
                        _this.activeFunction(event, "checkbox", selectedRows);
                    }
                });
                break;
            case "radio":
                table.on("radio(" + tableId + ")", function (res) {
                    var row = res.data;
                    var checked = res.checked;
                    var allRows = table.getData(tableId);

                    selectedRows = checked ? [row] : [];

                    _this.activeFunction(event, "beforeUpdate", { res: res, allRows: allRows, selectedRows: selectedRows });
                    renderSubmit();
                    if (autoRender) renderInput();
                    _this.activeFunction(event, "updated", { res: res, allRows: allRows, selectedRows: selectedRows });
                    _this.activeFunction(event, "change", { res: res, allRows: allRows, selectedRows: selectedRows });
                    _this.activeFunction(event, "checked", { res: res, allRows: allRows, selectedRows: selectedRows });

                    _this.activeFunction(event, "radio", row);
                });
                break;
        }
    }

    // 创建一个女朋友
    var plugin = new Plugin();
    // 给女朋友穿上漂亮小衣服
    layui.link(layui.cache.base + plugin.name + "/css/index.css");
    // 发个朋友圈秀一下恩爱 ❤
    exports(plugin.name, plugin);
});
