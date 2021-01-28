
var portal;
var portalType={url:-1,html:0,datagrid:1};
function getPanelById(id,portalData) {
    for (var i = 0; i < portalData.panels.length; i++) {
        if (portalData.panels[i].id == id) {
        	var p=portalData.panels[i];
        	p.oldtitle=p.title;
        	p.title='<span id="'+p.id+'_title">'+p.title+'</span>';
        	p.content='<div class="smartportal" >'+p.content+'</div>';
            return p;
        }
    }
    return undefined;
}

function initPortalHtml(portalData){
	var html='';
	 var cols = portalData.sort.split(":");
	 var colsWidth=portalData.width.split(":");
	    for (var j= 0; j < cols.length; j++) {
	    	var id=cols[j].split(",")[0];
	    	 for (var i = 0; i < portalData.panels.length; i++) {
	    	        if (portalData.panels[i].id == id ) {
	    	        	html+='<div style="width:'+colsWidth[j]+'%;" ></div>';
	    	        }
	    	 }
	    }
	 return $('#portal').html(html);
}

function startInitPortal(portalData){
	for(var i=0;i<portalData.panels.length;i++){
		var p=portalData.panels[i];
		if(p.type==portalType.datagrid){
			var id=p.title+"_"+p.id;
			portalData.panels[i].content='<table id="'+id+'"></table>';
			var html='<div id="'+id+'_toolbar" style="padding: 2px 0">'
			+'<table cellpadding="0" cellspacing="0" style="width: 100%">'
				+'<tr><td style="padding-left: 2px" id="'+id+'_toolbtn"></td>'
					+'<td style="text-align: right; padding-right: 2px"><input'
						+'class="easyui-searchbox" data-options="prompt:\'请输入搜索关键词\'"'
						+'style="width: 200px" searcher="dSearch" id="'+id+'_searchbox"'
						+'pdt="'+id+'"></input>'
						+'<div id="'+id+'_dSComb" style="width: 120px"></div></td>'
				+'</tr></table></div>';
			$('#g_toobar').html(html+$('#g_toobar').html());
		
		}else if(p.type==portalType.url){
			var url=p.uiurl;
			if(url.indexOf('/')==0){
				url=sysPath+url;
			}
			portalData.panels[i].content='<iframe scrolling="no" frameborder="0"  src="'+url+'" style="width:100%;height:100%;" ></iframe>';
			
		}
	}
}

function endInitPortal(portalData){
	for(var i=0;i<portalData.panels.length;i++){
		var p=portalData.panels[i];
		if(p.type==portalType.datagrid){
			initDatagrid(portalData,i);
		}
		else if(p.type==portalType.url){
			initIframe(portalData,i);
		}
	}
}

function initIframe(portalData,i){
	var dtobj=portalData.panels[i];
	var selftdtobj={};
	selftdtobj.id=dtobj.oldtitle+"_"+dtobj.id;
	delete portalData.panels[i].tablewebiframe;
	portalData.panels[i].tablewebiframe=$.window({  
    	url:sysPath+dtobj.uiurl,
      	isIframe:true,
		width:0,
		height:0,
        winId:dtobj.uiurl, 
        target:'self',
        closed:true,
        modal: false,
    onComplete:function(){
    	var obj =portalData.panels[i].tablewebiframe.find('iframe')[0].contentWindow;
    	portalData.panels[i].obj=obj;
    	portalData.panels[i].objWin=portalData.panels[i].tablewebiframe.find('iframe')[0].window;
      }
	}); 
}

//渲染panel
function renderPanel(portalData) {
    var cols = portalData.sort.split(":");
    for (var i = 0; i < cols.length; i++) {
        var rows = cols[i].split(",");
        if(rows==undefined)return;
        for (var j = 0; j < rows.length; j++) {
            var op = getPanelById(rows[j],portalData);
            if(op==undefined)continue;
            var p = $('<div/>').attr('id', op.id).appendTo('body');
            p.panel(op);
            portal.portal('add', {
                panel: p,
                columnIndex: i
            });
        }
    }
}

function initPortal(portalData) {
	initPortalHtml(portalData);
	startInitPortal(portalData);
      portal = $('#portal').portal({
          border: false,
          fit: true
      });
    renderPanel(portalData);
	portal.portal('resize'); 
	//初始化
	endInitPortal(portalData);
};


function setTitle(dt,id,newTitle){
	var obj=$('#'+id+'_title',portal);
	obj.html(newTitle);
}

function initDatagrid(portalData,i){
	var dtobj=portalData.panels[i];
	var selftdtobj={};
	selftdtobj.id=dtobj.oldtitle+"_"+dtobj.id;
	delete portalData.panels[i].tablewebiframe;
	portalData.panels[i].tablewebiframe=$.window({  
    	url:sysPath+dtobj.uiurl,
      	isIframe:true,
		width:0,
		height:0,
        winId:dtobj.uiurl, 
        target:'self',
        closed:true,
        modal: false,
    onComplete:function(){
    	var obj =portalData.panels[i].tablewebiframe.find('iframe')[0].contentWindow;
   		try{
   			if('hiddencols' in dtobj){
   				selftdtobj.columns=initcolumns_(dtobj.hiddencols,obj['columns']);
   			}else{
   				selftdtobj.columns=obj['columns'];
   			}
   			selftdtobj.url=dtobj.dataurl;
   			selftdtobj.dId='id';
   			portalData.panels[i].obj=gGrid2(selftdtobj);
   		}catch(e){
   			return log(e);
   		}
      }
	}); 
}

function initcolumns_(fields,columns){
	var fieldsarray=fields.split(',');
	for(var i=0; i<columns[0].length; i++){
		if(columns[0][i]){
			for(var j=0;j<fieldsarray.length;j++){
				var field=fieldsarray[j];
				if(columns[0][i].field==field){
					columns[0][i].hidden=true;
				}
			}
		}
	}
	return columns;
}

function listNotice(d) {
	setTitle(syNoticeDt,'p2','通知公告' + '<span style="color:red">[' + d.total + ']<span>');
}

function reshIWork() {
	 for (var i = 0; i < portalData.panels.length; i++) {
		 var p=portalData.panels[i];
			if(p.type==portalType.datagrid){
				p.obj.datagrid('reload');
			}
			else if(p.type==portalType.url){
				if(p.obj.window.refresh)
				p.obj.window.refresh();
			}
	 }
}