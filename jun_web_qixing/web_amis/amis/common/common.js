gbl = {};
gbl.data = {};
gbl.post = function(config){
    jQuery.ajax({
        url:config.url,
        async:config.async === false ? false:true,
        dataType:"JSON",
        success:function(data){
            if(config.success){
                config.success(data);
            }
        }
    });
}
gbl.initData = function(){
    if(arguments.length == 0){
        let apis = arguments[0];
        for(let key in apis) {
            let api = apis[i];
            gbl.initData(key,api);
        }
    }else{
        let [key,api] = arguments;
        let config={};
        let success = function(data){
            gbl.data[key] = data.data;
        }
        if (typeof api == "string") {
            config.url = api;
        }else{
            config = api;
        }
        config.success = success;
        config.async = false;
        gbl.post(config);
    }
}
