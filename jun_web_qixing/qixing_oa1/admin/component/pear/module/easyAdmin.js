layui.define(['jquery', 'element', 'form', 'table', 'yaml', 'common'], function (exports) {
    "use strict";

    var MOD_NAME = 'easyAdmin';
    var $ = layui.jquery;
    var table = layui.table;
    var element = layui.element;
    var common = layui.common;
    var form = layui.form;
//    debugger;
    var yaml = layui.yaml;
    // 配置服务端地址,先获取adminServerUrl，否则第一次easyAdmin为空
    // let adminServerUrl = 'http://qixing.hbqxcpa.cn';
    let adminServerUrl = '';
//    let adminServerUrl = yaml.load("config/pear.config.yml").admin.server;
    layui.data('easyAdmin', {
        key: 'serverUrl'
        , value: adminServerUrl
    });
    console.log('服务端地址:' + adminServerUrl);
 
    var easyAdmin = new function () {
	
	    layui.data('easyAdmin', {
	        key: 'serverUrl'
	        , value: ''
	        // , value: 'http://qixing.hbqxcpa.cn'
	    });
	    var adminServerUrl = layui.data('easyAdmin').serverUrl;
	    console.log('服务端地址1111:' + 2233332);

        this.GetAdminServerUrl = function () {
            return layui.data('easyAdmin').serverUrl;
        }

        this.GetTokenQueryString = function () {
//            var user = layui.data('user');
            // 用于判断未登录跳转到登录页
//            if (JSON.stringify(user) == "{}") {
//                console.log("当前浏览器存储中没有用户信息，讲跳转到login.html")
//                location.href = "login.html";
//            }
//            return user.token.name + "=" + user.token.value;
			return '';
        }
        /**
         * 通用 http请求 参数同 ajax 有部分扩展
         * sendToken：是否发送token默认发送
         * @param options
         */
        this.http = function (options) {
            var defaults = {
                type: 'get',
                headers: {},
                data: {},
                dataType: null,
                async: true,
                cache: false,
                // 是否发送token默认发送
                sendToken: true,
                beforeSend: null,
                success: null,
                complete: null
            };
            var o = $.extend({}, defaults, options);
            // 配置服务端地址
            var adminServerUrl = layui.data('easyAdmin').serverUrl;

            $.ajax({
                url: adminServerUrl + o.url,
                type: o.type,
                headers: {
                    'Content-Type': o.contentType,
                    // 'access_token': o.token
                },
                data: o.data,
                dataType: o.dataType,
                async: o.async,
                beforeSend: function (request) {
                    if (o.sendToken) {
                        o.beforeSend && o.beforeSend();
//                        var user = layui.data('user');
//                        if (JSON.stringify(user) == "{}") {
//                            console.log("当前浏览器存储中没有用户信息，讲跳转到login.html")
////                            location.href = "login.html";
//                        }
							var localData = layui.data('LocalData');
				        	var token = localData["access_token"];
				            var headers =  {"authorization": token};
                        request.setRequestHeader("authorization", token);
                    }
                },
                success: function (res) {
                    o.success && o.success(res);
                },
                complete: function () {
                    o.complete && o.complete();
                },
                error: function (res) {
                    let data = res.responseJSON;
                    o.error && o.error(res);
                    easyAdmin.redirectToLogin(data);
                }
            });
        };

        /**
         *
         * @param uri 地址
         * @param success 成功回调
         * @param sendToken 是否发送token
         * @param async 是否异步
         */
        this.httpGet = function (uri, success, sendToken, async) {
            this.http({
                url: uri,
                success: success,
                sendToken: sendToken,
                async: async
            })
        };

        this.redirectToLogin = function (data) {
            if (data &&!data.success && data.code === '401') {
                console.log("会话已经过期了")
                layer.open({
                    time: 1500,// 自动关闭所需毫秒
                    skin: 'layui-layer-admin',
                    content: '会话已过期，即将跳转到登录页',
                    end: function () {
                        var login = layui.data('login');
                        if (top != window) { // 如果不是最外面的壳，则让浏览器的url改变
                            // top.location.href = login.url;
                        }
                    }
                });
            }
        }

        /**
         * 表格渲染
         * @param options
         */
        this.tableRender = function (options) {
            var load = layer.load();
            var defaults = {
                elem: '#table',
                page: true,
                skin: 'line',
                where: null,
                toolbar: '#table-toolbar',
                defaultToolbar: [{
                    title: '刷新',
                    layEvent: 'refresh',
                    icon: 'layui-icon-refresh',
                },
                    'filter', 'print', 'exports']
                ,
                sendToken: true
            };
            var o = $.extend({}, defaults, options);
            // 配置服务端地址
            var adminServerUrl = layui.data('easyAdmin').serverUrl;
//            var user;
//            if (o.sendToken) {
//                user = layui.data('user');
//                // 用于判断未登录跳转到登录页
//                if (JSON.stringify(user) == "{}") {
//                    console.log("当前浏览器存储中没有用户信息，讲跳转到login.html")
////                    location.href = "login.html";
//                }
//            }
            var localData = layui.data('LocalData');
        	var token = localData["access_token"];
//        {"authorization": token}
//            var tokenName = user.token.name;
//            var tokenValue = user.token.value;
            var headers =  {"Authorization": localStorage.getItem("Authorization")};
            //headers: {"Authorization": localStorage.getItem("Authorization")},
            table.render({
                elem: o.elem, // 对应table的id
                headers: headers,
                error: function (res) {
                    let data = res.responseJSON;
                    easyAdmin.redirectToLogin(data);
                },
                url: adminServerUrl + o.url,
                where: o.where,
                page: o.page, // 分页参数可以自定义
                cols: o.cols, // 列表示
                skin: o.skin, // 表格样式
                toolbar: o.toolbar, // 表格头部工具栏 可定义左上角和右上角工具栏,一般用于左上角配置
                // 自由配置头部工具栏右侧的图标按钮
                defaultToolbar: o.defaultToolbar
            });
            layer.close(load);
        }

        /**
         * tableId : 'user-table'
         */

        this.tableRefresh = function (tableId) {
            table.reload(tableId);
        }

        /**
         * 默认刷新 表格 其 id = table
         */
        this.TableRefresh = function () {
            table.reload('table');
        }

        /**
         * 默认删除表格某行数据
         * @param obj
         * @param uri /sys/user/1
         * @constructor
         */
        this.TableRemove = function (obj, uri) {
            layer.confirm('确定要删除该用户', {
                icon: 3,
                title: '提示'
            }, function (index) {
                layer.close(index);
                let loading = layer.load();
                easyAdmin.http({
                    url: uri,
                    dataType: 'json',
                    type: 'delete',
                    success: function (result) {
                        layer.close(loading);
                        if (result.success) {
                            layer.msg(result.msg, {
                                icon: 1,
                                time: 1000,
                                area: ['100px', '65px'],
                                content: "删除成功"
                            }, function () {
                                obj.del();
                            });
                        } else {
                            layer.msg(result.msg, {
                                icon: 2,
                                time: 1000,
                                area: ['260px', '65px']
                            });
                        }
                    }
                })
            });
        }

        /**
         * 行内事件
         * uri:"/sys/dict"
         */
        this.TableTool = function (uri, module) {
            table.on('tool(table-filter)', function (obj) {
                if (obj.event === 'remove') {
                    easyAdmin.TableRemove(obj, uri + "/" + obj.data[module + 'Id']);
                } else if (obj.event === 'edit') {
                    easyAdmin.JumpEdit(module, obj.data[module + 'Id']);
                } else if (obj.event === 'diagram') {
                    easyAdmin.JumpDiagram(obj);
                } else if (obj.event === 'undo') { //流程撤回
                    easyAdmin.httpGet("/dev-api/flow/task/undo?taskId=" + obj.data.createTaskId, function (result) {
                        if (result.success) {
                            layer.msg("撤回成功", {
                                icon: 1,
                                area: ['100px', '65px'],
                                time: 1000
                            }, function () {
                                table.reload('table');
                            });
                        } else {
                            layer.msg("后续活动任务已完成或不存在，无法撤回", {
                                icon: 2,
                                area: ['350px', '65px'],
                                time: 1000
                            });
                        }
                    });
                }

            });
        }

        /**
         * 表格左右上面工具栏
         * @param uri "/sys/dict"
         * @param module
         */
        this.TableToolBar = function (uri, module) {
            table.on('toolbar(table-filter)', function (obj) {
                if (obj.event === 'add') {
                    easyAdmin.JumpAdd(module);
                } else if (obj.event === 'refresh') {
                    easyAdmin.TableRefresh();
                } else if (obj.event === 'batchRemove') {
                    easyAdmin.batchRemove(obj, module, uri)
                }
            });
        }

        this.batchRemove = function (obj, module, uri) {

            var ids = easyAdmin.checkField(obj, module + 'Id');
            console.log(ids)

            if (ids === "") {
                layer.msg("未选中数据", {
                    icon: 3,
                    time: 1000
                });
                return false;
            }

            layer.confirm('确定要删除这些用户', {
                icon: 3,
                title: '提示'
            }, function (index) {
                layer.close(index);
                let loading = layer.load();
                easyAdmin.http({
                    url: uri + "/batch/" + ids,
                    dataType: 'json',
                    type: 'delete',
                    success: function (result) {
                        layer.close(loading);
                        if (result.success) {
                            layer.msg(result.msg, {
                                icon: 1,
                                area: ['100px', '65px'],
                                time: 1000
                            }, function () {
                                table.reload('table');
                            });
                        } else {
                            layer.msg(result.msg, {
                                icon: 2,
                                area: ['260px', '65px'],
                                time: 1000
                            });
                        }
                    }
                })
            });
        }
        /**
         * 条件查询
         */
        this.FormQuery = function () {
            /**
             * 条件查询
             */
            form.on('submit(query)', function (data) {
                table.reload('table', {
                    where: data.field
                })
                return false;
            });
        }

        /**
         * uri : '/dev-api/sys/dict'
         *  用于 edit页面
         *  form表单回显
         *  <form class="layui-form" action="" lay-filter="edit">
         *
         */
        this.FormVal = function (uri) {
            var id = this.getQueryString("id");
            this.http({
                url: uri + "/" + id,
                dataType: 'json',
                contentType: 'application/json',
                type: 'get',
                success: function (result) {
                    if (result.success) {
                        //表单数据回显
                        form.val("edit", result.data);
                    } else {
                        layer.msg(result.msg, {icon: 2, time: 1000});
                    }
                }
            })
        }

        /**
         * <p>
         *     新增 button lay-filter="save"
         *    <button type="submit" lay-submit lay-filter="save"
         *     其父 main页面   id="table"
         *     <table id="table"
         * </p>
         *
         */
        this.FormSave = function (url) {
            form.on('submit(save)', function (data) {
                // 转换
                data.field.enable = data.field.enable == '1' ? true : false;
                easyAdmin.http({
                    url: url,
                    data: JSON.stringify(data.field),
                    dataType: 'json',
                    contentType: 'application/json',
                    type: 'post',
                    success: function (result) {
                        if (result.success) {
                            layer.msg(result.msg,
                                {
                                    icon: 1,
                                    time: 1000,
                                    area: ['100px', '65px'],
                                    content: "保存成功"
                                }
                                , function () {
                                    parent.layer.close(parent.layer.getFrameIndex(window.name));//关闭当前页
                                    parent.layui.table.reload("table");
                                });
                        } else {
                            layer.msg(result.msg,
                                {
                                    icon: 2,
                                    time: 1000,
                                    area: ['220px', '70px']
                                }
                            );
                        }
                    }
                })
                return false;
            });
        }


        /**
         * 跳转到add页面
         */
        this.JumpAdd = function (path) {
            layer.open({
                type: 2,
                title: '新增',
                skin: 'layui-layer-admin',
                shade: 0.1,
                area: [common.isModile() ? '100%' : '500px', common.isModile() ? '100%' : '600px'],
                content: path + '/add.html'
            });
        }

        this.JumpDiagram = function (obj) {
            layer.open({
                type: 2,
                title: '查看流程图',
                skin: 'layui-layer-admin',
                shade: 0.1,
                area: [common.isModile() ? '100%' : '980px', common.isModile() ? '100%' : '500px'],
                content: ['../flow/diagram.html?orderId=' + obj.data.orderId, 'no']
            });
        }
        /**
         * 跳转到edit页面
         */
        this.JumpEdit = function (MODULE_PATH, id) {
            layer.open({
                type: 2,
                title: '修改',
                skin: 'layui-layer-admin',
                shade: 0.1,
                area: ['500px', '500px'],
                content: [MODULE_PATH + '/edit.html?id=' + id, 'no']
            });
        }
        /**
         * 获取url中的 参数
         * @param name 参数名称
         * @returns {string}
         */
        this.getQueryString = function (name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
            var r = window.location.search.substr(1).match(reg);
            if (r !== null)
                return unescape(r[2]);
            return "";
        }

        /**
         * 获取当前表格选中字段
         * @param obj 表格回调参数
         * @param field 要获取的字段
         * */
        this.checkField = function (obj, field) {
            let data = table.checkStatus(obj.config.id).data;
            if (data.length === 0) {
                return "";
            }
            let ids = "";
            for (let i = 0; i < data.length; i++) {
                ids += data[i][field] + ",";
            }
            ids = ids.substr(0, ids.length - 1);
            return ids;
        }


        /**
         * 当前是否为与移动端
         * */
        this.isModile = function () {
            if ($(window).width() <= 768) {
                return true;
            }
            return false;
        }

        /**
         * 提交 json 数据
         * @param data 提交数据
         * @param href 提交接口
         * @param table 刷新父级表
         *
         * */
        this.submit = function (data, href, table, callback) {
            $.ajax({
                url: href,
                data: JSON.stringify(data),
                dataType: 'json',
                contentType: 'application/json',
                type: 'post',
                success: callback != null ? callback(result) : function (result) {
                    if (result.success) {
                        layer.msg(result.msg, {icon: 1, time: 1000}, function () {
                            parent.layer.close(parent.layer.getFrameIndex(window.name));//关闭当前页
                            parent.layui.table.reload(table);
                        });
                    } else {
                        layer.msg(result.msg, {icon: 2, time: 1000});
                    }
                }
            })
        }
    }
    exports(MOD_NAME, easyAdmin);
});
