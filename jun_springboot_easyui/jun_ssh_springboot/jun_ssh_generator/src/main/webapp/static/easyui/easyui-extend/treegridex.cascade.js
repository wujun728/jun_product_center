$.extend($.fn.treegrid.defaults,{
	singleSelect	: false,
	onClickRow      :function(row){  
        //级联选择  
        $(this).treegrid('cascadeCheck',{  
            id:row.id, //节点ID  
            deepCascade:true //深度级联  
        });  
    }		
})

$.extend($.fn.treegrid.methods,
		{
			/**
			 * 级联选择
			 * 
			 * @param {Object}
			 *            target
			 * @param {Object}
			 *            param param包括两个参数: id:勾选的节点ID deepCascade:是否深度级联
			 * @return {TypeName}
			 */
			cascadeCheck : function(target, param) {
				var opts = $.data(target[0], "treegrid").options;
				if (opts.singleSelect)
					return;
				var idField = opts.idField;// 这里的idField其实就是API里方法的id参数
				var status = false;// 用来标记当前节点的状态，true:勾选，false:未勾选
				var selectNodes = $(target).treegrid('getSelections');// 获取当前选中项
				for (var i = 0; i < selectNodes.length; i++) {
					if (selectNodes[i][idField] == param.id)
						status = true;
				}
				// 级联选择父节点
				selectParent(target[0], param.id, idField, status);
				selectChildren(target[0], param.id, idField, param.deepCascade,
						status);
				/**
				 * 级联选择父节点
				 * 
				 * @param {Object}
				 *            target
				 * @param {Object}
				 *            id 节点ID
				 * @param {Object}
				 *            status 节点状态，true:勾选，false:未勾选
				 * @return {TypeName}
				 */
				function selectParent(target, id, idField, status) {
					var parent = $(target).treegrid('getParent', id);
					if (parent) {
						var parentId = parent[idField];
						if (status)
							$(target).treegrid('select', parentId);
//						else
//							$(target).treegrid('unselect', parentId);
						selectParent(target, parentId, idField, status);
					}
				}
				/**
				 * 级联选择子节点
				 * 
				 * @param {Object}
				 *            target
				 * @param {Object}
				 *            id 节点ID
				 * @param {Object}
				 *            deepCascade 是否深度级联
				 * @param {Object}
				 *            status 节点状态，true:勾选，false:未勾选
				 * @return {TypeName}
				 */
				function selectChildren(target, id, idField, deepCascade,
						status) {
					// 深度级联时先展开节点
					// if (!status && deepCascade)
					// $(target).treegrid('expand', id);
					// 根据ID获取下层孩子节点
					var children = $(target).treegrid('getChildren', id);
					for (var i = 0; i < children.length; i++) {
						var childId = children[i][idField];
						if (status)
							$(target).treegrid('select', childId);
						else
							$(target).treegrid('unselect', childId);
						selectChildren(target, childId, idField, deepCascade,
								status);// 递归选择子节点
					}
				}
			}
		});