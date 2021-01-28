var NODE_MAP = new Map_();

function createNode(jsonNode, scene) {
	var node = new JTopo.Node(jsonNode.nodeName);
	node.dragable=false;
    node.c_Id=jsonNode.c_Id;
    node.nodeId=jsonNode.nodeId;
    node.setSize(jsonNode.fontSizeX, jsonNode.fontSizeY);
    node.setLocation(jsonNode.x, jsonNode.y);
    node.setImage(jsonNode.nodeImage, false);
    node.fontColor = '20,10,20';
    if(jsonNode.cNodeId!=undefined && jsonNode.cNodeId!="" && jsonNode.cNodeId!="undefined"){
    	node.cNodeId=jsonNode.cNodeId;
    }
    if (jsonNode.dId != undefined && jsonNode.dId != "" && jsonNode.dId != "undefined") {
    	node.dId=jsonNode.dId;
    }
    if (jsonNode.linkPage != undefined && jsonNode.linkPage != "" && jsonNode.linkPage != "undefined") {
    	node.linkPage=jsonNode.linkPage;
    }
    if (jsonNode.alarmText != undefined && jsonNode.alarmText != "" && jsonNode.alarmText != "undefined") {
        node.alarm = jsonNode.alarmText;
    }
    if (jsonNode.scaleX != undefined && jsonNode.scaleX != "" && jsonNode.scaleX != "undefined") {
        node.scaleX = jsonNode.scaleX;
    }
    if (jsonNode.scaleY != undefined && jsonNode.scaleY != "" && jsonNode.scaleY != "undefined") {
        node.scaleY = jsonNode.scaleY;
    }
    if (jsonNode.nodeField != undefined && jsonNode.nodeField != "" && jsonNode.nodeField != "undefined") {
    	node.nodeField=jsonNode.nodeField;
    }  
    
    if (jsonNode.fields != undefined && jsonNode.fields != "" && jsonNode.fields != "undefined") {
    	node.fields=jsonNode.fields;
    }
    if(jsonNode.dataId!=undefined&&jsonNode.dataId!=""&&jsonNode.dataId!="undefined"){
    	node.dataId=jsonNode.dataId;
    }
    if (jsonNode.elementEvent != undefined && jsonNode.elementEvent != "" && jsonNode.elementEvent != "undefined") {
    	eval(jsonNode.elementEvent);
    }
    if (jsonNode.stausImages != undefined && jsonNode.stausImages != "" && jsonNode.stausImages != "undefined") {
    	node.stausImages=jsonNode.stausImages;
    }
    node.dataElementEvent=jsonNode.dataElementEvent;
    node.elementEvent=jsonNode.elementEvent;
    scene.add(node);
    
    initARightButton(node);
    NODE_MAP.put(jsonNode.nodeId, node);
}

function createLine(jsonNode, scene) {
	var fromNode = NODE_MAP.get(jsonNode.fromNodeId);
    var toNode = NODE_MAP.get(jsonNode.toNodeId);
    var lTypeStr=jsonNode.lineType;
    var link=null;
    if(lTypeStr=="FoldLink"){
    	link = new JTopo.FoldLink(fromNode, toNode);
    }else if(lTypeStr=="FlexionalLink"){
    	link = new JTopo.FlexionalLink(fromNode, toNode);
    }else if(lTypeStr=="CurveLink"){
    	link = new JTopo.CurveLink(fromNode, toNode);
    }else{
    	link = new JTopo.Link(fromNode, toNode);
    }
    link.shadow = false;
    
    if (jsonNode.strokeColor && jsonNode.strokeColor != "") {
        link.strokeColor = jsonNode.strokeColor;
    }
    if (jsonNode.dashedPattern && jsonNode.dashedPattern != "") {
    	link.dashedPattern = jsonNode.dashedPattern;
    	link.bundleGap = 20; // 线条之间的间隔
    }
    if (jsonNode.lineWidth && jsonNode.lineWidth != "") {
    	link.lineWidth = jsonNode.lineWidth;
    }else{
    	link.lineWidth = 1.5;
    }
    link.lineType=jsonNode.lineType;
    initARightButton(link);
    scene.add(link);
}

function createTextNode(jsonNode, scene) {
    var node = new JTopo.TextNode(jsonNode.nodeName);
    node.setLocation(jsonNode.x, jsonNode.y);
    node.fontColor = jsonNode.fontColor;
    node._id = Math.uuid();
    node.dragable=false;
    node.nodeType=jsonNode.nodeType;
    node.cNodeId=jsonNode.cNodeId;
    node.nodeField=jsonNode.nodeField;
    node.bundlingNode=jsonNode.bundlingNode;
    initARightButton(node);
    scene.add(node);
    NODE_MAP.put(jsonNode.nodeId, node);
}

function renderToPo(jsons) {
    scene.clear();
    for (var i = 0; i < jsons.length; i++) {
        var jsonNode = jsons[i];
        if (jsonNode.nodeType == "node") {
            createNode(jsonNode, scene);
        } else if (jsonNode.nodeType == "link") {
            createLine(jsonNode, scene);
        } else if (jsonNode.nodeType == 'TextNode') {
            createTextNode(jsonNode, scene);
        }
    }
}
