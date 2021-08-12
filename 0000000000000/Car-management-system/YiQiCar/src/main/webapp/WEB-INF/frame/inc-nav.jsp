<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>物资采购与产品整合管理系统</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<script src="../js/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="../js/jquery.treeview.js" type="text/javascript"></script>
<link href="../css/jquery.treeview.css" rel="stylesheet" type="text/css" />

<script language="javascript">
$().ready(function(){
	
	//树状菜单生成 JQuery Treeview
	$("#browser").treeview({
		//animated菜单展开关闭时动画效果
		animated : "slow",
        //collapsed菜单载入时关闭还是展开
		collapsed: true
        //unique同一层次是否只允许展开一个
		//unique: true
	});
	//设置树状菜单外框DIV纵向滚动条属性为自动
    $("#nav-box").css("overflowY","auto");
	//自动添加a标签title为a标签中的内容
	for(var i=0; i<$("span.file a").length; i++ ){
		$("span.file a").eq(i).attr("title", $("span.file a").eq(i).text());
	}
	
});
	
//链接转入index.html页面ID为content-box的iframe显示
function urlTarget(urls) {
	$("#content-box",parent.document.body).attr("src",urls);
}
</script>

</head>

<body class="inc-nav-body">
<div id="nav-box">

	<ul id="browser" class="filetree">
		<li><span class="folder">订单模块</span>
			<ul>
            	<li><span class="file"><a onclick="urlTarget('pages/ordersys/order/orderlist/1');">订单管理</a></span></li>
				<li><span class="file"><a onclick="urlTarget('pages/ordersys/order/orderchecklist/1');">订单审核</a></span></li>
		  </ul>
	  </li>
	<li><span class="folder">配件模块</span>
			<ul>
            	<li><span class="file"><a onclick="urlTarget('pages/partssys/parts/partslist/1');">配件管理</a></span></li>
				<li><span class="file"><a onclick="urlTarget('pages/partssys/partsrep//partsreplist/1');">配件出入库管理</a></span></li>
				<li><span class="file"><a onclick="urlTarget('pages/partssys/partsrepbill/partsrepbilllist/1');">配件出入库流水查询</a></span></li>
		  </ul>
	  </li>
		<!--li><span class="folder">原料模块</span>
			<ul>
            	<li><span class="file"><a onclick="urlTarget('order/system-order-list.html');">原料管理</a></span></li>
		  </ul>
	  </li-->
		<li><span class="folder">产品信息</span>
			<ul>
            	<li><span class="file"><a onclick="urlTarget('pages/product/system-order-list.html');">产品管理</a></span></li>
				<li><span class="file"><a onclick="urlTarget('pages/product/system-order-list.html');">组装管理</a></span></li>
				<li><span class="file"><a onclick="urlTarget('pages/product/system-order-list.html');">上线管理</a></span></li>
				<li><span class="file"><a onclick="urlTarget('pages/product/system-order-list.html');">下线信息</a></span></li>
		  </ul>
	  </li>
	  <li><span class="folder">物资合同</span>
			<ul>
            	<li><span class="file"><a onclick="urlTarget('pages/product/system-order-list.html');">配件合同管理</a></span></li>
				<li><span class="file"><a onclick="urlTarget('pages/product/system-order-list.html');">产品销售管理</a></span></li>
		  </ul>
	  </li>


	</ul>
    
</div>
</body>
</html>