<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
//获取id
function gId(field,ero){return '#'+${classNameLower}Id+'_'+ero+'_form_'+field;}
//页面启动时
function ${classNameLower}onload(){
	
}

//打开新建页面时
function ${classNameLower}AddOnOpen(){
	
}

//打开编辑页面时
function ${classNameLower}EditOnOpen(){
	
}

//点击新建按钮时
function AddBtnClick(){
	return true;
}
//点击编辑按钮时
function EditBtnClick(){
	return true;
}