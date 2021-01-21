var currentNode = null;
var thisEvent = null;
var lineType = null;
function handler(event) {
	if (event.button == 2) {// 右键
		// 当前位置弹出菜单（div）
		thisEvent = event;
		if (currentNode.cNodeId != "undefined"
				&& currentNode.cNodeId != undefined) {
			getRightMenu(currentNode.cNodeId);
			$("#contextmenu").css({
				top : event.pageY,
				left : event.pageX
			}).show();
		}
	}
}

function initARightButton(n) {
	n.addEventListener('mouseup', function(event) {
		currentNode = this;
		handler(event);
	});
}

function initDBClick() {
	var textfield = $("#jtopo_textfield");
	scene.dbclick(function(event) {
		if (event.target == null)
			return;
		var e = event.target;
		textfield.css({
			top : event.pageY,
			left : event.pageX - e.width / 2
		}).attr('value', e.text).show().focus().select();
		e.text = "";
		textfield[0].JTopoNode = e;
	});
	$("#jtopo_textfield").blur(function() {
		textfield[0].JTopoNode.text = textfield.hide().val();
	});
}

function initRightButton() {

	stage.click(function(event) {
		if (event.button == 0) {// 右键
			// 关闭弹出菜单（div）
			$("#contextmenu").hide();
			thisEvent = null;
		}
	});
}
function loadNode() {
	var nodes = scene.childs;
	for (var i = 0; i < nodes.length; i++) {
		var nodeType = nodes.childs[i].nodeType;
		if (nodeType == "TextNode") {
			var parentNode = nodes.child[i].bundlingNode;
			for (var a = 0; a < nodes.length; a++) {
				node = nodes.childs[a];

			}
		}
	}
}

function showMsgBar(cNodeId, datas) {
	for (var i = 0; i < scene.childs.length; i++) {
		var nodeStr = scene.childs[i];
		var nodeType = nodeStr.nodeType;
		if (nodeStr.bundlingNode == cNodeId && nodeType == "TextNode") {
			var txtStr = "";
			for (var a = 0; a < datas.length; a++) {
				txtStr += (datas[a].name + ":" + datas[a].val + ",");
			}
			if (txtStr.length > 0) {
				txtStr = txtStr.substring(0, txtStr.length - 1);
			}
			nodeStr.text = txtStr;
		}
	}
}
