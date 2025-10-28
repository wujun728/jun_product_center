/**
 * 校验页面操作按钮
 * 
 * @param parentModuleCode
 * @param moduleCode
 * @param btnArray
 */
function validModule(moduleCode,$){
	if($ && $!="undefined"){
		validMod(moduleCode,btnArray,$);
	}else{
		layui.use(['form','layer'], function() {
			var form = layui.form,
			layer = layui.layer,
			$ = layui.jquery;
			validMod(moduleCode,$);
		});
	}
}

function validMod(moduleCode,$){
	var btnArray = $("a[module-field],button[module-field]");
	
	/*$.ajax({
    	url:opServerUrl+"userPageModuleList",
    	type:'GET',
    	data:{
			moduleCode:moduleCode
    	},
    	dataType:'json',
    	cache: false,
    	async:true,
    	headers:{
    		Authorization:Authorization
    	},
    	success:function(res){
    		if(res.code == 200){
    			if(res.data!=null && res.data.length>0){
					for(var i=0;i<btnArray.length;i++){
						var bts = $(btnArray[i]);
						for(var j=0;j<bts.length;j++){
							var field = $(bts[j]).attr("module-field");
							var flag=0;
							$.each(res.data,function(index,item){
								if(field==item.field){
									$(bts[j]).show();
									$(bts[j]).css("display","inline-block");
									flag = 1;
								}
							});
							if(flag == 0){
								$(bts[j]).on("click",function(){disabledClick();});
								$(btnArray[i]).remove();
							}
						}
					}
	    		}
    		}
    	}
 	});*/
}

function disabledClick(){
	layui.use(['form','layer'],function(){
		var form = layui.form;
		var layer = layui.layer;
		layer.msg("您没有操作权限！",{icon:0});
		return;
	});
}