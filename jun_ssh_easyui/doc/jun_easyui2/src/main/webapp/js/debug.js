var page_debug={
	link:'',
	loadTime:0,
	dataLink:new Array(),
	dataResult:new Array(),
	toHTML:function(){
		var html='';
		html+='<span class="titlep">页面地址:<span>'+this.link+'</br>';
		html+='<span class="titlep">加载时间:<span>'+this.loadTime+'秒</br>';
		for(var i=0;i<this.dataLink.length;i++){
			html+='<span class="titlep">数据地址:<span>'+this.dataLink[i]+'</br><span class="titlep">返回数据:<span>'+this.dataResult[i]+'</br>';
		}
		return html;
	}
}

function ajaxcomplete(obj,data){
	page_debug.dataLink.push(obj.url);
	page_debug.dataResult.push(data);
}

function g_page_debug(){
	窗体('调试模式',page_debug.toHTML());
}