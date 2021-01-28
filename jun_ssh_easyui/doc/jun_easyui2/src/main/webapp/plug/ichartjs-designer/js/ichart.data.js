function ichart_data(url,chart,i){
	if($(window.frameElement).is(":hidden")) return;
	$.ajax({
		url : url,
		dataType : 'text',
		type: "post", 
		async:true,
		contentType: "application/x-www-form-urlencoded; charset=utf-8", 
		success : function(r) {
			chart.data[i].value=parseFloat(r);
			chart.load(chart.data);
		}
	});
};

function getUrl_(url){
	return ((top.sysPath==undefined)?'/doroodo':top.sysPath)+'/sys/'+url;
}

function start(chart){
	for(var i=0;i<chart.data.length;(function(x){
		var url=chart.data[x].url;
		if(url&&(url!='')){
			var urls=url.split('!');
			if(urls.length==2){
				var index=x;
				var time=urls[1];
				var realUrl=urls[0];
				$(this).everyTime(time*1000, function(){ichart_data(getUrl_(realUrl),chart,index)});
			}
		}
	})(i++));
}
