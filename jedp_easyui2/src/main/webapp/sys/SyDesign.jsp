<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/include/taglibs.jsp"%>
<%@ include file="/include/meta.jsp"%>
<script>

var gc = [
      	{
    		type:"eType.Input",
    		attr:[
    			{
    				"name" : "选择框<span style=\"color:Red\">(*)</span>",
    				"ref":"checkbox",
    				"value" : "",
    				"group" : "类型",
    				"help":"选中则该字段将以checkbox的形式展现于页面中。<br/>填写方法：直接选中单选框即可",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "字段显示文本<span style=\"color:Red\">(*)</span>",
    				"ref":"title",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在表单及展示页面中的名称。<br/>填写方法：直接填入名称即可，名称的内容最好为汉字或者英文单词.",
    				"editor" :"text"
    			},
				{
    				"name" : "字段转义",
    				"ref":"map",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段的转义处理，格式为：'map表|map字段|map值' 备注：用于表之间关联时，map表：关联的表(完整表名)，map字段：关联的字段(Class中的属性)，map值：用于显示的字段(class中的属性),",
    				"editor" :"text"
    			}
				,
    			{
    				"name":"填写说明",
    				"ref":"tooltip",
    				"value":"",
    				"help":"该字段填写说明",
    				"editor":"text",
    			}
				,{
    				"name" : "字段展示页面是否隐藏",
    				"ref":"hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在展示页面中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
    			,{
    				"name" : "调用方法设置值(新增表单)",
    				"ref":"addform:setvalue",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于新增表单中设置字段的调用方法或者固定字符串,设置调用方法后，该字段可获得方法的返回值<br/>设置固定字符串后，该字段可获得该字符串。<br/>填写方法：'getCurrentUserId()'或者'\\\"--保存后自动生成--\\\"'",
    				"editor" :"text"
    			}
				,{
    				"name" : "字段附加样式（新增表单）",
    				"ref":"addform:style",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在新增表单中的宽度和高度，填写方法：例如,width:100px;height:200px",
    				"editor" :"text"
    			}
				,{
    				"name" : "字段只读（新增表单）",
    				"ref":"addform:readonly",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在新增表单中是否可编辑。<br/>如果设置为true，该字段将不可编辑。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "新增页面是否隐藏<span style=\"color:Red\">(*)</span>",
    				"ref":"addform:hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在新增表单中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
								,{
    				"name" : "字段是否为必填项(新增表单)",
    				"ref":"editform:dataoptions:required",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中是否应该输入。<br/>如果设置为true，指该字段应该输入。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "输入框为空的时候的提示文本（新增表单）",
    				"ref":"addform:dataoptions:missingMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中输入内容为空时的提示信息。<br/>填写方法：'您输入的内容不能为空。'",
    				"editor" : "text"
    			}
				,{
    				"name" : "输入框内容为不合法时的提示文本（新增表单）",
    				"ref":"addform:dataoptions:invalidMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中输入内容不合法时的提示信息。<br/>填写方法：'您输入的内容不合法。'",
    				"editor" : "text"
    			}
				,{
    				"name" : "是否为必填项(编辑表单)",
    				"ref":"addform:dataoptions:required",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中是否应该输入。<br/>如果设置为true，指该字段应该输入。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
    			,{
    				"name" : "输入框为空时的提示文本（编辑表单）",
    				"ref":"editform:dataoptions:missingMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中输入内容为空时的提示信息。<br/>填写方法：'您输入的内容不能为空。'",
    				"editor" : "text"
    			}
    			,{
    				"name" : "输入框内容为不合法时的提示文本（编辑表单）",
    				"ref":"editform:dataoptions:invalidMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中输入内容不合法时的提示信息。<br/>填写方法：'您输入的内容不合法。'",
    				"editor" : "text"
    			}
    			,{
    				"name" : "调用方法设置值(编辑表单)",
    				"ref":"editform:setvalue",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于修改表单设置字段的调用方法或者固定字符串,设置调用方法后，该字段可获得方法的返回值<br/>设置固定字符串后，该字段可获得该字符串。<br/>填写方法：'getCurrentUserId()'或者'\\\"--保存后自动生成--\\\"'",
    				"editor" :"text",
    			}
    			,{
    				"name" : "字段附加样式（编辑表单）",
    				"ref":"editform:style",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在修改表单中的宽度和高度，填写方法：例如,width:100px;height:200px",
    				"editor" :"text"
    			}
    			,{
    				"name" : "字段只读（编辑表单）",
    				"ref":"editform:readonly",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在新增表单中是否可编辑。<br/>如果设置为true，该字段将不可编辑。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
    			,{
    				"name" : "编辑页面是否隐藏<span style=\"color:Red\">(*)</span>",
    				"ref":"editform:hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在修改表单中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
    		]
    	},{
    		type:"eType.ValidateBox",
    		attr:[{
    				"name" : "字段显示文本<span style=\"color:Red\">(*)</span>",
    				"ref":"title",
    				"require":true,
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在表单及展示页面中的名称。<br/>填写方法：直接填入名称即可，名称的内容最好为汉字或者英文单词 ",
    				"editor" :"text"
    			},
				{
    				"name" : "字段转义",
    				"ref":"map",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段的转义处理，格式为：'map表|map字段|map值' 备注：用于表之间关联时，map表：关联的表(完整表名)，map字段：关联的字段(Class中的属性)，map值：用于显示的字段(class中的属性),",
    				"editor" :"text"
    			}
    		,
			{
				"name":"填写说明",
				"ref":"tooltip",
				"value":"",
				"help":"该字段填写说明",
				"editor":"text",
			}
    		,{
    				"name" : "字段展示页面是否隐藏",
    				"ref":"hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在展示页面中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "字段附加样式（新增表单）",
    				"ref":"addform:style",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在新增表单中的宽度和高度，填写方法：例如,width:100px;height:200px",
    				"editor" :"text"
    			}
    			,{
    				"name" : "新增页面是否隐藏",
    				"ref":"addform:hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在新增表单中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "字段是否为必填项(新增表单)",
    				"ref":"editform:dataoptions:required",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中是否应该输入。<br/>如果设置为true，指该字段应该输入。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "输入框为空的时候的提示文本（新增表单）",
    				"ref":"addform:dataoptions:missingMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中输入内容为空时的提示信息。<br/>填写方法：'您输入的内容不能为空。'",
    				"editor" : "text"
    			}
				,{
    				"name" : "输入框内容为不合法时的提示文本（新增表单）",
    				"ref":"addform:dataoptions:invalidMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中输入内容不合法时的提示信息。<br/>填写方法：'您输入的内容不合法。'",
    				"editor" : "text"
    			}
				,{
    				"name" : "字段验证类型（新增表单）",
    				"ref":"addform:dataoptions:validType",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中输入内容的验证类型。<br/>如果你输入'email'则该字段的输入内容只能为邮箱的格式。<br/>填写方法：'email'",
    				"editor" : "text"
    			}
    			,{
    				"name" : "编辑页面是否隐藏",
    				"ref":"editform:hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在修改表单中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "字段附加样式（编辑表单）",
    				"ref":"editform:style",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在修改表单中的宽度和高度，填写方法：例如,width:100px;height:200px",
    				"editor" :"text"
    			}
    			,{
    				"name" : "字段是否为必填项(编辑表单)",
    				"ref":"addform:dataoptions:required",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中是否应该输入。<br/>如果设置为true，指该字段应该输入。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
    			,{
    				"name" : "输入框为空时的提示文本（编辑表单）",
    				"ref":"editform:dataoptions:missingMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中输入内容为空时的提示信息。<br/>填写方法：'您输入的内容不能为空。'",
    				"editor" : "text"
    			}
    			,{
    				"name" : "输入框内容为不合法时的提示文本（编辑表单）",
    				"ref":"editform:dataoptions:invalidMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中输入内容不合法时的提示信息。<br/>填写方法：'您输入的内容不合法。'",
    				"editor" : "text"
    			}
    			,{
    				"name" : "字段验证类型（编辑表单）",
    				"ref":"editform:dataoptions:validType",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中输入内容的验证类型。<br/>如果你输入'email'则该字段的输入内容只能为邮箱的格式。<br/>填写方法：'email'",
    				"editor" : "text"
    			} ]
    	},{
    		type:"eType.ComboBox",
    		attr:[{
    				"name" : "字段显示文本<span style=\"color:Red\">(*)</span>",
    				"ref":"title",
    				"require":true,
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在表单及展示页面中的名称。<br/>填写方法：直接填入名称即可，名称的内容最好为汉字或者英文单词 ",
    				"editor" :"text"
    			},
				{
    				"name" : "字段转义",
    				"ref":"map",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段的转义处理，格式为：'map表|map字段|map值' 备注：用于表之间关联时，map表：关联的表(完整表名)，map字段：关联的字段(Class中的属性)，map值：用于显示的字段(class中的属性),",
    				"editor" :"text"
    			}
    		,
			{
				"name":"填写说明",
				"ref":"tooltip",
				"value":"",
				"help":"该字段填写说明",
				"editor":"text",
			}
    		,{
    				"name" : "展示页面是否隐藏",
    				"ref":"hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在展示页面中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "字段附加样式（新增表单）",
    				"ref":"addform:style",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在新增表单中的宽度和高度，填写方法：例如,width:100px;height:200px",
    				"editor" :"text"
    			}
				,{
    				"name" : "新增页面是否隐藏",
    				"ref":"addform:hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在新增表单中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
					"name" : "数据值名（新增表单）<span style=\"color:Red\">(*)</span>",
					"ref":"addform:dataoptions:valueField",
					"require":true,
					"value" : "",
					"group" : "数据",
					"help":"该属性用于设置字段在新增表单中存值的情况。<br/>如果设置为'id',则在数据库中所存的值为其id。<br/>填写方法：'id'或者'text'",
					"editor" : "text"
				}
				,{
					"name" : "数据获取路径（新增表单）<span style=\"color:Red\">(*)</span>",
					"ref":"addform:dataoptions:url",
					"value" : "",
					"group" : "数据",
					"help":"该属性用于设置字段在新增表单中获取值的路径。<br/>填写方法：/smarte/sys/secActSysSbdutyPlan_ComboBox",
					"editor" : "text"
				}
				,{
					"name" : "数据方法调用（新增表单）",
					"ref":"addform:dataoptions:data",
					"value" : "",
					"group" : "数据",
					"help":"该属性用于设置字段在新增表单中获取值的方法。<br/>填写方法：\\\\\\\"+getYearComboBoxData()+\\\\\\\"",
					"editor" : "text"
				}
								,{
    				"name" : "字段是否为必填项(新增表单)",
    				"ref":"editform:dataoptions:required",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中是否应该输入。<br/>如果设置为true，指该字段应该输入。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "输入框为空的时候的提示文本（新增表单）",
    				"ref":"addform:dataoptions:missingMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中输入内容为空时的提示信息。<br/>填写方法：'您输入的内容不能为空。'",
    				"editor" : "text"
    			}
				,{
    				"name" : "输入框内容为不合法时的提示文本（新增表单）",
    				"ref":"addform:dataoptions:invalidMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中输入内容不合法时的提示信息。<br/>填写方法：'您输入的内容不合法。'",
    				"editor" : "text"
    			}
				,{
    				"name" : "字段是否为必填项(编辑表单)",
    				"ref":"addform:dataoptions:required",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中是否应该输入。<br/>如果设置为true，指该字段应该输入。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
    			,{
    				"name" : "输入框为空时的提示文本（编辑表单）",
    				"ref":"editform:dataoptions:missingMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中输入内容为空时的提示信息。<br/>填写方法：'您输入的内容不能为空。'",
    				"editor" : "text"
    			}
    			,{
    				"name" : "输入框内容为不合法时的提示文本（编辑表单）",
    				"ref":"editform:dataoptions:invalidMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中输入内容不合法时的提示信息。<br/>填写方法：'您输入的内容不合法。'",
    				"editor" : "text"
    			}
				,{
    				"name" : "字段附加样式（编辑表单）",
    				"ref":"editform:style",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在修改表单中的宽度和高度，填写方法：例如,width:100px;height:200px",
    				"editor" :"text"
    			}
				,{
    				"name" : "编辑页面是否隐藏",
    				"ref":"editform:hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在修改表单中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
					"name" : "数据方法调用（编辑表单）",
					"ref":"editform:dataoptions:data",
					"value" : "",
					"group" : "数据",
					"help":"该属性用于设置字段在修改表单中获取值的方法。<br/>填写方法：\\\\\\\"+getYearComboBoxData()+\\\\\\\"",
					"editor" : "text"
				}
				
				,{
					"name" : "数据值名（编辑表单）<span style=\"color:Red\">(*)</span>",
					"ref":"editform:dataoptions:valueField",
					"require":true,
					"value" : "",
					"group" : "数据",
					"help":"该属性用于设置字段在修改表单中存值的情况。<br/>如果设置为'id',则在数据库中所存的值为其id。<br/>填写方法：'id'或者'text'",
					"editor" : "text"
				}
				
				,{
					"name" : "数据获取路径（编辑表单）<span style=\"color:Red\">(*)</span>",
					"ref":"editform:dataoptions:url",
					"value" : "",
					"group" : "数据",
					"help":"该属性用于设置字段在修改表单中获取值的路径。<br/>填写方法：/smarte/sys/secActSysSbdutyPlan_ComboBox",
					"editor" : "text"
				}
    		]
    	},{
    		type:"eType.ComboTree",
    		attr:[{
    				"name" : "字段显示文本<span style=\"color:Red\">(*)</span>",
    				"ref":"title",
    				"require":true,
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在表单及展示页面中的名称。<br/>填写方法：直接填入名称即可，名称的内容最好为汉字或者英文单词 ",
    				"editor" :"text"
    			},
				{
    				"name" : "字段转义",
    				"ref":"map",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段的转义处理，格式为：'map表|map字段|map值' 备注：用于表之间关联时，map表：关联的表(完整表名)，map字段：关联的字段(Class中的属性)，map值：用于显示的字段(class中的属性),",
    				"editor" :"text"
    			}
	      		,
	  			{
	  				"name":"填写说明",
	  				"ref":"tooltip",
	  				"value":"",
	  				"help":"该字段填写说明",
	  				"editor":"text",
	  			}
	      		,{
    				"name" : "展示页面是否隐藏",
    				"ref":"hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在展示页面中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "字段附加样式（新增表单）",
    				"ref":"addform:style",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在新增表单中的宽度和高度，填写方法：例如,width:100px;height:200px",
    				"editor" :"text"
    			}
				,{
    				"name" : "新增页面是否隐藏",
    				"ref":"addform:hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在新增表单中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
					"name" : "数据值名（新增表单）<span style=\"color:Red\">(*)</span>",
					"ref":"addform:dataoptions:valueField",
					"require":true,
					"value" : "",
					"group" : "数据",
					"help":"该属性用于设置字段在新增表单中存值的情况。<br/>如果设置为'id',则在数据库中所存的值为其id。<br/>填写方法：'id'或者'text'",
					"editor" : "text"
				}
				,{
					"name" : "数据获取路径（新增表单）<span style=\"color:Red\">(*)</span>",
					"ref":"addform:dataoptions:url",
					"require":true,
					"value" : "",
					"group" : "数据",
					"help":"该属性用于设置字段在新增表单中获取值的路径。<br/>填写方法：/smarte/sys/secActSysSbdutyPlan_ComboBox",
					"editor" : "text"
				}
				,{
					"name" : "数据方法调用（新增表单）",
					"ref":"addform:dataoptions:data",
					"value" : "",
					"group" : "数据",
					"help":"该属性用于设置字段在新增表单中获取值的方法。<br/>填写方法：\\\\\\\"+getYearComboBoxData()+\\\\\\\"",
					"editor" : "text"
				}
								,{
    				"name" : "字段是否为必填项(新增表单)",
    				"ref":"editform:dataoptions:required",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中是否应该输入。<br/>如果设置为true，指该字段应该输入。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "输入框为空的时候的提示文本（新增表单）",
    				"ref":"addform:dataoptions:missingMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中输入内容为空时的提示信息。<br/>填写方法：'您输入的内容不能为空。'",
    				"editor" : "text"
    			}
				,{
    				"name" : "输入框内容为不合法时的提示文本（新增表单）",
    				"ref":"addform:dataoptions:invalidMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中输入内容不合法时的提示信息。<br/>填写方法：'您输入的内容不合法。'",
    				"editor" : "text"
    			}
				,{
    				"name" : "字段是否为必填项(编辑表单)",
    				"ref":"addform:dataoptions:required",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中是否应该输入。<br/>如果设置为true，指该字段应该输入。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
    			,{
    				"name" : "输入框为空时的提示文本（编辑表单）",
    				"ref":"editform:dataoptions:missingMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中输入内容为空时的提示信息。<br/>填写方法：'您输入的内容不能为空。'",
    				"editor" : "text"
    			}
    			,{
    				"name" : "输入框内容为不合法时的提示文本（编辑表单）",
    				"ref":"editform:dataoptions:invalidMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中输入内容不合法时的提示信息。<br/>填写方法：'您输入的内容不合法。'",
    				"editor" : "text"
    			}
				,{
    				"name" : "字段附加样式（编辑表单）",
    				"ref":"editform:style",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在修改表单中的宽度和高度，填写方法：例如,width:100px;height:200px",
    				"editor" :"text"
    			}
				,{
    				"name" : "编辑页面是否隐藏",
    				"ref":"editform:hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在修改表单中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
					"name" : "数据方法调用（编辑表单）",
					"ref":"editform:dataoptions:data",
					"value" : "",
					"group" : "数据",
					"help":"该属性用于设置字段在修改表单中获取值的方法。<br/>填写方法：\\\\\\\"+getYearComboBoxData()+\\\\\\\"",
					"editor" : "text"
				}
				
				,{
					"name" : "数据值名（编辑表单）<span style=\"color:Red\">(*)</span>",
					"ref":"editform:dataoptions:valueField",
					"require":true,
					"value" : "",
					"group" : "数据",
					"help":"该属性用于设置字段在修改表单中存值的情况。<br/>如果设置为'id',则在数据库中所存的值为其id。<br/>填写方法：'id'或者'text'",
					"editor" : "text"
				}
				
				,{
					"name" : "数据获取路径（编辑表单）<span style=\"color:Red\">(*)</span>",
					"ref":"editform:dataoptions:url",
					"require":true,
					"value" : "",
					"group" : "数据",
					"help":"该属性用于设置字段在修改表单中获取值的路径。<br/>填写方法：/smarte/sys/secActSysSbdutyPlan_ComboBox",
					"editor" : "text"
				}
    		]
    	},{
    		type:"eType.ComboGrid",
    		attr:[{
    				"name" : "字段显示文本<span style=\"color:Red\">(*)</span>",
    				"ref":"title",
    				"require":true,
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在表单及展示页面中的名称。<br/>填写方法：直接填入名称即可，名称的内容最好为汉字或者英文单词 ",
    				"editor" :"text"
    			},
				{
    				"name" : "字段转义",
    				"ref":"map",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段的转义处理，格式为：'map表|map字段|map值' 备注：用于表之间关联时，map表：关联的表(完整表名)，map字段：关联的字段(Class中的属性)，map值：用于显示的字段(class中的属性),",
    				"editor" :"text"
    			}
	      		,
	  			{
	  				"name":"填写说明",
	  				"ref":"tooltip",
	  				"value":"",
	  				"help":"该字段填写说明",
	  				"editor":"text",
	  			}
    			,{
    				"name" : "展示页面是否隐藏",
    				"ref":"hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在展示页面中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "字段附加样式（新增表单）",
    				"ref":"addform:style",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在新增表单中的宽度和高度，填写方法：例如,width:100px;height:200px",
    				"editor" :"text"
    			}
				,{
    				"name" : "新增页面是否隐藏",
    				"ref":"addform:hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在新增表单中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
					"name" : "数据值名（新增表单）",
					"ref":"addform:dataoptions:valueField",
					"value" : "",
					"group" : "数据",
					"help":"该属性用于设置字段在新增表单中存值的情况。<br/>如果设置为'id',则在数据库中所存的值为其id。<br/>填写方法：'id'或者'text'",
					"editor" : "text"
				}
				,{
					"name" : "数据获取路径（新增表单）",
					"ref":"addform:dataoptions:url",
					"value" : "",
					"group" : "数据",
					"help":"该属性用于设置字段在新增表单中获取值的路径。<br/>填写方法：/smarte/sys/secActSysSbdutyPlan_ComboBox",
					"editor" : "text"
				}
				,{
					"name" : "数据方法调用（新增表单）",
					"ref":"addform:dataoptions:data",
					"value" : "",
					"group" : "数据",
					"help":"该属性用于设置字段在新增表单中获取值的方法。<br/>填写方法：\\\\\\\"+getYearComboBoxData()+\\\\\\\"",
					"editor" : "text"
				}
								,{
    				"name" : "字段是否为必填项(新增表单)",
    				"ref":"editform:dataoptions:required",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中是否应该输入。<br/>如果设置为true，指该字段应该输入。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "输入框为空的时候的提示文本（新增表单）",
    				"ref":"addform:dataoptions:missingMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中输入内容为空时的提示信息。<br/>填写方法：'您输入的内容不能为空。'",
    				"editor" : "text"
    			}
				,{
    				"name" : "输入框内容为不合法时的提示文本（新增表单）",
    				"ref":"addform:dataoptions:invalidMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中输入内容不合法时的提示信息。<br/>填写方法：'您输入的内容不合法。'",
    				"editor" : "text"
    			}
				,{
    				"name" : "字段是否为必填项(编辑表单)",
    				"ref":"addform:dataoptions:required",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中是否应该输入。<br/>如果设置为true，指该字段应该输入。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
    			,{
    				"name" : "输入框为空时的提示文本（编辑表单）",
    				"ref":"editform:dataoptions:missingMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中输入内容为空时的提示信息。<br/>填写方法：'您输入的内容不能为空。'",
    				"editor" : "text"
    			}
    			,{
    				"name" : "输入框内容为不合法时的提示文本（编辑表单）",
    				"ref":"editform:dataoptions:invalidMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中输入内容不合法时的提示信息。<br/>填写方法：'您输入的内容不合法。'",
    				"editor" : "text"
    			}
				,{
    				"name" : "字段附加样式（编辑表单）",
    				"ref":"editform:style",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在修改表单中的宽度和高度，填写方法：例如,width:100px;height:200px",
    				"editor" :"text"
    			}
				,{
    				"name" : "编辑页面是否隐藏",
    				"ref":"editform:hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在修改表单中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
					"name" : "数据方法调用（编辑表单）",
					"ref":"editform:dataoptions:data",
					"value" : "",
					"group" : "数据",
					"help":"该属性用于设置字段在修改表单中获取值的方法。<br/>填写方法：\\\\\\\"+getYearComboBoxData()+\\\\\\\"",
					"editor" : "text"
				}
				
				,{
					"name" : "数据值名（编辑表单）",
					"ref":"editform:dataoptions:valueField",
					"value" : "",
					"group" : "数据",
					"help":"该属性用于设置字段在修改表单中存值的情况。<br/>如果设置为'id',则在数据库中所存的值为其id。<br/>填写方法：'id'或者'text'",
					"editor" : "text"
				}
				
				,{
					"name" : "数据获取路径（编辑表单）",
					"ref":"editform:dataoptions:url",
					"value" : "",
					"group" : "数据",
					"help":"该属性用于设置字段在修改表单中获取值的路径。<br/>填写方法：/smarte/sys/secActSysSbdutyPlan_ComboBox",
					"editor" : "text"
				}
    		]
    	},{
    		type:"eType.NumberBox",
    		attr:[{
    				"name" : "字段显示文本<span style=\"color:Red\">(*)</span>",
    				"ref":"title",
    				"require":true,
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在表单及展示页面中的名称。<br/>填写方法：直接填入名称即可，名称的内容最好为汉字或者英文单词 ",
    				"editor" :"text"
    			},
				{
    				"name" : "字段转义",
    				"ref":"map",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段的转义处理，格式为：'map表|map字段|map值' 备注：用于表之间关联时，map表：关联的表(完整表名)，map字段：关联的字段(Class中的属性)，map值：用于显示的字段(class中的属性),",
    				"editor" :"text"
    			}
    		,
			{
				"name":"填写说明",
				"ref":"tooltip",
				"value":"",
				"help":"该字段填写说明",
				"editor":"text",
			}
    			,{
    				"name" : "展示页面是否隐藏",
    				"ref":"hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在展示页面中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "字段附加样式（新增表单）",
    				"ref":"addform:style",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在新增表单中的宽度和高度，填写方法：例如,width:100px;height:200px",
    				"editor" :"text"
    			}
				,{
    				"name" : "新增页面是否隐藏",
    				"ref":"addform:hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在新增表单中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
						
						,{
    				"name" : "字段是否为必填项(新增表单)",
    				"ref":"editform:dataoptions:required",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中是否应该输入。<br/>如果设置为true，指该字段应该输入。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "输入框为空的时候的提示文本（新增表单）",
    				"ref":"addform:dataoptions:missingMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中输入内容为空时的提示信息。<br/>填写方法：'您输入的内容不能为空。'",
    				"editor" : "text"
    			}
				,{
    				"name" : "输入框内容为不合法时的提示文本（新增表单）",
    				"ref":"addform:dataoptions:invalidMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中输入内容不合法时的提示信息。<br/>填写方法：'您输入的内容不合法。'",
    				"editor" : "text"
    			}
						,{
	          				"name" : "字段允许最大值（新增表单）",
	          				"ref":"addform:dataoptions:max",
	          				"value" : "",
	          				"group" : "数据",
							"help":"该属性用于设置字段在新增表单中填写的最大值为多少。<br/>填写方法：100",
	          				"editor" : "text"
	          			}
						,{
	          				"name" : "字段允许最小值（新增表单）",
	          				"ref":"addform:dataoptions:min",
	          				"value" : "",
	          				"group" : "数据",
							"help":"该属性用于设置字段在新增表单中填写的最小值为多少。<br/>填写方法：10",
	          				"editor" : "text"
	          			}
						,{
    				"name" : "字段附加样式（编辑表单）",
    				"ref":"editform:style",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在修改表单中的宽度和高度，填写方法：例如,width:100px;height:200px",
    				"editor" :"text"
    			}
						,{
							"name" : "编辑页面是否隐藏",
							"ref":"editform:hidden",
							"value" : "",
							"group" : "外观",
							"help":"该属性用于设置字段是否在修改表单中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
							"editor":{
								"type":"checkbox",
								"options":{
									"on":true,
									"off":false
								}
							}
						}
						,{
    				"name" : "字段是否为必填项(编辑表单)",
    				"ref":"addform:dataoptions:required",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中是否应该输入。<br/>如果设置为true，指该字段应该输入。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
    			,{
    				"name" : "输入框为空时的提示文本（编辑表单）",
    				"ref":"editform:dataoptions:missingMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中输入内容为空时的提示信息。<br/>填写方法：'您输入的内容不能为空。'",
    				"editor" : "text"
    			}
    			,{
    				"name" : "输入框内容为不合法时的提示文本（编辑表单）",
    				"ref":"editform:dataoptions:invalidMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中输入内容不合法时的提示信息。<br/>填写方法：'您输入的内容不合法。'",
    				"editor" : "text"
    			}
	          			
	          			,{
	          				"name" : "字段允许最大值（编辑表单）",
	          				"ref":"editform:dataoptions:max",
	          				"value" : "",
	          				"group" : "数据",
							"help":"该属性用于设置字段在修改表单中填写的最大值为多少。<br/>填写方法：100",
	          				"editor" : "text"
	          			}
	          			
	          			,{
	          				"name" : "字段允许最小值（编辑表单）",
	          				"ref":"editform:dataoptions:min",
	          				"value" : "",
	          				"group" : "数据",
							"help":"该属性用于设置字段在修改表单中填写的最小值为多少。<br/>填写方法：10",
	          				"editor" : "text"
	          			}
    		      ]
    	},{
    		type:"eType.DateBox",
    		attr:[{
    				"name" : "字段显示文本<span style=\"color:Red\">(*)</span>",
    				"ref":"title",
    				"require":true,
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在表单及展示页面中的名称。<br/>填写方法：直接填入名称即可，名称的内容最好为汉字或者英文单词 ",
    				"editor" :"text"
    			},
				{
    				"name" : "字段转义",
    				"ref":"map",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段的转义处理，格式为：'map表|map字段|map值' 备注：用于表之间关联时，map表：关联的表(完整表名)，map字段：关联的字段(Class中的属性)，map值：用于显示的字段(class中的属性),",
    				"editor" :"text"
    			}
    		,
			{
				"name":"填写说明",
				"ref":"tooltip",
				"value":"",
				"help":"该字段填写说明",
				"editor":"text",
			}
    		,{
    				"name" : "展示页面是否隐藏",
    				"ref":"hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在展示页面中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "字段附加样式（新增表单）",
    				"ref":"addform:style",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在新增表单中的宽度和高度，填写方法：例如,width:100px;height:200px",
    				"editor" :"text"
    			}
				,{
    				"name" : "新增页面是否隐藏",
    				"ref":"addform:hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在新增表单中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
								,{
    				"name" : "字段是否为必填项(新增表单)",
    				"ref":"editform:dataoptions:required",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中是否应该输入。<br/>如果设置为true，指该字段应该输入。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "输入框为空的时候的提示文本（新增表单）",
    				"ref":"addform:dataoptions:missingMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中输入内容为空时的提示信息。<br/>填写方法：'您输入的内容不能为空。'",
    				"editor" : "text"
    			}
				,{
    				"name" : "输入框内容为不合法时的提示文本（新增表单）",
    				"ref":"addform:dataoptions:invalidMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中输入内容不合法时的提示信息。<br/>填写方法：'您输入的内容不合法。'",
    				"editor" : "text"
    			}
				,{
    				"name" : "字段是否为必填项(编辑表单)",
    				"ref":"addform:dataoptions:required",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中是否应该输入。<br/>如果设置为true，指该字段应该输入。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
    			,{
    				"name" : "输入框为空时的提示文本（编辑表单）",
    				"ref":"editform:dataoptions:missingMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中输入内容为空时的提示信息。<br/>填写方法：'您输入的内容不能为空。'",
    				"editor" : "text"
    			}
    			,{
    				"name" : "输入框内容为不合法时的提示文本（编辑表单）",
    				"ref":"editform:dataoptions:invalidMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中输入内容不合法时的提示信息。<br/>填写方法：'您输入的内容不合法。'",
    				"editor" : "text"
    			}
						,{
							"name" : "编辑页面是否隐藏",
							"ref":"editform:hidden",
							"value" : "",
							"group" : "外观",
							"help":"该属性用于设置字段是否在修改表单中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
							"editor":{
								"type":"checkbox",
								"options":{
									"on":true,
									"off":false
								}
							}
						}
						,{
							"name" : "字段附加样式（编辑表单）",
							"ref":"editform:style",
							"value" : "",
							"group" : "外观",
							"help":"该属性用于设置字段在修改表单中的宽度和高度，填写方法：例如,width:100px;height:200px",
							"editor" :"text"
						}
    		      ]
    	},{
    		type:"eType.DateTimeBox",
    		attr:[{
    				"name" : "字段显示文本<span style=\"color:Red\">(*)</span>",
    				"ref":"title",
    				"require":true,
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在表单及展示页面中的名称。<br/>填写方法：直接填入名称即可，名称的内容最好为汉字或者英文单词 ",
    				"editor" :"text"
    			},
				{
    				"name" : "字段转义",
    				"ref":"map",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段的转义处理，格式为：'map表|map字段|map值' 备注：用于表之间关联时，map表：关联的表(完整表名)，map字段：关联的字段(Class中的属性)，map值：用于显示的字段(class中的属性),",
    				"editor" :"text"
    			}
    		,
			{
				"name":"填写说明",
				"ref":"tooltip",
				"value":"",
				"help":"该字段填写说明",
				"editor":"text",
			}
    		,{
    				"name" : "展示页面是否隐藏",
    				"ref":"hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在展示页面中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "字段附加样式（新增表单）",
    				"ref":"addform:style",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在新增表单中的宽度和高度，填写方法：例如,width:100px;height:200px",
    				"editor" :"text"
    			}
				,{
    				"name" : "新增页面是否隐藏",
    				"ref":"addform:hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在新增表单中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
								,{
    				"name" : "字段是否为必填项(新增表单)",
    				"ref":"editform:dataoptions:required",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中是否应该输入。<br/>如果设置为true，指该字段应该输入。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "输入框为空的时候的提示文本（新增表单）",
    				"ref":"addform:dataoptions:missingMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中输入内容为空时的提示信息。<br/>填写方法：'您输入的内容不能为空。'",
    				"editor" : "text"
    			}
				,{
    				"name" : "输入框内容为不合法时的提示文本（新增表单）",
    				"ref":"addform:dataoptions:invalidMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中输入内容不合法时的提示信息。<br/>填写方法：'您输入的内容不合法。'",
    				"editor" : "text"
    			}
				,{
    				"name" : "字段是否为必填项(编辑表单)",
    				"ref":"addform:dataoptions:required",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中是否应该输入。<br/>如果设置为true，指该字段应该输入。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
    			,{
    				"name" : "输入框为空时的提示文本（编辑表单）",
    				"ref":"editform:dataoptions:missingMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中输入内容为空时的提示信息。<br/>填写方法：'您输入的内容不能为空。'",
    				"editor" : "text"
    			}
    			,{
    				"name" : "输入框内容为不合法时的提示文本（编辑表单）",
    				"ref":"editform:dataoptions:invalidMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中输入内容不合法时的提示信息。<br/>填写方法：'您输入的内容不合法。'",
    				"editor" : "text"
    			}
						,{
							"name" : "编辑页面是否隐藏",
							"ref":"editform:hidden",
							"value" : "",
							"group" : "外观",
							"help":"该属性用于设置字段是否在修改表单中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
							"editor":{
								"type":"checkbox",
								"options":{
									"on":true,
									"off":false
								}
							}
						}
						,{
							"name" : "字段附加样式（编辑表单）",
							"ref":"editform:style",
							"value" : "",
							"group" : "外观",
							"help":"该属性用于设置字段在修改表单中的宽度和高度，填写方法：例如,width:100px;height:200px",
							"editor" :"text"
						}
    		      ]
    	},{
    		type:"eType.Calendar",
    		attr:[{
    				"name" : "字段显示文本<span style=\"color:Red\">(*)</span>",
    				"ref":"title",
    				"require":true,
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在表单及展示页面中的名称。<br/>填写方法：直接填入名称即可，名称的内容最好为汉字或者英文单词",
    				"editor" :"text"
    			},
				{
    				"name" : "字段转义",
    				"ref":"map",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段的转义处理，格式为：'map表|map字段|map值' 备注：用于表之间关联时，map表：关联的表(完整表名)，map字段：关联的字段(Class中的属性)，map值：用于显示的字段(class中的属性),",
    				"editor" :"text"
    			}
    		,
			{
				"name":"填写说明",
				"ref":"tooltip",
				"value":"",
				"help":"该字段填写说明",
				"editor":"text",
			}
    		,{
    				"name" : "展示页面是否隐藏",
    				"ref":"hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在展示页面中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "字段附加样式（新增表单）",
    				"ref":"addform:style",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在新增表单中的宽度和高度，填写方法：例如,width:100px;height:200px",
    				"editor" :"text"
    			}
				,{
    				"name" : "新增页面是否隐藏",
    				"ref":"addform:hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在新增表单中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
								,{
    				"name" : "字段是否为必填项(新增表单)",
    				"ref":"editform:dataoptions:required",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中是否应该输入。<br/>如果设置为true，指该字段应该输入。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "输入框为空的时候的提示文本（新增表单）",
    				"ref":"addform:dataoptions:missingMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中输入内容为空时的提示信息。<br/>填写方法：'您输入的内容不能为空。'",
    				"editor" : "text"
    			}
				,{
    				"name" : "输入框内容为不合法时的提示文本（新增表单）",
    				"ref":"addform:dataoptions:invalidMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中输入内容不合法时的提示信息。<br/>填写方法：'您输入的内容不合法。'",
    				"editor" : "text"
    			}
				,{
    				"name" : "字段是否为必填项(编辑表单)",
    				"ref":"addform:dataoptions:required",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中是否应该输入。<br/>如果设置为true，指该字段应该输入。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
    			,{
    				"name" : "输入框为空时的提示文本（编辑表单）",
    				"ref":"editform:dataoptions:missingMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中输入内容为空时的提示信息。<br/>填写方法：'您输入的内容不能为空。'",
    				"editor" : "text"
    			}
    			,{
    				"name" : "输入框内容为不合法时的提示文本（编辑表单）",
    				"ref":"editform:dataoptions:invalidMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中输入内容不合法时的提示信息。<br/>填写方法：'您输入的内容不合法。'",
    				"editor" : "text"
    			}
						,{
							"name" : "编辑页面是否隐藏",
							"ref":"editform:hidden",
							"value" : "",
							"group" : "外观",
							"help":"该属性用于设置字段是否在修改表单中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
							"editor":{
								"type":"checkbox",
								"options":{
									"on":true,
									"off":false
								}
							}
						}
						,{
							"name" : "字段附加样式（编辑表单）",
							"ref":"editform:style",
							"value" : "",
							"group" : "外观",
							"help":"该属性用于设置字段在修改表单中的宽度和高度，填写方法：例如,width:100px;height:200px",
							"editor" :"text"
						}
    		      ]
    	},{
    		type:"eType.NumberSpinner",
    		attr:[{
    				"name" : "字段显示文本<span style=\"color:Red\">(*)</span>",
    				"ref":"title",
    				"require":true,
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在表单及展示页面中的名称。<br/>填写方法：直接填入名称即可，名称的内容最好为汉字或者英文单词",
    				"editor" :"text"
    			},
				{
    				"name" : "字段转义",
    				"ref":"map",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段的转义处理，格式为：'map表|map字段|map值' 备注：用于表之间关联时，map表：关联的表(完整表名)，map字段：关联的字段(Class中的属性)，map值：用于显示的字段(class中的属性),",
    				"editor" :"text"
    			}
    		,
			{
				"name":"填写说明",
				"ref":"tooltip",
				"value":"",
				"help":"该字段填写说明",
				"editor":"text",
			}
    		,{
    				"name" : "展示页面是否隐藏",
    				"ref":"hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在展示页面中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "字段附加样式（新增表单）",
    				"ref":"addform:style",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在新增表单中的宽度和高度，填写方法：例如,width:100px;height:200px",
    				"editor" :"text"
    			}
				,{
    				"name" : "新增页面是否隐藏",
    				"ref":"addform:hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在新增表单中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
	          				"name" : "字段允许最大值（新增表单）",
	          				"ref":"addform:dataoptions:max",
	          				"value" : "",
	          				"group" : "数据",
							"help":"该属性用于设置字段在新增表单中填写的最大值为多少。<br/>填写方法：100",
	          				"editor" : "text"
	          			}
						,{
	          				"name" : "字段允许最小值（新增表单）",
	          				"ref":"addform:dataoptions:min",
	          				"value" : "",
	          				"group" : "数据",
							"help":"该属性用于设置字段在新增表单中填写的最小值为多少。<br/>填写方法：10",
	          				"editor" : "text"
	          			}
						,{
							"name" : "用户是否可以直接输入值（新增表单）",
							"ref":"addform:dataoptions:editable",
							"value" : "",
							"group" : "数据",
							"help":"该属性用于设置字段在新增表单中是否可以直接输入值。<br/>如果设置为true，该字段就可以直接输入值。<br/>填写方法：直接选中单选框即为true",
							"editor":{
								"type":"checkbox",
								"options":{
									"on":true,
									"off":false
								}
							}
						}
						,{
							"name" : "点击spinner按钮的时候增加的值（新增表单）",
							"ref" : "addform:dataoptions:increment",
							"value":"",
							"group" : "数据",
							"help":"该属性用于设置字段在新增表单中点击spinner按钮的时候增加的值。<br/>填写方法：10",
							"editor" : "text"
						}
										,{
    				"name" : "字段是否为必填项(新增表单)",
    				"ref":"editform:dataoptions:required",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中是否应该输入。<br/>如果设置为true，指该字段应该输入。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "输入框为空的时候的提示文本（新增表单）",
    				"ref":"addform:dataoptions:missingMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中输入内容为空时的提示信息。<br/>填写方法：'您输入的内容不能为空。'",
    				"editor" : "text"
    			}
				,{
    				"name" : "输入框内容为不合法时的提示文本（新增表单）",
    				"ref":"addform:dataoptions:invalidMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中输入内容不合法时的提示信息。<br/>填写方法：'您输入的内容不合法。'",
    				"editor" : "text"
    			}
				,{
    				"name" : "字段是否为必填项(编辑表单)",
    				"ref":"addform:dataoptions:required",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中是否应该输入。<br/>如果设置为true，指该字段应该输入。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
    			,{
    				"name" : "输入框为空时的提示文本（编辑表单）",
    				"ref":"editform:dataoptions:missingMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中输入内容为空时的提示信息。<br/>填写方法：'您输入的内容不能为空。'",
    				"editor" : "text"
    			}
    			,{
    				"name" : "输入框内容为不合法时的提示文本（编辑表单）",
    				"ref":"editform:dataoptions:invalidMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中输入内容不合法时的提示信息。<br/>填写方法：'您输入的内容不合法。'",
    				"editor" : "text"
    			}
						,{
							"name" : "编辑页面是否隐藏",
							"ref":"editform:hidden",
							"value" : "",
							"group" : "外观",
							"help":"该属性用于设置字段是否在修改表单中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
							"editor":{
								"type":"checkbox",
								"options":{
									"on":true,
									"off":false
								}
							}
						}
	          			,{
	          				"name" : "字段允许最大值（编辑表单）",
	          				"ref":"editform:dataoptions:max",
	          				"value" : "",
	          				"group" : "数据",
							"help":"该属性用于设置字段在修改表单中填写的最大值为多少。<br/>填写方法：100",
	          				"editor" : "text"
	          			}
	          			
	          			,{
	          				"name" : "字段允许最小值（编辑表单）",
	          				"ref":"editform:dataoptions:min",
	          				"value" : "",
	          				"group" : "数据",
							"help":"该属性用于设置字段在修改表单中填写的最小值为多少。<br/>填写方法：10",
	          				"editor" : "text"
	          			}
	          			,{
							"name" : "用户是否可以直接输入值（编辑表单）",
							"ref":"editform:dataoptions:editable",
							"value" : "",
							"group" : "数据",
							"help":"该属性用于设置字段在修改表单中是否可以直接输入值。<br/>如果设置为true，该字段就可以直接输入值。<br/>填写方法：直接选中单选框即为true",
							"editor":{
								"type":"checkbox",
								"options":{
									"on":true,
									"off":false
								}
							}
						}
						,{
							"name" : "点击spinner按钮的时候增加的值（编辑表单）",
							"ref" : "editform:dataoptions:increment",
							"value":"",
							"group" : "数据",
							"help":"该属性用于设置字段在修改表单中点击spinner按钮的时候增加的值。<br/>填写方法：10",
							"editor" : "text"
						}
						,{
							"name" : "字段附加样式（编辑表单）",
							"ref":"editform:style",
							"value" : "",
							"group" : "外观",
							"help":"该属性用于设置字段在修改表单中的宽度和高度，填写方法：例如,width:100px;height:200px",
							"editor" :"text"
						}
    		      ]
    	},{
    		type:"eType.TimeSpinner",
    		attr:[{
    				"name" : "字段显示文本<span style=\"color:Red\">(*)</span>",
    				"ref":"title",
    				"require":true,
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在表单及展示页面中的名称。<br/>填写方法：直接填入名称即可，名称的内容最好为汉字或者英文单词",
    				"editor" :"text"
    			},
				{
    				"name" : "字段转义",
    				"ref":"map",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段的转义处理，格式为：'map表|map字段|map值' 备注：用于表之间关联时，map表：关联的表(完整表名)，map字段：关联的字段(Class中的属性)，map值：用于显示的字段(class中的属性),",
    				"editor" :"text"
    			}
    		,
			{
				"name":"填写说明",
				"ref":"tooltip",
				"value":"",
				"help":"该字段填写说明",
				"editor":"text",
			}
    		,{
    				"name" : "展示页面是否隐藏",
    				"ref":"hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在展示页面中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "字段附加样式（新增表单）",
    				"ref":"addform:style",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在新增表单中的宽度和高度，填写方法：例如,width:100px;height:200px",
    				"editor" :"text"
    			}
				,{
    				"name" : "新增页面是否隐藏",
    				"ref":"addform:hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在新增表单中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
	          				"name" : "字段允许最大值（新增表单）",
	          				"ref":"addform:dataoptions:max",
	          				"value" : "",
	          				"group" : "数据",
							"help":"该属性用于设置字段在新增表单中填写的最大值为多少。<br/>填写方法：100",
	          				"editor" : "text"
	          			}
						,{
	          				"name" : "字段允许最小值（新增表单）",
	          				"ref":"addform:dataoptions:min",
	          				"value" : "",
	          				"group" : "数据",
							"help":"该属性用于设置字段在新增表单中填写的最小值为多少。<br/>填写方法：10",
	          				"editor" : "text"
	          			}
						,{
							"name" : "用户是否可以直接输入值（新增表单）",
							"ref":"addform:dataoptions:editable",
							"value" : "",
							"group" : "数据",
							"help":"该属性用于设置字段在新增表单中是否可以直接输入值。<br/>如果设置为true，该字段就可以直接输入值。<br/>填写方法：直接选中单选框即为true",
							"editor":{
								"type":"checkbox",
								"options":{
									"on":true,
									"off":false
								}
							}
						}
						,{
							"name" : "点击spinner按钮的时候增加的值（新增表单）",
							"ref" : "addform:dataoptions:increment",
							"value":"",
							"group" : "数据",
							"help":"该属性用于设置字段在新增表单中点击spinner按钮的时候增加的值。<br/>填写方法：10",
							"editor" : "text"
						}
						,{
							"name" : "是否显示秒信息（新增表单）",
							"ref":"addform:dataoptions:showSeconds",
							"value" : "",
							"group" : "数据",
							"help":"该属性用于设置字段在新增表单中是否显示秒信息。<br/>如果设置为true，则该字段显示秒信息。<br/>填写方法：直接选中单选框即为true",
							"editor":{
								"type":"checkbox",
								"options":{
									"on":true,
									"off":false
								}
							}
						}
										,{
    				"name" : "字段是否为必填项(新增表单)",
    				"ref":"editform:dataoptions:required",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中是否应该输入。<br/>如果设置为true，指该字段应该输入。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "输入框为空的时候的提示文本（新增表单）",
    				"ref":"addform:dataoptions:missingMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中输入内容为空时的提示信息。<br/>填写方法：'您输入的内容不能为空。'",
    				"editor" : "text"
    			}
				,{
    				"name" : "输入框内容为不合法时的提示文本（新增表单）",
    				"ref":"addform:dataoptions:invalidMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中输入内容不合法时的提示信息。<br/>填写方法：'您输入的内容不合法。'",
    				"editor" : "text"
    			}
				,{
    				"name" : "字段是否为必填项(编辑表单)",
    				"ref":"addform:dataoptions:required",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中是否应该输入。<br/>如果设置为true，指该字段应该输入。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
    			,{
    				"name" : "输入框为空时的提示文本（编辑表单）",
    				"ref":"editform:dataoptions:missingMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中输入内容为空时的提示信息。<br/>填写方法：'您输入的内容不能为空。'",
    				"editor" : "text"
    			}
    			,{
    				"name" : "输入框内容为不合法时的提示文本（编辑表单）",
    				"ref":"editform:dataoptions:invalidMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中输入内容不合法时的提示信息。<br/>填写方法：'您输入的内容不合法。'",
    				"editor" : "text"
    			}
						,{
							"name" : "编辑页面是否隐藏",
							"ref":"editform:hidden",
							"value" : "",
							"group" : "外观",
							"help":"该属性用于设置字段是否在修改表单中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
							"editor":{
								"type":"checkbox",
								"options":{
									"on":true,
									"off":false
								}
							}
						}
	          			,{
	          				"name" : "字段允许最大值（编辑表单）",
	          				"ref":"editform:dataoptions:max",
	          				"value" : "",
	          				"group" : "数据",
							"help":"该属性用于设置字段在修改表单中填写的最大值为多少。<br/>填写方法：100",
	          				"editor" : "text"
	          			}
	          			
	          			,{
	          				"name" : "字段允许最小值（编辑表单）",
	          				"ref":"editform:dataoptions:min",
	          				"value" : "",
	          				"group" : "数据",
							"help":"该属性用于设置字段在修改表单中填写的最小值为多少。<br/>填写方法：10",
	          				"editor" : "text"
	          			}
	          			,{
							"name" : "用户是否可以直接输入值（编辑表单）",
							"ref":"editform:dataoptions:editable",
							"value" : "",
							"group" : "数据",
							"help":"该属性用于设置字段在修改表单中是否可以直接输入值。<br/>如果设置为true，该字段就可以直接输入值。<br/>填写方法：直接选中单选框即为true",
							"editor":{
								"type":"checkbox",
								"options":{
									"on":true,
									"off":false
								}
							}
						}
						,{
							"name" : "点击spinner按钮的时候增加的值（编辑表单）",
							"ref" : "editform:dataoptions:increment",
							"value":"",
							"group" : "数据",
							"help":"该属性用于设置字段在修改表单中点击spinner按钮的时候增加的值。<br/>填写方法：10",
							"editor" : "text"
						}
						,{
							"name" : "字段附加样式（编辑表单）",
							"ref":"editform:style",
							"value" : "",
							"group" : "外观",
							"help":"该属性用于设置字段在修改表单中的宽度和高度，填写方法：例如,width:100px;height:200px",
							"editor" :"text"
						}
						,{
							"name" : "是否显示秒信息（编辑表单）",
							"ref":"editform:dataoptions:showSeconds",
							"value" : "",
							"group" : "数据",
							"help":"该属性用于设置字段在修改表单中是否显示秒信息。<br/>如果设置为true，则该字段显示秒信息。<br/>填写方法：直接选中单选框即为true",
							"editor":{
								"type":"checkbox",
								"options":{
									"on":true,
									"off":false
								}
							}
						}
    		      ]
    	},{
    		type:"eType.Slider",
    		attr:[{
    				"name" : "字段显示文本<span style=\"color:Red\">(*)</span>",
    				"ref":"title",
    				"require":true,
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在表单及展示页面中的名称。<br/>填写方法：直接填入名称即可，名称的内容最好为汉字或者英文单词",
    				"editor" :"text"
    			},
				{
    				"name" : "字段转义",
    				"ref":"map",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段的转义处理，格式为：'map表|map字段|map值' 备注：用于表之间关联时，map表：关联的表(完整表名)，map字段：关联的字段(Class中的属性)，map值：用于显示的字段(class中的属性),",
    				"editor" :"text"
    			}
    		,
			{
				"name":"填写说明",
				"ref":"tooltip",
				"value":"",
				"help":"该字段填写说明",
				"editor":"text",
			}
    		,{
    				"name" : "展示页面是否隐藏",
    				"ref":"hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在展示页面中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "字段附加样式（新增表单）",
    				"ref":"addform:style",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在新增表单中的宽度和高度，填写方法：例如,width:100px;height:200px",
    				"editor" :"text"
    			}
				,{
    				"name" : "新增页面是否隐藏",
    				"ref":"addform:hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在新增表单中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "设置滑动条方向（新增表单）",
    				"ref":"addform:dataoptions:mode",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中滑动条的方向。<br/>填写方法：h或v<br/>h为水平，v为垂直",
    				"editor" : "text"
    			}
				,{
    				"name" : "是否显示值信息提示（新增表单）",
    				"ref":"addform:dataoptions:showTip",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中是否显示值信息提示。<br/>如果设置为true，显示值信息提示。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "设置滑动条规则（新增表单）<span style=\"color:Red\">(*)</span>",
    				"ref":"addform:dataoptions:rule",
    				"require":true,
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中滑动条规则。<br/>填写方法：[0,10,20,30,40,50]",
    				"editor" : "text"
    			}
								,{
    				"name" : "字段是否为必填项(新增表单)",
    				"ref":"editform:dataoptions:required",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中是否应该输入。<br/>如果设置为true，指该字段应该输入。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "输入框为空的时候的提示文本（新增表单）",
    				"ref":"addform:dataoptions:missingMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中输入内容为空时的提示信息。<br/>填写方法：'您输入的内容不能为空。'",
    				"editor" : "text"
    			}
				,{
    				"name" : "输入框内容为不合法时的提示文本（新增表单）",
    				"ref":"addform:dataoptions:invalidMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中输入内容不合法时的提示信息。<br/>填写方法：'您输入的内容不合法。'",
    				"editor" : "text"
    			}
				,{
    				"name" : "字段是否为必填项(编辑表单)",
    				"ref":"addform:dataoptions:required",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中是否应该输入。<br/>如果设置为true，指该字段应该输入。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
    			,{
    				"name" : "输入框为空时的提示文本（编辑表单）",
    				"ref":"editform:dataoptions:missingMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中输入内容为空时的提示信息。<br/>填写方法：'您输入的内容不能为空。'",
    				"editor" : "text"
    			}
    			,{
    				"name" : "输入框内容为不合法时的提示文本（编辑表单）",
    				"ref":"editform:dataoptions:invalidMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中输入内容不合法时的提示信息。<br/>填写方法：'您输入的内容不合法。'",
    				"editor" : "text"
    			}
				,{
							"name" : "编辑页面是否隐藏",
							"ref":"editform:hidden",
							"value" : "",
							"group" : "外观",
							"help":"该属性用于设置字段是否在修改表单中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
							"editor":{
								"type":"checkbox",
								"options":{
									"on":true,
									"off":false
								}
							}
						}
				,{
    				"name" : "设置滑动条方向（编辑表单）",
    				"ref":"editform:dataoptions:mode",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中滑动条的方向。<br/>填写方法：'h'或'v'<br/>h为水平，v为垂直",
    				"editor" : "text"
    			}
				,{
    				"name" : "是否显示值信息提示（编辑表单）",
    				"ref":"editform:dataoptions:showTip",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中是否显示值信息提示。<br/>如果设置为true，显示值信息提示。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "设置滑动条规则（编辑表单）<span style=\"color:Red\">(*)</span>",
    				"ref":"editform:dataoptions:rule",
    				"require":true,
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中滑动条规则。<br/>填写方法：[0,10,20,30,40,50]",
    				"editor" : "text"
    			}
				,{
							"name" : "字段附加样式（编辑表单）",
							"ref":"editform:style",
							"value" : "",
							"group" : "外观",
							"help":"该属性用于设置字段在修改表单中的宽度和高度，填写方法：例如,width:100px;height:200px",
							"editor" :"text"
						}
    		]
    	},{
    		type:"eType.HtmlEdit",
    		attr:[{
    				"name" : "字段显示文本<span style=\"color:Red\">(*)</span>",
    				"ref":"title",
    				"require":true,
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在表单及展示页面中的名称。<br/>填写方法：直接填入名称即可，名称的内容最好为汉字或者英文单词",
    				"editor" :"text"
    			},
				{
    				"name" : "字段转义",
    				"ref":"map",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段的转义处理，格式为：'map表|map字段|map值' 备注：用于表之间关联时，map表：关联的表(完整表名)，map字段：关联的字段(Class中的属性)，map值：用于显示的字段(class中的属性),",
    				"editor" :"text"
    			}
    		,
			{
				"name":"填写说明",
				"ref":"tooltip",
				"value":"",
				"help":"该字段填写说明",
				"editor":"text",
			}
    		,{
    				"name" : "展示页面是否隐藏",
    				"ref":"hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在展示页面中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "字段附加样式（新增表单）",
    				"ref":"addform:style",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在新增表单中的宽度和高度，填写方法：例如,width:100px;height:200px",
    				"editor" :"text"
    			}
				,{
    				"name" : "新增页面是否隐藏",
    				"ref":"addform:hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在新增表单中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
								,{
    				"name" : "字段是否为必填项(新增表单)",
    				"ref":"editform:dataoptions:required",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中是否应该输入。<br/>如果设置为true，指该字段应该输入。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "输入框为空的时候的提示文本（新增表单）",
    				"ref":"addform:dataoptions:missingMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中输入内容为空时的提示信息。<br/>填写方法：'您输入的内容不能为空。'",
    				"editor" : "text"
    			}
				,{
    				"name" : "输入框内容为不合法时的提示文本（新增表单）",
    				"ref":"addform:dataoptions:invalidMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中输入内容不合法时的提示信息。<br/>填写方法：'您输入的内容不合法。'",
    				"editor" : "text"
    			}
				,{
    				"name" : "字段是否为必填项(编辑表单)",
    				"ref":"addform:dataoptions:required",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中是否应该输入。<br/>如果设置为true，指该字段应该输入。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
    			,{
    				"name" : "输入框为空时的提示文本（编辑表单）",
    				"ref":"editform:dataoptions:missingMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中输入内容为空时的提示信息。<br/>填写方法：'您输入的内容不能为空。'",
    				"editor" : "text"
    			}
    			,{
    				"name" : "输入框内容为不合法时的提示文本（编辑表单）",
    				"ref":"editform:dataoptions:invalidMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中输入内容不合法时的提示信息。<br/>填写方法：'您输入的内容不合法。'",
    				"editor" : "text"
    			}
						,{
							"name" : "编辑页面是否隐藏",
							"ref":"editform:hidden",
							"value" : "",
							"group" : "外观",
							"help":"该属性用于设置字段是否在修改表单中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
							"editor":{
								"type":"checkbox",
								"options":{
									"on":true,
									"off":false
								}
							}
						}
						,{
							"name" : "字段附加样式（编辑表单）",
							"ref":"editform:style",
							"value" : "",
							"group" : "外观",
							"help":"该属性用于设置字段在修改表单中的宽度和高度，填写方法：例如,width:100px;height:200px",
							"editor" :"text"
						}
    		      ]
    	},{
    		type:"eType.DataGrid",
    		attr:[{
    				"name" : "字段显示文本<span style=\"color:Red\">(*)</span>",
    				"ref":"title",
    				"require":true,
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在表单及展示页面中的名称。<br/>填写方法：直接填入名称即可，名称的内容最好为汉字或者英文单词",
    				"editor" :"text"
    			},
				{
    				"name" : "字段转义",
    				"ref":"map",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段的转义处理，格式为：'map表|map字段|map值' 备注：用于表之间关联时，map表：关联的表(完整表名)，map字段：关联的字段(Class中的属性)，map值：用于显示的字段(class中的属性),",
    				"editor" :"text"
    			}
    		,
			{
				"name":"填写说明",
				"ref":"tooltip",
				"value":"",
				"help":"该字段填写说明",
				"editor":"text",
			}
    		,{
    				"name" : "展示页面是否隐藏",
    				"ref":"hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在展示页面中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "字段附加样式（新增表单）",
    				"ref":"addform:style",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在新增表单中的宽度和高度，填写方法：例如,width:100px;height:200px",
    				"editor" :"text"
    			}
				,{
    				"name" : "新增页面是否隐藏",
    				"ref":"addform:hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在新增表单中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
					"name" : "数据获取路径（新增表单）<span style=\"color:Red\">(*)</span>",
					"ref":"addform:dataoptions:url",
					"require":true,
					"value" : "",
					"group" : "数据",
					"help":"该属性用于设置字段在新增表单中获取值的路径。<br/>填写方法：/smarte/sys/secActSysSbdutyPlan_ComboBox",
					"editor" : "text"
				}
				,{
    				"name" : "页面数据数组（新增表单）<span style=\"color:Red\">(*)</span>",
    				"ref":"addform:dataoptions:columns",
    				"require":true,
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中的也面数据数组。<br/>填写方法：'testcolumns'",
    				"editor" : "text"
    			}
				,{
					"name" : "数据方法调用（新增表单）",
					"ref":"addform:dataoptions:data",
					"value" : "",
					"group" : "数据",
					"help":"该属性用于设置字段在新增表单中获取值的方法。<br/>填写方法：\\\\\\\"+getYearComboBoxData()+\\\\\\\"",
					"editor" : "text"
				}
								,{
    				"name" : "字段是否为必填项(新增表单)",
    				"ref":"editform:dataoptions:required",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中是否应该输入。<br/>如果设置为true，指该字段应该输入。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "输入框为空的时候的提示文本（新增表单）",
    				"ref":"addform:dataoptions:missingMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中输入内容为空时的提示信息。<br/>填写方法：'您输入的内容不能为空。'",
    				"editor" : "text"
    			}
				,{
    				"name" : "输入框内容为不合法时的提示文本（新增表单）",
    				"ref":"addform:dataoptions:invalidMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中输入内容不合法时的提示信息。<br/>填写方法：'您输入的内容不合法。'",
    				"editor" : "text"
    			}
				,{
    				"name" : "字段是否为必填项(编辑表单)",
    				"ref":"addform:dataoptions:required",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中是否应该输入。<br/>如果设置为true，指该字段应该输入。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
    			,{
    				"name" : "输入框为空时的提示文本（编辑表单）",
    				"ref":"editform:dataoptions:missingMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中输入内容为空时的提示信息。<br/>填写方法：'您输入的内容不能为空。'",
    				"editor" : "text"
    			}
    			,{
    				"name" : "输入框内容为不合法时的提示文本（编辑表单）",
    				"ref":"editform:dataoptions:invalidMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中输入内容不合法时的提示信息。<br/>填写方法：'您输入的内容不合法。'",
    				"editor" : "text"
    			}
				,{
    				"name" : "字段附加样式（编辑表单）",
    				"ref":"editform:style",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在修改表单中的宽度和高度，填写方法：例如,width:100px;height:200px",
    				"editor" :"text"
    			}
				,{
    				"name" : "编辑页面是否隐藏",
    				"ref":"editform:hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在修改表单中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
					"name" : "数据方法调用（编辑表单）",
					"ref":"editform:dataoptions:data",
					"value" : "",
					"group" : "数据",
					"help":"该属性用于设置字段在修改表单中获取值的方法。<br/>填写方法：\\\\\\\"+getYearComboBoxData()+\\\\\\\"",
					"editor" : "text"
				}
				,{
					"name" : "数据获取路径（编辑表单）<span style=\"color:Red\">(*)</span>",
					"ref":"editform:dataoptions:url",
					"require":true,
					"value" : "",
					"group" : "数据",
					"help":"该属性用于设置字段在修改表单中获取值的路径。<br/>填写方法：/smarte/sys/secActSysSbdutyPlan_ComboBox",
					"editor" : "text"
				}
				,{
    				"name" : "页面数据数组（编辑表单）<span style=\"color:Red\">(*)</span>",
    				"ref":"editform:dataoptions:columns",
    				"require":true,
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中的也面数据数组。<br/>填写方法：'testcolumns'",
    				"editor" : "text"
    			}
    		]
    	},{
    		type:"eType.TreeGrid",
    		attr:[{
    				"name" : "字段显示文本<span style=\"color:Red\">(*)</span>",
    				"ref":"title",
    				"require":true,
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在表单及展示页面中的名称。<br/>填写方法：直接填入名称即可，名称的内容最好为汉字或者英文单词",
    				"editor" :"text"
    			},
				{
    				"name" : "字段转义",
    				"ref":"map",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段的转义处理，格式为：'map表|map字段|map值' 备注：用于表之间关联时，map表：关联的表(完整表名)，map字段：关联的字段(Class中的属性)，map值：用于显示的字段(class中的属性),",
    				"editor" :"text"
    			}
    		,
			{
				"name":"填写说明",
				"ref":"tooltip",
				"value":"",
				"help":"该字段填写说明",
				"editor":"text",
			}
    		,{
    				"name" : "展示页面是否隐藏",
    				"ref":"hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在展示页面中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "字段附加样式（新增表单）",
    				"ref":"addform:style",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在新增表单中的宽度和高度，填写方法：例如,width:100px;height:200px",
    				"editor" :"text"
    			}
				,{
    				"name" : "新增页面是否隐藏",
    				"ref":"addform:hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在新增表单中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
					"name" : "数据获取路径（新增表单）",
					"ref":"addform:dataoptions:url",
					"value" : "",
					"group" : "数据",
					"help":"该属性用于设置字段在新增表单中获取值的路径。<br/>填写方法：/smarte/sys/secActSysSbdutyPlan_ComboBox",
					"editor" : "text"
				}
				,{
    				"name" : "页面数据数组（新增表单）",
    				"ref":"addform:dataoptions:columns",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中的也面数据数组。<br/>填写方法：'testcolumns'",
    				"editor" : "text"
    			}
				,{
					"name" : "数据方法调用（新增表单）",
					"ref":"addform:dataoptions:data",
					"value" : "",
					"group" : "数据",
					"help":"该属性用于设置字段在新增表单中获取值的方法。<br/>填写方法：\\\\\\\"+getYearComboBoxData()+\\\\\\\"",
					"editor" : "text"
				}
								,{
    				"name" : "字段是否为必填项(新增表单)",
    				"ref":"editform:dataoptions:required",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中是否应该输入。<br/>如果设置为true，指该字段应该输入。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "输入框为空的时候的提示文本（新增表单）",
    				"ref":"addform:dataoptions:missingMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中输入内容为空时的提示信息。<br/>填写方法：'您输入的内容不能为空。'",
    				"editor" : "text"
    			}
				,{
    				"name" : "输入框内容为不合法时的提示文本（新增表单）",
    				"ref":"addform:dataoptions:invalidMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中输入内容不合法时的提示信息。<br/>填写方法：'您输入的内容不合法。'",
    				"editor" : "text"
    			}
				,{
    				"name" : "字段是否为必填项(编辑表单)",
    				"ref":"addform:dataoptions:required",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中是否应该输入。<br/>如果设置为true，指该字段应该输入。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
    			,{
    				"name" : "输入框为空时的提示文本（编辑表单）",
    				"ref":"editform:dataoptions:missingMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中输入内容为空时的提示信息。<br/>填写方法：'您输入的内容不能为空。'",
    				"editor" : "text"
    			}
    			,{
    				"name" : "输入框内容为不合法时的提示文本（编辑表单）",
    				"ref":"editform:dataoptions:invalidMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中输入内容不合法时的提示信息。<br/>填写方法：'您输入的内容不合法。'",
    				"editor" : "text"
    			}
				,{
    				"name" : "字段附加样式（编辑表单）",
    				"ref":"editform:style",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在修改表单中的宽度和高度，填写方法：例如,width:100px;height:200px",
    				"editor" :"text"
    			}
				,{
    				"name" : "编辑页面是否隐藏",
    				"ref":"editform:hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在修改表单中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
					"name" : "数据方法调用（编辑表单）",
					"ref":"editform:dataoptions:data",
					"value" : "",
					"group" : "数据",
					"help":"该属性用于设置字段在修改表单中获取值的方法。<br/>填写方法：\\\\\\\"+getYearComboBoxData()+\\\\\\\"",
					"editor" : "text"
				}
				,{
					"name" : "数据获取路径（编辑表单）",
					"ref":"editform:dataoptions:url",
					"value" : "",
					"group" : "数据",
					"help":"该属性用于设置字段在修改表单中获取值的路径。<br/>填写方法：/smarte/sys/secActSysSbdutyPlan_ComboBox",
					"editor" : "text"
				}
				,{
    				"name" : "页面数据数组（编辑表单）",
    				"ref":"editform:dataoptions:columns",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中的也面数据数组。<br/>填写方法：'testcolumns'",
    				"editor" : "text"
    			}
    		]
    	},{
    		type:"eType.TextArea",
    		attr:[{
    				"name" : "字段显示文本<span style=\"color:Red\">(*)</span>",
    				"ref":"title",
    				"require":true,
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在表单及展示页面中的名称。<br/>填写方法：直接填入名称即可，名称的内容最好为汉字或者英文单词",
    				"editor" :"text"
    			},
				{
    				"name" : "字段转义",
    				"ref":"map",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段的转义处理，格式为：'map表|map字段|map值' 备注：用于表之间关联时，map表：关联的表(完整表名)，map字段：关联的字段(Class中的属性)，map值：用于显示的字段(class中的属性),",
    				"editor" :"text"
    			}
    		,
			{
				"name":"填写说明",
				"ref":"tooltip",
				"value":"",
				"help":"该字段填写说明",
				"editor":"text",
			}
    		,{
    				"name" : "展示页面是否隐藏",
    				"ref":"hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在展示页面中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "字段附加样式（新增表单）",
    				"ref":"addform:style",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在新增表单中的宽度和高度，填写方法：例如,width:100px;height:200px",
    				"editor" :"text"
    			}
				,{
    				"name" : "新增页面是否隐藏",
    				"ref":"addform:hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在新增表单中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,
				{
					"name" : "textarea输入框（新增表单）<span style=\"color:Red\">(*)</span>",
    				"ref":"addform:attribute",
    				"require":true,
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在新增表单中文本框的大小，填写方法：例如,'row=\\\"4\\\" cols=\\\"10\\\"'",
    				"editor" :"text"
				}
								,{
    				"name" : "字段是否为必填项(新增表单)",
    				"ref":"editform:dataoptions:required",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中是否应该输入。<br/>如果设置为true，指该字段应该输入。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "输入框为空的时候的提示文本（新增表单）",
    				"ref":"addform:dataoptions:missingMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中输入内容为空时的提示信息。<br/>填写方法：'您输入的内容不能为空。'",
    				"editor" : "text"
    			}
				,{
    				"name" : "输入框内容为不合法时的提示文本（新增表单）",
    				"ref":"addform:dataoptions:invalidMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中输入内容不合法时的提示信息。<br/>填写方法：'您输入的内容不合法。'",
    				"editor" : "text"
    			}
				,{
    				"name" : "字段验证类型（新增表单）",
    				"ref":"addform:dataoptions:validType",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中输入内容的验证类型。<br/>如果你输入'email'则该字段的输入内容只能为邮箱的格式。<br/>填写方法：'email'",
    				"editor" : "text"
    			}
				,{
    				"name" : "字段是否为必填项(编辑表单)",
    				"ref":"addform:dataoptions:required",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中是否应该输入。<br/>如果设置为true，指该字段应该输入。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "字段验证类型（编辑表单）",
    				"ref":"editform:dataoptions:validType",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中输入内容的验证类型。<br/>如果你输入'email'则该字段的输入内容只能为邮箱的格式。<br/>填写方法：'email'",
    				"editor" : "text"
    			} 
				
    			,{
    				"name" : "输入框为空时的提示文本（编辑表单）",
    				"ref":"editform:dataoptions:missingMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中输入内容为空时的提示信息。<br/>填写方法：'您输入的内容不能为空。'",
    				"editor" : "text"
    			}
    			,{
    				"name" : "输入框内容为不合法时的提示文本（编辑表单）",
    				"ref":"editform:dataoptions:invalidMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中输入内容不合法时的提示信息。<br/>填写方法：'您输入的内容不合法。'",
    				"editor" : "text"
    			}
				,{
    				"name" : "字段附加样式（编辑表单）",
    				"ref":"editform:style",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在修改表单中的宽度和高度，填写方法：例如,width:100px;height:200px",
    				"editor" :"text"
    			}
				,{
    				"name" : "编辑页面是否隐藏",
    				"ref":"editform:hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在修改表单中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			},
				{
					"name" : "textarea输入框（编辑表单）<span style=\"color:Red\">(*)</span>",
    				"ref":"editform:attribute",
    				"require":true,
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在修改表单中文本框的大小，填写方法：例如,'row=\\\"4\\\" cols=\\\"10\\\"'",
    				"editor" :"text"
				}
    		]
    	},{
    		type:"eType.SelUsersInput",
    		attr:[{
    				"name" : "字段显示文本<span style=\"color:Red\">(*)</span>",
    				"ref":"title",
    				"require":true,
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在表单及展示页面中的名称。<br/>填写方法：直接填入名称即可，名称的内容最好为汉子或者英文单词 ",
    				"editor" :"text"
    			},
				{
    				"name" : "字段转义",
    				"ref":"map",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段的转义处理，格式为：'map表|map字段|map值' 备注：用于表之间关联时，map表：关联的表(完整表名)，map字段：关联的字段(Class中的属性)，map值：用于显示的字段(class中的属性),",
    				"editor" :"text"
    			}
    		,
			{
				"name":"填写说明",
				"ref":"tooltip",
				"value":"",
				"help":"该字段填写说明",
				"editor":"text",
			}
    		,{
    				"name" : "展示页面是否隐藏",
    				"ref":"hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在展示页面中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "字段附加样式（新增表单）",
    				"ref":"addform:style",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在新增表单中的宽度和高度，填写方法：例如,background-color: #EFF7FF",
    				"editor" :"text"
    			}
    			,{
    				"name" : "新增页面是否隐藏",
    				"ref":"addform:hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在新增表单中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
    			,{
    				"name" : "编辑页面是否隐藏",
    				"ref":"editform:hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在修改表单中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "字段附加样式（编辑表单）",
    				"ref":"editform:style",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在修改表单中的宽度和高度，填写方法：例如,background-color: #EFF7FF",
    				"editor" :"text"
    			}
    		]
    	},{
    		type:"eType.Checkbox",
    		attr:[{
    				"name" : "字段显示文本<span style=\"color:Red\">(*)</span>",
    				"ref":"title",
    				"require":true,
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在表单及展示页面中的名称。<br/>填写方法：直接填入名称即可，名称的内容最好为汉子或者英文单词 ",
    				"editor" :"text"
    			},
				{
    				"name" : "字段转义",
    				"ref":"map",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段的转义处理，格式为：'map表|map字段|map值' 备注：用于表之间关联时，map表：关联的表(完整表名)，map字段：关联的字段(Class中的属性)，map值：用于显示的字段(class中的属性),",
    				"editor" :"text"
    			}
    		,
			{
				"name":"填写说明",
				"ref":"tooltip",
				"value":"",
				"help":"该字段填写说明",
				"editor":"text",
			}
    		,{
    				"name" : "展示页面是否隐藏",
    				"ref":"hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在展示页面中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "字段附加样式（新增表单）",
    				"ref":"addform:style",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在新增表单中的宽度和高度，填写方法：例如,width:100px;height:200px",
    				"editor" :"text"
    			}
    			,{
    				"name" : "新增页面是否隐藏",
    				"ref":"addform:hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在新增表单中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
    			,{
    				"name" : "编辑页面是否隐藏",
    				"ref":"editform:hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在修改表单中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "字段附加样式（编辑表单）",
    				"ref":"editform:style",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在修改表单中的宽度和高度，填写方法：例如,width:100px;height:200px",
    				"editor" :"text"
    			}
    			 ]
    	},
//prop new
    	{type:"eType.Chart",attr:[{
    				"name" : "字段显示文本<span style=\"color:Red\">(*)</span>",
    				"ref":"title",
    				"require":true,
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在表单及展示页面中的名称。<br/>填写方法：直接填入名称即可，名称的内容最好为汉字或者英文单词",
    				"editor" :"text"
    			},
				{
    				"name" : "字段转义",
    				"ref":"map",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段的转义处理，格式为：'map表|map字段|map值' 备注：用于表之间关联时，map表：关联的表(完整表名)，map字段：关联的字段(Class中的属性)，map值：用于显示的字段(class中的属性),",
    				"editor" :"text"
    			}
    		,
			{
				"name":"填写说明",
				"ref":"tooltip",
				"value":"",
				"help":"该字段填写说明",
				"editor":"text",
			}
    		,{
    				"name" : "展示页面是否隐藏",
    				"ref":"hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在展示页面中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "字段附加样式（新增表单）",
    				"ref":"addform:style",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在新增表单中的宽度和高度，填写方法：例如,width:100px;height:200px",
    				"editor" :"text"
    			}
				,{
    				"name" : "新增页面是否隐藏",
    				"ref":"addform:hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在新增表单中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
					"name" : "数据获取路径（新增表单）<span style=\"color:Red\">(*)</span>",
					"ref":"addform:dataoptions:url",
					"require":true,
					"value" : "",
					"group" : "数据",
					"help":"该属性用于设置字段在新增表单中获取值的路径。<br/>填写方法：/smarte/sys/secActSysSbdutyPlan_ComboBox",
					"editor" : "text"
				}
				,{
    				"name" : "字段是否为必填项(新增表单)",
    				"ref":"editform:dataoptions:required",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中是否应该输入。<br/>如果设置为true，指该字段应该输入。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "字段附加样式（编辑表单）",
    				"ref":"editform:style",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在修改表单中的宽度和高度，填写方法：例如,width:100px;height:200px",
    				"editor" :"text"
    			}
				,{
    				"name" : "编辑页面是否隐藏",
    				"ref":"editform:hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在修改表单中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
					"name" : "数据获取路径（编辑表单）<span style=\"color:Red\">(*)</span>",
					"ref":"editform:dataoptions:url",
					"require":true,
					"value" : "",
					"group" : "数据",
					"help":"该属性用于设置字段在修改表单中获取值的路径。<br/>填写方法：/smarte/sys/secActSysSbdutyPlan_ComboBox",
					"editor" : "text"
				}
    		]
    	},{
    		type:"eType.FlexPaper",
    		attr:[{
    				"name" : "字段显示文本<span style=\"color:Red\">(*)</span>",
    				"ref":"title",
    				"require":true,
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在表单及展示页面中的名称。<br/>填写方法：直接填入名称即可，名称的内容最好为汉子或者英文单词 ",
    				"editor" :"text"
    			},
				{
    				"name" : "字段转义",
    				"ref":"map",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段的转义处理，格式为：'map表|map字段|map值' 备注：用于表之间关联时，map表：关联的表(完整表名)，map字段：关联的字段(Class中的属性)，map值：用于显示的字段(class中的属性),",
    				"editor" :"text"
    			},
    			{
    				"name":"填写说明",
    				"ref":"tooltip",
    				"value":"",
    				"help":"该字段填写说明",
    				"editor":"text",
    			},
    			{
    				"name" : "目标路径",
    				"ref":"addform:addr",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置所要显示的文件的名称,请在附件管理的系统名称中进行复试,然后粘贴到此处,并用单引号将其括起来。例如：'20140410092408903'",
    				"editor" :"text"
    			},
    			{
    				"name" : "展示页面是否隐藏",
    				"ref":"hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在展示页面中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "字段附加样式（新增表单）",
    				"ref":"addform:style",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在新增表单中的宽度和高度，填写方法：例如,background-color: #EFF7FF",
    				"editor" :"text"
    			}
    			,{
    				"name" : "新增页面是否隐藏",
    				"ref":"addform:hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在新增表单中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			},
    			{
    				"name" : "编辑目标路径",
    				"ref":"editform:addr",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置所要显示的文件的名称,请在附件管理的系统名称中进行复试,然后粘贴到此处,并用单引号将其括起来。例如：'20140410092408903'",
    				"editor" :"text"
    			}
    			,{
    				"name" : "编辑页面是否隐藏",
    				"ref":"editform:hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在修改表单中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "字段附加样式（编辑表单）",
    				"ref":"editform:style",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在修改表单中的宽度和高度，填写方法：例如,background-color: #EFF7FF",
    				"editor" :"text"
    			}
    		]
    	},{
    		type:"eType.Radio",
    		attr:[{
    				"name" : "字段显示文本<span style=\"color:Red\">(*)</span>",
    				"ref":"title",
    				"require":true,
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在表单及展示页面中的名称。<br/>填写方法：直接填入名称即可，名称的内容最好为汉子或者英文单词 ",
    				"editor" :"text"
    			},
				{
    				"name" : "字段转义",
    				"ref":"map",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段的转义处理，格式为：'map表|map字段|map值' 备注：用于表之间关联时，map表：关联的表(完整表名)，map字段：关联的字段(Class中的属性)，map值：用于显示的字段(class中的属性),",
    				"editor" :"text"
    			}
    		,
			{
				"name":"填写说明",
				"ref":"tooltip",
				"value":"",
				"help":"该字段填写说明",
				"editor":"text",
			}
    		,{
    				"name" : "展示页面是否隐藏",
    				"ref":"hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在展示页面中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "字段附加样式（新增表单）",
    				"ref":"addform:style",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在新增表单中的宽度和高度，填写方法：例如,width:100px;height:200px",
    				"editor" :"text"
    			}
    			,{
    				"name" : "新增页面是否隐藏",
    				"ref":"addform:hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在新增表单中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
    			,{
    				"name" : "编辑页面是否隐藏",
    				"ref":"editform:hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在修改表单中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "字段附加样式（编辑表单）",
    				"ref":"editform:style",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在修改表单中的宽度和高度，填写方法：例如,width:100px;height:200px",
    				"editor" :"text"
    			}
    			 ]
    	},{
    		type:"eType.MultipleDateBox",
    		attr:[{
    				"name" : "字段显示文本<span style=\"color:Red\">(*)</span>",
    				"ref":"title",
    				"require":true,
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在表单及展示页面中的名称。<br/>填写方法：直接填入名称即可，名称的内容最好为汉字或者英文单词",
    				"editor" :"text"
    			},
				{
    				"name" : "字段转义",
    				"ref":"map",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段的转义处理，格式为：'map表|map字段|map值' 备注：用于表之间关联时，map表：关联的表(完整表名)，map字段：关联的字段(Class中的属性)，map值：用于显示的字段(class中的属性),",
    				"editor" :"text"
    			}
    		,
			{
				"name":"填写说明",
				"ref":"tooltip",
				"value":"",
				"help":"该字段填写说明",
				"editor":"text",
			}
    		,{
    				"name" : "展示页面是否隐藏",
    				"ref":"hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在展示页面中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "字段附加样式（新增表单）",
    				"ref":"addform:style",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段在新增表单中的宽度和高度，填写方法：例如,width:100px;height:200px",
    				"editor" :"text"
    			}
				,{
    				"name" : "新增页面是否隐藏",
    				"ref":"addform:hidden",
    				"value" : "",
    				"group" : "外观",
					"help":"该属性用于设置字段是否在新增表单中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
								,{
    				"name" : "字段是否为必填项(新增表单)",
    				"ref":"editform:dataoptions:required",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中是否应该输入。<br/>如果设置为true，指该字段应该输入。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
				,{
    				"name" : "输入框为空的时候的提示文本（新增表单）",
    				"ref":"addform:dataoptions:missingMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中输入内容为空时的提示信息。<br/>填写方法：'您输入的内容不能为空。'",
    				"editor" : "text"
    			}
				,{
    				"name" : "输入框内容为不合法时的提示文本（新增表单）",
    				"ref":"addform:dataoptions:invalidMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在新增表单中输入内容不合法时的提示信息。<br/>填写方法：'您输入的内容不合法。'",
    				"editor" : "text"
    			}
				,{
    				"name" : "字段是否为必填项(编辑表单)",
    				"ref":"addform:dataoptions:required",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中是否应该输入。<br/>如果设置为true，指该字段应该输入。<br/>填写方法：直接选中单选框即为true",
    				"editor":{
    					"type":"checkbox",
    					"options":{
    						"on":true,
    						"off":false
    					}
    				}
    			}
    			,{
    				"name" : "输入框为空时的提示文本（编辑表单）",
    				"ref":"editform:dataoptions:missingMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中输入内容为空时的提示信息。<br/>填写方法：'您输入的内容不能为空。'",
    				"editor" : "text"
    			}
    			,{
    				"name" : "输入框内容为不合法时的提示文本（编辑表单）",
    				"ref":"editform:dataoptions:invalidMessage",
    				"value" : "",
    				"group" : "数据",
					"help":"该属性用于设置字段在修改表单中输入内容不合法时的提示信息。<br/>填写方法：'您输入的内容不合法。'",
    				"editor" : "text"
    			}
						,{
							"name" : "编辑页面是否隐藏",
							"ref":"editform:hidden",
							"value" : "",
							"group" : "外观",
							"help":"该属性用于设置字段是否在修改表单中显示。<br/>如果设置为true，展示页面中将不会显示该字段。<br/>填写方法：直接选中单选框即为true",
							"editor":{
								"type":"checkbox",
								"options":{
									"on":true,
									"off":false
								}
							}
						}
						,{
							"name" : "字段附加样式（编辑表单）",
							"ref":"editform:style",
							"value" : "",
							"group" : "外观",
							"help":"该属性用于设置字段在修改表单中的宽度和高度，填写方法：例如,width:100px;height:200px",
							"editor" :"text"
						}
    		      ]
    	}
    ];

//获取类型属性
function getTypeAttrByName(typeName){
	if(typeName=='' || typeName=='--请选择--')return null;
	if(typeName==null) return null;
	for(var i=0;i<gc.length;i++){
		if(typeName==gc[i].type){
			return gc[i].attr;
		}
    }
	return null;
}

function getRefValue(typeName,pName){
	var attrs=getTypeAttrByName(typeName);
	if(attrs==null){return null;}
	for(var i=0;i<attrs.length;i++){
		if(attrs[i].name==pName){
			return attrs[i].ref;
		}
	}
	return null;
}

//获取editor的值,通过attr
function getEditorValueByAttr(attr){
	var rows=$('#tt').datagrid('getRows');
	for(var i=0;i<rows.length;i++){
		if(attr.name==rows[i].name){
			editor=$('#tt').datagrid("getEditor",attr.name);
			//so(editor);
		}
	}
}

var fieldid='';
var tableid='';
var p_obj={};
$(function(){
	var linkp = (location.href.split('?')[1]).split('&');
	fieldid = linkp[0].split('=')[1];
	tableid= linkp[1].split('=')[1];
	var url='${ctx}'+actionUrl('/sys/','syDb','Get_TableField')+'?tableId='+tableid+'&fieldId='+fieldid;
	data(url,null,'json',loadData);
});


var g_remarks_obj=null;
//初始化方法-以后实现
function loadData(d){
	if(d){
		if(d.fieldRemarks){
			if((d.fieldRemarks+'').toLowerCase()!=fieldid.toLowerCase()){
				g_remarks_obj=strToObj(d.fieldRemarks);
				ui(g_remarks_obj);
			}
		}else{
			ui(null);
		}
	}
	ui(null);
}

function strToObj(strdata){
	if(strdata.indexOf(':')==-1) return;
	var obj={};
	var strDataAdv=strdata;
	var str = strdata.replace(/\^/g,',');
	str=temp(str);
	strDataAdv=temp(strDataAdv);
	window.parent.window.advConstruction(strDataAdv);
	try{
		obj=eval('({'+str+'})');
	}catch(e){
		alert(e);
	}
	return obj;
}

function temp(str){
	str=str.replace(/dataoptions:\\"/g,'dataoptions:{');
	str=rpForm(str,"addform","}");
	str=rpForm(str,"editform","}");
	str = str.replace(/\\"/g,'}');
	return str;
} 

function rpForm(str,indexstr,endstr){
	var rpStr="";
	var iIndex=str.indexOf(indexstr);
	var iEnd=iIndex+str.substring(iIndex,str.length).indexOf(endstr);
	rpStr=str.substring(iIndex,iEnd);
	var strs=rpStr.split('\\\\\\"');
	rpStr=strs[0];
	for(var i=1;i<strs.length;i++){
		if(i==(strs.length-1) && (strs.length%2==0)){
			rpStr+="}"+strs[i];
		}else{
			rpStr+='\\\\\\"'+strs[i];
		}
	}
	str = str.substring(0,iIndex)+rpStr+str.substring(iEnd,str.length);
	return str;
}

/**ui start
 */
 function ui(obj){
		$('#mm').combo({
	   	    onChange:function(newValue, oldValue){
	    	if(newValue==oldValue) return;
	    	if(newValue=='')return;
	    	/*清空
	    	*/
	    	p_obj={};
	    	$('#pg').html('');
	    	/*设置控件属性
	    	*/
	    	$('#pg').append('<table id="tt"></table>');
	    	$('#tt').propertygrid({
	    		width:$(self).width()*0.9,
	    		scrollbarSize:0,
	    		onClickRow:function(rowIndex, rowData){
	    			$('#helpmsg').panel({
	    				content:rowData.help==undefined?"无帮助信息!":rowData.help
	    			});
	    		},
	    		onAfterEdit:function(rowIndex, rowData, changes){
	    			if(undefined==changes.value)return;
	    			var ref= getRefValue($('#mm').combo('getText'),rowData.name);
	    			if(ref==null) return;
	    			var refs=ref.split(':');
	    			if(refs.length==1){
						p_obj[ref]=changes.value;  
					}else if(refs.length==2){//二级属性
	    				if(!p_obj[refs[0]]){//不存在refs[0]的属性
	    					p_obj[refs[0]]={};	
	    				}
						(p_obj[refs[0]])[refs[1]]=changes.value;
	    				
	    			}else if(refs.length==3){//三级属性
	    				if(!p_obj[refs[0]]){//不存在refs[0]的属性
							p_obj[refs[0]]={};
						}
						if(!(p_obj[refs[0]])[refs[1]]){//不存在refs[1]的属性
							(p_obj[refs[0]])[refs[1]]={};
						}
						((p_obj[refs[0]])[refs[1]])[refs[2]]=changes.value;
						
	    			}else{
						log('ref配置有错，请联系管理员!');
					}
	    			window.parent.window.advConstruction(temp(getRemarks()));
	    		}
	    	});
	    	var attr= getTypeAttrByName(newValue);
	    	if(!attr){
	    		log('该组件目前不在使用，请重新选择!');
	    		return;
	    	}
	    	var obj=g_remarks_obj;
	    	p_obj=obj||{};
	    		for(var i = 0;i<attr.length;i++){
		    			if(obj){
		    			var refs = attr[i].ref.split(':');
		    			var xobj=obj;
		    			var xflat=true;
		    			for(var zz=0;zz<refs.length;zz++){
		    				if(xobj[refs[zz]]!=undefined){
		    					/*
		    					if(refs[zz]=="data"||refs[zz]=="url"){
		    						xobj='\\\\\\"'+xobj[refs[zz]]+'\\\\\\"';
		    					}else{*/
		    						xobj=xobj[refs[zz]];
		    					/*
		    					}*/
		    				} 
		    				else{xflat=false}
		    			}
		    			if(xobj!=obj && xflat){
		    				attr[i].value = xobj;
		    			}
		    			g_remarks_obj=null;
		    		}
	    			$('#tt').propertygrid('appendRow',attr[i]);
	    		}
	    }
	});
	 			if(obj){
	 			if(obj.addform){
	 				if(obj.addform.type){
	 					$('#mm').combobox('setValue',obj.addform.type);
	 				}
	 			}
	 			}
}
/**ui end
 */


//将对象转换为规则文本
function objToTxt(obj,objKey,objRuleTxt){
	var objRuleTxts=objRuleTxt.split('.');
	var txtStr='';
	if(objRuleTxts.length==2){
		var txt=objKey+':'+objRuleTxts[0];
		//处理属性
		for (var i in obj){
			if(i=='dataoptions'){
				txt+=objToTxt(obj[i],i,'\\\\\\".\\\\\\" ');
			}else{
				if(i=="title"||i=="missingMessage"||i=="invalidMessage"||i=="rule"||i=="edit"||i=="mode"||i=="valueField"||i=="validType"||i=="url"||i=="style"){
					txt+=i+":'"+obj[i]+"', ";
				}else{
					txt+=i+":"+obj[i]+", ";
				}
			}
			
		}
		txtStr = txt.substring(0,txt.length-2);
		txtStr+=objRuleTxts[1]+"^";
	}
	return txtStr;
}


function validateField(fieldObj,valueObj){
	if(fieldObj.require){
		if(fieldObj.require){
			var value='';
			var ref=fieldObj.ref;
			var refs=ref.split(':');
			if(refs.length==1){
				value=valueObj[ref];  
			}else if(refs.length==2){//二级属性
				value=(valueObj[refs[0]])[refs[1]];
				
			}else if(refs.length==3){//三级属性
				value=((valueObj[refs[0]])[refs[1]])[refs[2]];
			}
			if(typeof value=="boolean") {
				if(!value) value='false';
			}
			if(value==''||value==null){
				logt('['+fieldObj.name+']不能为空！',1);
				return false;
			}
		}
	}
	return true;
}

function validateType(type,obj){
	var attr=getTypeAttrByName(type);
	for(var i=0;i<attr.length;i++){
		if(!validateField(attr[i],obj)){
			return false;
		}
	}
	return true;
}

function getRemarks(){
     var remarks = '';
     var remarksStr='';
    if(p_obj){
    	if(!p_obj.addform){
    		p_obj.addform={};
    	}
    	if(!p_obj.editform){
    		p_obj.editform={};
    	}
    	var formType=$('#mm').combo('getText');
    	p_obj.addform.type='\''+formType+'\'';
    	p_obj.editform.type='\''+formType+'\'';
    	if(!validateType(formType,p_obj))//验证属性
    	{
    		return remarks;
    	}
		for (var i in p_obj){
			if((i=='addform')||(i=='editform')){
				remarks+=objToTxt(p_obj[i],i,'{.}');
			}
			else{
				if(i=="title"||i=="map"||i=="missingMessage"||i=="invalidMessage"||i=="rule"||i=="edit"||i=="mode"||i=="valueField"||i=="validType"||i=="url"||i=="tooltip"){
					remarks+=i+":'"+p_obj[i]+"'^";
				}else{
					remarks+=i+":"+p_obj[i]+"^";
				}
			}
		}
		
	}
    remarksStr = remarks.substring(0,remarks.length-1); 
     return remarksStr; 
	
}

function arrayToData(a){
	var d=[];
	for (var i in a){
		var o ={
			"id":'eType.'+i,  "text":'eType.'+i
		};
		d.push(o);
	}
	return d;
}

function saveFieldRemarks() {
	var url = '${ctx}'
			+ actionUrl('/sys/', 'syDb', 'Set_TableFieldRemarks');
	var setData = {};
	setData['tableId'] = tableid;
	setData['fieldId'] = fieldid;
	setData['remarks'] =getRemarks();
	if(setData['remarks']=='')return false;
	data(url, setData, 'json', null);
}

</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north'" style="height: 40px;"
		class="datagrid-toolbar">
		<table border="1" class="formtable">
			<tr>
				<td>控件类型</td>
				<td><input id="mm" class="easyui-combobox"
					data-options="valueField:'text',textField:'text',data:arrayToData(eType)" /></td>
				<td><a href="#" class="easyui-linkbutton"
					data-options="iconCls:'icon-save',plain:true"
					onclick="javascript:saveFieldRemarks()">保存字段设计</a></td>
			</tr>
		</table>
	</div>
	<div id="pg" data-options="region:'center'" style="padding: 5px;"></div>
	<div
		data-options="iconCls:'icon-help',region:'south',title:'帮助信息',split:true"
		style="height: 100px; padding: 5px;">
		<div id="helpmsg" class="easyui-panel" style="background: #fafafa;"
			data-options="closable:false,   
                collapsible:false,minimizable:false,maximizable:false,fit:true">
		</div>
	</div>
</body>
</html>
