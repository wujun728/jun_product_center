// 一个菜单可以包括的所有属性 
// {
// 	id: '12345',		// 菜单id, 必须唯一
// 	name: '用户中心',		// 菜单名称, 同时也是tab选项卡上显示的名称
// 	icon: 'el-icon-user',	// 菜单图标, 参考地址:  https://element.eleme.cn/#/zh-CN/component/icon
//	info: '管理所有用户',	// 菜单介绍, 在菜单预览和分配权限时会有显示 
// 	url: 'sa-view/user/user-list.html',	// 菜单指向地址
// 	parentId: 1,			// 所属父菜单id, 如果指定了一个值, sa-admin在初始化时会将此菜单转移到指定菜单上 
// 	isShow: true,			// 是否显示, 默认true
// 	isBlank: false,		// 是否属于外部链接, 如果为true, 则点击菜单时从新窗口打开 
// 	childList: [			// 指定这个菜单所有的子菜单, 子菜单可以继续指定子菜单, 至多支持四级菜单
// 		// .... 
// 	],
//	click: function(){}		// 点击菜单执行一个函数 
// }

// 定义菜单列表 
var menuList =	[
	{
		id: "2",
		name: '各种示例',
		icon: 'el-icon-document-remove',
		info: '增删改查各种常用组件示例',
		childList: [
			{id: "21", name: '查询参数示例', url: 'sa-view/case/query-p-case.html'},
			{id: "22", name: '表格显示示例', url: 'sa-view/case/query-table-case.html'},
			{id: "23", name: '表单提交示例', url: 'sa-view/case/submit-form.html'},
			{id: "24", name: '在线表单构建', url: 'https://mrhj.gitee.io/form-generator/#/'},
		]
	} 
]