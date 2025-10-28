/**
 * layselect 下拉框插件，只支持单选
 * @author:Darker.Wang
 * @version:1.0
 * render参数{
 * 	elem：元素ID，待#号必传
 *  url：请求路径的URL，必传
 *  data:请求url所携带的参数，可选
 *  type:请求方式，默认为get，可选
 *  format:格式化方法映射，将返回的Data元素映射乘标准格式
 *  select:指定选中的索引项，默认第一个
 *  onselect:点击选择时鉴定
 * }
 * 请求返回需对象格式：rtvObj={status,code,codeName}，不满足的通过format映射处理
 * status=0 时表示禁用，
 * 分组展示时按照：groupName，groupChildren 数组 [rtvObj]
 * @param exports
 * @returns 返回一个对象
 * 码云地址：https://gitee.com/godbirds/layselect
 */
layui.define(['element','form','jquery'],function(exports){
	var element = layui.element;
	var form = layui.form;
	var $ = layui.jquery;
	var obj={
		//{elem,url,data,type,format}
		render:function(param){
			var that = this;
			var eid = param.elem;
			if(param.type == null || param.type==undefined){
				param.type = 'get';//默认get请求
			}
			if(param.url == null || param.url==undefined){
				param.url = '/error?msg=请求路径不存在';
			}
			if(param.data){
				param.data = JSON.stringify(data);
			}else{
				param.data = {}
			}
			$.ajax({
		        url: param.url,
		        type: param.type,
		        data: param.data,
		        async: 'false',
		        dataType: 'json',
		        headers: {Accept: "application/json; charset=utf-8"},
		        contentType : 'application/json',//指定json头
		        success: function(data){
		        	$(eid).empty();//请求成功时清空
		        	$(eid).prepend("<option value=''>请选择</option>");//添加第一个option值
		        	var option = new Array();
		        	if(param.format){//格式化
		        		//console.log('param.format',data);
		        		for (var i = 0; i < data.length; i++) {
		        			var tdata = param.format(data[i]);
		        			//console.log('mapping',tdata);
		        			option.push({
			        			code:tdata.code,
			        			status:tdata.status||'1',
			        			codeName:tdata.codeName,
			        			groupName:tdata.groupName,
			        			groupChildren:tdata.groupChildren
		        			});
		        		}
		        	}else{//不格式化
		        		for (var i = 0; i < data.length; i++) {
		        			option.push({
			        			code:data[i].code,
			        			status:data[i].status||'1',
			        			codeName:data[i].codeName,
			        			groupName:data[i].groupName,
			        			groupChildren:data[i].groupChildren
		        			});
		        		}
		        	}
		        	for (var i = 0; i < option.length; i++) {
		        		 //分组
		        		 if(option[i].groupChildren != null && option[i].groupChildren != undefined && 
		        			option[i].groupChildren != ''   && option[i].groupChildren.length > 0){
		        			 $(eid).append("<optgroup label='"+option[i].groupName+"'>");
		        			 option[i].groupChildren.forEach(function(item,index){
								 var status = "";var topborder ="",buttomborder="",checkoff="";
		        				 if(item.status && item.status == '0'){status = "disabled='disabled'";}//是否有效
				        	　　　$(eid).append("<option value='"+item.code+"' "+status+">"+item.codeName+"</option>");
				        		 if(param.select != undefined && param.select == index && item.status != '0'){
				        			 $(eid).find("option[value = '"+item.code+"']").attr("selected","selected");
				        		 }//选择指定选中项
		        			 });
		        			 $(eid).append("</optgroup>");
		        		 }else{//不分组
		        			 var status = "";var topborder ="",buttomborder="",checkoff="";
		        			 if(option[i].status && option[i].status == '0'){status = "disabled='disabled'";}//是否有效
			        	　　　$(eid).append("<option value='"+option[i].code+"' "+status+">"+option[i].codeName+"</option>");
							 if(param.select != undefined && param.select == i && option[i].status != '0'){
			        			 $(eid).find("option[value = '"+option[i].code+"']").attr("selected","selected");
			        		 }//选择指定选中项
		        		 }
		        	}
		        	//监听事件
		        	form.render('select');//select是固定写法 不是选择器
    				//这里做自己想做的事情
                    if(param.onselect){//选中事件
                    	form.on('select('+eid.replace('#','')+')', function(select){
                    		param.onselect(select.value);
                    	});
                    }
		        },
		        error: function (e) {
					console.log('url:'+param.url);
		        	console.log("ajax request error");
		        }
		    });
		}
	};
	exports('layselect',obj);
});