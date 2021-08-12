/*!
 * 使用须知：仅供INDEX页面使用
 * 必要条件：先载入jQuery1.4+
 * 
 * Copyright 2010-4, Hugo Zhang
 */
$(document).ready(function() {
						   
	//去除a、button标签点击后的选线框
	$(function(){	
		$('a,input[type="button"],input[type="submit"]').bind('focus',function(){	
            if(this.blur){ //如果支持 this.blur
                 this.blur();	
             };		
		});	
	});	
	
	//待添加...

});
