var successCallback = function () {};
var el5;
layui.use(['eleTree', 'common'], function () {
    var $ = layui.$
        , form = layui.form
        , eleTree = layui.eleTree
        , common = layui.common;
    //部门树
    $("[name='deptName']").on("click", function (e) {
        e.stopPropagation();
        if (!el5) {
            el5 = eleTree.render({
                elem: '.deptName',
                url: ctx + "system/dept/treeData2",
                request: {
                    name: "name",
                    key: "id",
                    children: "children",
                    checked: "checked",
                    disabled: "disabled",
                    isLeaf: "isLeaf"
                },
                response: {
                    statusName: "code",
                    statusCode: 0,
                    dataName: "data"
                },
                defaultExpandAll: true,
                expandOnClickNode: false,
                highlightCurrent: true
            });
            $(".eleTree").css("position", "absolute");
        }
        $(".deptName").toggle();
    });
    eleTree.on("nodeClick(deptNameTree)", function (d) {
        $("[name='deptName']").val(d.data.currentData.name);
        $("[name='deptId']").val(d.data.currentData.id);
        $(".deptName").hide();
    });
    //表单校验
    form.verify({
        pass: [
            /^[\S]{2,25}$/
            , '密码必须2到25位，且不能出现空格'
        ],
        username: function (value, item) {
            var msg;
            if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
                msg = '用户名不能有特殊字符';
            }
            if (/(^\_)|(\__)|(\_+$)/.test(value)) {
                msg = '用户名首尾不能出现下划线\'_\'';
            }
            if (/^\d+\d+\d$/.test(value)) {
                msg = '用户名不能全为数字';
            }
            $.ajax({
                type: "POST",
                url: ctx + "system/user/checkLoginNameUnique",
                async: false,
                dataType: "json",
                data: {
                    loginName: $("[name='loginName']").val(),
                    userId: $.trim($("[name='userId']").val())
                },
                success: function (res) {
                    if (res != "0") {
                        msg = "登录用户名已存在，请修改！";
                    }
                },
                error: function () {
                    msg = "验证登录名出错！";
                }
            });
            return msg;
        }
    });
    //表单提交
    var userId = $.trim($('#userId').val());
    var submitBtnId = userId ? 'btn-edit' : 'btn-add';
    form.render(null, 'form-user');
    form.on('submit(' + submitBtnId + ')', function (data) {
        var roleIds = common.getCheckValues("role");
        var postIds = common.getCheckValues("post");
        data.field.roleIds = roleIds;
        data.field.postIds = postIds;
        data.field.status = data.field.status === "on" ? "1" : "0";
        var url = userId ? (ctx + 'system/user/edit') : (ctx + 'system/user/add');
        $.ajax({
            type: "post",
            url: url,
            data: data.field,
            async: false,
            error: function (re) {
                layui.layer.msg("系统错误");
            },
            success: function (data) {
                console.log("success:", data);
                if (data.code > 0) {
                    layui.layer.msg(data.msg);
                } else {
                    successCallback();
                }
            }
        });
        return false;
    });
});