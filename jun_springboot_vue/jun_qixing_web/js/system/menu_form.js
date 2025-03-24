var successCallback = function () {};
var el5;
layui.use(['eleTree', 'common'], function () {
    var $ = layui.jquery
        ,layer = layui.layer
        ,form = layui.form
        ,eleTree = layui.eleTree
        ,common = layui.common;
    //菜单树
    $("[id='treeName']").on("click",function (e) {
        e.stopPropagation();
        if(!el5) {
            el5 = eleTree.render({
                elem: '.menuName',
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
            $(".eleTree").css("position","absolute");
        }
        $(".menuName").toggle();
    });

    eleTree.on("nodeClick(menuNameTree)",function(d) {
        $("[id='treeName']").val(d.data.currentData.name);
        $("[name='parentId']").val(d.data.currentData.id);
        $(".menuName").hide();
    });
    //提交表单
    var menuId = $.trim($('#menuId').val());
    var submitBtnId = menuId ? 'btn-edit' : 'btn-add';
    form.render(null, 'form-menu');
    form.on('submit('+submitBtnId+')', function(data){
        data.field.status = data.field.status === "on" ? "1":"0";
        var url = menuId ? (ctx + 'system/menu/edit') : (ctx + 'system/menu/add');
        $.ajax({
            type : "post",
            url : url,
            data : data.field,
            async : false,
            error : function(res) {
                layer.msg("系统错误");
            },
            success : function(data) {
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