var successCallback = function () {
};
layui.use(['eleTree', 'common'], function () {
    var $ = layui.jquery
        , layer = layui.layer
        , form = layui.form
        , eleTree = layui.eleTree
        , common = layui.common;

    //表单校验
    form.verify({
        roleKey: function (value, item) {
            var msg;
            if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
                msg = '不能有特殊字符';
                return msg;
            }
            $.ajax({
                type: "post",
                url: ctx + "system/role/checkRoleKeyUnique",
                async: false,
                dataType: "json",
                data: {roleKey: $.trim($("[name='roleKey']").val()), roleId: $.trim($("[name='roleId']").val())},
                success: function (res) {
                    if (res != "0") {
                        msg = "roleKey已存在，请修改！";
                    }
                },
                error: function () {
                    msg = "验证roleKey出错！";
                }
            });
            return msg;
        },
        roleName: function (value, item) {
            var msg;
            if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
                msg = '不能有特殊字符';
                return msg;
            }
            $.ajax({
                type: "POST",
                url: ctx + "system/role/checkRoleNameUnique",
                async: false,
                dataType: "json",
                data: {roleName: $.trim($("[name='roleName']").val()), roleId: $.trim($("[name='roleId']").val())},
                success: function (res) {
                    if (res != "0") {
                        msg = "roleName已存在，请修改！";
                    }
                },
                error: function () {
                    msg = "验证roleName出错！";
                }
            });
            return msg;
        }
    });
    //提交表单
    var roleId = $.trim($('#roleId').val());
    var submitBtnId = roleId ? 'btn-edit' : 'btn-add';
    form.render(null, 'form-role');
    form.on('submit(' + submitBtnId + ')', function (data) {
        var menus = menuTree.getChecked(false, true);
        data.field.menuIds = common.joinArray(menus, "id");
        data.field.status = data.field.status === "on" ? "1" : "0";
        var url = roleId ? (ctx + 'system/role/edit') : (ctx + 'system/role/add');
        $.ajax({
            type: "post",
            url: url,
            data: data.field,
            async: false,
            error: function (re) {
                layer.msg("系统错误");
            },
            success: function (data) {
                if (data.code > 0) {
                    layui.layer.msg(data.msg);
                } else {
                    successCallback();
                }
            }
        });
        return false;
    });

    //树形菜单
    var treeUrl = ctx + "system/menu/roleMenuTreeData2";
    if (roleId) {
        treeUrl += "?roleId=" + roleId;
    }
    var menuTree = eleTree.render({
        elem: '#menuTrees',
        showCheckbox: true,
        url: treeUrl,
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
        done: function (res) {
            var ss = common.getJsonArrayValue(res.data, "children", "checked", "id");
            menuTree.setChecked(ss);
            console.log("tree checked:", ss)
        },
        defaultExpandAll: true,
        expandOnClickNode: false,
        highlightCurrent: true
    });
});