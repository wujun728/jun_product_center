$(function() {
	$("#tree").tree({
		method : "get",
		lines : true,
		checkbox: true,
		url : "http://localhost:8080/sshe/test/tree_data1.json",
		autoBindDblClick : false,
		enableContextMenu : true,
		contextMenu : [ {
			text : "测试菜单1",
			iconCls : "icon-hamburg-address",
			disabled : false,
			handler : function() {
				alert("您点击了测试菜单1");
			}
		}, {
			text : "测试菜单2",
			children : [ {
				text : "测试子菜单1",
				handler : function() {
					alert("aaa");
				}
			}, {
				text : "测试子菜单2",
				handler : function() {
					alert("bbb");
				}
			} ]
		}, "-", {
			text : "测试菜单3",
			iconCls : "icon-hamburg-pencil",
			disabled : false,
			handler : function() {
				alert("您点击了测试菜单3");
			}
		} ]
	});
});

//$(function() {
//	alert('ttt');
//	$('#tree').tree({
//		checkbox : true,
//		url : '../../js/tree_data1.json',
//		onClick : function(node) {
//			$(this).tree('toggle', node.target);
//			var ss = node.attributes.cas.toString();// p1
//			alert('you click ' + ss);
//		},
//		onContextMenu : function(e, node) {
//			e.preventDefault();
//			$('#tree').tree('select', node.target);
//		}
//	});
//});
