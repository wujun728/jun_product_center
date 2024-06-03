<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="../../../component/pear/css/pear.css"/>
</head>
<body>
<form class="layui-form" action="">
    <div class="mainBox">
        <div class="main-container">

            <#list table.fields as field>
                <#if !field.keyFlag && field.propertyName != 'enable'>
                    <div class="layui-form-item">
                        <label class="layui-form-label">${field.comment}</label>
                        <div class="layui-input-block">
                            <input type="text" name="${field.propertyName}" lay-verify="title" autocomplete="off"
                                   placeholder="请输入"
                                   class="layui-input">
                        </div>
                    </div>
                </#if>
                <#if field.propertyName == 'enable'>
                    <div class="layui-form-item">
                        <label class="layui-form-label">字典状态</label>
                        <div class="layui-input-block">
                            <input type="radio" name="enable" value="true" title="开启" checked>
                            <input type="radio" name="enable" value="false" title="关闭">
                        </div>
                    </div>
                </#if>
            </#list>
        </div>
    </div>
    <div class="bottom">
        <div class="button-container">
            <button type="submit" class="pear-btn pear-btn-primary pear-btn-sm" lay-submit="" lay-filter="save">
                <i class="layui-icon layui-icon-ok"></i>
                提交
            </button>
            <button type="reset" class="pear-btn pear-btn-sm">
                <i class="layui-icon layui-icon-refresh"></i>
                重置
            </button>
        </div>
    </div>
</form>
<script src="../../../component/layui/layui.js"></script>
<script src="../../../component/pear/pear.js"></script>

<script>
    layui.use(['easyAdmin'], function () {
        let module = "${cfg.easyMain}";
        let uri = "/${cfg.easyModule}/" + module;
        let easyAdmin = layui.easyAdmin;
        easyAdmin.FormSave(uri);
    })
</script>
<script>
</script>
</body>
</html>