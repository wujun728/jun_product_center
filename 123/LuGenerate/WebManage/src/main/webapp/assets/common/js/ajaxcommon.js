
function ajaxGet($, url,data){
    var data = $.ajax({
        url:url,
        data:data,
        async:false,
        type:'get',
        dataType:'json',
        success:function (res) {
            return res.data;
        }
    })
    return data.responseJSON;
}


function ajaxGetAsync(url,data){
    var data = $.ajax({
        url:url,
        data:data,
        async:true,
        type:'get',
        dataType:'json',
        success:function (res) {
            return res.data;
        }
    })
    return data.responseJSON;
}


function ajaxPost($,url,data){
    var data = $.ajax({
        url:url,
        data:data,
        async:false,
        type:'post',
        dataType:"json",
        success:function (res) {
            return res.data;
        }
    })
    return data.responseJSON;
}

function ajaxPostAsync(url,data){
    var data = $.ajax({
        url:url,
        data:data,
        async:true,
        type:'post',
        dataType:'json',
        success:function (res) {
            return res.data;
        }
    })
    return data.responseJSON;
}

// select选择框初始化请求数据
function initSelectData($,url,data,selector,selectedValue) {
    var data = $.ajax({
        url : url,
        type : 'get',
        data : data,
        async : false,
        dataType : 'json',
        success : function (res) {
            if(res.code == 0){
                initSelect($,res.data,selector,selectedValue);
            }
        }
    });
}

// select选择框初始化渲染
function initSelect($,data,selector,selectedValue){
    $.each(data,function (index,d) {
        if(d.code == selectedValue){
            selector.append('<option value="'+d.code+'" selected>'+d.name+'</option>');
        }else{
            selector.append('<option value="'+d.code+'">'+d.name+'</option>');
        }
    });
}