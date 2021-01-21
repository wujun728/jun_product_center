layui.use(['form','layer','jquery'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer
        $ = layui.jquery;

    //验证码刷新
    $("#imgCode img").click(function(){
        $(this).attr("src","sys/captcha.jpg");
    });

    //登录按钮
    form.on("submit(login)",function(data){
       // var info = JSON.stringify(data.field); 不能直接这么用，这是把json转为字符串
       // console.log(info);
        $(this).text("登录中...").attr("disabled","disabled").addClass("layui-disabled");
        //登录
        $.post("sys/login.json",data.field,function(jsonData){
            layer.msg(jsonData.msg);
            if(!jsonData.result){
                return false;
            }
            window.location.href = "admin/index.page";
        });
        $(this).text("登录").removeAttr("disabled").removeClass("layui-disabled");
        return false;
    })

    //表单输入效果
    $(".loginBody .input-item").click(function(e){
        e.stopPropagation();
        $(this).addClass("layui-input-focus").find(".layui-input").focus();
    })
    $(".loginBody .layui-form-item .layui-input").focus(function(){
        $(this).parent().addClass("layui-input-focus");
    })
    $(".loginBody .layui-form-item .layui-input").blur(function(){
        $(this).parent().removeClass("layui-input-focus");
        if($(this).val() != ''){
            $(this).parent().addClass("layui-input-active");
        }else{
            $(this).parent().removeClass("layui-input-active");
        }
    })
})
