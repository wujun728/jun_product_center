var currentNode = null;
var thisEvent = null;
var lineType=null;
function handler(event) {
    if (event.button == 2) {// 右键
        // 当前位置弹出菜单（div）
        if (currentNode.elementType == "node") {
            thisEvent = event;
            $("#contextmenu").css({
                top: event.pageY,
                left: event.pageX
            }).show();
        } else if (currentNode.elementType == "TextNode") {
            thisEvent = event;
            $("#contextmenu3").css({
                top: event.pageY,
                left: event.pageX
            }).show();
        } else {
            thisEvent = event;
            $("#contextmenu2").css({
                top: event.pageY,
                left: event.pageX
            }).show();
        }
    }
}

function initARightButton(n) {
    n.addEventListener('mouseup', function (event) {
        currentNode = this;
        handler(event);
    });
}

function initDBClick() {
    var textfield = $("#jtopo_textfield");
    scene.dbclick(function (event) {
        if (event.target == null) return;
        var e = event.target;
        textfield.css({
            top: event.pageY,
            left: event.pageX - e.width / 2
        }).attr('value', e.text).show().focus().select();
        e.text = "";
        textfield[0].JTopoNode = e;
    });
    $("#jtopo_textfield").blur(function () {
        textfield[0].JTopoNode.text = textfield.hide().val();
    });
}

