//--------------------json traverse----------
// called with every property and it's value
//JSON.traverse(obj,function (key, value,isArrItem) {
//		if(isArrItem){
//			// do something
//		}else{
//			// do something
//		}		
//});



(function(){
	function traverse(obj, func) {
		if (isArr(obj)) {
			traverse_arr(obj, func);
		} else {
			traverse_obj(obj, func);
		}
	}
	function isArr(obj) {
		return Object.prototype.toString.call(obj) === '[object Array]';
	}
	function traverse_obj(obj, func) {
		for (var key in obj) {
			if (typeof (obj[key]) == "object") {
				// going on step down in the object tree!!
				traverse(obj[key], func);
			}
			func(obj,key,false);
		}
	}
	function traverse_arr(arr, func) {
		for (var index in arr) {
			if (typeof (arr[index]) == "object") {
				// going on step down in the object tree!!
				traverse(arr[index], func);
			}
			func(arr,index,true);
		}
	}
	JSON.traverse=traverse;
})();

// --------------------json traverse end----------
//JSON.specs(obj),将类似{"a.b":'value'}的转换为标准JSON{a:{b:'value'}}
(function(){
	JSON.specs=function(obj){
		JSON.traverse(obj,function (parent, key,isArrItem) {
			if(isArrItem){
				// do something
			}else{
				if(key.indexOf(".")!=-1){
					var splitArr=key.splitFirst(".");
					parent[splitArr[0]]=restore(key, parent[key])[splitArr[0]];
					delete parent[key];
				}
				//console.log(key+"	"+JSON.stringify(parent[key]))
			}		
		});
		return obj;
	};
	function restore(key,value){
		var target={};
		var splitArr=key.splitFirst(".");		
		if(splitArr[1].indexOf(".")!=-1){
			target[splitArr[0]]=restore(splitArr[1],value);
		}else{
			var lastObj={};
			lastObj[splitArr[1]]=value;
			target[splitArr[0]]=lastObj;
		}
		return target;
	}
})();

String.prototype.splitFirst=function(separator){
	var arr=[];
	arr.push(this.substring(0,this.indexOf(separator)));
	arr.push(this.substring(this.indexOf(separator)+1,this.length));
	return arr;
};

var myUtil={
	getUrlParam : function(name){
		var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r!=null) return unescape(r[2]); return null;
	}
}


$.fn.toHTMLString = function() {
	var HTMLstr = "";
	$(this).each(function() {
		if (typeof ($(this)[0].outerHTML) != "undefined")
			HTMLstr += $(this)[0].outerHTML+"\n";
	});
	return HTMLstr;
}

// 默认去除表单元素未填写的字段，nullable=true保留
$.fn.serializeJson = function(nullable) {
	var serializeObj = {};
	var array = this.serializeArray();
	$(array).each(function() {
		if (!nullable&&this.value == "") // 去除表单元素未填写的字段
			return;
		if (serializeObj[this.name]) {
			if ($.isArray(serializeObj[this.name])) {
				serializeObj[this.name].push(this.value);
			} else {
				serializeObj[this.name] = [ serializeObj[this.name], this.value ];
			}
		} else {
			serializeObj[this.name] = this.value;
		}
	});
	return serializeObj;
};

