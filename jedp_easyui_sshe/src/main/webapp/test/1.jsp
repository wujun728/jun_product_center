<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
<title></title>
<script>
	var o = {
		"knoList" : [ {
			"ID" : "-1",
			"NAME" : "知识分类",
			"PARENTID" : "-100",
			"coun" : 3
		}, {
			"ID" : "085a0107-c15b-4fd9-8cb2-0a4b8c2f1cf6",
			"NAME" : "研发类",
			"PARENTID" : "-1",
			"coun" : 0
		}, {
			"ID" : "1d8abf83-e634-4f7f-b333-928d6ff1ee3c",
			"NAME" : "美术类",
			"PARENTID" : "-1",
			"coun" : 1
		}, {
			"ID" : "4e0127d6-2206-4211-8861-1173de290152",
			"NAME" : "测试类",
			"PARENTID" : "-1",
			"coun" : 1
		}, {
			"ID" : "7405f176-cc87-4279-a847-9d1c6605d6ae",
			"NAME" : "美术3",
			"PARENTID" : "f8e8c18c-0fa5-4b34-a937-f482a1c4de8c",
			"coun" : 0
		}, {
			"ID" : "b78c4b06-6456-4f9a-a353-38c43065ef5b",
			"NAME" : "asdf",
			"PARENTID" : "4e0127d6-2206-4211-8861-1173de290152",
			"coun" : 1
		}, {
			"ID" : "c1ce4fcd-8ad2-4062-a561-a54b5b88c762",
			"NAME" : "666",
			"PARENTID" : "b78c4b06-6456-4f9a-a353-38c43065ef5b",
			"coun" : 0
		}, {
			"ID" : "f8e8c18c-0fa5-4b34-a937-f482a1c4de8c",
			"NAME" : "美术2",
			"PARENTID" : "1d8abf83-e634-4f7f-b333-928d6ff1ee3c",
			"coun" : 1
		} ],
		"pubKnoList" : [ {
			"ID" : "-1",
			"NAME" : "知识分类",
			"PARENTID" : "-100",
			"coun" : 7
		}, {
			"ID" : "045caaa5-35e9-468b-b0d8-7972e94803bf",
			"NAME" : "财务类",
			"PARENTID" : "-1",
			"coun" : 0
		}, {
			"ID" : "4dbef8b5-5692-4604-983a-28164513093e",
			"NAME" : "行政类",
			"PARENTID" : "-1",
			"coun" : 0
		}, {
			"ID" : "75e8a40d-e226-49af-8274-cf803fb640d6",
			"NAME" : "scorm测试课件分类",
			"PARENTID" : "-1",
			"coun" : 0
		}, {
			"ID" : "88e16a47-58e0-43a6-a6bb-8f2d84ff1fc8",
			"NAME" : "测试类",
			"PARENTID" : "-1",
			"coun" : 0
		}, {
			"ID" : "a2b12d03-cc18-44ea-ad03-f23115a740b5",
			"NAME" : "aicc课测试",
			"PARENTID" : "-1",
			"coun" : 0
		}, {
			"ID" : "da2114e2-383f-44bd-936d-8d3291f1a058",
			"NAME" : "开发类",
			"PARENTID" : "-1",
			"coun" : 0
		}, {
			"ID" : "df527e40-8fa3-4a14-807d-85de3f570bb9",
			"NAME" : "策划类",
			"PARENTID" : "-1",
			"coun" : 0
		} ]
	};
	var knoList = o.knoList;
	function pushTree(data) {
		var idField = 'ID';
		var textField = 'NAME';
		var parentField = 'PARENTID';
		var i, l, treeData = [], tmpMap = [];
		for (i = 0, l = data.length; i < l; i++) {
			tmpMap[data[i][idField]] = data[i];
		}
		for (i = 0, l = data.length; i < l; i++) {
			if (tmpMap[data[i][parentField]] && data[i][idField] != data[i][parentField]) {
				if (!tmpMap[data[i][parentField]]['children'])
					tmpMap[data[i][parentField]]['children'] = [];
				data[i]['text'] = data[i][textField];
				tmpMap[data[i][parentField]]['children'].push(data[i]);
			} else {
				data[i]['text'] = data[i][textField];
				treeData.push(data[i]);
			}
		}
		return treeData;
	}
	function buildTree(tree) {
		var ul = $("<ul/>");
		$.each(tree, function() {
			var li = $("<li/>");
			li.append("<span>" + this.text + "</span>");
			if (this.children) {
				var children = buildTree(this.children);
				li.append(children);
			}
			ul.append(li);
		});
		return ul;
	}

	var tree = pushTree(knoList);
	console.info(tree);
	$(function() {
		$('body').append(buildTree(tree));
	});
</script>
</head>
<body>
</body>
</html>