function initRightButton() {
    var beginNode;
    var tempNodeA = new JTopo.Node('tempA');
    tempNodeA.setSize(1, 1);
    var tempNodeZ = new JTopo.Node('tempZ');
    tempNodeZ.setSize(1, 1);
    var tempLink = new JTopo.Link(tempNodeA, tempNodeZ);
    scene.mouseup(function (e) {
        if (e.button == 2) {
            scene.remove(tempLink);
            return;
        }
        if (e.target != null && e.target instanceof JTopo.Node) {
            if (beginNode !== e.target) {
                var endNode = e.target;
                var l=null;
                if(lineType=='FoldLink'){
                	//折线
                	l = new JTopo.FoldLink(beginNode, endNode);
                	l.arrowsRadius = 15; //箭头大小
                }else if(lineType=='FlexionalLink'){
                	//二次折线
                	l=new JTopo.FlexionalLink(beginNode,endNode);
                	l.arrowsRadius = 10;
                }else if(lineType=='CurveLink'){
                	//曲线
                	l=new JTopo.CurveLink(beginNode,endNode);
                	l.lineWidth = 3;
                }else{
                	l = new JTopo.Link(beginNode, endNode);
                }
                l.lineType=lineType;
                initARightButton(l);
                scene.add(l);
                beginNode = null;
                scene.remove(tempLink);
            }
        } else {
            scene.remove(tempLink);
        }
    });
    scene.mousedown(function (e) {
        if (e.target == null || e.target === beginNode || e.target === tempLink) {
            scene.remove(tempLink);
        }
    });
    scene.mousemove(function (e) {
        tempNodeZ.setLocation(e.x, e.y);
    });
    var k = scene.getDisplayedElements();
    var kLength = k.length;
    for (var i = kLength - 1; i >= 0; i--) {
        var node = k[i];
        if (node != undefined) {
            node.addEventListener('mouseup', function (event) {
                currentNode = this;
                handler(event);
            });
        }
    }
    stage.click(function (event) {
        if (event.button == 0) {// 右键
            // 关闭弹出菜单（div）
            $("#contextmenu").hide();
            $("#contextmenu2").hide();
            $("#contextmenu3").hide();
            thisEvent = null;
        }
    });
    $("#contextmenu a").click(function () {
        var text = $(this).text();
        if (text == '直线连线') {
            //currentNode.fillColor=
            //scene.remove(currentNode);
            if (thisEvent.target != null && thisEvent.target instanceof JTopo.Node) {
                if (beginNode == null) {
                    beginNode = thisEvent.target;
                    scene.add(tempLink);
                    lineType="link";
                    tempNodeA.setLocation(thisEvent.x, thisEvent.y);
                    tempNodeZ.setLocation(thisEvent.x, thisEvent.y);
                } else if (beginNode !== thisEvent.target) {
                    var endNode = thisEvent.target;
                    var l = new JTopo.Link(beginNode, endNode);
                    l.lineWidth = 1;
                    initARightButton(l);
                    scene.add(l);
                    beginNode = null;
                    scene.remove(tempLink);
                } else {
                    beginNode = null;
                }
            } else {
                scene.remove(tempLink);
            }
            currentNode = null;
            thisEvent = null;
            $("#contextmenu").hide();
        }
        if (text == '折线连线') {
            if (thisEvent.target != null && thisEvent.target instanceof JTopo.Node) {
                if (beginNode == null) {
                    beginNode = thisEvent.target;
                    scene.add(tempLink);
                    lineType="FoldLink";
                    tempNodeA.setLocation(thisEvent.x, thisEvent.y);
                    tempNodeZ.setLocation(thisEvent.x, thisEvent.y);
                } else if (beginNode !== thisEvent.target) {
                    var endNode = thisEvent.target;
                    var l = new JTopo.FoldLink(beginNode, endNode);
                    l.lineWidth = 1;
                    initARightButton(l);
                    scene.add(l);
                    beginNode = null;
                    scene.remove(tempLink);
                } else {
                    beginNode = null;
                }
            } else {
                scene.remove(tempLink);
            }
            currentNode = null;
            thisEvent = null;
            $("#contextmenu").hide();
        }
        if (text == '二次折线连线') {
            if (thisEvent.target != null && thisEvent.target instanceof JTopo.Node) {
                if (beginNode == null) {
                    beginNode = thisEvent.target;
                    scene.add(tempLink);
                    lineType="FlexionalLink";
                    tempNodeA.setLocation(thisEvent.x, thisEvent.y);
                    tempNodeZ.setLocation(thisEvent.x, thisEvent.y);
                } else if (beginNode !== thisEvent.target) {
                    var endNode = thisEvent.target;
                    var l = new JTopo.FlexionalLink(beginNode, endNode);
                    l.lineWidth = 1;
                    initARightButton(l);
                    scene.add(l);
                    beginNode = null;
                    scene.remove(tempLink);
                } else {
                    beginNode = null;
                }
            } else {
                scene.remove(tempLink);
            }
            currentNode = null;
            thisEvent = null;
            $("#contextmenu").hide();
        }
        if (text == '曲线连线') {
            if (thisEvent.target != null && thisEvent.target instanceof JTopo.Node) {
                if (beginNode == null) {
                    beginNode = thisEvent.target;
                    scene.add(tempLink);
                    lineType="CurveLink";
                    tempNodeA.setLocation(thisEvent.x, thisEvent.y);
                    tempNodeZ.setLocation(thisEvent.x, thisEvent.y);
                } else if (beginNode !== thisEvent.target) {
                    var endNode = thisEvent.target;
                    var l = new JTopo.CurveLink(beginNode, endNode);
                    l.lineWidth = 1;
                    initARightButton(l);
                    scene.add(l);
                    beginNode = null;
                    scene.remove(tempLink);
                } else {
                    beginNode = null;
                }
            } else {
                scene.remove(tempLink);
            }
            currentNode = null;
            thisEvent = null;
            $("#contextmenu").hide();
        }
        if(text=='绑定数据'){
        	$('#bundlingData').dialog('open');
        	var nodeField=currentNode.nodeField;
        	$("#bundlingField").val(nodeField);
        	$("#contextmenu").hide();
        }
        if(text=='绑定多字段'){
        	setOPtionValue();
        	$("#contextmenu").hide();
        }
        if(text=='设置节点状态'){
        	var idStr=currentNode.c_Id;
        	ssetOPtionValue(idStr);
        	$("#contextmenu").hide();
        }
        if(text=='鼠标左键事件'){
        	var str=currentNode.elementEvent;
        	if(str!="undefined"&& str!=undefined){
        		$("#eventType").prop("disabled", true);
        		$("#eventClass").prop("disabled", true);
        		$("#eventContent").val(str);
        	}else{
        		$("#eventType").prop("disabled", false);
        		$("#eventClass").prop("disabled", false);
        		$("#eventContent").val("");
        	}
        	$('#bundlingEvent').dialog('open');
        	$("#contextmenu").hide();
        }
        if(text=='鼠标右键事件'){
        	var cNodeId=currentNode.cNodeId;
        	if(cNodeId!="undefined"&& cNodeId!=undefined){
        		datagridLoad(cNodeId);
        	}
        	$('#RbundlingEvent').dialog('open');
        	$("#contextmenu").hide();
        }
        if(text=='绑定设备编号'){
        	var dId=currentNode.dId;
        	if(dId!="undefined"&& dId!=undefined&& dId!=""){
        		$("input[name='device_id']").val(dId);
        	}else{
        		$("input[name='device_id']").val("");
        	}
        	$('#setDeviceDlg').dialog('open');
        	$("#contextmenu").hide();
        }
        
        if(text=='设置数据编号'){
        	var dataId=currentNode.dataId;
        	if(dataId!="undefined"&& dataId!=undefined&& dataId!=""){
        		$("input[name='data_id']").val(dataId);
        	}else{
        		$("input[name='data_id']").val("");
        	}
        	$('#setDataIdDlg').dialog('open');
        	$("#contextmenu").hide();
        }
        
        if(text=='数据事件'){
        	var str=currentNode.dataElementEvent;
        	if(str!="undefined"&& str!=undefined){
        		$("#dataEventContent").val(str);
        	}else{
        		$("#dataEventContent").val("function sysloadnode(cNodeId,datas){/*输入执行内容*/}");
        	}
        	$('#bundlingDataEvent').dialog('open');
        	$("#contextmenu").hide();
        }
        if(text=='绑定跳转页面'){
        	getpageCom();
        	
        	$('#bundlingPage').dialog('open');
        	
        	$("#contextmenu").hide();
        }
        if (text == '设置成告警') {
            //currentNode.fillColor=
            //scene.remove(currentNode);
            currentNode.alarm = "严重告警";
            currentNode = null;
            $("#contextmenu").hide();
        }
        if (text == '闪烁告警') {
            //currentNode.fillColor=
            //scene.remove(currentNode);
            currentNode = null;
        }
        if (text == '清除告警') {
            currentNode.alarm = undefined;
            //currentNode.fillColor=
            //scene.remove(currentNode);
            currentNode = null;
            $("#contextmenu").hide();
        }
        if (text == '设置文字颜色') {
            currentNode = null;
        }
        if (text == '设置文字大小') {
            //scene.remove(currentNode);
            currentNode = null;
        }
        if (text == '删除该节点') {
            scene.remove(currentNode);
            currentNode = null;
            $("#contextmenu").hide();
        }
        if (text == '撤销上一次操作') {
            currentNode.restore();
            $("#contextmenu").hide();
        }
        if (text == '更改颜色') {
            currentNode.fillColor = JTopo.util.randomColor();
        } else if (text == '顺时针旋转') {
            currentNode.rotate += 0.5;
        } else if (text == '逆时针旋转') {
            currentNode.rotate -= 0.5;
        } else if (text == '放大') {
            currentNode.scaleX += 0.2;
            currentNode.scaleY += 0.2;
        } else if (text == '缩小') {
            currentNode.scaleX -= 0.2;
            currentNode.scaleY -= 0.2;
        }
        $("#contextmenu").hide();
    });
    $("#contextmenu2 a").click(function () {
        var text = $(this).text();
        if (text == '删除连线') {
            scene.remove(currentNode);
            currentNode = null;
            $("#contextmenu2").hide();
        }
        if (text == '设置颜色') {
        	$('#colorcaiter').dialog('open');
            //currentNode.strokeColor = '182,41,43';
            //currentNode = null;
            $("#contextmenu2").hide();
        }
        if (text == '设置线条宽度') {
        	var lineWidth=currentNode.lineWidth;
        	if(lineWidth!="undefined"&& lineWidth!=undefined&&lineWidth!==""){
        		$("#wlineValue").val(lineWidth);
        	}else{
        		$("#wlineValue").val("1");
        	}
        	$('#lineMain').dialog('open');
            $("#contextmenu2").hide();
        }
        if (text == '设置线条类型') {
        	var dashedPattern=currentNode.dashedPattern;
        	if(dashedPattern!="undefined"&& dashedPattern!=undefined&&dashedPattern!==""){
        		$("#lineClassValue").val("arrow");
        	}else{
        		$("#lineClassValue").val("link");
        	}
        	$('#lineClassMain').dialog('open');
            $("#contextmenu2").hide();
        }
        $("#contextmenu2").hide();
    });
    $("#contextmenu3 a").click(function () {
        var text = $(this).text();
        if (text == '直线连线') {
            //currentNode.fillColor=
            //scene.remove(currentNode);
            if (thisEvent.target != null && thisEvent.target instanceof JTopo.Node) {
                if (beginNode == null) {
                    beginNode = thisEvent.target;
                    scene.add(tempLink);
                    lineType="link";
                    tempNodeA.setLocation(thisEvent.x, thisEvent.y);
                    tempNodeZ.setLocation(thisEvent.x, thisEvent.y);
                } else if (beginNode !== thisEvent.target) {
                    var endNode = thisEvent.target;
                    var l = new JTopo.Link(beginNode, endNode);
                    l.lineWidth = 1;
                    initARightButton(l);
                    scene.add(l);
                    beginNode = null;
                    scene.remove(tempLink);
                } else {
                    beginNode = null;
                }
            } else {
                scene.remove(tempLink);
            }
            currentNode = null;
            thisEvent = null;
            $("#contextmenu3").hide();
        }
        if (text == '折线连线') {
            if (thisEvent.target != null && thisEvent.target instanceof JTopo.Node) {
                if (beginNode == null) {
                    beginNode = thisEvent.target;
                    scene.add(tempLink);
                    lineType="FoldLink";
                    tempNodeA.setLocation(thisEvent.x, thisEvent.y);
                    tempNodeZ.setLocation(thisEvent.x, thisEvent.y);
                } else if (beginNode !== thisEvent.target) {
                    var endNode = thisEvent.target;
                    var l = new JTopo.FoldLink(beginNode, endNode);
                    l.lineWidth = 1;
                    initARightButton(l);
                    scene.add(l);
                    beginNode = null;
                    scene.remove(tempLink);
                } else {
                    beginNode = null;
                }
            } else {
                scene.remove(tempLink);
            }
            currentNode = null;
            thisEvent = null;
            $("#contextmenu3").hide();
        }
        if (text == '二次折线连线') {
            if (thisEvent.target != null && thisEvent.target instanceof JTopo.Node) {
                if (beginNode == null) {
                    beginNode = thisEvent.target;
                    scene.add(tempLink);
                    lineType="FlexionalLink";
                    tempNodeA.setLocation(thisEvent.x, thisEvent.y);
                    tempNodeZ.setLocation(thisEvent.x, thisEvent.y);
                } else if (beginNode !== thisEvent.target) {
                    var endNode = thisEvent.target;
                    var l = new JTopo.FlexionalLink(beginNode, endNode);
                    l.lineWidth = 1;
                    initARightButton(l);
                    scene.add(l);
                    beginNode = null;
                    scene.remove(tempLink);
                } else {
                    beginNode = null;
                }
            } else {
                scene.remove(tempLink);
            }
            currentNode = null;
            thisEvent = null;
            $("#contextmenu3").hide();
        }
        if (text == '曲线连线') {
            if (thisEvent.target != null && thisEvent.target instanceof JTopo.Node) {
                if (beginNode == null) {
                    beginNode = thisEvent.target;
                    scene.add(tempLink);
                    lineType="CurveLink";
                    tempNodeA.setLocation(thisEvent.x, thisEvent.y);
                    tempNodeZ.setLocation(thisEvent.x, thisEvent.y);
                } else if (beginNode !== thisEvent.target) {
                    var endNode = thisEvent.target;
                    var l = new JTopo.CurveLink(beginNode, endNode);
                    l.lineWidth = 1;
                    initARightButton(l);
                    scene.add(l);
                    beginNode = null;
                    scene.remove(tempLink);
                } else {
                    beginNode = null;
                }
            } else {
                scene.remove(tempLink);
            }
            currentNode = null;
            thisEvent = null;
            $("#contextmenu3").hide();
        }
        if(text=='设置填充颜色'){
        	$('#colorcaiter1').dialog('open');
        	
        	$("#contextmenu3").hide();
        }
        if(text=='设置字体颜色'){
        	$('#colorcaiter2').dialog('open');
        	$("#contextmenu3").hide();
        }
        if(text=='绑定数据'){
        	//$('#bundlingData').dialog('open');
        	//var nodeField=currentNode.nodeField;
        	//alert(bundlingNode);
        	//$("#sbundlingField").val(nodeField);
        	//$("#contextmenu3").hide();
        }
        if(text=='绑定节点'){
        	bundlingNodeFieldJson();
        	$('#bundlingNode').dialog('open');
        	var bundlingNode=currentNode.bundlingNode;
        	$("#bundlingNodeField").val(bundlingNode);
        	$("#contextmenu3").hide();
        }
        if (text == '删除该节点') {
            scene.remove(currentNode);
            currentNode = null;
            $("#contextmenu3").hide();
        }
        $("#contextmenu3").hide();
    });
}


