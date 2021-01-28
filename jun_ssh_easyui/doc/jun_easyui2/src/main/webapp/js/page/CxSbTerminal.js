//获取id
function gId(field,ero){return '#'+cxSbTerminalId+'_'+ero+'_form_'+field;}
//页面启动时
function cxSbTerminalonload(){
	
}

//打开新建页面时
function cxSbTerminalAddOnOpen(){
	console.info('xxxx')
	获取数据对象('vcxSbTakeStateMonitor','v_cx_sb_take_state_monitor',function(a,b,c){console.info(a)});
}

//打开编辑页面时
function cxSbTerminalEditOnOpen(){
	
}

//点击新建按钮时
function AddBtnClick(){
	return true;
}
//点击编辑按钮时
function EditBtnClick(){
	return true;
}