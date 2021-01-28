//表与chart连接的方法
function gridToChart(id,title,url,left,top,width,height){
	var chart={};
	chart.title=title;
	chart.url=url;
	chart.id=id;
	chart.left=left;
	chart.top=top;
	chart.width=width;
	chart.height=height;
	gChart(chart);
}

